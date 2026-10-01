# Proyecto Final — Automatización de Caso de Negocio (OrangeHRM)

Este repositorio contiene la automatización E2E del caso de negocio sobre el módulo **PIM** de [OrangeHRM Demo](https://opensource-demo.orangehrmlive.com/), desarrollada con **Java, Selenium WebDriver y TestNG** aplicando estrictamente el patrón de diseño **Page Object Model (POM)**.

---

## Mapa del Proyecto (¿Dónde está cada componente?)

Para facilitar la revisión y evaluación del proyecto, a continuación se detalla la ubicación exacta de las pruebas, las páginas, los datos y los reportes:

```text
OrangeHRM_Automation/
├── src/test/java/
│   ├── base/
│   │   ├── BasePage.java          # Wrappers de Selenium y esperas explícitas (WebDriverWait 30s)
│   │   └── BaseTest.java          # Configuración de WebDriver multi-navegador (@Parameters)
│   ├── pages/
│   │   ├── LoginPage.java         # Locators y acciones de inicio de sesión
│   │   └── PIMPage.java           # Locators y acciones de alta y búsqueda de empleados
│   └── tests/
│       └── EmployeeTest.java      # PRUEBA PRINCIPAL, @DataProvider y Aserciones
├── test-output/                   # REPORTE DE EJECUCIÓN DE PRUEBAS (TestNG)
│   ├── emailable-report.html      # Reporte resumido listo para visualizar en navegador
│   └── index.html                 # Reporte detallado de la suite ejecutada
├── datos_empleados.csv            # Archivo externo con los datos de los 2 empleados
├── testng.xml                     # Archivo de Suite para ejecución en Chrome y Firefox
└── pom.xml                        # Dependencias del proyecto (Selenium, TestNG, OpenCSV)