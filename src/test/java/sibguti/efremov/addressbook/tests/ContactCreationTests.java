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
    ContactData contact = new ContactData("Test", "Test",
            "Test", "Test", "Test", "Test", "Test", "Test",
            "1111", "222", "333", "test@test.com", "test@test.com",
            "test@test.com", "test.com", "test",
            "444", "test", "test1");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).
            get().getId());
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
