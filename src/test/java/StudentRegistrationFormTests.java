import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {
    private final String url = "https://demoqa.com/automation-practice-form";
    private final String firstName = "Firstname";
    private final String secondName = "Lastname";
    private final String email = "email@test.com";

    @BeforeEach
    public void setup() {
        open(url);
    }

    @Test
    public void positive() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(secondName);
        $("#userEmail").setValue(email);
        $x("//input[@name='gender' and @value='Male']").click(0, 0);
        $("#userNumber").setValue("1231231234");
        setDate("11", "May", "2010");
        $("#hobbies-checkbox-1").click(0, 0);
        $("#hobbies-checkbox-3").click(0, 0);
        $("#uploadPicture").uploadFile(new File("src/test/resources/Toolsqa.jpg"));
        $("#currentAddress").setValue("Country\nCity\n123 Street");
        $("#submit").scrollTo().click();

        Assertions.assertTrue($x("//div[@class='modal-content']").isDisplayed());
    }

    @Test
    public void negitive() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(secondName);
        $("#userEmail").setValue(email);
        $("#submit").scrollTo().click();

        Assertions.assertFalse($x("//div[@class='modal-content']").isDisplayed());
    }

    @AfterEach
    public void teardown() {
        clearBrowserCookies();
    }

    private void setDate(String dd, String month, String yyyy) {
        $("#dateOfBirthInput").click();
        $x("//div[@class='react-datepicker__month-container']").waitUntil(Condition.visible, 10000L);
        if ($x("//div[@class='react-datepicker__month-container']").isDisplayed()) {
            $x("//select[@class='react-datepicker__year-select']").selectOption(yyyy);
            $x("//select[@class='react-datepicker__month-select']").selectOption(month);
            $x("//div[contains(@aria-label, '" + month + " " + dd + "')]").click();
        }
    }
}
