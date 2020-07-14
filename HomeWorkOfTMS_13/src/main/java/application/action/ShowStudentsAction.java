package application.action;

public class ShowStudentsAction extends BaseAction implements Action {

    @Override
    public String getName() {
        return "Вывод всех студентов";
    }

    @Override
    public void action() {
        showList(studentController.getList());
    }
}
