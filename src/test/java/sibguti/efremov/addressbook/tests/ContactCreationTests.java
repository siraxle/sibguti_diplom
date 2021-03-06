package sibguti.efremov.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;
import sibguti.efremov.addressbook.model.Contacts;
import sibguti.efremov.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromCsv() throws IOException {
    File photo = new File(String.format("src/test/resources/ava_00%s.png",
            1 + (int) (Math.random() * 9)));
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader
            (new File("src\\test\\resources\\contacts.csv")))){
      String line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[]{new ContactData().withAddress(split[0])
                .withAddress2(split[1])
                .withCompany(split[2]).withEmail(split[3])
                .withEmail2(split[4]).withEmail3(split[5])
                .withFax(split[6]).withFirstname(split[7])
                .withHomePhone(split[9])
                .witHomePage(split[9]).withLastname(split[8])
                .withMobilePhone(split[10]).withNotes(split[11])
                .withPhone2(split[12]).withPhoto(photo)});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    File photo = new File(String.format("src/test/resources/ava_00%s.png",
            1 + (int) (Math.random() * 9)));
    try (BufferedReader reader = new BufferedReader(new FileReader
            (new File("src\\test\\resources\\contacts.json")));){
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((c) ->
              new Object[] {c.withPhoto(photo)}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    File photo = new File(String.format("src/test/resources/ava_00%s.png",
            1 + (int) (Math.random() * 9)));
    try (BufferedReader reader = new BufferedReader(new FileReader
            (new File("src\\test\\resources\\contacts.xml")));){
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((c) ->
              new Object[] {c.withPhoto(photo)}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromXml")
  public void testContactCreation(ContactData contact) {
    Groups groups = app.db().groups();
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.goTo().contactCreationPage();
    app.contact().create(contact.inGroup(groups.iterator().next()));
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsoluteFile());
    File photo = new File("src/test/resources/ava_001.jpg");
    System.out.println(photo.getAbsoluteFile());
    System.out.println(photo.exists());
  }

}