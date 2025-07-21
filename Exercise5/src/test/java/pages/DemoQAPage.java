package pages;

import org.openqa.selenium.*;

public class DemoQAPage extends BasePage {

    public DemoQAPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By genderMale = By.id("gender-radio-1");
    private By genderFemale = By.id("gender-radio-2");
    private By genderOther = By.id("gender-radio-3");
    private By mobileNumber = By.id("userNumber");
    private By dobInput = By.id("dateOfBirthInput");
    private By subjectsInput = By.id("subjectsInput");
    private By hobbiesSports = By.id("hobbies-checkbox-1");
    private By hobbiesReading = By.id("hobbies-checkbox-2");
    private By hobbiesMusic = By.id("hobbies-checkbox-3");
    private By uploadPicture = By.id("uploadPicture");
    private By currentAddress = By.id("currentAddress");
    private By stateInput = By.id("react-select-3-input");
    private By cityInput = By.id("react-select-4-input");
    private By submitBtn = By.id("submit");

    // Actions
    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");

        // Scroll to firstName to avoid any overlay issues
        WebElement firstNameInput = waitForVisibility(firstName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstNameInput);
    }


    public void enterName(String first, String last) {
        type(firstName, first);
        type(lastName, last);
    }

    public void enterEmail(String emailText) {
        type(email, emailText);
    }

    public void selectGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male": click(By.xpath("//label[@for='gender-radio-1']")); break;
            case "female": click(By.xpath("//label[@for='gender-radio-2']")); break;
            case "other": click(By.xpath("//label[@for='gender-radio-3']")); break;
        }
    }

    public void enterMobile(String number) {
        type(mobileNumber, number);
    }

    public void enterDOB(String date) {
        WebElement dob = waitForVisibility(dobInput);
        dob.sendKeys(Keys.CONTROL + "a");
        dob.sendKeys(date);
        dob.sendKeys(Keys.ENTER);
    }

    public void enterSubject(String subject) {
        WebElement subjectInputField = waitForVisibility(subjectsInput);
        subjectInputField.sendKeys(subject);
        subjectInputField.sendKeys(Keys.ENTER);
    }

    public void selectHobby(String hobby) {
        switch (hobby.toLowerCase()) {
            case "sports": click(By.xpath("//label[@for='hobbies-checkbox-1']")); break;
            case "reading": click(By.xpath("//label[@for='hobbies-checkbox-2']")); break;
            case "music": click(By.xpath("//label[@for='hobbies-checkbox-3']")); break;
        }
    }

    public void uploadFile(String absolutePath) {
        WebElement upload = waitForVisibility(uploadPicture);
        upload.sendKeys(absolutePath);
    }

    public void enterAddress(String address) {
        type(currentAddress, address);
    }

    public void selectState(String state) {
        WebElement stateInputField = waitForVisibility(stateInput);
        stateInputField.sendKeys(state);
        stateInputField.sendKeys(Keys.ENTER);
    }

    public void selectCity(String city) {
        WebElement cityInputField = waitForVisibility(cityInput);
        cityInputField.sendKeys(city);
        cityInputField.sendKeys(Keys.ENTER);
    }

    public void submitForm() {
        WebElement btn = waitForVisibility(submitBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }
}
