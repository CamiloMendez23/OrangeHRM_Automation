# Proyecto Final Automatización QA - OrangeHRM

Este repositorio contiene la resolución del caso de negocio final para la automatización del flujo de Recursos Humanos (PIM) en el sistema OrangeHRM.

El proyecto automatiza el inicio de sesión, la creación de nuevos empleados con credenciales de usuario, y la validación de su existencia en la grilla de búsqueda.

## Tecnologías y Frameworks
* **Lenguaje:** Java 21
* **Automatización Web:** Selenium WebDriver (v4.24.0)
* **Framework de Pruebas:** TestNG (v7.9.0)
* **Manejo de Datos Externos:** OpenCSV (v5.9)
* **Gestor de Dependencias:** Maven

## Arquitectura y Patrón de Diseño
El código está diseñado estrictamente bajo el patrón **Page Object Model (POM)** para separar la lógica de negocio, los selectores web y las aserciones, garantizando su escalabilidad:

* `src/test/java/base/`:
    * `BasePage.java`: Centraliza las esperas explícitas (`WebDriverWait`) y el uso avanzado de `JavascriptExecutor` para interacciones estables con el DOM.
    * `BaseTest.java`: Inicializa y destruye el WebDriver dinámicamente según el navegador especificado.
* `src/test/java/pages/`:
    * `LoginPage.java` y `PIMPage.java`: Contienen exclusivamente los locators y las acciones de la página. No contienen aserciones.
* `src/test/java/tests/`:
    * `EmployeeTest.java`: Contiene las pruebas y aserciones. Consume datos a través de un `@DataProvider`.

## Estrategia de Datos (Data-Driven Testing)
* **Archivo Externo:** Los datos base de los empleados se leen desde el archivo `datos_empleados.csv` ubicado en la raíz del proyecto.
* **Unicidad en Ejecución:** Para garantizar que el nombre de usuario y el ID sean únicos en cada corrida y evitar colisiones en la base de datos de OrangeHRM, el script de prueba genera y concatena dinámicamente un timestamp (milisegundos) a los datos leídos del CSV durante la ejecución.

## Instrucciones de Ejecución

Para ejecutar la suite de pruebas no es necesario lanzar las clases de forma individual. El proyecto cuenta con un archivo de configuración de TestNG preparado para evaluar el ciclo completo.

### Requisitos Previos
1. Tener instalado Java JDK (11 o superior).
2. Tener Google Chrome y Mozilla Firefox instalados en el sistema operativo.

### Pasos para ejecutar
1. Clonar este repositorio localmente.
2. Abrir el proyecto en un IDE (IntelliJ IDEA, Eclipse, VS Code) como proyecto **Maven**.
3. Recargar el archivo `pom.xml` para descargar las dependencias de Selenium, TestNG y OpenCSV.
4. En la raíz del proyecto, ubicar el archivo **`testng.xml`**.
5. Hacer clic derecho sobre `testng.xml` y seleccionar **Run '.../testng.xml'**.

### Comportamiento Esperado
Al ejecutar la suite, el sistema realizará una **ejecución secuencial**:
1. Levantará Google Chrome y registrará a los dos empleados detallados en el archivo CSV, validando su aparición en la tabla.
2. Posteriormente, cerrará Chrome, levantará Mozilla Firefox y repetirá el mismo proceso para asegurar la compatibilidad multi-navegador.