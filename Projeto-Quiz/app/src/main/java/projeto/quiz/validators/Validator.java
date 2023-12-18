package projeto.quiz.validators;


public interface Validator<T> {
    boolean validate(T data);
}
