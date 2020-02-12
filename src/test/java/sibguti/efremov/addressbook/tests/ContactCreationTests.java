package sibguti.efremov.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToContactCreationPage();
    app.getContactHelper().createContact(new ContactData("Test", "Test",
            "Test", "Test", "Test", "Test", "Test", "Test",
            "1111", "222", "333", "test@test.com", "test@test.com",
            "test@test.com", "test.com", "test",
            "444", "test", "test1"));
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
