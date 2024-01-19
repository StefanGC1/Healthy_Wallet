package com.example.healthy_wallet.ui.admin_scene_classes;

public class UserInfo {
    private String username;
    private int userID;
    private int amountInLastDay;

    public UserInfo(String _username, int _userID, int _amountInLastDay) {
        this.username = _username;
        this.userID = _userID;
        this.amountInLastDay = _amountInLastDay;
    }

    public String getUsername() { return username; }
    public int getUserID() { return userID; }
    public int getAmountInLastDay() { return amountInLastDay; }
}
