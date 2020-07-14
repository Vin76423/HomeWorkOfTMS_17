package services;

import entity.Mail;
import entity.Student;
import factories.MailsFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

public class MailService implements Runnable {
    private Queue<Mail> mails;

    public MailService(List<Student> students) {
        this.mails = MailsFactory.createMails(students);
    }

    public Queue<Mail> getMails() { return mails; }
    public void setMails(Queue<Mail> mails) { this.mails = mails; }



    public void sendMails() {
        while (!mails.isEmpty()){
            Mail mail = mails.poll();
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("mails\\for_"
                    + mail.getEmail() + ".txt"))){
                bufferedWriter.write("Кому : " + mail.getEmail());
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                bufferedWriter.write(mail.getMassage().getText());
                Thread.sleep(2000);
            }catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        sendMails();
    }
}
