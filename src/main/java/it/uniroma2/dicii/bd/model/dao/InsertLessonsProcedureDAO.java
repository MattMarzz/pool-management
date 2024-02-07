package main.java.it.uniroma2.dicii.bd.model.dao;


import main.java.it.uniroma2.dicii.bd.exception.NotValidDataException;
import main.java.it.uniroma2.dicii.bd.model.Lesson;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertLessonsProcedureDAO implements StdProcedureDAO<String>{
    @Override
    public String execute(Object... params) {
        Lesson lsn = (Lesson) params[0];
        try {
            CallableStatement cs = getCallableStatement(lsn);

            cs.execute();

        } catch (SQLException e) {
            if(e.getErrorCode() == 1644) {
                throw new NotValidDataException(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }

        return "Inserimento avvenuto correttamente.";
    }

    private static CallableStatement getCallableStatement(Lesson lsn) throws SQLException {
        Connection conn = DbConnection.getConnection();
        CallableStatement cs = conn.prepareCall("{call inserisci_lezioni(?,?,?,?,?)}");
        cs.setString(1, lsn.getCourse());
        cs.setString(2, lsn.getCommaDays());
        cs.setTime(3, lsn.getStartAt());
        cs.setTime(4, lsn.getEndAt());
        cs.setString(5, lsn.getTub());

        return cs;
    }
}
