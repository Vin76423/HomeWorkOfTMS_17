package application.action;

import application.exceptions.StopApplicationException;

public class CreateReportAction extends BaseAction implements Action {

    @Override
    public String getName() {
        return "Вывести отчет по всем студентам";
    }

    @Override
    public void action() {
        studentController.createReport();
    }
}
