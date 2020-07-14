package application;

import application.action.Action;
import application.config.ConfigActions;
import application.exceptions.StopApplicationException;
import application.utils.Input;

import java.util.Map;

public class ConsoleApplication {
    public void start() {
        System.out.println("Привет");
        run();
        System.out.println("Пока");
    }

    private void run() {
        while (true) {
            Action action = getAction();
            try {
                action.action();
            }catch (StopApplicationException e) {
                break;
            }
        }
    }

    private void showMenu() {
        for (Map.Entry<Integer, Action> item : ConfigActions.actions.entrySet()) {
            System.out.println(item.getKey() + " - " + item.getValue().getName());
        }
    }

    private Action getAction() {
        showMenu();
        int number = Input.getInt("Выберите действие");
        Action action = ConfigActions.actions.get(number);

        if (action != null) {
            return action;
        }

        System.out.println("Нет такого действия. Повторите ввод.");
        return getAction();
    }
}
