package data.DemoqaDataProviders;

import org.testng.annotations.DataProvider;

public class TextBoxDataProvider {

    @DataProvider(name = "formData")
    public Object[][] formatData(){
        return new Object[][]{
                {"Ptr Kr", "test@gmail.com","Dream City","Sad City"}
        };
    }

    @DataProvider(name = "InvalidData")
    public Object[][] invalitFormatData(){
        return new Object[][]{
                {"Ptr Kr", "asdasd---asd","Dream City","Sad City"}
        };
    }
}
