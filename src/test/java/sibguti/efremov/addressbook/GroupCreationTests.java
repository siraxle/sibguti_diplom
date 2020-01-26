package sibguti.efremov.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void groupCreationTest() {
    goToGroupPage();
    initGroupCreation("new");
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
