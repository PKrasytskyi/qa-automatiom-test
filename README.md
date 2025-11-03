# 🧪 UI Test Automation Framework (Java + Selenium + TestNG + Allure)

This project demonstrates a **UI test automation framework** built with **Java, Selenium WebDriver, TestNG, and Allure Reporting**.  
It is designed to showcase clean code structure, reusability, and CI/CD integration using **GitHub Actions**.

---

## 🚀 Features

- **Java + Selenium WebDriver**
- **TestNG** test runner with annotations and grouping
- **Page Object Model (POM)** structure
- **Data-driven tests** using TestNG DataProviders
- **Allure Report** integration
- **Automatic screenshots on failure**
- **GitHub Actions CI pipeline**

---

## 🧱 Project Structure

src
└── test
├── java
│ ├── data # Test data providers
│ ├── pages # Page Object classes
│ ├── setup # BaseTest setup and driver management
│ ├── tests # Test classes
│ └── utils # Helper utilities and listeners
└── resources
└── testng.xml # TestNG suite configuration


---

## ⚙️ How to Run Tests

### Prerequisites
- JDK 17+
- Maven 3.9+
- Google Chrome browser

### Run from command line
```bash
mvn clean test -DsuiteXmlFile=testng.xml

Generate Allure Report

mvn allure:serve

CI/CD Integration

This project is integrated with GitHub Actions (.github/workflows/maven.yml)
to automatically build and execute tests on every commit to the master branch.

📸 Reports & Artifacts

Allure report with screenshots attached for failed tests

Downloadable from GitHub Actions artifacts

👨‍💻 Author

Petro Krasytskyi
QA Automation Engineer
🔗 LinkedIn | GitHub



