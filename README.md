# GDP Maven Project

This is a simple **Java Maven project** that fetches India's GDP data from the **World Bank API**, sorts it by year, and displays the results.

# Please check the Output.pdf file for checking the Outputs

## 📂 Project Structure
gdp-maven-project/
├── src/
│ ├── main/java/com/example/gdp/App.java # Main program
│ └── test/java/com/example/AppTest.java # (Optional) Unit tests
├── pom.xml # Maven configuration


## ⚙️ Prerequisites
- Java 17+
- Apache Maven 3.9+

## 🚀 Build and Run


## Build the Project
mvn clean install
## Run the Project
mvn exec:java -Dexec.mainClass="com.example.gdp.App"

📡 API Used

World Bank API – GDP (NY.GDP.MKTP.CD)

✨ Features

Fetches GDP data from World Bank API

Parses JSON response

Sorts GDP values by year in ascending order

Displays GDP trends for India

🛠️ Tech Stack

Java

Maven

JSON Processing (org.json)
