package sibguti.efremov.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToContactCreationPage();
      app.getContactHelper().createContact(new ContactData("Test", "Test",
              "Test", "Test", "Test", "Test", "Test", "Test",
              "1111", "222", "333", "test@test.com", "test@test.com",
              "test@test.com", "test.com", "test",
              "444", "test", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    WebElement selectedContact = app.getContactHelper().selectContact(before.size() - 1);
    System.out.println(selectedContact);
    String id = selectedContact.findElement(By.xpath(".//input[@type='checkbox']")).
            getAttribute("id");
    app.getContactHelper().initContactModification(selectedContact);
    ContactData contact = new ContactData(
            before.get(before.size() - 1).getId(), "Test", "Test",
            "Test", "Test", "Test", "Test", "Test", "Test",
            "1111", "222", "333", "test@test.com", "test@test.com",
            "test@test.com", "test.com", "test",
            "444", "test", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    //before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
