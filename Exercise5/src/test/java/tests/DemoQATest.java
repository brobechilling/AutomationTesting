package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.DemoQAPage;

import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Practice Form Tests using Page Object Model")
public class DemoQATest extends BaseTest {
    static WebDriverWait wait;
    static DemoQAPage formPage;

    @BeforeAll
    static void initPage() {
        formPage = new DemoQAPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Order(1)
    @DisplayName("Should submit form successfully with valid data")
    void testFormSubmitSuccess() {
        formPage.navigate();
        formPage.enterName("John", "Doe");
        formPage.enterEmail("john.doe@example.com");
        formPage.selectGender("Male");
        formPage.enterMobile("1234567890");
        formPage.enterDOB("21 Jul 1995");
        formPage.enterSubject("Maths");
        formPage.selectHobby("Sports");
        formPage.uploadFile(Paths.get("src/test/resources/dog.png").toAbsolutePath().toString());
        formPage.enterAddress("123 Main Street");
        formPage.selectState("NCR");
        formPage.selectCity("Delhi");
        formPage.submitForm();

        WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("example-modal-sizes-title-lg")));
        assertTrue(modalTitle.getText().contains("Thanks for submitting the form"));
    }

    @ParameterizedTest(name = "CSV Inline: {0} {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}")
    @Order(2)
    @CsvFileSource(resources = "/valid-form-data.csv", numLinesToSkip = 1)
    @DisplayName("Test valid data from csv file")
    void testValidForm(String firstName, String lastName, String email, String gender, String number, String date, String subject,
                                   String hobby, String imagePath, String address, String state, String city) {
        formPage.navigate();
        formPage.enterName(firstName == null ? "" : firstName, lastName == null ? "" : lastName);
        formPage.enterEmail(email == null ? "" : email);
        formPage.selectGender(gender == null ? "" : gender);
        formPage.enterMobile(number == null ? "" : number);
        formPage.enterDOB(date == null ? "" : date);
        if (subject != null && !subject.isBlank())
            formPage.enterSubject(subject);
        if (hobby != null && !hobby.isBlank())
            formPage.selectHobby(hobby);
        if (imagePath != null && !imagePath.isBlank())
            formPage.uploadFile(Paths.get("src/test/resources/dog.png").toAbsolutePath().toString());
        formPage.enterAddress(address == null ? "" : address);
        if (state != null && !state.isBlank()) {
            formPage.selectState(state);
            if (city != null && !city.isBlank())
                formPage.selectCity(city);
        }
        formPage.submitForm();

        WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));
        assertTrue(modalTitle.getText().contains("Thanks for submitting the form"));
    }

    @ParameterizedTest(name = "CSV Inline: {0} {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}")
    @Order(3)
    @CsvFileSource(resources = "/invalid-form-data.csv", numLinesToSkip = 1)
    @DisplayName("Test invalid data from csv file")
    void testInvalidForm(String firstName, String lastName, String email, String gender, String number, String date, String subject,
                                   String hobby, String imagePath, String address, String state, String city) {
        formPage.navigate();
        formPage.enterName(firstName == null ? "" : firstName, lastName == null ? "" : lastName);
        formPage.enterEmail(email == null ? "" : email);
        formPage.selectGender(gender == null ? "" : gender);
        formPage.enterMobile(number == null ? "" : number);
        formPage.enterDOB(date == null ? "" : date);
        formPage.enterSubject(subject == null ? "" : subject);
        formPage.selectHobby(hobby == null ? "" : hobby);
        if (imagePath != null && !imagePath.isBlank())
            formPage.uploadFile(Paths.get("src/test/resources/dog.png").toAbsolutePath().toString());
        formPage.enterAddress(address == null ? "" : address);
        formPage.selectState(state == null ? "" : state);
        formPage.selectCity(city == null ? "" : city);
        formPage.submitForm();

        boolean isModalDisplayed = !driver.findElements(By.id("example-modal-sizes-title-lg")).isEmpty();
        Assertions.assertFalse(isModalDisplayed, "Form should NOT be submitted with invalid data.");
    }


    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
