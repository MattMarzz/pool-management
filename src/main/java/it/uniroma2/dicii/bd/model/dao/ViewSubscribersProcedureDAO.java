package main.java.it.uniroma2.dicii.bd.model.dao;

import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;
import main.java.it.uniroma2.dicii.bd.model.Member;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewSubscribersProcedureDAO implements StdProcedureDAO<List<Member>> {
    @Override
    public List<Member> execute(Object... params) throws ItemNotFoundException {
        String courseName = (String) params[0];
        List<Member> members = new ArrayList<>();

        try {
            Connection conn = DbConnection.getConnection();
            CallableStatement cs = conn.prepareCall("{call lista_membri_corso(?)}");
            cs.setString(1, courseName);
            ResultSet resultSet = cs.executeQuery();

            if(!resultSet.next()) return members;
            do {
                Member m = new Member();
                m.setCf(resultSet.getString("cf"));
                m.setName(resultSet.getString("nome"));
                m.setSurname(resultSet.getString("cognome"));
                m.setCap(resultSet.getString("cap"));
                m.setAdd(resultSet.getString("via"));
                m.setN(resultSet.getInt("civico"));
                m.setCell(resultSet.getString("cellulare"));
                m.setTel(resultSet.getString("telefono"));
                m.setEmail(resultSet.getString("email"));
                members.add(m);
            } while (resultSet.next());

        } catch (SQLException e) {
            if(e.getErrorCode() == 1644) {
                throw new ItemNotFoundException(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
        return members;
    }
}
