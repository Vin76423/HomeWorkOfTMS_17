package controllers;

import dao.DataBaseStudentDao;
import dao.StudentDao;
import entity.Student;
import exception.DuplicateStudentException;
import exception.NotStudentException;
import services.CreateReportService;
import services.MailService;
import services.XMLExportStudentsOperationService;
import services.XMLImportStudentsOperationService;

import java.util.*;

public class StudentController {
    private StudentDao studentDao = new DataBaseStudentDao();


    // Standard(CRUD) ability: ..........................................................
    public void add(Student student) throws DuplicateStudentException {
        // повезло халявная дополнительная отценка за изовый реферат.
        if (student.getName().startsWith("С")) {
            student.getMarks().add(10);
        }

        studentDao.add(student);
    }



    public List<Student> getList() {
        return studentDao.getList();
    }

    public List<Student> getSortedList(String sql) { return studentDao.getImprovedList(sql); }

    public List<Student> getSubList(double lowerLimit, double upperLimit) {
        NavigableSet<Student> students = new TreeSet<>(getList());
        return new ArrayList<>(students.subSet(new Student(lowerLimit),true,
                new Student(upperLimit), true));
    }



    public void remove(int id) { studentDao.remove(new Student(id)); }

    public void update(Student student) { studentDao.update(student); }

    public Student getStudentByName(String name) throws NotStudentException {
        return studentDao.getStudentByName(new Student(name));
    }


    // Special ability: .................................................................
    public void sendMailsAboutStudentsSuccess() {
        Thread thread = new Thread(new MailService(getList()));
        thread.start();
    }

    public void createReport() {
        new Thread(new CreateReportService(getList())).start();
    }



    public void exportStudentsByXML(String path) {
        Thread thread = new Thread(new XMLExportStudentsOperationService(getList(), path));
        thread.start();
    }

    public void importStudentsByXML(String path) {
        Thread thread = new Thread(new XMLImportStudentsOperationService(path));
        thread.start();
    }
}
