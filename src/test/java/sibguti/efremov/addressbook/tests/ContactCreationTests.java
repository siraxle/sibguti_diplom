package sibguti.efremov.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;
import sibguti.efremov.addressbook.model.Contacts;
import sibguti.efremov.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    File photo = new File("src/test/resources/photo_001.jpg");
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withAddress("address1").withAddress2("address2").
            withCompany("test2").withEmail("test1").withEmail2("test2").withEmail3("test3").
            withFax("111").withFirstname("Firstname").withGroup("test1").withHomePhone("111").
            witHomePage("test2").withLastname("Lastname").withMobilePhone("222").withNotes("test2").
            withPhone2("333").withPhoto(photo)});
    list.add(new Object[] {new ContactData().withAddress("address2").withAddress2("address2").
            withCompany("test3").withEmail("test3").withEmail2("test3").withEmail3("test3").
            withFax("111").withFirstname("Firstname").withGroup("test1").withHomePhone("111").
            witHomePage("test2").withLastname("Lastname").withMobilePhone("222").withNotes("test2").
            withPhone2("333").withPhoto(photo)});
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
    File photo = new File("src/test/resources/photo_001.jpg");
    System.out.println(photo.getAbsoluteFile());
    System.out.println(photo.exists());
  }

}