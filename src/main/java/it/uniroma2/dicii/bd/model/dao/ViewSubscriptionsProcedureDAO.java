package main.java.it.uniroma2.dicii.bd.model.dao;

import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;
import main.java.it.uniroma2.dicii.bd.model.Course;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewSubscriptionsProcedureDAO implements StdProcedureDAO<List<Course>>{
    @Override
    public List<Course> execute(Object... params) throws ItemNotFoundException {
        String cf = (String) params[0];
        List<Course> courses = new ArrayList<>();

        try {
            Connection conn = DbConnection.getConnection();
            CallableStatement cs = conn.prepareCall("{call lista_corsi_membro(?)}");
            cs.setString(1, cf);
            ResultSet resultSet = cs.executeQuery();

            if(!resultSet.next()) return courses;
            do {
                Course c = new Course();
                c.setName(resultSet.getString("nome"));
                c.setCosto(resultSet.getDouble("costo"));
                c.setStartDate(resultSet.getDate("data_inizio"));
                c.setEndDate(resultSet.getDate("data_fine"));
                c.setMinSub(resultSet.getInt("minimo_iscritti"));
                c.setMaxSub(resultSet.getInt("massimo_iscritti"));
                c.setnSub(resultSet.getInt("numero_iscritti"));
                courses.add(c);
            } while (resultSet.next());

        } catch (SQLException e) {
            if(e.getErrorCode() == 1644) {
                throw new ItemNotFoundException(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
        return courses;
    }
}
