package dao;

import java.io.Serializable;
import java.util.List;

public interface CommonDao<T> {
    public void save(T t);
    public void delete(T object);
    public void update(T object);
}