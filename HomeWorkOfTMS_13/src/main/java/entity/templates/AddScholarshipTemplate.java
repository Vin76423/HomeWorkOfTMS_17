package entity.templates;

import entity.Student;

public class AddScholarshipTemplate implements Template{
    private String name;
    private double mark;

    private AddScholarshipTemplate(Student student) {
        this.name = student.getName();
        this.mark = student.getAverageMark();
    }

    public AddScholarshipTemplate() { }

    @Override
    public String getText() {
        return String.format("Уважаемый/мая %s !\nДоводим до вашего свединия, что ваш средний бал за симестр составляет %s " +
                "\nи превышает планку в 8 балла. " +
                "\nПоэтому за успехи в обучениии размер вашей стипендии будет увеличен на 20 процентов.", name, mark);
    }

    @Override
    public Template getClone(Student student) {
        return new AddScholarshipTemplate(student);
    }
}
