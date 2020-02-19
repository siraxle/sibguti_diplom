package sibguti.efremov.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToContactCreationPage();
      app.getContactHelper().createContact(new ContactData("Test", "Test",
              "Test", "Test", "Test", "Test", "Test", "Test",
              "1111", "222", "333", "test@test.com", "test@test.com",
              "test@test.com", "test.com", "test",
              "444", "test", "test1"));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().modifyContact(before, index);
    List<ContactData> after = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToHomePage();
    Assert.assertEquals(after.size(), index);
    before.remove(index);
    //before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
