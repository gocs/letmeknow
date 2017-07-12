package org.gocs.letmeknow;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.gocs.letmeknow.testmodel.Phone;
import org.gocs.letmeknow.testmodel.User;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dynamicheart on 7/10/2017.
 */

@Ignore
public class JacksonMapperTest {

    @Test
    public void printMappingResult(){
        ObjectMapper mapper = new ObjectMapper();

        //Convert POJO to map
        Phone phone1 = new Phone();
        phone1.setNumber("110");
        Phone phone2 = new Phone();
        phone2.setNumber("120");
        Map<String,Object> phones = new HashMap<>();
        phones.put("phone1",phone1);
        phones.put("phone2",phone2);

        User user = new User();
        user.setPhones(phones);

        Map<String, Object> map = mapper.convertValue(user, new TypeReference<Map<String, Object>>() {});
        System.out.print(map);
    }

}
