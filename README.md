# MyHealth
JavaFX project with CRUD functionality for user health records. 

### CONTEXT
This project was for a postgraduate course called 'Advanced Programming'. The weight of the project was 52% of the overall grade. This project receieved a grade of High Distinction Grade of ~90%. Marker comments emphasized the clean class design and code readability. 

### OVERVIEW
This application allows a user to store health records. The project is coded in Java. It uses JavaFX sdk to build the UI. 

### APP FUNCTIONALITY 
- User can create profile. 
- Login & password verification. 
- CRUD functions for health records (CREATE, READ, UPDATE, DELETE) 
- Health records can be selected and exported to a csv file. 
- Profile info can be updated. 
  
### DESIGN PATTERNS USED
- MVC: This forms the core of the application design. The separation of responsibilities enables low coupling and high cohesion between classes. 
- DAO: This decouples database interaction from non-DAO classes. 

### FURTHER DESIGN CHOICES
- Inheritance and abstraction is utilized where possible to allow inversion dependency. (Although interfaces could have been used to increase extensiblility)
- Singe responsiblity principle has been adhered to as much as practical.  
- Encapsulation is used as a default through private fields and getters and setters. 
- Static classes have been used instead of dependency injection for simplicity (this would be something to change in a future interation). 

### THINGS I WOULD CHANGE IF I WERE TO DO IT AGAIN. 
- Less static classes. Use Dependency Injection. 
- Use interfaces to increase extensibility. 
- Consider creating Factory classes for abstractions such as controller. This delegates the responsiblity of creating new instances to a set of factory classes. 
- Make the UI look a bit better. 
- Add a datepicker (ran out of time). 
- use password hashing (ran out of time).

### PLEASE NOTE
Database connection class has been removed for privacy purposes. 
