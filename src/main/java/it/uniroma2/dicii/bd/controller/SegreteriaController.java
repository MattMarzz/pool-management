package main.java.it.uniroma2.dicii.bd.controller;

import main.java.it.uniroma2.dicii.bd.enums.Role;
import main.java.it.uniroma2.dicii.bd.exception.NotValidDataException;
import main.java.it.uniroma2.dicii.bd.model.Member;
import main.java.it.uniroma2.dicii.bd.model.dao.MemberDAO;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;
import main.java.it.uniroma2.dicii.bd.view.SegreteriaView;

import java.io.IOException;
import java.sql.SQLException;

public class SegreteriaController implements Controller{

    private SegreteriaView segreteriaView = new SegreteriaView();
    @Override
    public void start() {
        //first of all change db connection role
        try {
            DbConnection.changeRole(Role.SEGRETERIA);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int choice;
        while (true) {
            choice = segreteriaView.userChoice();
            switch (choice) {
                case 1 -> insertMember();
                case 2 -> getCoursesByMember();
                case 3 -> getMembersByCourse();
                case 4 -> generateReport();
                case 5 -> System.exit(0);
            }
        }
    }

    public void insertMember() {
        Member mbr;

        try {
            mbr = segreteriaView.memberForm();
        } catch (IOException e) {
            throw new NotValidDataException("Dati inseriti non validi: " + e.getMessage());
        }

        System.out.println(new MemberDAO().execute(mbr));
    }

    public void getCoursesByMember() {}

    public void getMembersByCourse() {}

    public void generateReport() {}
}
