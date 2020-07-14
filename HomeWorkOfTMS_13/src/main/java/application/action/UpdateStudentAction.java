package application.action;

import entity.Student;

public class UpdateStudentAction extends BaseAction implements Action {

    @Override
    public String getName() {
        return "Редактировать профиль студента";
    }

    @Override
    public void action() {
        Student student = getStudent();
        studentController.update(student);
    }
}
