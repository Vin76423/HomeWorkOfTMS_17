package services;

import entity.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class XMLOperationService implements Runnable {
    private StudentWrapper students;
    private String path;

    @XmlRootElement(name = "students")
    private static class StudentWrapper {
        private List<Student> students;

        public StudentWrapper(List<Student> students) {
            this.students = students;
        }
        public StudentWrapper() { }

        @XmlElement(name = "student")
        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }


    public XMLOperationService(List<Student> students, String path) {
        this.students = new StudentWrapper(students);
        this.path = path;
    }

    public XMLOperationService(String path) { this.path = path; }





    protected void exportStudentsByXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(Student.class, StudentWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(students, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    protected List<Student> importStudentsByXML() {
        List<Student> students = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(Student.class, StudentWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StudentWrapper wrapper = (StudentWrapper) unmarshaller.unmarshal(new File(path));
            students =  wrapper.getStudents();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return students;
    }


    @Override
    public abstract void run();
}
