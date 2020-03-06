package sibguti.efremov.addressbook.tests;

import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;
import sibguti.efremov.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().contactCreationPage();
    File photo = new File("src/test/resources/photo_001.jpg");
    ContactData contact = new ContactData().withAddress("address1").withAddress2("address2").
            withCompany("test2").withEmail("test1").withEmail2("test2").withEmail3("test3").
            withFax("111").withFirstname("Firstname").withGroup("test1").withHomePhone("111").
            witHomePage("test2").withLastname("Lastname").withMobilePhone("222").withNotes("test2").
            withPhone2("333").withPhoto(photo);
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
    File photo = new File("src/test/resources/photo_001.jpg");
    System.out.println(photo.getAbsoluteFile());
    System.out.println(photo.exists());
  }

}