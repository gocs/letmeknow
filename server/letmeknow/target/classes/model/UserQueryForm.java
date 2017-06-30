package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
public class UserQueryForm {
    private String username;
    private Integer userId;

    public String getUsername() {
        return username;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserQueryForm(User user) {
        this.username = user.getUsername();
        this.userId = user.getUser_id();
    }

    public static List<UserQueryForm> convertToForm(List<User> users) {
        List<UserQueryForm> forms = new ArrayList<UserQueryForm>();
        for (User user : users) forms.add(new UserQueryForm(user));
        return forms;
    }
}
