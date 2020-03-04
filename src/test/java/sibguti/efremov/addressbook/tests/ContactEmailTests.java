package sibguti.efremov.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().contactCreationPage();
      app.contact().create(new ContactData().withAddress("test").withAddress2("test").
              withCompany("test").withEmail("test").withEmail2("test").withEmail3("test").
              withFax("111").withFirstname("test").withGroup("test1").withHomePhone("111").
              witHomePage("test").withLastname("test").withMiddlename("test").
              withMobilePhone("222").withNickname("test").withNotes("test").withPhone2("333"));
    }
  }

  @Test()
  public void testContactEmails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditFor = app.contact().infoFromEditForm(contact);
    assertThat(contact.getHomePhone(), equalTo(mergeEmails(contactInfoFromEditFor)));;
  }

  private String mergeEmails(ContactData contact) {
    Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter(s -> ! s.equals(""))
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));

    return null;
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]",
            "");
  }


}
