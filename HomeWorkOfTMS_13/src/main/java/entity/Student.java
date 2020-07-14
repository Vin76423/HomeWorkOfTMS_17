package entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlType(propOrder = {"id", "name", "email", "marks", "averageMark"})
public class Student implements Comparable<Student>, Serializable {
    private int id;
    private String name;
    private String email;
    private List<Integer> marks;
    private double averageMark; // средний бал за симестр
    private static final long serialVersionUID = 1L;

    public Student(int id, String name, String email, List<Integer> marks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.marks = marks;
    }

    public Student(int id, String name, String email, double averageMark) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.averageMark = averageMark;
    }

    public Student() { }

    public Student(int id) {
        this.id = id;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(double averageMark) { this.averageMark = averageMark; }



    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "average_mark")
    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @XmlElementWrapper(name = "marks")
    @XmlElement(name = "mark")
    public List<Integer> getMarks() { return marks; }

    public void setMarks(List<Integer> marks) { this.marks = marks; }

    @Override
    public String toString() {
        return "Student{" +
                "id студента =" + id +
                ", имя = " + name +
                ", email = " + email +
                ", оценки = " + marks +
                ", средний бал успеваемости за симестр =" + averageMark +
                '}';
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        if (id == 0) {
            return name.equals(student.name);
        }
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }





    @Override
    public int compareTo(Student o) {
        return (int) ((this.getAverageMark() - o.getAverageMark()) * 100);
    }
}
