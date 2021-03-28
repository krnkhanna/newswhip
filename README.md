# Newswhip

A system for storing URLs with social interaction and getting the summary statistics of the social data associated with these URLs.
It provides a REPL command line interface for managing this system, and supports the following commands:

### ADD: 
Adding a URL with an associated social score.
### REMOVE: 
Removing a URL from the system.
### EXPORT: 
Getting statistics about the URLs stored in the system in a CSV style. The export consist of the aggregated social score for the domains in the system.
### QUIT: 
To quit from the application.

## Syntax
The syntax to use the commands is as follows:
### ADD 
ADD http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 20
### REMOVE
REMOVE https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/
### EXPORT
EXPORT
### QUIT
QUIT

## Some assumptions I made for the solution.
1. I am using a map which is not persistent for storing the data, so after the application ends you will lose all the data, 
   this can be easily replaced with and persistent storage by implementing the interface `SocialScoreStore`.
2. If the number of arguments passed with the command are not exactly what is required, it will cause and exception, please use the exact syntax for the commands.
3. If you are trying to use the same url again, it will override the exiting data. This is specifically for the ADD command.
4. The urls are required to be in the format http:// or https://

## Run the application
The application is a maven application, so to run it you will need java and maven installed on the system.
Once java and maven are installed running the application is pretty easy.
You will need to use command `mvn clean package` to run the unit tests and build an executable jar for the application.
Once we have the executable jar we can use the command `java -jar target\newswhip-test-1.0-SNAPSHOT-jar-with-dependencies.jar` 
to run the application.
