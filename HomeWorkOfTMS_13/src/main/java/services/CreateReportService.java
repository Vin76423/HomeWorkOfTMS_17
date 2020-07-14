package services;

import entity.Student;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CreateReportService implements Runnable {
    List<Student> students;

    public CreateReportService(List<Student> students) {
        this.students = students;
    }



    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("reports\\report_%s.txt",
                DateTimeFormatter.ofPattern("yyy_MM_dd").format(LocalDate.now()))))){
            writer.write("****Отчет****");
            writer.newLine();
            double sum = 0;
            for (Student student : students){
                writer.write(student.toString());
                writer.newLine();
                sum += student.getAverageMark();
            }
            writer.write(String.format("Всего численность студентов в университете - %s", students.size()));
            writer.newLine();
            writer.write(String.format("Сумма среднего балла всех студентов - %s", sum));
            writer.newLine();
            writer.write(String.format("Средний бал успеваемости по всему университету - %s", sum/students.size()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
