
# Real-Time Ticketing System






## Introduction


The Real-Time Ticketing System is a software solution designed to manage ticket sales through concurrent ticket releases by vendors and ticket purchases by customers.  In this way, vendors release tickets and customers can purchase tickets using multithreading and synchronization principles. It uses thread safe methods such as synchronized, wait(), and notifyAll() to maintain data consistency and inhibit race conditions.It consists of three major parts: a Command-Line Interface (CLI), a backend service, and a frontend web application.


The project includes a basic ticket pool management command-line interface (CLI) and Backend Service (Spring Boot)- gets defined as the core system and exposes REST APIs that manage ticket records, transactions, and the configuration storage of persistent data in a MySQL database. Finally, the Frontend Web Application (React.js) serves as a user-friendly interface to give real-time ticket availability, configurations, and the transaction history. This, of course, is tied to the backend with REST APIs for seamless updates and interaction. Entire complete components are called strong ticket management systems illustrating concepts like multi-threading, REST API design, and modern web design.





## Setup Instructions

    1. Prerequisites:

        Java Development Kit (JDK): Version 21

        Node.js: Version 16+

        MySQL Database: Version 8+

        IDE: IntelliJ IDEA, Visual Studio Code


    2. CLI part Setup:

        The project includes a basic ticket pool management command-line interface (CLI).
        Use prefer IDE (IntelliJ) to run the CLI.
        
    3. Backend Setup: (Spring Boot)

        3.1. Clone the repository:

        git clone https://github.com/Madubhashinie/RealTimeTicketingSystem.git
        cd RealTimeTicketingSystem/backend


        3.2. Configure the Database:

        Open application.properties in the src/main/resources directory.
        Update  MySQL configurations:

            # Database Configuration
            spring.datasource.url=jdbc:mysql://localhost:3306/ticket_system
            spring.datasource.username=root
            spring.datasource.password=your_password
            spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

            # JPA and Hibernate Settings
            spring.jpa.hibernate.ddl-auto=update
            spring.jpa.show-sql=true
            spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
        

        3.3. Build the Backend:
                mvn clean install

        3.4. Run the Backend Application
                mvn spring-boot:run

            
    4. Frontend Setup: (React.js)
            
            Navigate to the frontend directory:
                cd ../ticketing_system

            Install the dependencies:
                npm install

            Start the frontend application:
                npm run dev

            



## Usage Instructions

CLI:

    Use the CLI for ticket pool management via IntelliJ IDEA.

    Start: Begins ticket addition by vendors and purchase processes by customers.
    Stop: Stops ticket transactions and stop the system.

    config.json: store configurations
    transactions.json: store transactions


FrontEnd:

    1. Configure the System:

        Update the ticket pool configurations
        (Total Tickets, Ticket Release Rate, Customer Retrieval Rate, Maximum Ticket Capacity)
        from the Event Ticket Controls UI section.

    2. Start, Stop, and Reset Controls:

        Start: Starts ticket addition and purchase processes.
        Stop: Stops ticket transactions.
        Reset: reset the ticket queue.

    3. UI Controls Explained:        
| UI Component  | Description            |
| :-------- | :------------------------- |
| Configuration Form | Update the system's ticket configuration|
| Control Panel | Start, stop, and reset the system|
| Ticket Status | Displays Remaining tickets in the current thread and  maximum ticket capacity|
| Log Display | Shows real-time ticket transactions|

    4.  Viewing Logs:

    Transactions are displayed under the Transaction Logs section.
| columns  | Description            |
| :-------- | :------------------------- |
| Transaction ID |Unique ID for each transaction |
| User |Vendor or Customer|
| Action|Ticket added/purchased |
| Date and Time |When the transaction occurred|




    


## Technologies Used
        CLI:
        JSON        - for data storage
        
        Frontend:
         React.js   - for UI
         Axios      - for API requests

        Backend: 
        Spring Boot - Framework (for API services)
        Hibernate   - for Object-Relational Mapping(ORM)
        MySQL       - for data storage

        Build Tools: 
        Maven       - for backend build management
        npm         - for frontend dependency management

        Database: 
        MySQL       - for persistent data storage
## API Reference

#### Get Configurations

```http
  GET /api/configuration
```

#### Get All Tickets

```http
  GET /api/tickets
```

#### Check Controls (Start, Stop, Reset)

```http
  GET /api/control
```

#### Get All Transactions

```http
  GET /api/transactions
```




## Demo

https://drive.google.com/file/d/1EkCWSaHONScsN6HULdmu87On6JJAc6BS/view?usp=sharing
