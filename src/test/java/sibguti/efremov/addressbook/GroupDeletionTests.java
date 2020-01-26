package sibguti.efremov.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() {
    goToGroupPage();
    selectGroup();
    deleteSelectionGroups();
    returnToGroupPage();
  }

}
