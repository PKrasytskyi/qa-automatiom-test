package tests;

import Pages.WebTablesPage;
import TestSetUp.BaseTestAbstract;
import org.testng.annotations.Test;

public class WebTablesPageTest extends BaseTestAbstract {

    String firstName = "Ptr";
    String lastName = "Kr";
    String email = "test@gmail.com";
    String age = "32";
    String salary = "112233";
    String departament = "audit";

    @Override
    @Test
    public void runTest() throws InterruptedException {
        WebTablesPage webTablesPage = new WebTablesPage(driver);

        webTablesPage
                .open()
                .clickAddButton()
                .setFirstname(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setAge(age)
                .setSalary(salary)
                .setDepartment(departament)
                .clickSubmit()
                .getWebTable();

        System.out.println(webTablesPage.getWebTable().getText());
    }
}
