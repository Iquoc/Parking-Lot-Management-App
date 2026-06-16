# Parking-Lot-Management-App

## Overview

A simple Java console application for managing a parking lot. The program initializes a parking lot with a number of spots, then lets you park vehicles, remove vehicles, and display vehicle parking status.

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
  4. Display parking lot status
  5. Exit

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

## Java Updated Features

String Blocks (Java SE 15) <br>
<img src="/java 17+ features/displayVSFormat-preStringBlock.png" alt="multiple print statements, readability" style="" />
<img src="/java 17+ features/displayVSFormat-postStringBlock.png" alt="single print statement, readability" style="" />

Records (Java SE 16) <br>
[public record Vehicle](https://github.com/Iquoc/Parking-Lot-Management-App/blob/main/src/main/java/com/parkinglot/models/Vehicle.java)

Stream and Optionals (Java SE 8) <br>
<img src="/java 17+ features/parkVehicle()-preStream.png" alt="nested conditionals, iterate through arraylist" style="" />
<img src="/java 17+ features/parkVehicle()-postStream.png" alt="stream arraylist and filter, iterate through arraylist" style="" />

Stream (Java SE 8) <br>
<img src="/java 17+ features/getAvailableSpots()-preStream.png" alt="nested conditionals, iterate through arraylist" style="" />
<img src="/java 17+ features/getAvailableSpots()-postStream.png" alt="stream arraylist and filter, iterate through arraylist" style="" />

## Notes

- Vehicle sizes are: `SMALL`, `LARGE`, `OVERSIZE`
- Invalid menu choices or invalid vehicle input will prompt you again.
- The program runs in the console until you choose option `4` to exit.
