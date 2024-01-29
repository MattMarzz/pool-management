package main.java.it.uniroma2.dicii.bd.controller;

import main.java.it.uniroma2.dicii.bd.enums.Role;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.SQLException;

public class SegreteriaController implements Controller{
    @Override
    public void start() {
        //first of all change db connection role
        try {
            DbConnection.changeRole(Role.SEGRETERIA);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
    }
}
