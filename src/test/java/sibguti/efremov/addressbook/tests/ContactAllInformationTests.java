package sibguti.efremov.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAllInformationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().contactCreationPage();
      app.contact().create(new ContactData().withAddress("test").withEmail("test").
              withEmail2("test").withEmail3("test").
              withFirstname("Firstname").withGroup("test1").withHomePhone("111").
              withLastname("test").withMobilePhone("222").withPhone2("333").
              withLastname("Lastname"));
    }
  }

  //  @Test
  public void testAllInformation() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    String contactInfoFromDetailsPage = app.contact().getContactInfoFromDetailsPage(contact);
    assertThat(mergeAllContactInformationFromHomePage(contact),
            equalTo(contactInfoFromDetailsPage));
  }

  private static String mergeAllContactInformationFromHomePage(ContactData contact) {
    String[] phones = contact.getAllPhones().split("\\n");
    return (contact.getFirstname() + contact.getLastname() + contact.getAddress() +
            phones[0] + phones[1] + contact.getAllEmails() +
            phones[2]).replaceAll("\\s", "");
  }

}
