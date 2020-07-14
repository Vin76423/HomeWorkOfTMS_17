package application.action;

import application.utils.Input;

public class RemoveStudentAction extends BaseAction implements Action {

    @Override
    public String getName() {
        return "Отчислить студента";
    }

    @Override
    public void action() {
        int id = Input.getInt("Введите ID студента");
        studentController.remove(id);
    }
}
