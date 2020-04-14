package sibguti.efremov.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;
import sibguti.efremov.addressbook.model.Contacts;
import sibguti.efremov.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  File photo = new File(String.format("src/test/resources/ava_00%s.png",
          1 + (int) (Math.random() * 9)));
  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().contactCreationPage();
      app.contact().create((new ContactData().withAddress("test").withAddress2("test").
              withCompany("test").withEmail("test").withEmail2("test").withEmail3("test").
              withFax("111").withFirstname("test").
              withHomePhone("111").witHomePage("test").withLastname("test").
              withMiddlename("test").withMobilePhone("222").withNickname("test").
              withNotes("test").withPhone2("333")).inGroup(groups.iterator().next()).
              withPhoto(photo));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
