### Java-Book-Library

Simple Library Management System written in Java with a MySQL backend. This repository is an archived student project: it implements basic book inventory, user accounts and loan tracking. The codebase is minimal and organized into three folders: dao, model and ui.

---

### What it is
- Java application that connects to a MySQL database to manage books, users and loans.  
- Core layers:  
  - **model** — domain objects (Book, User, Loan).  
  - **dao** — data access objects handling JDBC/MySQL operations.  
  - **ui** — simple entry point and user interaction (CLI or basic UI).  
- Purpose: learning exercise / small demo, not a production-ready system.

---

### Important note
This repo is provided as an archive of a student project. Some files, assets or configuration (for example SQL migration scripts or certain classes) may be missing. Expect to make small fixes or restore files before the program runs.

---

### Requirements
- Java JDK 11+
- MySQL server accessible with a database for the app

---

### Quick run (minimal steps)
1. Create and configure a MySQL database and user for the app.  
2. Update DB connection settings in your code (look for a config class or JDBC URL in dao code).  
3. Compile the code:
   - javac -d out $(find src -name "*.java")
4. Run the main UI class (replace ui.MainApp with your actual main class):
   - java -cp out ui.MainApp

If you use an IDE (IntelliJ, Eclipse) import the project as a simple Java project, set the DB settings, and run the main class from the IDE.

---

### Tips if something is missing
- If SQL table creation scripts are not present, inspect dao code to reconstruct the schema (typical tables: Books, Users, Loans).  
- If a main class is not obvious, search the ui folder for a class with a public static void main method.  
- Check for hard-coded paths or resources and update them if needed.
