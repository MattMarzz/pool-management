package main.java.it.uniroma2.dicii.bd.controller;

import main.java.it.uniroma2.dicii.bd.enums.Role;
import main.java.it.uniroma2.dicii.bd.exception.AccessDeniedException;
import main.java.it.uniroma2.dicii.bd.exception.NotValidDataException;
import main.java.it.uniroma2.dicii.bd.model.dao.AccessProcedureDAO;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;
import main.java.it.uniroma2.dicii.bd.view.TornelloSys;

import java.io.IOException;
import java.sql.SQLException;

public class TornelloController implements Controller{

    private TornelloSys tornelloSys = new TornelloSys();
    @Override
    public void start() {
        //first of all change db connection role
        try {
            DbConnection.changeRole(Role.TORNELLO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int choice;
        while (true) {
            choice = tornelloSys.userChoice();
            switch (choice) {
                case 1 -> registerAccess();
                case 2 -> {
                    DbConnection.closeConnection();
                    System.exit(0);
                }
            }
        }
    }

    private void registerAccess() {
        String badge;
        try {
            badge = tornelloSys.getDesiredIn("Leggi codice badge", "Inserisci codice letto: ");
        } catch (IOException e) {
            throw new NotValidDataException("Dati inseriti non validi: " + e.getMessage());
        }
        try {
            //gate unlock
            tornelloSys.printMsg(new AccessProcedureDAO().execute(badge));
        } catch (AccessDeniedException e) {
            //gate stay locked
            System.out.println(e.getMessage());
        }
    }
}
