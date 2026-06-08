# Parking-Lot-Management-App

## Overview

A simple Java console application for managing a parking lot. The program initializes a parking lot with a number of spots, then lets you park vehicles, remove vehicles, and display vehicle parking status.

## Key Highlights

- Designed and implemented a Java CLI application modeling a configurable parking lot with tiered slot sizing (Small, Large, Oversize), applying core OO principles including inheritance, encapsulation, and polymorphism across a Vehicle, ParkingSlot, and ParkingLot class hierarchy
- Produced a formal UML architecture diagram defining class relationships, method signatures, and data flow prior to implementation, demonstrating structured design thinking and distributed system planning skills
- Built full vehicle lifecycle management (entry, exit, and real-time status display) with in-memory state tracking, exercising problem-solving and critical thinking to handle edge cases like slot availability and size mismatch
- Authored a JUnit test suite covering core business logic including slot assignment, vehicle entry/exit validation, and capacity edge cases, reflecting familiarity with test-driven development in an agile workflow
- Documented the project in a public GitHub repository with a structured README covering design approach, key files, and step-by-step instructions to run, test, and verify the application — modeling clear technical communication standards
- Maintained clean separation of concerns across the codebase with modular, extensible class design, enabling future enhancements such as database persistence or REST API integration with minimal refactoring



## Requirements

- Java SE Development Kit (JDK) 17 or later installed
- `javac` and `java` available on your system `PATH`

## Build and Run

Open a terminal in the project root (e.g. `C:\path\to\Parking-Lot-Management-App`) and run:
1. Create an output directory for compiled classes:

   ```cmd
   mkdir out
   ```

2. Compile the application:

   ```cmd
   javac -d out src\Main.java src\models\*.java src\services\*.java
   ```

3. Run the application:

   ```cmd
   java -cp out src.Main
   ```

## How to Use

- When prompted, enter the number of parking spots to initialize the lot.
- Use the menu options:
  1. Park a vehicle
  2. Remove a vehicle
  3. Display vehicle status
  4. Exit

### Parking input format

- License plate and size, for example:
  - `ABC123 SMALL`
  - `XYZ789 LARGE`
  - `DEF456 OVERSIZE`

*NOT CASE-SENSITIVE

### Remove or display input

- Enter only the license plate, for example:
  - `ABC123`

## Seed Data

If you want to seed data instead of manually setting up the parking lot, comment the block between (Main.java, line 23-40).  And, uncomment (Main.java, line 44).

The application initializes parking slots automatically
using a 50/30/20 slot distribution.

Sample vehicles (3, 1 of each size) will also be preloaded for testing.

## Notes

- Vehicle sizes are: `SMALL`, `LARGE`, `OVERSIZE`
- Invalid menu choices or invalid vehicle input will prompt you again.
- The program runs in the console until you choose option `4` to exit.
