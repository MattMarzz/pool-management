package main.java.it.uniroma2.dicii.bd.model.dao;

import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;
import main.java.it.uniroma2.dicii.bd.model.Course;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertCourseProcedureDAO implements StdProcedureDAO<String> {


    @Override
    public String execute(Object... params) {
        Course crs = (Course) params[0];
        try {
            CallableStatement cs = getCallableStatement(crs);

            cs.execute();
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062)
                return "Corso già esistente!";
            e.printStackTrace();
            return "Impossibile inserire corso!";
        }

        return "Inserimento avvenuto correttamente.";
    }

    private static CallableStatement getCallableStatement(Course crs) throws SQLException {
        Connection conn = DbConnection.getConnection();
        CallableStatement cs = conn.prepareCall("{call inserisci_corso(?,?,?,?,?)}");
        cs.setString(1, crs.getName());
        cs.setDouble(2, crs.getCosto());
        cs.setDate(3, crs.getStartDate());
        cs.setInt(4, crs.getMinSub());
        cs.setInt(5, crs.getMaxSub());

        return cs;
    }

}
