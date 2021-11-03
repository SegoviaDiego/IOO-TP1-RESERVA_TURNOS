package com.company.DAO;

import java.lang.reflect.Field;
import java.util.List;

public interface DAO<T> {

    static void setFields(Object from, Object to) {
        Field[] fields = from.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                Field fieldFrom = from.getClass().getDeclaredField(field.getName());
                Object value = fieldFrom.get(from);
                to.getClass().getDeclaredField(field.getName()).set(to, value);

            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    List<T> findAll();

    T findById(long id);

    void create(T t);

    void update(T t);

    void delete(T t);
}
