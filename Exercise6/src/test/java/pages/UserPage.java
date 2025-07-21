package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UserPage extends BasePage {

    private final WebDriverWait wait;

    public UserPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By userEditButtons = By.xpath("//button[.//svg[contains(@class,'lucide-square-pen')]]");
    private final By dialog = By.xpath("//div[@role='dialog']");
    private final By nameInput = By.id("edit-name");
    private final By emailInput = By.id("edit-email");
    private final By roleButton = By.xpath("//label[@for='edit-role']/following-sibling::button");
    private final By statusButton = By.xpath("//label[@for='edit-status']/following-sibling::button");
    private final By dobInput = By.id("edit-dob");
    private final By addressInput = By.id("edit-address");
    private final By idInput = By.id("edit-id");
    private final By saveChangesBtn = By.xpath("//button[.//svg[contains(@class,'lucide-save')]]");

    public void navigate() {
        navigateTo("https://vroomvroom.click/admin");
    }

    public void openEditDialog(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userEditButtons));
        List<WebElement> editButtons = driver.findElements(userEditButtons);
        if (index >= editButtons.size()) {
            throw new IndexOutOfBoundsException("No edit button at index: " + index);
        }
        editButtons.get(index).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dialog));
    }

    public void updateUserInfo(String fullName, String email, String dob, String address, String credentialId) {
        clearAndType(nameInput, fullName);
        clearAndType(emailInput, email);
        clearAndType(dobInput, dob);
        clearAndType(addressInput, address);
        clearAndType(idInput, credentialId);
    }

    public void saveChanges() {
        click(saveChangesBtn);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(dialog));
    }

    // Utilities
    private void clearAndType(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
        field.sendKeys(value);
    }
}
