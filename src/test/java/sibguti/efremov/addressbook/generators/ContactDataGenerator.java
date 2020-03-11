package sibguti.efremov.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import sibguti.efremov.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();

  }

  void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format");
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                contact.getAddress(), contact.getAddress2(),
                contact.getCompany(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                contact.getFaxPhone(), contact.getFirstname(), contact.getLastname(), contact.getGroup(),
                contact.getHomePhone(), contact.getHomepage(), contact.getMobilePhone(), contact.getNotes(),
                contact.getPhone2(), contact.getPhoto()));
      }
    }
  }

  private void saveAsJson(List<ContactData> contact, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contact);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    String xml = xStream.toXML(contacts);
    try (Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withAddress(String.format("address %s", i))
              .withAddress2(String.format("address %s", i))
              .withCompany(String.format("company %s", i)).withEmail(String.format("email %s", i))
              .withEmail2(String.format("email %s", i + 1)).withEmail3(String.format("email %s", i + 2))
              .withFax(String.format("fax %s", i)).withFirstname(String.format("firstname %s", i))
              .withGroup(String.format("group %s", i)).withHomePhone(String.format("111%s", i))
              .witHomePage(String.format("homepage %s", i)).withLastname(String.format("lastname %s", i))
              .withMobilePhone(String.format("222%s", i)).withNotes(String.format("notes %s", i))
              .withPhone2(String.format("333%s", i)));
    }
    return contacts;
  }
}
