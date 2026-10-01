# Proyecto Final — Automatización de Caso de Negocio (OrangeHRM)

Este repositorio contiene la automatización End-to-End (E2E) del caso de negocio sobre el módulo **PIM** de la plataforma [OrangeHRM Demo](https://opensource-demo.orangehrmlive.com/), desarrollada con **Java, Selenium WebDriver y TestNG** aplicando estrictamente el patrón de diseño **Page Object Model (POM)**.

---

## Estructura y Mapa del Proyecto (¿Dónde está cada archivo?)

Para facilitar la revisión del proyecto, a continuación se detalla la ubicación exacta de las pruebas, las páginas (Page Objects), los datos externos y el reporte de ejecución:

    OrangeHRM_Automation/
    ├── src/test/java/
    │   ├── base/
    │   │   ├── BasePage.java          # Métodos base de Selenium y esperas explícitas (WebDriverWait 30s)
    │   │   └── BaseTest.java          # Inicialización y cierre del WebDriver multi-navegador (@Parameters)
    │   ├── pages/
    │   │   ├── LoginPage.java         # Locators y acciones de la pantalla de Login
    │   │   └── PIMPage.java           # Locators y acciones del módulo PIM (Alta y Búsqueda)
    │   └── tests/
    │       └── EmployeeTest.java      # PRUEBA PRINCIPAL, @DataProvider y Aserciones
    ├── test-output/                   # REPORTE DE EJECUCIÓN DE PRUEBAS (Generado por TestNG)
    │   ├── emailable-report.html      # Reporte resumido en HTML listo para abrir en navegador
    │   └── index.html                 # Reporte detallado de la suite ejecutada
    ├── datos_empleados.csv            # Archivo externo con los datos de prueba de los 2 empleados
    ├── testng.xml                     # Suite de ejecución multi-navegador (Chrome y Firefox)
    └── pom.xml                        # Configuración de Maven y dependencias del proyecto

---

## Reporte de Ejecución de las Pruebas

El reporte oficial generado por TestNG tras la ejecución completa de la suite se encuentra incluido dentro del repositorio en la carpeta **`test-output/`**:

* **Reporte ejecutivo (HTML):** `test-output/emailable-report.html`
* **Reporte completo de la suite:** `test-output/index.html`
* **Resultados XML:** `test-output/testng-results.xml`

### Resumen del Resultado Obtenido:
* **Total de pruebas ejecutadas:** 4 (`Passes: 4, Failures: 0, Skips: 0`)
  1. **Google Chrome** — Empleado 1 (`Rodrigo Camilo Mendez`) → **PASSED**
  2. **Google Chrome** — Empleado 2 (`Maria Renee Gallardo`) → **PASSED**
  3. **Mozilla Firefox** — Empleado 1 (`Rodrigo Camilo Mendez`) → **PASSED**
  4. **Mozilla Firefox** — Empleado 2 (`Maria Renee Gallardo`) → **PASSED**

*(Para visualizar el reporte gráfico localmente, basta con clonar o descargar este repositorio y abrir el archivo `test-output/emailable-report.html` en cualquier navegador web).*

---

##️ ¿Cómo funciona el proyecto?

### 1. Flujo del Caso de Negocio (`src/test/java/tests/EmployeeTest.java`)
La clase de prueba `EmployeeTest` está limpia de selectores (`By`) y de llamadas directas al `WebDriver`, leyéndose directamente como la especificación del caso de negocio:
1. **Iniciar sesión:** Autenticación en la plataforma con las credenciales de administrador mediante `LoginPage`.
2. **Navegar al módulo PIM:** Acceso desde el menú lateral izquierdo mediante `PIMPage`.
3. **Crear empleado nuevo:** En el formulario *Add Employee*, completa los datos personales (**Nombre, Segundo Nombre, Apellido e ID de empleado**), activa el switch **Create Login Details** y registra los datos de usuario (**Username, Password, Confirm Password y Status Enabled**).
4. **Buscar empleado:** Navega a la pestaña *Employee List*, filtra por el nombre único del empleado recién creado y ejecuta la búsqueda.
5. **Verificar en la grilla:** Obtiene el nombre del primer registro de la tabla de resultados y valida su coincidencia mediante la aserción `Assert.assertTrue(...)`.

### 2. Cumplimiento del Patrón Page Object Model (POM)
* **Locators separados de las acciones:** En `LoginPage.java` y `PIMPage.java`, todos los selectores `By` están encapsulados como atributos privados al inicio de cada clase, separados de los métodos públicos que ejecutan las interacciones.
* **Aserciones exclusivas en la prueba:** Ninguna clase de página (`pages`) contiene aserciones de TestNG. Las páginas únicamente interactúan con la interfaz o retornan textos (`getFirstSearchResultName()`), delegando la validación final a `EmployeeTest.java`.
* **Sincronización avanzada:** Se utilizan esperas explícitas (`WebDriverWait`) junto con la verificación de cambio de ruta (`ExpectedConditions.urlContains("viewPersonalDetails")`) para asegurar que el backend de OrangeHRM haya guardado el registro antes de pasar a la búsqueda.

### 3. Datos de Prueba y Unicidad en cada corrida (`@DataProvider` + CSV)
* **Carga desde archivo externo:** Los datos base no están quemados en el código; se leen desde el archivo `datos_empleados.csv` utilizando la librería `OpenCSV` a través del método `leerDatosCSV()` anotado con `@DataProvider(name = "datosEmpleados")`.
* **Resolución de unicidad dinámica:** Para garantizar que la suite pueda ejecutarse múltiples veces sin fallar por duplicidad de usuario o ID en OrangeHRM:
  * **Del archivo CSV provienen:** Nombre base, Segundo nombre, Apellido y Contraseña.
  * **En tiempo de ejecución se genera:** Un sufijo único de 4 dígitos extraído de los milisegundos del sistema (`System.currentTimeMillis()`). Este identificador único se asigna al campo **Employee Id** y se concatena al **Nombre** y al **Username** en cada corrida.

---

## Instrucciones para Ejecutar las Pruebas

### Requisitos Previos
* **Java JDK:** Versión 11 o superior.
* **Maven:** Integrado en el IDE o instalado en el sistema.
* **Navegadores:** Tener instalados **Google Chrome** y **Mozilla Firefox** (Selenium Manager descarga y configura automáticamente `chromedriver` y `geckodriver`).

### Pasos de Ejecución desde el IDE (IntelliJ IDEA / Eclipse)
1. Clonar o descargar este repositorio en la computadora.
2. Abrir la carpeta del proyecto en **IntelliJ IDEA** como proyecto **Maven** y permitir que descargue las dependencias declaradas en el archivo `pom.xml`.
3. En la raíz del proyecto, localizar el archivo de suite **`testng.xml`**.
4. Hacer **clic derecho** sobre el archivo **`testng.xml`** y seleccionar **`Run '.../testng.xml'`**.

La suite ejecutará automáticamente el flujo completo para los **dos empleados** del archivo CSV, primero en **Google Chrome** y a continuación en **Mozilla Firefox**.

---