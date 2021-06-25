package engine.service;

import engine.dto.QuizDTO;
import engine.dto.ResultDTO;
import engine.model.Option;
import engine.model.Quiz;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizDTO addQuizToDB(QuizDTO quizDTO) {
        Quiz q = convertQuizDtoToQuizEntity(quizDTO);
        quizRepository.save(q);
        return convertQuizEntityToQuizDto(q);
    }

    public Collection<QuizDTO> getQuizzes() {
        List<Quiz> listQuiz = quizRepository.findAll();
        return listQuiz.stream()
                .map(this::convertQuizEntityToQuizDto)
                .collect(Collectors.toList());
    }

    public QuizDTO getQuizDtoById(long id) throws ResponseStatusException {
        Quiz q = findQuizById(id);
        return convertQuizEntityToQuizDto(q);
    }

    public ResultDTO solveQuiz(long id, TreeSet<Integer> answers) {

        Quiz quiz = findQuizById(id);

        Set<Integer> result = getSetAnswersFromOptions(quiz.getOptions());

        if (Objects.equals(result, answers)) {
            return ResultDTO.successResult();
        } else {
            return ResultDTO.wrongResult();
        }
    }

    private Quiz findQuizById(long id) {
        return quizRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Quiz not found for this id : " + id));
    }

    private QuizDTO convertQuizEntityToQuizDto(Quiz quiz) {
        QuizDTO q = new QuizDTO();

        q.setId(quiz.getId());
        q.setTitle(quiz.getTitle());
        q.setText(quiz.getText());

        q.setOptions(getListOptionsFromOptions(quiz.getOptions()));
        q.setAnswer(getSetAnswersFromOptions(quiz.getOptions()));
        return q;
    }

    private Quiz convertQuizDtoToQuizEntity(QuizDTO dto) {
        Quiz q = new Quiz();

        q.setId(dto.getId());
        q.setTitle(dto.getTitle());
        q.setText(dto.getText());

        List<Option> result = new ArrayList<>();

        for (var i = 0; i < dto.getOptions().size(); i++) {
            Option o = new Option();

            o.setBody(dto.getOptions().get(i));
            o.setRight(dto.getAnswer().contains(i));
            o.setNumber(i);

            result.add(o);
        }

        q.setOptions(result);

        return q;
    }

    private Set<Integer> getSetAnswersFromOptions(List<Option> list) {
        return list.stream()
                .filter(Option::isRight)
                .map(Option::getNumber)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private List<String> getListOptionsFromOptions(List<Option> list) {
        return list.stream()
                .map(Option::getBody)
                .collect(Collectors.toList());
    }
}