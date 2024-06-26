package main.java.it.uniroma2.dicii.bd.controller;

import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;
import main.java.it.uniroma2.dicii.bd.model.User;
import main.java.it.uniroma2.dicii.bd.model.dao.LoginProcedureDAO;
import main.java.it.uniroma2.dicii.bd.view.LoginView;

import java.io.IOException;

public class LoginController implements Controller{

    private User usr;
    private LoginView loginView = new LoginView();

    @Override
    public void start() {
        try {
            usr = loginView.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            usr = new LoginProcedureDAO().execute(usr);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean retry() {
        return loginView.userChoice() == 1;
    }

    public User getUsr() {return usr;}

}
