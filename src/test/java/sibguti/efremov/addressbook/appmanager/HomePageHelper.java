package sibguti.efremov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomePageHelper extends HelperBase{
  public HomePageHelper(WebDriver wd) {
    super(wd);
  }

  public void checkContact() {
    click(By.xpath("//td[@class='center'][1]/input"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }
}
