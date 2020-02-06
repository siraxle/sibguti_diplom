package sibguti.efremov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void goToGroupPage() {
    if (isElementPresent(By.tagName("h1")) &&
            wd.findElement(By.tagName("h1")).equals("Groups") &&
            isElementPresent(By.name("new"))) {
      return;
    } else {
      click(By.linkText("groups"));
    }
  }

  public void goToContactPage() {
    if (isElementPresent(By.tagName("h1")) &&
            wd.findElement(By.tagName("h1")).equals("Edit / add address book entry") &&
            isElementPresent(By.name("new_group"))) {
      return;
    } else {
      click(By.linkText("add new"));
    }
  }

  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    } else {
      click(By.linkText("home"));
    }

  }

  public void goToContactModificationPage() {
    if (isElementPresent(By.tagName("h1")) &&
            wd.findElement(By.tagName("h1")).equals("Edit / add address book entry") &&
            isElementPresent(By.name("new_group"))) {
      return;
    } else {
      click(By.xpath("//img[@title='Edit']/.."));
    }
  }
}
