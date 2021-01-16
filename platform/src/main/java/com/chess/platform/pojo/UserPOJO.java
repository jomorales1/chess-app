package com.chess.platform.pojo;

import com.chess.platform.model.User;

public class UserPOJO extends User {
    public static boolean isRequestInfoValid(UserPOJO userPOJO) {
        if (userPOJO.getUsername().isEmpty() || userPOJO.getEmail().isEmpty() ||
            userPOJO.getName().isEmpty() || userPOJO.getPassword().isEmpty())
            return false;
        return true;
    }

    public static User mapRequestEntity(UserPOJO userPOJO) {
        User user = new User();
        user.setUsername(userPOJO.getUsername());
        user.setEmail(userPOJO.getEmail());
        user.setName(userPOJO.getName());
        user.setPassword(userPOJO.getPassword());
        return user;
    }
}
