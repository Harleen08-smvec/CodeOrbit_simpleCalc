# Simple Calculator (Java)

A robust, console-based arithmetic calculator written in Java. The application accepts interactive user input via `java.util.Scanner`, performs core mathematical calculations, and incorporates rigorous exception handling to gracefully recover from invalid inputs and prevent runtime crashes.

---

## 📌 Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [How to Compile and Run](#how-to-compile-and-run)
- [Sample Usage & Demo](#sample-usage--demo)
- [Exception & Error Handling](#exception--error-handling)
- [Code Architecture](#code-architecture)

---

## ✨ Features

- **Basic Arithmetic Operations**:
  - ➕ **Addition (`+`)**: Adds two real numbers.
  - ➖ **Subtraction (`-`)**: Subtracts the second number from the first.
  - ✖️ **Multiplication (`*`)**: Multiplies two numbers.
  - ➗ **Division (`/`)**: Divides the first number by the second.
  - 🔢 **Modulo (`%`)**: Calculates the remainder of the division.
- **Robust Exception Handling**:
  - Traps non-numeric inputs and prompts the user to re-enter without terminating the program.
  - Traps division by zero and modulo by zero using `ArithmeticException`.
  - Validates operator selection to ensure only supported arithmetic symbols are accepted.
- **Interactive Multi-Run Loop**:
  - Allows consecutive calculations in a single session.
  - Safely closes resources (`Scanner`) upon exit.

---

## 📋 Prerequisites

To compile and run this application, you need:
- **Java Development Kit (JDK)**: Version 8 or higher (JDK 17, 21, or 23 recommended).
- A terminal or command prompt (PowerShell, Command Prompt, Bash, Zsh).

Check your installed Java version:
```bash
java -version
javac -version
```

---

## 📂 Project Structure

```text
CodeOrbit_simpleCalc/
│
├── SimpleCalculator.java   # Main Java source code containing all calculator logic
├── SimpleCalculator.class  # Compiled bytecode
└── README.md               # Project documentation
```

---

## 🚀 How to Compile and Run

### 1. Compile the Source Code
Open your terminal in the project directory and run:
```bash
javac SimpleCalculator.java
```
This generates the `SimpleCalculator.class` bytecode file.

### 2. Execute the Application
Run the compiled Java class:
```bash
java SimpleCalculator
```

---

## 💻 Sample Usage & Demo

### Example 1: Standard Calculation
```text
=========================================
        Welcome to Simple Calculator     
=========================================

Enter the first number: 25.5
Enter an operator (+, -, *, /, %): *
Enter the second number: 4
-----------------------------------------
Result: 25.5000 * 4.0000 = 102.0000
-----------------------------------------

Do you want to perform another calculation? (yes/no): yes
```

### Example 2: Handling Division by Zero
```text
Enter the first number: 50
Enter an operator (+, -, *, /, %): /
Enter the second number: 0

[Math Error] Division by zero is undefined.

Do you want to perform another calculation? (yes/no): yes
```

### Example 3: Handling Invalid User Input
```text
Enter the first number: abc
Invalid input ('abc'). Please enter a valid number: 18
Enter an operator (+, -, *, /, %): ^
Invalid operator. Please enter one of (+, -, *, /, %): +
Enter the second number: 7
-----------------------------------------
Result: 18.0000 + 7.0000 = 25.0000
-----------------------------------------

Do you want to perform another calculation? (yes/no): no

Thank you for using Simple Calculator. Goodbye!
```

---

## 🛡️ Exception & Error Handling

| Scenario | Exception / Handling Mechanism | Behavior |
| :--- | :--- | :--- |
| **Non-numeric input** (`"abc"`, `"!@"`) | `InputMismatchException` | Caught inside `readNumber()`; clears buffer and prompts again. |
| **Division / Modulo by 0** | `ArithmeticException` | Standard Java `double` produces `Infinity` on division by 0; `calculate()` explicitly throws `ArithmeticException` to display a clean math error message. |
| **Invalid Operator** (`"^"`, `"&"`) | Operator validation loop | Re-prompts the user until a valid operator is entered. |
| **Unknown Errors** | General `Exception` handler | Catches any unforeseen errors in the main execution block. |

---

## 🏗️ Code Architecture

The program is organized into modular methods:

1. **`main(String[] args)`**:
   - Manages the program loop and user session.
   - Coordinates reading inputs, dispatching calculations, and handling errors.
   - Closes the `Scanner` resource before application exit.
2. **`readNumber(Scanner scanner)`**:
   - Safely reads a `double` using a `try-catch` block around `scanner.nextDouble()`.
3. **`readOperator(Scanner scanner)`**:
   - Ensures the operator is a single valid arithmetic character (`+`, `-`, `*`, `/`, `%`).
4. **`calculate(double a, double b, char operator)`**:
   - Executes the mathematical operation using a `switch` statement.
   - Throws `ArithmeticException` on division or modulo by zero.
