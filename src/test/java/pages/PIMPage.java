package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PIMPage extends BasePage {
    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addEmployeeTab = By.xpath("//a[text()='Add Employee']");
    private By employeeListTab = By.xpath("//a[text()='Employee List']");


    private By firstNameInput = By.name("firstName");
    private By middleNameInput = By.name("middleName");
    private By lastNameInput = By.name("lastName");
    private By employeeIdInput = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input");


    private By createLoginDetailsSwitch = By.xpath("//input[@type='checkbox']/parent::label/span");
    private By usernameInput = By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input");
    private By passwordInput = By.xpath("(//input[@type='password'])[1]");
    private By confirmPasswordInput = By.xpath("(//input[@type='password'])[2]");

    private By saveButton = By.xpath("//button[@type='submit']");


    private By empNameSearch = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By searchResultRecord = By.xpath("//div[@class='oxd-table-card']//div[3]");

    public PIMPage(WebDriver driver) { super(driver); }

    public void navigateToPIM() throws InterruptedException {
        click(pimMenu);
        Thread.sleep(2000);
    }

    public void addEmployee(String first, String middle, String last, String empId, String username, String pass) throws InterruptedException {
        click(addEmployeeTab);
        Thread.sleep(2000);

        typeText(firstNameInput, first);
        typeText(middleNameInput, middle);
        typeText(lastNameInput, last);


        WebElement empIdElement = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdInput));
        empIdElement.click();
        for (int i = 0; i < 10; i++) {
            empIdElement.sendKeys(Keys.BACK_SPACE);
        }
        empIdElement.sendKeys(empId);


        WebElement switchBtn = wait.until(ExpectedConditions.presenceOfElementLocated(createLoginDetailsSwitch));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", switchBtn);
        Thread.sleep(1000);

        typeText(usernameInput, username);
        typeText(passwordInput, pass);
        typeText(confirmPasswordInput, pass);


        Thread.sleep(2000);


        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);


        wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
        Thread.sleep(2000);
    }

    public void searchEmployee(String name) throws InterruptedException {
        click(employeeListTab);
        Thread.sleep(4000); // Esperar a que cargue la tabla general inicial

        typeText(empNameSearch, name);
        Thread.sleep(2000);

        click(searchButton);
        Thread.sleep(4000); // Esperar a que la tabla muestre el resultado filtrado
    }

    public String getFirstSearchResultName() {
        return getText(searchResultRecord);
    }
}