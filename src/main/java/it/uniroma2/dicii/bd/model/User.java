package main.java.it.uniroma2.dicii.bd.model;

import main.java.it.uniroma2.dicii.bd.enums.Role;

public class User {

    private String usr;
    private String pwd;
    private Role role;

    public User() {
    }

    public User(String usr, String pwd, Role role) {
        this.usr = usr;
        this.pwd = pwd;
        this.role = role;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
