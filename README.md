## University Mangement System (backend) (دانشجویار)
### Description
This piece of code handels backend of the project , which is written by **Java** and connects to frontend via socket . Also this app provides ***Multi Threading*** 
which means many clients can use the App at the same time . hope you enjoy it . 

## Installation and Setup

1.Make sure JDK and a appropriate IDE are installed on your system.

2.Clone the source code:
git clone https://github.com/AloofAli/danshjoyar-back.git

3.Open the project in IDE.

4.Run the project.

## Project Structure
```
───src
│   └───main
│       ├───java
│       │   ├───exceptions
│       │   │       AssignmentNotFoundException.java
│       │   │       CourseNotFoundException.java
│       │   │       StudentNotFoundException.java
│       │   │       TeacherNotFoundException.java
│       │   │       TermNotFoundException.java
│       │   │
│       │   ├───mainClasses
│       │   │       Assignment.java
│       │   │       BeheshtiUniversityField.java
│       │   │       Course.java
│       │   │       Student.java
│       │   │       StudentCourse.java
│       │   │       Teacher.java
│       │   │       Term.java
│       │   │
│       │   ├───Menus
│       │   │       AdminLogin.java
│       │   │       AdminMenu.java
│       │   │       firstMenu.java
│       │   │       RegisterTeacher.java
│       │   │       SigningTeacher.java
│       │   │       TeacherMenu.java
│       │   │
│       │   └───Server
│       │           Server.java
│       │           test.java
│       │
│       └───resources
│           ├───Students
│           │       402243080.xml
│           │       402243082.xml
│           │       402243104.xml
│           │
│           ├───Tasks
│           │       AminAzizi.txt
│           │
│           └───Teachers
│                   SadeghAliakbary.xml
```
- ***exceptions*** : The entire exceptions of the app are in this directory.
- ***mainClasses*** : Basic handlings.
- ***Menus*** : The other name is CLI , teachers and admins can handle and control students,assignments,... from here.
- ***Server*** : the server side is here , all datas are recieved and sent through this directory.
- ***resources*** : keeps app's resources, such as student's files , task's files and ... .
