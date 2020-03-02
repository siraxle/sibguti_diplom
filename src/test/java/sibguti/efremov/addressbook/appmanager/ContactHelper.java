package sibguti.efremov.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import sibguti.efremov.addressbook.model.ContactData;
import sibguti.efremov.addressbook.model.Contacts;

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

  public void create(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
    contactsHash = null;
    returnToHomePage();
  }

  public void modify(ContactData modifiedContact, ContactData contact) {
    WebElement selectedContact = selectContactById(modifiedContact.getId());
    String id = selectedContact.findElement(By.xpath("//input[@value=" + modifiedContact.getId() + "]")).
            getAttribute("id");
    initContactModification(selectedContact, id);
    fillContactForm(contact, false);
    submitContactModification();
    contactsHash = null;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName).
            withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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
    WebElement el = wd.findElement(By.xpath("//tr[@name='entry'][" + i + "]"));
    String id = wd.findElement(By.xpath("//tr[@name = 'entry'][" + i + "]/td[1]/input")).getAttribute("id");
    return el;
  }

  private WebElement selectContactById(int id) {
    click(By.xpath("//input[@value=" + id + "]"));
    WebElement el = wd.findElement(By.xpath("//input[@value=" + id + "]"));
    String ident = wd.findElement(By.xpath("//input[@value=" + id + "]")).getAttribute("id");
    return el;
  }

  public void initContactModification(WebElement contact, String id) {
    wd.findElement(By.xpath("//input[@value=" + id + "]/../..//img[@title='Edit']/..")).click();
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    contactsHash = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    contactsHash = null;
  }

  Contacts contactsHash = null;

  public Contacts all() {
    contactsHash = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (int i = 0; i < elements.size(); i++) {
      String firstName = elements.get(i).findElement(
              By.xpath("//tr[@name = 'entry'][" + (i + 1) + "]/td[3]"))
              .getText();
      String lastName = elements.get(i).findElement(
              By.xpath("//tr[@name = 'entry'][" + (i + 1) + "]/td[2]"))
              .getText();
      int id = Integer.parseInt(elements.get(i).findElement(
              By.xpath("//tr[@name = 'entry'][" + (i + 1) + "]/td[1]/input"))
              .getAttribute("id"));
      String allPhones = elements.get(i).findElement(
              By.xpath("//tr[@name = 'entry'][" + (i + 1) + "]/td[6]"))
              .getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstName).
              withLastname(lastName).withAllPhones(allPhones);
      contactsHash.add(contact);
    }
    return new Contacts(contactsHash);
  }
}
