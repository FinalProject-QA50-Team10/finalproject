package test.cases.selenuim;

import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class RegistrationTests extends BaseTestSetup{

    @Test
    public void test(){
        var t = actions.generateRandomText(MIN_LENGTH_USERNAME,MAX_LENGTH_USERNAME);
    }
}
