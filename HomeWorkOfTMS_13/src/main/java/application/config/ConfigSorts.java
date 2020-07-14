package application.config;


import entity.Student;
import entity.sortsSQLqueries.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ConfigSorts {
    public static Map<Integer, SqlQueries> sorts = new HashMap<>();

    static {
        sorts.put(1, new ByNameSortAscendingComparator());
        sorts.put(2, new ByNameSortDescendingComparator());
        sorts.put(3, new ByAverageMarkSortAscendingComparator());
        sorts.put(4, new ByAverageMarkSortDescendingComparator());
        sorts.put(5, new ByEmailSortAscendingComparator());
        sorts.put(6, new ByEmailSortDescendingComparator());
    }
}
