package sibguti.efremov.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;
import sibguti.efremov.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().contactCreationPage();
      app.contact().create(new ContactData().withAddress("test").withAddress2("test").
              withCompany("test").withEmail("test").withEmail2("test").withEmail3("test").
              withFax("111").withFirstname("test").withGroup("test1").withHomePhone("test").
              witHomePage("test").withLastname("test").withMiddlename("test").
              withMobilePhone("222").withNickname("test").withNotes("test").withPhone2("333"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withAddress("test").withAddress2("test").
            withCompany("test").withEmail("test").withEmail2("test").withEmail3("test").
            withFax("111").withFirstname("test").withHomePhone("test").
            witHomePage("test").withLastname("test").withMiddlename("test").
            withMobilePhone("222").withNickname("test").withNotes("test").withPhone2("333");
    app.contact().modify(modifiedContact, contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(modifiedContact).without(contact)));
  }

}
