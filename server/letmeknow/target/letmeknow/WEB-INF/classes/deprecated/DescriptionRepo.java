package deprecated;

import deprecated.Description;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by admin on 2017/7/11.
 */
public interface DescriptionRepo extends CrudRepository<Description,String> {
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    List<Description> findAll();
}
