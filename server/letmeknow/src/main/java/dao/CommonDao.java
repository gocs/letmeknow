package dao;

import java.io.Serializable;

public interface CommonDao<T> {
    public void save(T t);
    public void delete(T object);
    public void update(T object);
}