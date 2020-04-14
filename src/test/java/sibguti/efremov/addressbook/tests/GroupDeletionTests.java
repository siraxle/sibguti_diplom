package sibguti.efremov.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.GroupData;
import sibguti.efremov.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("test1"));
      }
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    app.goTo().groupPage();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    app.goTo().groupPage();
    Groups after = app.db().groups();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUi();
  }

}
