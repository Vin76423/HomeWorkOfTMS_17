package services;

import entity.Student;

import javax.xml.bind.JAXBContext;
import java.util.List;

public class XMLExportStudentsOperationService extends XMLOperationService {

    public XMLExportStudentsOperationService(List<Student> students, String path) {
        super(students, path);
    }

    @Override
    public void run() {
        exportStudentsByXML();
    }
}
