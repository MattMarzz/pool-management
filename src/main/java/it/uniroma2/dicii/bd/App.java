package main.java.it.uniroma2.dicii.bd;

import main.java.it.uniroma2.dicii.bd.controller.AppController;
import main.java.it.uniroma2.dicii.bd.controller.Controller;

public class App {

    public static void main(String[] args) {
        Controller controller = new AppController();
        controller.start();
    }
}