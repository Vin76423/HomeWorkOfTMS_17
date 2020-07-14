package entity.templates;

import entity.Student;

public interface Template {
    String getText();
    Template getClone(Student student);
}
