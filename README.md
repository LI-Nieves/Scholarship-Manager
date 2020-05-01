# Scholarship Distributor for USask

Disclaimer: This is a term project for a university Computer Science class. This has no affiliation with the University of Saskatchewan whatsoever.

## Compiling and running the application through the terminal
Step 0. Open the terminal

Step 1. Change your directory to `/<projectFolder>/src`

Step 2. Type the command: `javac -d . *.java`

Step 3. Type the command: `java frontend.Main`

The login screen is the first to pop-up, and you can enter the application by registering yourself (as either one of the two roles: Student or Coordinator) and logging in. You will see that the functionalities for the Student and the functionalities for the Coordinator are different. 

A very important part of our project is the use of "databases" (in reality, they are text files) that are written to and read from in order to maintain the information entered in the application by the users. To see these databases, redirect `\src\databases` and you will see the information being stored in the files at that directory. 
Currently, all databases are empty except for `scholarships.txt`, which handles all the scholarships and their attributes. You can still write to and read from this file; it is not empty merely to provide several examples for you to work with.

Also, note the existence of the directory `/<projectFolder>/src/uploadedFiles/proxy.txt`. The reason for this is that `/uploadedFiles` is the directory where all student portfolios are held. And thus, the file `proxy.txt` serves to keep `/uploadedFiles` in the system; otherwise `/uploadedFiles` would cease to exist and the application will not run correctly.

Note that there are two packages: `frontend` and `backend`. They are aptly-named.


