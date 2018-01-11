# Document-Similarity

####  Lectuerer: *John Healy*
####  Student:   *Tianle Shu* 
####  StudentID: *G00353418*

##  *Introduction*
* Develop a Java API that can rapidly compare two large text files by computing their Jaccard Index. The API should uphold the principles of loose-coupling and high cohesion throughout its design by correctly applying abstraction, encapsulation, composition and inheritance

* The purpose of this project is to create a Java API that can compare two documents. The main focus of the project is to demonstrate an understanding of the principles of object-oriented design by using abstraction, composition, inheritance and polymorphism throughout the application. 

##  *Minimum Requirement*
* Use the package name ie.gmit.sw. The application must be deployed and runnable using the specification in Section 3.
* Input the path to two text files and any other parameters you need from a console-based menu-driven UI.
* Each file should be parsed in a separate thread and its constituent words / shingles added to a blocking queue for processing.
* A set of worker threads, one for each MinHash function, should take elements from the queue and process them.
* The MinHash approximation of the Jaccard Index of the two text files should then be reported to the user.
* Provide a UML diagram of your design and JavaDoc your code.

A Java Archive containing your API and runner classes with a main() method. You can create the JAR file using Ant or with the following command from inside the “bin” folder of the Eclipse project:
`jar –cf oop.jar *`
The application should be executable from a command line as follows: 
`java –cp ./oop.jar ie.gmit.sw.Runner`

## *UML Diagram*
![UML](Orient-Object-Project2017/Design-Diagram.png)
