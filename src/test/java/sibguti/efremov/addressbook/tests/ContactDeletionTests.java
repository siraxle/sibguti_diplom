package sibguti.efremov.addressbook.tests;

import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().goToContactPage();
      app.getContactHelper().createContact(new ContactData("Test", "Test",
              "Test", "Test", "Test", "Test", "Test", "Test",
              "1111", "222", "333", "test@test.com", "test@test.com",
              "test@test.com", "test.com", "test",
              "444", "test", "test1"));
    }
    app.getHomePageHelper().checkContact();
    app.getHomePageHelper().deleteContact();
    app.getNavigationHelper().goToHomePage();
  }
}
