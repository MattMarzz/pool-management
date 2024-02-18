package main.java.it.uniroma2.dicii.bd.model.dao;

import main.java.it.uniroma2.dicii.bd.exception.AccessDeniedException;
import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;
import main.java.it.uniroma2.dicii.bd.exception.NotValidDataException;
import main.java.it.uniroma2.dicii.bd.model.Member;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AddSubscriptionProcedureDAO implements StdProcedureDAO<String>{
    @Override
    public String execute(Object... params) {
        String cf = (String) params[0];
        String courseName = (String) params[1];

        try {
            Connection conn = DbConnection.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_iscrizione(?,?)}");
            cs.setString(1, cf);
            cs.setString(2, courseName);

            cs.execute();

        } catch (SQLException e) {
            if(e.getErrorCode() == 1644) {
                throw new NotValidDataException(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }

        return "Iscrizione aggiunta correttamente";
    }
}
