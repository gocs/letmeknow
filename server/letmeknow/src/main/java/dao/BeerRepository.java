package dao;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import model.Beer;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BeerRepository extends CrudRepository<Beer, String> {
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    public List<Beer> findAll();
}