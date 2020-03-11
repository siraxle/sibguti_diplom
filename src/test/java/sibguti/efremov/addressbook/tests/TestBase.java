package sibguti.efremov.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import sibguti.efremov.addressbook.appmanager.ApplicationManager;

import java.io.IOException;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class TestBase {

  public static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", CHROME));

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
