package data.SaucedemoDataProviders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaucedemoLoginPageDataProvider {

    @DataProvider(name="successfulLogin")
    public Object[][] successfulLoginData() throws IOException {
        return readJsonFile("src/test/resources/testData/logPassData.json");
    }

    @DataProvider(name="invalidPassword")
    public Object[][] invalidPasswordData() throws IOException {
        return readJsonFile("src/test/resources/testData/invalidPassword.json");
    }

    private Object[][] readJsonFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(path));
        List<Object[]> data = new ArrayList<>();
        for (JsonNode node : root){
            data.add(new Object[]{node.get("login").asText(), node.get("password").asText()});
        }
        return data.toArray(new Object[0][]);
    }
}
