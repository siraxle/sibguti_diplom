package sibguti.efremov.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private HomePageHelper homePageHelper;
  private DbHelper dbHelper;
  private String browser;

  public ApplicationManager(String browser){
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {

    String target = System.getProperty("target", "local");
    properties.load(new FileReader(
            new File(String.format("src\\test\\resources\\%s.properties", target))));

    dbHelper = new DbHelper();

    if (browser.equals(BrowserType.FIREFOX)) {
      System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IEXPLORE)) {
      System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    homePageHelper = new HomePageHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"),
            properties.getProperty("web.adminPassword") );
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public HomePageHelper getHomePageHelper() {
    return homePageHelper;
  }

  public DbHelper db() {
    return dbHelper;
  }
}
