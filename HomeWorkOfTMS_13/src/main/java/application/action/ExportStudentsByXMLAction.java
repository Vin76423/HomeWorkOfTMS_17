package application.action;

import application.exceptions.StopApplicationException;
import application.utils.Input;

public class ExportStudentsByXMLAction extends BaseAction implements Action {

    @Override
    public String getName() { return "Экспорт списка студентов в формате XML"; }

    @Override
    public void action() {
        String path = Input.getString("Введите путь к файлу для экспорта");
        studentController.exportStudentsByXML(path);
    }
}
