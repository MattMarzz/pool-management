package main.java.it.uniroma2.dicii.bd.model.dao;

import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class ReportProcedureDAO implements StdProcedureDAO<int[]>{
    @Override
    public int[] execute(Object... params) {
        LocalDateTime startDate = (LocalDateTime) params[0];
        LocalDateTime endDate = (LocalDateTime) params[1];
        int[] array = new int[2];

        try {
            Connection conn = DbConnection.getConnection();
            CallableStatement cs = conn.prepareCall("{call report(?,?,?,?)}");

            cs.setTimestamp(1, Timestamp.valueOf(startDate));
            cs.setTimestamp(2, Timestamp.valueOf(endDate));

            cs.registerOutParameter(3, Types.INTEGER);
            cs.registerOutParameter(4, Types.INTEGER);

            cs.execute();
            //expected
            array[0] = cs.getInt(3);
            //effective
            array[1] = cs.getInt(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return array;
    }
}
