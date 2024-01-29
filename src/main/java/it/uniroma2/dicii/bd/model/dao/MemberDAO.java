package main.java.it.uniroma2.dicii.bd.model.dao;


import main.java.it.uniroma2.dicii.bd.exception.NotValidDataException;
import main.java.it.uniroma2.dicii.bd.model.Member;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class MemberDAO implements StdProcedureDAO<String>{
    @Override
    public String execute(Object... params) {
        Member mbr = (Member) params[0];
        String badge = "";

        try {
            CallableStatement cs = getCallableStatement(mbr);

            cs.execute();

            badge = cs.getString(13);
        } catch (SQLException e) {
            if(e.getErrorCode() == 1644) {
                throw new NotValidDataException(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }

        return "Inserimento avvenuto correttamente. Tessera membro associata: " + badge;
    }

    private static CallableStatement getCallableStatement(Member mbr) throws SQLException {
        Connection conn = DbConnection.getConnection();
        CallableStatement cs = conn.prepareCall("{call inserisci_membro(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        cs.setString(1, mbr.getCf());
        cs.setString(2, mbr.getName());
        cs.setString(3, mbr.getSurname());
        cs.setString(4, mbr.getCap());
        cs.setString(5, mbr.getComune());
        cs.setString(6, mbr.getProvince());
        cs.setString(7, mbr.getAdd());
        cs.setInt(8, mbr.getN());
        cs.setString(9, mbr.getCell());
        cs.setString(10, mbr.getTel());
        cs.setString(11, mbr.getEmail());
        cs.setString(12, mbr.getCourse());

        cs.registerOutParameter(13, Types.CHAR);
        return cs;
    }
}
