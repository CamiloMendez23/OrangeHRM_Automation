# Proyecto de Automatización QA - OrangeHRM

Este proyecto automatiza el flujo de inicio de sesión, creación de un empleado y búsqueda del mismo en el sistema de demostración de OrangeHRM.

## Arquitectura y Tecnologías
* **Lenguaje:** Java 21
* **Framework Web:** Selenium WebDriver 4.24.0
* **Framework de Pruebas:** JUnit 5
* **Gestor de Dependencias:** Maven
* **Patrón de Diseño:** Page Object Model (POM)

##  Estructura del Código
El proyecto utiliza POM para asegurar un código mantenible y escalable:
* `base`: Contiene `BasePage` (esperas explícitas) y `BaseTest` (configuración del navegador).
* `pages`: Contiene los selectores (Locators) y métodos de las páginas Login y PIM.
* `tests`: Contiene los scripts de prueba asertivos (`EmployeeTest`).

## ️ Cómo ejecutar este proyecto localmente
1. Clona este repositorio en tu computadora.
2. Abre la carpeta del proyecto en IntelliJ IDEA (o Eclipse/VS Code).
3. Asegúrate de recargar Maven para descargar las dependencias (`pom.xml`).
4. Navega a `src/test/java/tests/EmployeeTest.java`.
5. Ejecuta la clase `EmployeeTest`.