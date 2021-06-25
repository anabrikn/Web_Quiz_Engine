Project on hyperskill: https://hyperskill.org/projects/91

In this project, you will develop a multi-user web service for creating and solving quizzes using REST API, 
an embedded database, security, and other technologies. 
Here we will concentrate on the server side ("engine") without a user interface at all. 
The project stages are described in terms of the client-server model, 
where the client can be a browser, the curl tool, a REST client (like postman) or something else.

 - Solving a quiz
To solve a quiz, the client sends the POST request to /api/quizzes/{id}/solve with a JSON that contains the indexes of all chosen options as the answer. 
This looks like a regular JSON object with key "answer" and value as the array: {"answer": [0,2]}.

It is also possible to send an empty array [] since some quizzes may not have correct options.

The service returns a JSON with two fields: success (true or false) and feedback (just a string). There are three possible responses.

If the passed answer is correct (e.g., POST to /api/quizzes/1/solve with content answer=2):
{"success":true,"feedback":"Congratulations, you're right!"}
If the answer is incorrect (e.g., POST to /api/quizzes/1/solve with content answer=1):
{"success":false,"feedback":"Wrong answer! Please, try again."}
If the specified quiz does not exist, the server returns the 404 (Not found) status code.
 
 - Create a new quiz
To create a new quiz, the client needs to send a JSON as the request's body via POST to /api/quizzes. The JSON should contain the four fields:

title: a string, required;
text: a string, required;
options: an array of strings, required, should contain at least 2 items;
answer: an array of indexes of correct options, optional, since all options can be wrong.

The server response is a JSON with four fields: id, title, text and options.

 - Get a quiz by id
To get a quiz by id, the client sends the GET request to /api/quizzes/{id}.

 - Get all quizzes
To get all existing quizzes in the service, the client sends the GET request to /api/quizzes.
(The response must not include the answer field, otherwise, any user will be able to find the correct answer for any quiz.)
