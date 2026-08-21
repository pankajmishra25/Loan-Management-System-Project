# Loan Management System

## Overview

The **Loan Management System** is a web-based application developed to simplify and manage the complete loan management process. The system provides separate functionalities for **Administrators** and **Customers**, allowing loan applications, approvals, repayments, customer management, and other loan-related activities to be handled efficiently.

## Features

### Customer Module

- Customer registration and login
- Customer dashboard
- View available loan types
- Apply for loans
- EMI calculation
- View loan application status
- View active loans
- Make loan repayments
- View payment history
- Generate payment receipts
- Change password

### Admin Module

- Admin login
- Admin dashboard
- View customer details
- Manage loan types
- View all loan applications
- Approve or reject loan applications
- View pending and approved loans
- Monitor repayments
- View defaulter information
- Generate customer loan reports
- Change admin password

## Technologies Used

- **Java**
- **JSP (Java Server Pages)**
- **Servlets**
- **JDBC**
- **HTML5**
- **CSS3**
- **JavaScript**
- **MySQL / MariaDB**
- **Apache Tomcat**
- **Maven**
- **Eclipse/IntelliJ IDEA**

## Project Architecture

The application follows the **MVC (Model-View-Controller)** architecture.

```text
Loan Management System
│
├── Model
│   └── Java Model Classes
│
├── View
│   └── JSP Pages
│
├── Controller
│   └── Servlets
│
└── DAO
    └── Database Access Classes