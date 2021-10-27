# MERCHANT-S-GUIDE-TO-THE-GALAXY
This project is a small coding challenge.

## About
This application can:
1. Convert Roman numbers to decimal
2. Save aliases for Roman numerals
3. Save item names with their cost
4. Calculate cost of an item for given quantities

## Rules
Rules will be included later on.

## Build
To build the project here are 2 options:
1. Eclipse: Import the git repository as maven project and run it as Java Application
2. Maven: Use ```mvn package``` from the command line to package the project into a .jar file and 
execute it with ```java -cp target/merchant-1.0-SNAPSHOT.jar com.challenge.App```  
(the name of the generated .jar file depends on the options in the pom.xml file used by maven)  
or use ```mvn compile``` from the command line to compile the source files to class files and 
execute it with ```java -cp target/classes com.challenge.App``` 

## Usage

1. To add an alias for a Roman numeral: ```alias is A``` where A is a Roman numera like I or C
2. To add an item with cost: ```aliases item is cost Credits``` where aliases is a Roman number written with defined aliases, item is an item name and cost is a decimal number
3. To convert from Roman written with aliases to decimal: ```how much is aliases ?``` where aliases is a Roman number written with defined aliases
4. To calcualte cost of an item: ```how many Credits is aliases item ?``` where aliases is a Roman number written with defined aliases and item is an item name

### Example
```
Test input:
> glob is I
> prok is V
> pish is X
> tegj is L
> glob glob Silver is 34 Credits
> glob prok Gold is 57800 Credits
> pish pish Iron is 3910 Credits
> how much is pish tegj glob glob ?
> how many Credits is glob prok Silver ?
> how many Credits is glob prok Gold ?
> how many Credits is glob prok Iron ?

Test Output:
> pish tegj glob glob is 42
> glob prok Silver is 68 Credits
> glob prok Gold is 57800 Credits
> glob prok Iron is 782 Credits
