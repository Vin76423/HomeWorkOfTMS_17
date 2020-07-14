package application.action;

import application.exceptions.StopApplicationException;
import application.utils.Input;
import services.XMLImportStudentsOperationService;

import java.io.File;

public class ImportStudentsByXMLAction extends BaseAction implements Action {

    @Override
    public String getName() {
        return "Импорт студентов из формата XML, с учетом существующего списка";
    }

    @Override
    public void action() {
        studentController.importStudentsByXML(getPath());
    }

    private String getPath() {
        String path = Input.getString("Введите путь к файлу для экспорта");
        if (new File(path).exists())
            return path;

        System.out.println("Файл не найден! Повторите ввод.");
        return getPath();
    }
}
