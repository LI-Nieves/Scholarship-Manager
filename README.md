# Scholarship Distributor for USask

Disclaimer: This is a term project for the class SENG300 W2020. This has no affiliation with the University of Saskatchewan whatsoever.

## Compiling and running the terminal-based version
Step 0. Open the terminal

Step 1. Change your directory to `\<projectFolder>\src`

Step 2. Type the command: `javac -d . \*.java`

Step 3. Type the command: `java logic.Start`

By pressing the *Login* button, the program will try to log you in, but correct login information is needed. For now you can look inside the `UserInfoDatabase.txt` and choose one of the username/password combinations. It's in the form *<username> <password> <role>*; you will also have to check the correct role, depending on the account belongs to a student or a coordinator.

### User stories implemented in the terminal-based version:

Students: log in, log out, view all scholarships available.

Scholarship coordinators: log in, log out, add scholarships, remove scholarships, view all scholarships available.

## Compiling and running the GUI-based

#### Option 1: Running in Eclipse
I ran this file on Eclipse, I would prefer running this file in Eclipse.

In order to compile the files, please put the following files into the same folder:


- AfterLogin.java

- Guii.java

- Register.java

- Coordinator.java

- Scholarship.java

- StartGui.java

- Student.java

- studentClass.java

- User.java


As well as:

- rsz_2019_05_03-cap_and_gown_commercial_shoot_jatorner_-0255-web.jpg

- rsz_usask.png

- sask.jpg

- usask.png

- UserInfoDatabase.txt


#### Option 2: Cloning/forking from GitHub and running in the terminal
If you clone/download straight from GitHub, which is higly preferred, it makes the above step really easy.

In order to run in terminal do the following:

Step 0. Open the terminal

Step 1. Change your directory to `\<projectFolder>\src`

Step 2. Type the command: `javac -d . \*.java`

Step 3. Type the command: `java logic.Guii`

In front of you, the GUI will appear.

Running from the terminal, the GUI will look really bad, but running it in Eclipse will make it look much better.

Right now, the *Login* button and *Register* button work.

By pressing the *Register* button, it will prompt for your name and other details.

As of right now, that's all that has been implemented for the register part.

By pressing the *Login* button, the program will try to log you in, but correct login information is needed. For now you can look inside the `UserInfoDatabase.txt` and choose one of the username/password combinations. It's in the form *<username> <password> <role>*; you will also have to check the correct role, depending on the account belongs to a student or a coordinator.

Once you login, you will be taken to a new Panel, which has not been fully implemented yet.

As of now we are trying to implement the backend and have the code working.

We are getting closer and closer to finishing this, once we are done we will fully implement these functions into the GUI. As of now, we have started a little bit of the GUI, and have been focusing more on implementing the classes and funcitonality. 

### User stories implemented in the GUI-based version:

Students: log in, log out. 

Scholarship coordinators: log in, log out.


