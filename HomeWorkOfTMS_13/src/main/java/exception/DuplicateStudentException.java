package exception;

public class DuplicateStudentException extends Exception{
    private int id;

    public DuplicateStudentException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Студент с id = " + id + " у нас уже учиться";
    }
}
