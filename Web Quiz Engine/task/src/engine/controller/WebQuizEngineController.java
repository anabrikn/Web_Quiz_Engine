package engine.controller;

import engine.dataTransferObject.Result;
import engine.dataTransferObject.Quiz;
import engine.dataTransferObject.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.*;

@RestController
@Validated
public class WebQuizEngineController {
    private static Integer count = 0;
    private Map<Integer, Quiz> quizMap = new HashMap<>();

    @GetMapping(path = "/api/quizzes")
    public Collection<Quiz> getQuizzes() {
        return quizMap.values();
    }

    @PostMapping(path = "/api/quizzes")
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
        if (quiz.getOptions().size() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Need more options.");
        } else {
            quizMap.put(quiz.getId(), quiz);
            return quiz;
        }
    }

    @GetMapping(path = "api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable("id") @Min(1) int id) {
        if (quizMap.containsKey(id)) {
            return quizMap.get(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No such quiz");
        }
    }

    @PostMapping(path = "/api/quizzes/{id}/solve", consumes = "application/json")
    public Result getAnswer(@PathVariable @Min(1) int id, @RequestBody UserResponse answer) {
        if (quizMap.containsKey(id)) {
            if (answer.getAnswer().equals(quizMap.get(id).getAnswer())) {
                return Result.successResult();
            } else {
                return Result.wrongResult();
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No such quiz");
        }
    }
}