package sibguti.efremov.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().contactCreationPage();
      app.contact().create(new ContactData("Test", "Test",
              "Test", "Test", "Test", "Test", "Test", "Test",
              "1111", "222", "333", "test@test.com", "test@test.com",
              "test@test.com", "test.com", "test",
              "444", "test", "test1"));
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), index);
    before.remove(index);
    Assert.assertEquals(after, before);
  }



}
