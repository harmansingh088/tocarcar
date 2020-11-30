package services;

import models.User;

public class LoginUser {
    private static User loginUser;

    public static User getLoginUser() {
        return loginUser;
    }

    public static void setLoginUser(User loginUser) {
        LoginUser.loginUser = loginUser;
    }
}
