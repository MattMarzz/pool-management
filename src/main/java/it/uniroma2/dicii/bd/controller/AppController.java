package main.java.it.uniroma2.dicii.bd.controller;

import main.java.it.uniroma2.dicii.bd.model.User;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

public class AppController implements Controller{

    @Override
    public void start() {
        User authUsr;
        boolean loop_cond= false;
        LoginController loginController = new LoginController();

        do {
            loginController.start();
            authUsr = loginController.getUsr();
            if(authUsr.getRole() != null)
                loop_cond = false;
            else if (loginController.retry()) {
                loop_cond = true;
            } else {
                DbConnection.closeConnection();
                System.exit(0);
            }
        } while (loop_cond);

        switch (authUsr.getRole()) {
            case SEGRETERIA -> new SegreteriaController().start();
            case TORNELLO -> new TornelloController().start();
        }

    }
}
