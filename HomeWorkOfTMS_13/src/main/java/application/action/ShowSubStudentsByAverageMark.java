package application.action;


import application.utils.Input;

public class ShowSubStudentsByAverageMark extends BaseAction implements Action{

    @Override
    public String getName() {
        return "Вывод выборки успеваемости студентов в симестре в заданном диапозоне среднего бала";
    }

    @Override
    public void action() {
       double lowerLimit = Input.getDouble("Введити нижнюю границу диапозона в ввиде дробного значения");
       double upperLimit = Input.getDouble("Введите верхнюю границу диапозона в ввиде дробного значения");
       showList(studentController.getSubList(lowerLimit,upperLimit));
    }
}

