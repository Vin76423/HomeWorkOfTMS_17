package application.action;

import entity.Student;
import exception.DuplicateStudentException;

public class AddStudentAction extends BaseAction implements Action {

    @Override
    public String getName() {
        return "Добавить студента";
    }

    @Override
    public void action() {
        Student student = getStudent();

        try {
            studentController.add(student);
        }catch (DuplicateStudentException e) {
            System.out.println(e.getMessage());
        }
    }
}
