package engine.controller;

import engine.dto.ResultDTO;
import engine.dto.QuizDTO;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping
public class WebQuizEngineController {

    private final QuizService manager;

    @Autowired
    public WebQuizEngineController(QuizService manager) {
        this.manager = manager;
    }

    @PostMapping(path = "/api/quizzes", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public QuizDTO addQuiz(@Valid @RequestBody QuizDTO quiz) {
        return manager.addQuizToDB(quiz);
    }

    @GetMapping(path = "/api/quizzes", produces = APPLICATION_JSON_VALUE)
    public Collection<QuizDTO> getQuizzes() {
        return manager.getQuizzes();
    }

    @GetMapping(path = "/api/quizzes/{id}", produces = APPLICATION_JSON_VALUE)
    public QuizDTO getQuiz(@PathVariable("id") @Min(1) long id) {
        return manager.getQuizDtoById(id);
    }

    @PostMapping(path = "/api/quizzes/{id}/solve", produces = APPLICATION_JSON_VALUE)
    public ResultDTO solveQuiz(@PathVariable @Min(1) long id, @RequestBody Map<String, TreeSet<Integer>> userResponse) {
        return manager.solveQuiz(id, userResponse.get("answer"));
    }

    // TODO /api/quizzes/{id}
    //  A user can delete their quiz by sending the DELETE request to /api/quizzes/{id}.
    //  If the operation was successful, the service returns the 204 (No content) status code without any content.
    //  If the specified quiz does not exist, the server returns 404 (Not found).
    //  If the specified user is not the author of this quiz, the response is the 403 (Forbidden) status code.


    // TODO
    //  If you would like your service to support more operations,
    //  add PUT or PATCH to update existing quizzes in the similar way as DELETE.
    //  Our tests will not verify these operations.
}