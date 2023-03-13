Library Management

Added functionality for the following:
-> Return all records from Book table.
-> To add new books to Book and quantity in Quantity tables.
-> Fetch records based on ISBN(Path param) Author(Query param) and Genre(Query Param).
-> Return records from Book and Quantity together.
-> Added Exception handling.
-> Added Member add and fetch functionality
-> Added Issue book functionality
-> Added Return book functionality
-> Add logging. -> DONE
-> Added unit tests 
-> Added Jacoco for code coverage
-> Completed Docker integration 
-> Added Swagger
-> Added Spring Security

Todo:
-> Apply scalability concept.
-> Add pagination.

APIs:
localhost:8080/library/api/greeting
localhost:8080/library/api/addBooks
localhost:8080/library/api/allBooks
localhost:8080/library/api/member/title?title=Fantastic Beasts
localhost:8080/library/api/author?author=George
localhost:8080/genre?genre=Fiction
localhost:8080/isbn/1111
localhost:8080/library/api/addMember
localhost:8080/library/api/issueBook
localhost:8080/library/api/returnBook
localhost:8080/library/api/name?name=George
localhost:8080/library/api/email?email=sam@gmail.com
localhost:8080/library/api/phone?phone=6345432056
