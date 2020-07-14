package entity.templates;

import entity.Student;

public class OutFromUniversityTemplate implements Template{
    private String name;
    private double mark;

    private OutFromUniversityTemplate(Student student) {
        this.name = student.getName();
        this.mark = student.getAverageMark();
    }

    public OutFromUniversityTemplate() { }

    @Override
    public String getText() {
        return String.format("Уважаемый/мая %s !\nДоводим до вашего свединия, что ваш средний бал за симестр составляет %s " +
                "\nи не превышает необходимого минимума в 4 балла. " +
                "\nПо этому если вы не пересдадите свои зачеты в течении следующей недели и не исправите свой средний бал успеваемости," +
                "\nмы будем вынуждены отчислить вас из нашего университета из-за вашей неуспеваемости.", name, mark);
    }

    @Override
    public Template getClone(Student student) {
        return new OutFromUniversityTemplate(student);
    }
}
