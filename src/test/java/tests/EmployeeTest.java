package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.PIMPage;

public class EmployeeTest extends BaseTest {

    @Test
    public void testAddAndSearchEmployee() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        PIMPage pimPage = new PIMPage(driver);

        // 1. Iniciar sesión
        loginPage.login("Admin", "admin123");

        // 2. Navegar a PIM y registrar tu usuario
        pimPage.navigateToPIM();
        pimPage.addEmployee("Rodrigo Camilo", "Mendez");

        // 3. Buscar al empleado recién agregado
        pimPage.searchEmployee("Rodrigo Camilo");
        String resultName = pimPage.getFirstSearchResultName();

        // 4. Aserción: Verificar que la creación fue exitosa
        Assertions.assertTrue(resultName.contains("Rodrigo Camilo"),
                "Error: El empleado no apareció en la lista de resultados.");
    }
}