package engine.controller;

import engine.dto.ResultDTO;
import engine.dto.QuizDTO;
import engine.manager.QuizManager;
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

    private final QuizManager manager;

    @Autowired
    public WebQuizEngineController(QuizManager manager) {
        this.manager = manager;
    }

    @GetMapping(path = "/api/quizzes", produces = APPLICATION_JSON_VALUE)
    public Collection<QuizDTO> getQuizzes() {
        return manager.getQuizzes();
    }

    @PostMapping(path = "/api/quizzes", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public QuizDTO addQuiz(@Valid @RequestBody QuizDTO quiz) {
        return manager.addQuizToDB(quiz);
    }

    @GetMapping(path = "/api/quizzes/{id}", produces = APPLICATION_JSON_VALUE)
    public QuizDTO getQuiz(@PathVariable("id") @Min(1) long id) {
        return manager.getQuizDtoById(id);
    }

    @PostMapping(path = "/api/quizzes/{id}/solve", produces = APPLICATION_JSON_VALUE)
    public ResultDTO solveQuiz(@PathVariable @Min(1) long id, @RequestBody Map<String, TreeSet<Integer>> userResponse) {
        return manager.solveQuiz(id, userResponse.get("answer"));
    }
}