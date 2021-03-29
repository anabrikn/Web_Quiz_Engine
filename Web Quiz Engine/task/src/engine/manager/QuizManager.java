package engine.manager;

import engine.dataTransferObject.QuizDTO;
import engine.dataTransferObject.ResultDTO;
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
public class QuizManager {

    private QuizRepository quizRepository;

    @Autowired
    public QuizManager(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz findQuizById(long id) {
        return quizRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Quiz not found for this id : " + id));
    }

    public QuizDTO addQuiz(QuizDTO quizDTO) {
        Quiz q = convertQuizDtoToQuizEntity(quizDTO);
        quizRepository.save(q);
        return convertQuizToQuizDto(q);
    }

    public QuizDTO getQuizDtoById(long id) throws ResponseStatusException {
        Quiz q = findQuizById(id);
        return convertQuizToQuizDto(q);
    }

    public Collection<QuizDTO> getQuizzes() {
        List<Quiz> listQuiz = quizRepository.findAll();
        return listQuiz.stream()
                .map(this::convertQuizToQuizDto)
                .collect(Collectors.toList());
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

    public QuizDTO convertQuizToQuizDto(Quiz quiz) {
        QuizDTO q = new QuizDTO();

        q.setId(quiz.getId());
        q.setTitle(quiz.getTitle());
        q.setText(quiz.getText());

        q.setOptions(getListOptionsFromOptions(quiz.getOptions()));
        q.setAnswer(getSetAnswersFromOptions(quiz.getOptions()));
        return q;
    }

    public Quiz convertQuizDtoToQuizEntity(QuizDTO dto) {
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

    // метод, который лист Option трансформирует в TreeSet с номерами правильных ответов
    private Set<Integer> getSetAnswersFromOptions(List<Option> list) {
        return list.stream()
                .filter(Option::isRight)
                .map(Option::getNumber)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    // метод, который лист Option трансформирует в List с вариантами ответов
    private List<String> getListOptionsFromOptions(List<Option> list) {
        return list.stream()
                .map(Option::getBody)
                .collect(Collectors.toList());
    }
}
