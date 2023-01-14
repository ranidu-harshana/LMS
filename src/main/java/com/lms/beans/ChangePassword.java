package com.lms.beans;

import com.lms.Helpers;

import java.io.Serializable;

public class ChangePassword implements Serializable {
    private int id;
    private String ppassword;
    private String password;
    private String cpassword;

    public ChangePassword() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPpassword() {
        return Helpers.getMd5(ppassword);
    }

    public void setPpassword(String ppassword) {
        this.ppassword = ppassword;
    }

    public String getPassword() {
        return Helpers.getMd5(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return Helpers.getMd5(cpassword);
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }
}
