package sibguti.efremov.addressbook.tests;

import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    app.getNavigationHelper().goToContactModificationPage();
    app.getContactHelper().fillContactForm(new ContactData("Test", "Test",
            "Test", "Test", "Test", "Test", "Test", "Test",
            "1111", "222", "333", "test@test.com", "test@test.com",
            "test@test.com", "test.com", "test",
            "444", "test", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
  }

}
