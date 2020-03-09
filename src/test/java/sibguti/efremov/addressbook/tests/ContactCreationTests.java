package sibguti.efremov.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;
import sibguti.efremov.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    File photo = new File(String.format("src/test/resources/ava_00%s.png",
            1 + (int) (Math.random() * 9)));
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader
            (new File("src\\test\\resources\\contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withAddress(split[0])
              .withAddress2(split[1])
              .withCompany(split[2]).withEmail(split[3])
              .withEmail2(split[4]).withEmail3(split[5])
              .withFax(split[6]).withFirstname(split[7])
              .withGroup(split[9]).withHomePhone(split[10])
              .witHomePage(split[11]).withLastname(split[8])
              .withMobilePhone(split[12]).withNotes(split[13])
              .withPhone2(split[14]).withPhoto(photo)});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().contactCreationPage();
    app.contact().create(contact);
    Contacts after = app.contact().all();
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