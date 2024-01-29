package main.java.it.uniroma2.dicii.bd.controller;

import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;
import main.java.it.uniroma2.dicii.bd.model.User;
import main.java.it.uniroma2.dicii.bd.model.dao.UserDAO;
import main.java.it.uniroma2.dicii.bd.view.LoginView;

import java.io.IOException;

public class LoginController implements Controller{

    private User usr;

    @Override
    public void start() {
        LoginView loginView = new LoginView();

        try {
            usr = loginView.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            usr = new UserDAO().execute(usr);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUsr() {return usr;}


}
