# kata-scores

Implementation of a REST Api with the following methods:

Login: This endpoint returns a session key in the form of a string which shall be valid for use with the other functions for 10 minutes. The session keys should be unique.

    Request: POST /login
    Request body: <username> and <password>
    Response: <uniquesessionkey>
    Code: 202

Add Level Scores: This other endpoint can be called several times per user and level and does not return anything. Only requests with valid session keys shall be processed.

    Request: PUT /level/3/score/1500
    Set session key in header with name Session-Key
    Response: (nothing)
    Code: 204

High score list for a level: Retrieves the high scores for a specific level for each user.

    Request: GET /level/3/score?filter=highestscore
    Response: JSON of array <username> and <score>
    Code: 200
 
## Start the app

### Localhost

Run 

    ./gradlew clean build

You will find the test coverage report at: /buil/jacocoHtml/index.html

1. You can run directly the jar file:

    java -jar build/libs/kata-scores-1.0.0.jar
    
2. Or you can run the bootRun task from gradle:

    ./gradlew bootRun
    
3. Or you can run the main class located on com.joantolos.kata.scores.KataScoresApp on you IDE

Once the app is running, you can access it on localhost:8080

Swagger is included and accessible in: http://localhost:8080/swagger-ui.html#/ where you can try out the features.

### Deployed on Heroku

The app is deployed on Heroku and Swagger can be access at the following URL:

https://kata-scores.herokuapp.com/swagger-ui.html#/

## Configuration and parameters

On the resources folder you can find the application.properties where you can configure:

* The application port
* The token timeout (in miliseconds)
* The creation script for the database (H2 in memory database)