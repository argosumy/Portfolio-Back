package com.portfolio.helpers.repository_helpers;

import com.portfolio.helpers.repository_helpers.repository_convertors.RepositoryFieldConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Component
public class SqlBuilder {
    public String buildSql(String sqlTemplate, Object object, RepositoryFieldConverter converter) {
        final Field[] fields = object.getClass().getDeclaredFields();
        final StringBuilder sql = new StringBuilder();
        String id = "";
        for(Field field : fields) {
            try {
                field.setAccessible(true);
                if(field.get(object) != null
                        && !field.get(object).toString().isEmpty()
                        && !Modifier.isStatic(field.getModifiers())
                ) {
                    String name = field.getName();
                    if(name.equals("id") ) {
                        id = field.get(object).toString();
                    } else {
                        String value = field.get(object).toString();
                        sql.append(converter.convertToFieldNameDB(name))
                                .append("=").append("'")
                                .append(value).append("'")
                                .append(", ");
                    }
                }
            } catch (IllegalAccessException ignored) {
            }
        }
        final String part = sql.toString().replaceAll(", $", " ");
        return part.isEmpty() ? part : String.format(sqlTemplate, part, id);
    }
}
