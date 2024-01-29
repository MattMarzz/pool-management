package main.java.it.uniroma2.dicii.bd.model.dao;

import main.java.it.uniroma2.dicii.bd.enums.Role;
import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;
import main.java.it.uniroma2.dicii.bd.model.User;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class UserDAO implements StdProcedureDAO<User>{


    @Override
    public User execute(Object... params) throws ItemNotFoundException {
        User usr = (User) params[0];
        int roleId = -1;

        try {
            Connection conn = DbConnection.getConnection();
            CallableStatement cs = conn.prepareCall("{call login(?,?,?)}");
            cs.setString(1, usr.getUsr());
            cs.setString(2, usr.getPwd());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();

            roleId = cs.getInt(3);
        } catch (SQLException e) {
            if(e.getErrorCode() == 1644) {
                throw new ItemNotFoundException(e.getMessage() + ": Credenziali errate!");
            } else {
                e.printStackTrace();
            }
        }

        if(roleId != -1)
            usr.setRole(Role.getRole(roleId));
        return usr;
    }
}
