package factories;

import entity.Mail;
import entity.Student;
import entity.templates.OutFromUniversityTemplate;
import entity.templates.Template;
import factories.config.ConfigTemplates;

import java.util.*;

public class MailsFactory {
    public static Queue<Mail> createMails(List<Student> students){
        Queue<Mail> mails = new ArrayDeque<>();
        for (Student student : students)
            mails.offer(createOneMail(student));
        return mails;
    }

    private static Mail createOneMail(Student student){
        Template template = getTemplate(student);
        template = template.getClone(student);
        return new Mail(student.getEmail(), template);
    }

    private static Template getTemplate(Student student) {
        Template template = null;
        int mark = (int) student.getAverageMark();
        while (template == null) {
            template = ConfigTemplates.templates.get(mark--);
        }
        return template;
    }
}
