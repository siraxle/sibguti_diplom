package sibguti.efremov.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToHomePage();
    app.getHomePageHelper().checkContact();
    app.getHomePageHelper().deleteContact();
    app.getNavigationHelper().goToHomePage();
  }
}
