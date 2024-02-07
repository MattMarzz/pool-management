package main.java.it.uniroma2.dicii.bd.model.dao;

import main.java.it.uniroma2.dicii.bd.exception.AccessDeniedException;
import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;
import main.java.it.uniroma2.dicii.bd.model.Course;
import main.java.it.uniroma2.dicii.bd.model.Lesson;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewLessonsProcedureDAO implements StdProcedureDAO<List<Lesson>> {
    @Override
    public List<Lesson> execute(Object... params) {
        List<Lesson> lessons = new ArrayList<>();
        try {
            Connection conn = DbConnection.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_lezioni()}");
            ResultSet resultSet = cs.executeQuery();

            if(!resultSet.next()) return lessons;
            do {
                Lesson l = new Lesson();
                l.setCommaDays(resultSet.getString("giorno"));
                l.setStartAt(resultSet.getTime("ora_inizio"));
                l.setEndAt(resultSet.getTime("ora_fine"));
                l.setTub(resultSet.getString("vasca"));
                l.setCourse(resultSet.getString("corso"));
                lessons.add(l);
            } while (resultSet.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

}
