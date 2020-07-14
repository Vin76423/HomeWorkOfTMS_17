package exception;

public class NotStudentException extends Exception {
    String name;

    public NotStudentException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "Студента " + " c именем - " + name + " нет в списке";
    }
}
