package sibguti.efremov.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import sibguti.efremov.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFaxPhone());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.xpath("//input[@value='Enter'][2]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@title='Edit']/.."));
  }

  public int getContactCount() {
    return wd.findElements(By.name("entry")).size();
  }

  public WebElement selectContact(int index) {
    int i = index + 1;
    click(By.xpath("//tr[@name='entry'][" + i + "]//td/input"));
    return wd.findElements(By.name("entry")).get(index);
  }

  public void initContactModification(WebElement contact) {
    contact.findElement(By.xpath("//img[@title='Edit']/..")).click();
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (int i = 0; i < elements.size(); i++) {
      String lastName = elements.get(i).findElement(
              By.xpath("//tr[@name = 'entry'][" + (i + 1) + "]/td[2]"))
              .getText();
      int id = Integer.parseInt(elements.get(i).findElement(
              By.xpath("//tr[@name = 'entry'][" + (i + 1) + "]/td[1]/input"))
              .getAttribute("id"));
      ContactData contact = new ContactData(id, null, null, lastName,
              null, null, null, null, null, null,
              null, null, null, null, null, null,
              null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
