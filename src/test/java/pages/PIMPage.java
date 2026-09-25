package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage extends BasePage {
    // Locators de Menú
    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addEmployeeTab = By.xpath("//a[text()='Add Employee']");
    private By employeeListTab = By.xpath("//a[text()='Employee List']");

    // Locators de Formulario
    private By firstNameInput = By.name("firstName");
    private By lastNameInput = By.name("lastName");
    private By saveButton = By.xpath("//button[@type='submit']");

    // Locators de Búsqueda (Actualizado con un XPath indestructible)
    private By empNameSearch = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By searchResultRecord = By.xpath("//div[@class='oxd-table-card']//div[3]");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToPIM() {
        click(pimMenu);
    }

    public void addEmployee(String firstName, String lastName) throws InterruptedException {
        click(addEmployeeTab);
        typeText(firstNameInput, firstName);
        typeText(lastNameInput, lastName);
        click(saveButton);
        Thread.sleep(5000); // Pausa ligeramente mayor para que la base de datos procese el registro
    }

    public void searchEmployee(String name) throws InterruptedException {
        click(employeeListTab);
        Thread.sleep(3000); // Pausa clave: Espera a que la pantalla de carga (spinner) de OrangeHRM desaparezca
        typeText(empNameSearch, name);
        Thread.sleep(2000); // Espera a que reaccione el autocompletado
        click(searchButton);
        Thread.sleep(3000); // Espera a que los resultados de la tabla se filtren
    }

    public String getFirstSearchResultName() {
        return getText(searchResultRecord);
    }
}