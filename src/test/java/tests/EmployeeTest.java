package tests;

import base.BaseTest;
import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeTest extends BaseTest {

    @DataProvider(name = "datosEmpleados")
    public Iterator<Object[]> leerDatosCSV() throws Exception {
        List<Object[]> datos = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("datos_empleados.csv"));
        String[] linea;
        while ((linea = reader.readNext()) != null) {
            datos.add(new Object[]{linea[0], linea[1], linea[2], linea[3]});
        }
        return datos.iterator();
    }

    @Test(dataProvider = "datosEmpleados")
    public void testAddAndSearchEmployee(String nombre, String segundoNombre, String apellido, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        PIMPage pimPage = new PIMPage(driver);

        // Generar unicidad combinando el nombre del archivo con la hora actual en milisegundos
        String idUnico = String.valueOf(System.currentTimeMillis()).substring(7); // Extrae los últimos 6 dígitos
        String nombreDinamico = nombre + idUnico;
        String usuarioDinamico = nombre.toLowerCase() + apellido.toLowerCase() + idUnico;

        loginPage.login("Admin", "admin123");
        pimPage.navigateToPIM();

        // Agregar empleado con todos los campos requeridos
        pimPage.addEmployee(nombreDinamico, segundoNombre, apellido, idUnico, usuarioDinamico, password);

        // Buscar por el nombre único generado
        pimPage.searchEmployee(nombreDinamico);
        String resultName = pimPage.getFirstSearchResultName();

        // Aserción en TestNG
        Assert.assertTrue(resultName.contains(nombreDinamico), "El empleado no apareció en la grilla.");
    }
}