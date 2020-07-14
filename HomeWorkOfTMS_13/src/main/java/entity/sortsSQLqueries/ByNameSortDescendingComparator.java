package entity.sortsSQLqueries;

import entity.Student;

import java.util.Comparator;

public class ByNameSortDescendingComparator implements SqlQueries {

    @Override
    public String getSQL() { return "SELECT s.id, s.name, s.email, avg(sm.mark) average_mark FROM " +
            "students s LEFT JOIN students_marks sm ON s.id = sm.students_id GROUP BY sm.students_id ORDER BY s.name DESC"; }

    @Override
    public String toString() {
        return "По имени, от \"Я\" до \"А\"";
    }
}
