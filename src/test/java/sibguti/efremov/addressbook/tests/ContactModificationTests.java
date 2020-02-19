package sibguti.efremov.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().contactCreationPage();
      app.contact().create(new ContactData().setAddress("test").setAddress2("test").
              setCompany("test").setEmail("test").setEmail2("test").setEmail3("test").
              setFax("111").setFirstname("test").setGroup("test1").setHome("test").
              setHomepage("test").setLastname("test").setMiddlename("test").
              setMobile("222").setNickname("test").setNotes("test").setPhone2("333"));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().modifyContact(before, index);
    List<ContactData> after = app.contact().list();
    app.goTo().homePage();
    Assert.assertEquals(after.size(), index);
    before.remove(index);
    //before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
