package sibguti.efremov.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void groupCreationTest() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test1", "test2", "test3");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(group);
    int max = 0;
    for (GroupData g : after){
      if (g.getId() > max){
        max = g.getId();
      }
    }
    group.setId(max);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
