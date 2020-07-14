package application.action;

import application.exceptions.StopApplicationException;

public class SendMailsAction extends BaseAction implements Action {
    @Override
    public String getName() {
        return "Разослать электронные письма студентам об их успеваемости";
    }

    @Override
    public void action() {
        studentController.sendMailsAboutStudentsSuccess();
    }
}
