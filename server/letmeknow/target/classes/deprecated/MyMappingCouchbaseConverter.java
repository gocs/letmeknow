package deprecated;

import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;
import org.springframework.data.couchbase.core.mapping.CouchbaseDocument;
import org.springframework.data.couchbase.core.mapping.CouchbasePersistentEntity;
import org.springframework.data.couchbase.core.mapping.CouchbasePersistentProperty;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.util.TypeInformation;

/**
 * Created by admin on 2017/7/19.
 */
public class MyMappingCouchbaseConverter extends MappingCouchbaseConverter {
    MyMappingCouchbaseConverter(MappingContext<? extends CouchbasePersistentEntity<?>, CouchbasePersistentProperty> mappingContext) {
        super(mappingContext);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <R> R read(final TypeInformation<R> type, final CouchbaseDocument source, final Object parent) {
        if (Object.class == typeMapper.readType(source, type).getType()) {
            return (R) source.export();
        } else {
            return super.read(type, source, parent);
        }
    }
}
