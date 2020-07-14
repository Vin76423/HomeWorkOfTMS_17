package application.action;

import application.utils.Input;
import exception.NotStudentException;

public class GetStudentByNameAction extends BaseAction implements Action {

    @Override
    public String getName() {
        return "Получить студента по имени";
    }

    @Override
    public void action()  {
        String name = Input.getString("Введите имя студента");
        try {
            System.out.println(studentController.getStudentByName(name));
        }catch (NotStudentException e) {
            System.out.println(e.getMessage());
        }
    }
}
