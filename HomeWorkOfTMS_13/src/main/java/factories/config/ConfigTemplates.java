package factories.config;

import entity.templates.AddScholarshipTemplate;
import entity.templates.OutFromUniversityTemplate;
import entity.templates.Template;
import entity.templates.WithoutChangesTemplate;

import java.util.HashMap;
import java.util.Map;

public class ConfigTemplates {
    public static Map<Integer, Template> templates = new HashMap<>();

    static {
        templates.put(0, new OutFromUniversityTemplate());
        templates.put(4, new WithoutChangesTemplate());
        templates.put(8, new AddScholarshipTemplate());
    }
}
