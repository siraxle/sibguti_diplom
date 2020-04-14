package sibguti.efremov.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sibguti.efremov.addressbook.model.ContactData;
import sibguti.efremov.addressbook.model.Groups;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

  File photo = new File(String.format("src/test/resources/ava_00%s.png",
          1 + (int) (Math.random() * 9)));
  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().contactCreationPage();
      app.contact().create((new ContactData().withAddress("test").withAddress2("test").
              withCompany("test").withEmail("test").withEmail2("test").withEmail3("test").
              withFax("111").withFirstname("test").
              withHomePhone("111").witHomePage("test").withLastname("test").
              withMiddlename("test").withMobilePhone("222").withNickname("test").
              withNotes("test").withPhone2("333")).inGroup(groups.iterator().next()).
              withPhoto(photo));
    }
  }

  @Test()
  public void testContactEmails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getHomePhone(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter(s -> !s.equals(""))
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));

    return null;
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]",
            "");
  }


}
