package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PIMPage extends BasePage {
    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addEmployeeTab = By.xpath("//a[text()='Add Employee']");
    private By employeeListTab = By.xpath("//a[text()='Employee List']");

    // Locators Formulario extendido
    private By firstNameInput = By.name("firstName");
    private By middleNameInput = By.name("middleName");
    private By lastNameInput = By.name("lastName");
    private By employeeIdInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");

    // Switch y Datos de Login
    private By createLoginDetailsSwitch = By.xpath("//input[@type='checkbox']/parent::label/span");
    private By usernameInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
    private By passwordInput = By.xpath("(//input[@type='password'])[1]");
    private By confirmPasswordInput = By.xpath("(//input[@type='password'])[2]");

    private By saveButton = By.xpath("//button[@type='submit']");

    // Búsqueda
    private By empNameSearch = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By searchResultRecord = By.xpath("//div[@class='oxd-table-card']//div[3]");

    public PIMPage(WebDriver driver) { super(driver); }

    public void navigateToPIM() throws InterruptedException {
        Thread.sleep(3000);
        click(pimMenu);
        Thread.sleep(2000);
    }

    public void addEmployee(String first, String middle, String last, String empId, String username, String pass) throws InterruptedException {
        click(addEmployeeTab);
        Thread.sleep(2000);

        typeText(firstNameInput, first);
        typeText(middleNameInput, middle);
        typeText(lastNameInput, last);
        typeText(employeeIdInput, empId);

        // Clic JS en el switch
        WebElement switchBtn = driver.findElement(createLoginDetailsSwitch);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", switchBtn);

        typeText(usernameInput, username);
        typeText(passwordInput, pass);
        typeText(confirmPasswordInput, pass);

        // Clic JS en GUARDAR para evitar la intercepción del spinner en Firefox
        WebElement saveBtn = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);

        Thread.sleep(7000);
    }

    public void searchEmployee(String name) throws InterruptedException {
        click(pimMenu);
        Thread.sleep(2000);
        click(employeeListTab);
        Thread.sleep(4000);

        WebElement searchBox = driver.findElement(empNameSearch);
        searchBox.clear();
        searchBox.sendKeys(name);
        Thread.sleep(3000); // Pausa necesaria para el autocompletado "Searching..."

        // Clic NORMAL de Selenium (No JS) para asegurar que el sistema lo registre
        click(searchButton);
        Thread.sleep(4000);
    }

    public String getFirstSearchResultName() {
        return getText(searchResultRecord);
    }
}