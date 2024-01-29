package main.java.it.uniroma2.dicii.bd.view;

import main.java.it.uniroma2.dicii.bd.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginView extends TemplateView{

    public User show() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printHeader("Benvenuto nel sistema di login");

        System.out.print("Inserisci username: ");
        String usr = reader.readLine();

        System.out.print("Inserisci password: ");
        String pwd = reader.readLine();

        return new User(usr, pwd);
    }

    @Override
    public int userChoice() {
       return operationMenu("Come vuoi procedere?",
                                    "Riprova", "Esci");
    }
}
