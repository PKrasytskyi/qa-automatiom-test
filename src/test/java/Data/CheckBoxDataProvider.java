package Data;

import org.testng.annotations.DataProvider;

public class CheckBoxDataProvider {

    @DataProvider(name= "checkBox")
    public Object[][] checkBox(){

        return new Object[][]{

                { new String[]{"Notes", "React", "Public", "Word File.doc"} }
        };
    }
}
