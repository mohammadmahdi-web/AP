# Bank Management System 🏦

A comprehensive console-based banking simulation developed in **Java** for the **Advanced Programming** course.
This project utilizes **Object Serialization** for data persistence and features a role-based architecture (Admin, Cashier, Customer) with a secure transaction approval workflow.

## 🚀 Project Overview

**Bank System** simulates core banking operations where data integrity and object-oriented design are prioritized. Unlike simple banking apps, this system implements a **Transaction Verification Logic**, requiring Cashier approval for fund transfers between customers.

## 📌 Key Features

### 1. 👤 Role-Based Access Control
* **Admin:**
    * Manage staff (Add/View Cashiers).
    * Manage customers (Add/View/Sort Customers).
    * View total bank liquidity (Total Money in all accounts).
    * *Hardcoded Access:* Username: `Admin`, Password: `Admin`.
* **Cashier (Employee):**
    * Secure Login using Employee ID and Password.
    * **Transaction Review:** Approve or Reject money transfer requests from customers.
    * View Customer details and Accounts.
* **Customer:**
    * Register and Login.
    * Create multiple Bank Accounts (Savings/Current).
    * **Request Money Transfer:** Initiate a transfer to another account (pending approval).

### 2. 💾 Custom Data Persistence
* Uses **Java Object Serialization** to store data in `.ser` files (`Customers.ser`, `Accounts.ser`, etc.).
* **Advanced File I/O:** Implemented a custom `TempObjectOutputStream` to allow appending objects to existing files without corrupting the stream header.

### 3. 💸 Transaction Engine
* Transfers are **not atomic** immediately; they follow a state pattern:
    * `Pending` (Created by Customer)
    * `Confirmed` (Approved by Cashier -> Balances updated)
    * `Rejected` (Denied by Cashier)

### 4. 📊 Algorithmic Implementation
* **Bubble Sort:** Implemented specifically to sort customers based on their total account balance.
* **Validation:** Regex validation for National Codes (must be exactly 10 digits).

## 🛠 Technologies & Concepts
* **Language:** Java SE
* **OOP Principles:** Inheritance, Abstraction, Polymorphism, Encapsulation.
* **I/O Operations:** `ObjectOutputStream`, `ObjectInputStream`, `Serializable` interface, File Handling.

## 🎓 Academic Information
* **University:** University of Hormozgan
* **Course:** Advanced Programming (Java)
* **Instructor:** Dr. Khalili
