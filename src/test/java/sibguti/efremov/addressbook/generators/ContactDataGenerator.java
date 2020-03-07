package sibguti.efremov.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
              contact.getAddress(), contact.getAddress2(),
              contact.getCompany(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
              contact.getFaxPhone(), contact.getFirstname(), contact.getLastname(), contact.getGroup(),
              contact.getHomePhone(), contact.getHomepage(), contact.getMobilePhone(), contact.getNotes(),
              contact.getPhone2(), contact.getPhoto()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    File photo = new File("src/test/resources/photo_001.jpg");
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withAddress(String.format("address %s", i))
              .withAddress2(String.format("address %s", i))
              .withCompany(String.format("company %s", i)).withEmail(String.format("email %s", i))
              .withEmail2(String.format("email %s", i + 1)).withEmail3(String.format("email %s", i + 2))
              .withFax(String.format("fax %s", i)).withFirstname(String.format("firstname %s", i))
              .withGroup(String.format("group %s", i)).withHomePhone(String.format("111%s", i))
              .witHomePage(String.format("homepage %s", i)).withLastname(String.format("lastname %s", i))
              .withMobilePhone(String.format("222%s", i)).withNotes(String.format("notes %s", i))
              .withPhone2(String.format("333%s", i)).withPhoto(photo));
    }
    return contacts;
  }
}
