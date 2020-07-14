package application.action;

import application.config.ConfigSorts;
import application.utils.Input;
import entity.Student;
import entity.sortsSQLqueries.SqlQueries;

import java.util.Comparator;
import java.util.Map;

public class ShowSortedStudentsAction extends BaseAction implements Action{

    @Override
    public String getName() {
        return "Вывод всех студентов в отсортированом виде";
    }

    @Override
    public void action() {
        showList(studentController.getSortedList(getSqlQuery()));
    }



    private void showSortsCase() {
        for (Map.Entry<Integer, SqlQueries> sort : ConfigSorts.sorts.entrySet())
            System.out.println(sort.getKey() + " - " + sort.getValue());
    }

    private String getSqlQuery() {
        showSortsCase();
        int index = Input.getInt("Выберите вариант сортировки");
        SqlQueries query = ConfigSorts.sorts.get(index);

        if (query != null) return query.getSQL();

        System.out.println("Нет такого варианта. Повторите ввод.");
        return getSqlQuery();
    }
}
