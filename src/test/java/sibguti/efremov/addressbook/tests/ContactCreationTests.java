package sibguti.efremov.addressbook.tests;

import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;
import sibguti.efremov.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().contactCreationPage();
    ContactData contact = new ContactData().withAddress("test2").withAddress2("test").
            withCompany("test2").withEmail("test2").withEmail2("test2").withEmail3("test2").
            withFax("111").withFirstname("test").withGroup("test1").withHome("test2").
            witHomePage("test2").withLastname("test2").withMiddlename("test2").
            withMobile("222").withNickname("test2").withNotes("test2").withPhone2("333");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}