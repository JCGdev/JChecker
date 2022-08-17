# JChecker

A Java based server (API REST) for validating, extrapolating or generating CCs


## Table of contents

* [Disclaimer](#disclaimer)
* [Functionality](#functionality)
* [Setup](#setup)
* [API](#api)


## Disclaimer
***JChecker is meant for testing or experimental purposes!***.

JChecker generates ***random credit cards*** wich are ***not associated to a cardholder***.

The generated credit cards ***cannot be used in payment gateways***. In fact, they ***will not pass the CSV/CVC verification algorithm***, as the values are randomly generated.

[source1](https://developer.visa.com/capabilities/dps-card-and-account-services/docs-how-to-cvv2)
[source2](https://developer.visa.com/capabilities/dps-card-and-account-services/docs-how-to-cvv2)

The credit card number looks valid, as the check digit (the last one) is ***computed using the [Luhn algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm)***.

 
## Functionality

- Generate ***Luhn-valid*** CCs.
- Validate CCs using the [Luhn algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm).
- Extrapolate CCs with a variety of methods: ***Similarity***, ***Activation*** and ***Sofia***


## Setup

- ### Requirements
    [JDK](https://www.oracle.com/java/technologies/downloads/) installation must be in your PATH enviromental variable.

- ### Build
    - For compiling and building a jar file:

        `$ ./gradlew build -p server/`
    
        > The jar will be stored in ***server/build/libs/***
    
    - For cleaning build files:
    
        `$ ./gradlew clean`

- ### Opening in Eclipse
    Generate and clean Eclipse dotfiles by executing:

    `$ ./gradlew eclipse`
 
    `$ ./gradlew cleanEclipse`

## API
### Endpoints

- /cc/validate/luhn?cc=NUM
    - *cc: Credit card number
    - returns: ***{ "isValid" : true/false }***

- /cc/extrapolate/activation?cc=NUM
    - *cc: Credit card number
    - returns: ***{ "cc" : "extrapolatedNumber" }***

- /cc/extrapolate/similarity?cc1=NUM&cc2=NUM
    - *cc1: First credit card number
    - *cc2: Second credit card number
    - NOTE: cc1 and cc2 must have same bin
    - returns: ***{ "cc" : "extrapolatedNumber" }***

- /cc/extrapolate/sofia?cc1=NUM&cc2=NUM
    - *cc1: First credit card number
    - *cc2: Second credit card number
    - NOTE: cc1 and cc2 must have same bin
    - returns: ***{ "cc" : "extrapolatedNumber" }***

- /cc/generate?bin=BIN&quantity=NUM
    - bin: First six numbers of a credit card (Ex. 242443xxxxxxxxxx)
    - *quantity: Number of credit cards to generate
    - NOTE: The BIN can also be an extrapolated credit card
    - returns: 
    ```
     { 
         "generated" : [ 
           "NUM|MONTH|YEAR|CVV",
           ..... ]
     }
     ```


- /cc/validate/doc
    - returns: html doc explaining API endpoints

- /cc/extrapolate/doc
    - returns: html doc explaining API endpoints

- /cc/generate?bin=BIN&quantity=NUM
    - returns: html doc explaining API endpoints
