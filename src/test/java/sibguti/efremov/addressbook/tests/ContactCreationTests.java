package sibguti.efremov.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    app.goTo().contactCreationPage();
    ContactData contact = new ContactData().setAddress("test2").setAddress2("test").
            setCompany("test2").setEmail("test2").setEmail2("test2").setEmail3("test2").
            setFax("111").setFirstname("test").setGroup("test1").setHome("test2").
            setHomepage("test2").setLastname("test2").setMiddlename("test2").
            setMobile("222").setNickname("test2").setNotes("test2").setPhone2("333");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).
            get().getId());
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
