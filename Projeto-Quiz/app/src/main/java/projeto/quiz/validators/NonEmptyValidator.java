package projeto.quiz.validators;


public class NonEmptyValidator implements Validator<String> {
    @Override
    public boolean validate(String data) {
        return !data.equals("");
    }
}
