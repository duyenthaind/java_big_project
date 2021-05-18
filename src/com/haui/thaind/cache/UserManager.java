package com.haui.thaind.cache;

/**
 * @author duyenthai
 */
public class UserManager {
    private static User user;

    private UserManager(){
    }

    public static UserManager instance(){
        return new UserManager();
    }

    public String getUserName(){
        return user.getMaSV();
    }

    public static void init(String maSV){
        user = new User(maSV);
    }
}

class User{
    private String maSV;

    public User(String maSV){
        this.maSV = maSV;
    }

    public String getMaSV() {
        return maSV;
    }
}
