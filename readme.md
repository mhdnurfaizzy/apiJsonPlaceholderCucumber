# API Automation Project - RestAssured Cucumber

This project is an **API automation testing framework** built using **Java**, **RestAssured**, **Cucumber**, **JUnit**, and **JSON Schema validation**. It demonstrates **Create, Retrieve, and Delete (CRD)** operations on the [JSONPlaceholder](https://jsonplaceholder.typicode.com/) API.

---

## 🛠 Tech Stack

* **Language:** Java 17+
* **Testing Framework:** JUnit
* **BDD Framework:** Cucumber
* **API Automation:** RestAssured
* **JSON Schema Validation:** RestAssured module `jsv`
* **Build Tool:** Maven
* **IDE Recommended:** IntelliJ IDEA

---

## 📋 Features

1. **Create Post**

    * Endpoint: `POST /posts`
    * Validates response fields and JSON schema

2. **Retrieve Posts**

    * Endpoint: `GET /posts`
    * Validates that each post has a non-null `id`
    * Validates response against JSON schema

3. **Update Post**

    * Endpoint: `UPDATE /posts/{id}`
    * Validates HTTP status code (200 OK)
    * Validates response body and JSON schema

4  **Update Post**
     
* Endpoint: `UPDATE /posts/{id}`
    * Validates response body and JSON schema

---

## ⚙️ Installation

1. Clone the repository:

   ```bash
   git clone repo https://github.com/mhdnurfaizzy/apiJsonPlaceholderCucumber.git
   ```
2. Navigate to project folder:

   ```bash
   cd apiJsonPlaceholder
   ```
3. Make sure **Java 17+** and **Maven** are installed.

---

## 🏃‍♂️ Running the Tests

### **Run via IntelliJ**

1. Right-click on `TestRunner.java` → `Run 'TestRunner'`
2. All scenarios (Create, Retrieve, Delete) will run in sequence.

### **Run via Maven**

```
mvn clean test
```

---

## 📝 Test Design

* **Feature Files:** Written in **Gherkin** syntax with scenarios per API operation.
* **Step Definitions:** Java classes under `stepdefinitions` handle requests and assertions.
* **Schema Validation:** Each response is validated using **JSON Schema** located in `resources/schemas`.

---
