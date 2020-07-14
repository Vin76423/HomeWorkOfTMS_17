package services;

import dao.DataBaseStudentDao;
import dao.StudentDao;
import entity.Student;
import exception.DuplicateStudentException;

public class XMLImportStudentsOperationService extends XMLOperationService {
    private StudentDao studentDao = new DataBaseStudentDao();

    public XMLImportStudentsOperationService(String path) {
        super(path);
    }

    @Override
    public void run() {
        for (Student student : importStudentsByXML()) {
            try {
                studentDao.add(student);
                Thread.sleep(2000);
            } catch (DuplicateStudentException | InterruptedException e) {
                continue;
            }
        }
    }
}
