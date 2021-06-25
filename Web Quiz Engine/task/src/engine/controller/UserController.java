package engine.controller;

import engine.dto.UserDto;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    // TODO ргистрация ноого пользователя
    //  To register a new user, the client needs to send a JSON with email and password
    //  via POST request to /api/register:
    //  {
    //  "email": "test@gmail.com",
    //  "password": "secret"
    //  }
    @PostMapping(path = "/api/register", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void registerUser(@Valid @RequestBody UserDto userDto) {
        logger.info("New user has email " + userDto.getEmail());
        service.addUserToDB(userDto);
        //return userDto.getEmail();
    }



    // TODO авторизация пользовтеля



    //TODO
    //  The service returns 200 (OK) status code if the registration has been completed successfully.
    //  If the email is already taken by another user, the service will return the 400 (Bad request) status code.
    //  Here are some additional restrictions to the format of user credentials:
    //  the email must have a valid format (with @ and .);
    //  the password must have at least five characters.
    //  If any of them is not satisfied, the service will also return the 400 (Bad request) status code.
    //  All the following operations need a registered user to be successfully completed.

}
