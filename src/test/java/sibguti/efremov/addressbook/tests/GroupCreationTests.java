package sibguti.efremov.addressbook.tests;

import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void groupCreationTest() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData(
            "test1", "test2", "test3"));
  }

}
