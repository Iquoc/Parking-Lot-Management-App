# Parking-Lot-Management-App

## Overview

A simple Java console application for managing a parking lot. The program initializes a parking lot with a number of spots, then lets you park vehicles, remove vehicles, and display vehicle parking status.

## Requirements

- Java SE Development Kit (JDK) 17 or later installed
- `javac` and `java` available on your system `PATH`

## Build and Run

Open a terminal in the project root (`c:\Users\iquoc\Documents\GitHub\Parking-Lot-Management-App`) and run:

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

### Remove or display input

- Enter only the license plate, for example:
  - `ABC123`

## Notes

- Vehicle sizes are: `SMALL`, `LARGE`, `OVERSIZE`
- Invalid menu choices or invalid vehicle input will prompt you again.
- The program runs in the console until you choose option `4` to exit.
