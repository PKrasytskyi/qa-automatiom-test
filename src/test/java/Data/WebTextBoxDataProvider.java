package Data;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class WebTextBoxDataProvider {

    @DataProvider(name = "formData")
    public Object[][] formatData(){
        return new Object[][]{
                {createUserMap ("Ptr", "Kr", "test@gmail.com", "35", "24333", "Audit")},
                {createUserMap ("ЕЕк", "ППА", "test22@gmail.com", "21", "22", "Lock")}
        };
    }

    private Map<String, String> createUserMap(String firstName, String lastName, String email,
                                              String age, String salary, String department) {
        Map<String, String> user = new HashMap<>();
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);
        user.put("age", age);
        user.put("salary", salary);
        user.put("department", department);
        return user;
    }
}
