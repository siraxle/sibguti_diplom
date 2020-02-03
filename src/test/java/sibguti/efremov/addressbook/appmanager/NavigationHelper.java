package sibguti.efremov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {
  private WebDriver wd;

  public NavigationHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void goToGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void goToContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void goToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }
}
