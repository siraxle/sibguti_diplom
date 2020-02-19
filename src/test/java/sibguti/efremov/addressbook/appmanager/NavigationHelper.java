package sibguti.efremov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1")) &&
            wd.findElement(By.tagName("h1")).equals("Groups") &&
            isElementPresent(By.name("new"))) {
      return;
    } else {
      click(By.linkText("groups"));
    }
  }

  public void contactCreationPage() {
    if (isElementPresent(By.tagName("h1")) &&
            wd.findElement(By.tagName("h1")).equals("Edit / add address book entry") &&
            isElementPresent(By.name("new_group"))) {
      return;
    } else {
      click(By.linkText("add new"));
    }
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    } else {
      click(By.linkText("home"));
    }

  }

}
