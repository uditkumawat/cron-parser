This is java command line program to parse the cron expression.

This will run into your system if JAVA is installed and its path is correctly set.

Unzip the folder and go to /src folder.

Run commands :

1. Go to src folder inside cron-parser folder.

2. Compile all files:

javac com/udit/kumawat/*.java

3. Run the tests:

java com.udit.kumawat.Main RunTests

4. To run manual test

java com.udit.kumawat.Main 6/15 "*" "*" "*" "*" /usr/bin/find
