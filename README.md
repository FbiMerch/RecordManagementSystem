# Record Management System (Java)

![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)
![Build](https://img.shields.io/badge/Build-IntelliJ%20%2F%20CLI-orange.svg)

A modular, file-based **Record Management System** written in **Java** using Object-Oriented Programming (OOP) principles. The application serves as a CLI-based library management system allowing users to perform complete CRUD (Create, Read, Update, Delete) operations, sort records, and export data to CSV.

---

## 📋 Features

- ➕ **Add Record**: Insert new book entries with automatic duplicate ID validation.
- 📖 **Display All Records**: Formatted tabular view displaying all stored records.
- 🔍 **Search by ID**: Quick lookup for specific records by unique identifier.
- ✏️ **Edit Record**: Interactive in-place editing for existing records.
- 🗑️ **Delete Record**: Safe record removal from persistence storage.
- 🔀 **Sorting**: Sort dataset dynamically by title or publication year.
- 📊 **CSV Export**: Export all records into an Excel-compatible CSV file.
- 🛡️ **Input Validation & Error Handling**: Robust menu system with exception handling for smooth runtime execution.

---

## 🏗️ Architecture & OOP Design

The system follows clean separation of concerns across core classes:

| Class | Responsibility |
| :--- | :--- |
| `Book` | **Model**: Represents a book object (`id`, `title`, `author`, `year`) with getters, setters, and serialization methods. |
| `RecordManager` | **Service**: Manages file storage, data parsing, CRUD operations, sorting, and CSV export. |
| `Main` | **Controller / CLI**: Handles user interactions, menu navigation, and input validation. |

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher (JDK 17 / 21 recommended).
- **IDE**: IntelliJ IDEA (or any Java-compatible IDE / terminal).

---

## ⚙️ Setup & Execution in IntelliJ IDEA

1. **Clone or Download the Repository**
   ```bash
   git clone https://github.com/your-username/RecordManagementSystem.git
   ```

2. **Open Project in IntelliJ IDEA**
   - Open IntelliJ IDEA and click **Open**.
   - Select the `RecordManagementSystem` folder.

3. **Verify Source Root**
   - In the project tool window, right-click the `src` directory.
   - Select **Mark Directory as** $
ightarrow$ **Sources Root** (if not automatically recognized).

4. **Run the Application**
   - Open `Main.java` located inside `src/`.
   - Click the green **Run** arrow next to the `main` method or press `Shift + F10`.

---

## 🖥️ Running via Terminal / Command Line

If you prefer running without an IDE:

1. **Navigate to the Source Folder**
   ```bash
   cd RecordManagementSystem/src
   ```

2. **Compile the Java Files**
   ```bash
   javac Main.java Book.java RecordManager.java
   ```

3. **Execute the Application**
   ```bash
   java Main
   ```

---

## 💾 Data Persistence & Storage

- **Local Storage File**: `books.txt` (created automatically on first run).
- **Delimiter**: Delimited with `|` for internal parsing (`ID|Title|Author|Year`).
- **Export File**: `exported_books.csv` (generated upon selecting the CSV export option).

---
