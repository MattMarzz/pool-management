package main.java.it.uniroma2.dicii.bd.controller;

import main.java.it.uniroma2.dicii.bd.enums.Role;
import main.java.it.uniroma2.dicii.bd.exception.ItemNotFoundException;
import main.java.it.uniroma2.dicii.bd.exception.NotValidDataException;
import main.java.it.uniroma2.dicii.bd.model.Course;
import main.java.it.uniroma2.dicii.bd.model.Lesson;
import main.java.it.uniroma2.dicii.bd.model.Member;
import main.java.it.uniroma2.dicii.bd.model.dao.*;
import main.java.it.uniroma2.dicii.bd.utils.DbConnection;
import main.java.it.uniroma2.dicii.bd.view.SegreteriaView;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
                case 5 -> insertCourse();
                case 6 -> insertLessons();
                case 7 -> getAllCourses();
                case 8 -> getAllLessons();
                case 9 -> {
                    DbConnection.closeConnection();
                    System.exit(0);
                }
            }
        }
    }

    private void insertMember() {
        Member mbr;
        try {
            mbr = segreteriaView.memberForm();
        } catch (IOException e) {
            throw new NotValidDataException("Dati inseriti non validi: " + e.getMessage());
        }
        System.out.println(new InsertMemberProcedureDAO().execute(mbr));
    }

    private void insertCourse() {
        Course crs;
        try {
            crs = segreteriaView.courseForm();
        } catch (IOException e) {
            throw new NotValidDataException("Dati inseriti non validi: " + e.getMessage());
        }
        System.out.println(new InsertCourseProcedureDAO().execute(crs));
    }

    private void insertLessons() {
        Lesson lsn;
        try {
            lsn = segreteriaView.lessonForm();
        } catch (IOException e) {
            throw new NotValidDataException("Dati inseriti non validi: " + e.getMessage());
        }
        System.out.println(new InsertLessonsProcedureDAO().execute(lsn));
    }

    private void getCoursesByMember() {
        String cf;
        try {
            cf = segreteriaView.getDesiredIn("Visualizza iscrizioni", "Inserisci codice fiscale: ");
        } catch (IOException e) {
            throw new NotValidDataException("Dati inseriti non validi: " + e.getMessage());
        }
        try {
            segreteriaView.printTable(new ViewSubscriptionsProcedureDAO().execute(cf));
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getMembersByCourse() {
        String courseName;
        try {
            courseName = segreteriaView.getDesiredIn("Visualizza iscritti", "Inserisci nome corso: ");
        } catch (IOException e) {
            throw new NotValidDataException("Dati inseriti non validi: " + e.getMessage());
        }
        try {
            segreteriaView.printTable(new ViewSubscribersProcedureDAO().execute(courseName));
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateReport() {
        LocalDateTime startDate = segreteriaView.getDateTimeFromUser("Inserire data di inizio periodo (yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime endDate = segreteriaView.getDateTimeFromUser("Inserire data di fine periodo (yyyy-MM-dd HH:mm:ss) ");
        int[] v = new ReportProcedureDAO().execute(startDate, endDate);
        double ratio = ((double) v[1] / v[0]) * 100;
        segreteriaView.printReport(v[0], v[1], ratio);
    }

    private void getAllCourses() {
        segreteriaView.printTable(new ViewCoursesProcedureDAO().execute());
    }

    private void getAllLessons() {
        segreteriaView.printTable(new ViewLessonsProcedureDAO().execute());
    }
}
