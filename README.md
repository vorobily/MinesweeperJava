# Minesweeper Java

This is a simple Minesweeper game implemented in Java.

## Prerequisites

Before running the application, ensure you have the following:

- Java Development Kit (JDK) installed on your system
- PostgreSQL database server installed and running
- IDE (Integrated Development Environment) such as IntelliJ IDEA or Eclipse

## Setup

1. Clone the repository:
git clone https://github.com/vorobily/MinesweeperJava.git

2. Open the project in your preferred IDE.

3. Create the database table manually in your PostgreSQL database:
```sql
CREATE TABLE Minesweeper (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    size INTEGER,
    miny INTEGER,
    cas VARCHAR(10),
    result VARCHAR(255)
);
```

4. Update the JDBC connection details in the `Miny.java` file:
```java
static final String jdbcURL = "jdbc:postgresql://localhost:5432/YOUR_DATABASE_NAME";
static final String username = "YOUR_USERNAME";
static final String password = "YOUR_PASSWORD";
```
Replace YOUR_DATABASE_NAME, YOUR_USERNAME, and YOUR_PASSWORD with your PostgreSQL database name, username, and password, respectively.

## Running the Application
- Run the Start.java file in your IDE.
- The Minesweeper game window should open, allowing you to play the game.

## Gameplay
- Left-click to uncover a tile.
- Right-click to flag a tile as a mine.
- The game ends when you uncover a mine or when you've uncovered all non-mine tiles.

## Saving Game Results
- The game results are automatically saved to the PostgreSQL database table named Minesweeper.
- Each game result includes the player's name, game size, number of mines, time taken to complete the game, and the game result (win/lose).
