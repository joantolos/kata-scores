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
 
## Deployed on Heroku

https://kata-scores.herokuapp.com/swagger-ui.html#/