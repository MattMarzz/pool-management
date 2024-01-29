package main.java.it.uniroma2.dicii.bd.model.dao;

import main.java.it.uniroma2.dicii.bd.exception.AccessDeniedException;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AccessProcedureDAO implements StdProcedureDAO<String>{
    @Override
    public String execute(Object... params) throws AccessDeniedException {
        String badgeCode = (String) params[0];
        try {
            Connection conn = DbConnection.getConnection();
            CallableStatement cs = conn.prepareCall("{call ingresso_tornello(?)}");
            cs.setString(1, badgeCode);
            cs.execute();
        } catch (SQLException e) {
            if(e.getErrorCode() == 1644) {
                throw new AccessDeniedException(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
        return "Accesso effettuato!";
    }
}
