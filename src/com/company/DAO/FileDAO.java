package com.company.DAO;

import java.util.List;

public interface FileDAO<T> extends DAO<T> {
    void save(List<T> data);

    File getFile();
}
