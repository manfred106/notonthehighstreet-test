# notonthehighstreet-test

### Overview
***
This is a repository for storing the test solution code of implementing a checkout system 
that involves the inclusion of different promotions. 

The core `PromotionRule` is implemented as an abstraction. `AbstractPromotionRule` is a abstract class that implements getter and setter method of some common attributes that are applicable to promtions. All concrete promotion rules must either implement `PromotionRule` interface or extend `AbstractPromotionRule` class, and provide the business logic implementation in `void apply(CheckoutBasket)` method.

Checkout process `Checkout` replies on `PromotionRule` abstraction instead of the concrete promotion rule class. This follows the principle of "Dependency Inversion Principle".

The skeleton class diagram is shown as below:

![Class Diagram](https://github.com/manfred106/notonthehighstreet-test/blob/main/class%20diagram.png?raw=true)


### Prerequisite
***
1. You must have at least Java 8 installed.



### How to run the application?
***
Download the source code as a zip file and unzip it to a project folder. In the project folder, execute the following command. The application will ask for the input of basket items when it starts. Simply input a sequence of product codes in comma deimited form (e.g. 001,002,003).
```bash
java -jar build/libs/notonthehighstreet-test-0.1.jar
```

Alteratively, you may use gradlew to run it as well:
```bash
gradlew run
```


### How to run test cases?
***
There are 3 standard test cases defined in [`com.notonthehighstreet.CheckoutStandardTest`] covering the examples in the test sheet. To run them, execute
```bash
gradlew test --tests com.notonthehighstreet.CheckoutStandardTest --info
```
