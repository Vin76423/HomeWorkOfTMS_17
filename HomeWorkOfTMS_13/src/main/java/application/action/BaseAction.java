package application.action;

import application.utils.Input;
import controllers.StudentController;
import entity.Student;

import java.util.List;

public abstract class BaseAction implements Action{
    protected StudentController studentController = new StudentController();

    protected Student getStudent() {
        int id = Input.getInt("Введите ID");
        if (id > 0) {
            String name = Input.getString("Введите имя");
            String email = Input.getString("Введите email студента");
            List<Integer> marks = Input.getMarks("Введите оценки студента, через разделитель-пробел");
            return new Student(id, name, email, marks);
        } else {
            System.out.println("ID не может быть отрицательным, или ровняться 0");
            return getStudent();
        }
    }

    protected void showList(List<Student> students) {
        System.out.println("========================");
        for (Student student : students)
            System.out.println(student);
        System.out.println("========================");
    }
}
