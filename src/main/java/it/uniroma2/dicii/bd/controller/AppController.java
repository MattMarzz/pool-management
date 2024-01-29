package main.java.it.uniroma2.dicii.bd.controller;

import main.java.it.uniroma2.dicii.bd.model.User;

public class AppController implements Controller{

    @Override
    public void start() {
        User authUsr;

        //authentication phase
        LoginController loginController = new LoginController();
        loginController.start();
        authUsr = loginController.getUsr();

        switch (authUsr.getRole()) {
            case SEGRETERIA -> new SegreteriaController().start();
            case TORNELLO -> new TornelloController().start();
            default -> throw new RuntimeException("Credenziali errate.");
        }

    }
}
