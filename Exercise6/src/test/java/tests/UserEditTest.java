package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.UserPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Edit User Tests for Admin Panel")
public class UserEditTest extends BaseTest {

    static LoginPage loginPage;
    static UserPage userPage;

    @BeforeAll
    static void setupPagesAndLogin() {
        loginPage = new LoginPage(driver);
        userPage = new UserPage(driver);

        // Login before accessing the admin page
        loginPage.navigate();
        loginPage.enterEmail("admin@vroomvroom.vn");
        loginPage.enterPassword("Abc@12345");
        loginPage.clickLogin();
    }

    @Test
    @Order(1)
    @DisplayName("Should edit user information successfully")
    void testEditUser() {
        userPage.navigate();
        userPage.openEditDialog(0); // edit first user

        userPage.updateUserInfo(
                "Nguyễn Văn A",
                "nguyenvana@example.com",
                "1990-12-01",
                "123 Đường ABC, Quận 1, HCM",
                "987654321"
        );

        userPage.saveChanges();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
