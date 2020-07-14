package application.action;

import application.exceptions.StopApplicationException;

public class StopApplicationAction implements Action {

    @Override
    public String getName() {
        return "Выход";
    }

    @Override
    public void action() throws StopApplicationException {
        throw new StopApplicationException();
    }
}
