package entity.templates;

import entity.Student;

public class WithoutChangesTemplate implements Template {
    private String name;
    private double mark;

    private WithoutChangesTemplate(Student student) {
        this.name = student.getName();
        this.mark = student.getAverageMark();
    }

    public WithoutChangesTemplate() { }

    @Override
    public String getText() {
        return String.format("Уважаемый/мая %s !\nДоводим до вашего свединия, что ваш средний бал за симестр составляет %s, " +
                "\nтем самым превышает необходимый минимум в 4 балла, " +
                "\nно не превышает планку в 8 баллов для рассмотрения дополнительных поощрений. " +
                "\nПоэтому размер вашей стипендии по итогом симестра остается прежним.", name, mark);
    }

    @Override
    public Template getClone(Student student) {
        return new WithoutChangesTemplate(student);
    }
}
