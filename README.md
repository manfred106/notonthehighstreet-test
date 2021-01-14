# notonthehighstreet-test

### Overview
***
This is a repository to storing the solution code of implementing a checkout system 
that involves the inclusion of different promotions.


### Prerequisite
***
1. You must have at least Java 8 installed.



### How to run the application?
***
Download the source code as a zip file and unzip it to a project folder. In the project folder, execute the following command:
```bash
java -jar build/libs/notonthehighstreet-test-0.1.jar
```

Alteratively, you may use gradlew to run it as well:
```bash
gradlew run
```


### How to run test cases?
There are 3 standard test cases defined in [`com.notonthehighstreet.CheckoutStandardTest`] covering the examples in the test sheet. To run them, execute
```bash
gradlew test --tests com.notonthehighstreet.CheckoutStandardTest --info
```
