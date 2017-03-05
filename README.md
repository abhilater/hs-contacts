# hs-contacts
A simple contacts manager app

# Current features
1. Stores contact names in memory
2. Provides user command prompt to add/search contacts
3. Supports prefix search (auto-complete)

# Future enhancements
1. Persist Contacts datasource so that restarting the app can pick up from last state persisted.
2. Help command options in command line

# Steps to run
1. Unzip zip i.e hs-contacts.zip at a location where user has execute access
2. Move to hs-contacts
3. Make the Make file executable using
    chmod +x Make
4. Set ENV variable JAVA_HOME and MAVEN_HOME in Make, if not alread available in the environment
   or export the same using <br/>
   export JAVA_HOME= <br/>
   export MAVEN_HOME= <br/>
   eg. <br/>
	export JAVA_HOME=/home/abhishek/softwares/jdk1.8.0_77 <br/>
	export MAVEN_HOME=/home/abhishek/softwares/apache-maven-3.3.3 <br/>

   Note: Java 8 required to run app
5. Run using Make file (Builds, packages, runs unit tests and then starts the app) <br/>
    ./Make
