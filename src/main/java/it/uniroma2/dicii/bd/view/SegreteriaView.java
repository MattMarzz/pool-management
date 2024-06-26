package main.java.it.uniroma2.dicii.bd.view;


import main.java.it.uniroma2.dicii.bd.exception.NotValidDataException;
import main.java.it.uniroma2.dicii.bd.model.Course;
import main.java.it.uniroma2.dicii.bd.model.Lesson;
import main.java.it.uniroma2.dicii.bd.model.Member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SegreteriaView extends TemplateView{
    @Override
    public int userChoice() {
        printHeader("SEGRETERIA ADMINISTRATION PANEL");
        return operationMenu("Che operazione desideri effettuare?",
                "Inserisci membro", "Visualizza le iscrizioni per membro",
                        "Visualizza membri iscritti ad un corso", "Effettua report",
                        "Inserisci corso","Inserisci lezioni", "Visualizza tutti i corsi",
                        "Visualizza tutti le lezioni", "Aggiungi iscrizione", "Esci");
    }

    public Member memberForm() throws IOException {
        Member mbr = new Member();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        printHeader("Inserisci informazioni membro");

        System.out.print("Inserisci codice fiscale: ");
        String cf = reader.readLine();
        mbr.setCf(cf);

        System.out.print("Inserisci nome: ");
        String name = reader.readLine();
        mbr.setName(name);

        System.out.print("Inserisci cognome: ");
        String srnm = reader.readLine();
        mbr.setSurname(srnm);

        System.out.print("Inserisci comune: ");
        String com = reader.readLine();
        mbr.setComune(com);

        System.out.print("Inserisci provincia: ");
        String prv = reader.readLine();
        mbr.setProvince(prv);

        System.out.print("Inserisci via: ");
        String via = reader.readLine();
        mbr.setAdd(via);

        System.out.print("Inserisci civico: ");
        String civ = reader.readLine();
        mbr.setN(Integer.parseInt(civ));

        System.out.print("Inserisci cap: ");
        String cap = reader.readLine();
        mbr.setCap(cap);

        System.out.print("Inserisci cellulare: ");
        String cell = reader.readLine();
        mbr.setCell(cell);

        System.out.print("Inserisci telefono: ");
        String tel = reader.readLine();
        mbr.setTel(tel);

        System.out.print("Inserisci email: ");
        String email = reader.readLine();
        mbr.setEmail(email);

        System.out.print("Inserisci nome del corso a cui si iscrive: ");
        String corso = reader.readLine();
        mbr.setCourse(corso);

        return mbr;
    }

    public Course courseForm() throws IOException {
        Course csr = new Course();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        printHeader("Inserisci informazioni corso");

        System.out.print("Inserisci nome: ");
        csr.setName(reader.readLine());

        System.out.print("Inserisci costo (xx.xx): ");
        csr.setCosto(Double.parseDouble(reader.readLine()));

        System.out.print("Inserisci data inizio (yyyy-MM-dd): ");
        csr.setStartDate(stringToDate(reader.readLine()));

        System.out.println("Inserisci numero minimo iscritti: ");
        csr.setMinSub(Integer.parseInt(reader.readLine()));

        System.out.println("Inserisci numero massimo iscritti: ");
        csr.setMaxSub(Integer.parseInt(reader.readLine()));

        return csr;
    }

    public Lesson lessonForm() throws IOException {
        Lesson lsn = new Lesson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        printHeader("Inserisci programmazione lezioni");

        System.out.print("Inserisci corso di riferimento: ");
        lsn.setCourse(reader.readLine());

        System.out.print("Inserisci la lista dei giorni settimanale separati da virgola\n" +
                "(p.e: monday,wednesday,friday): ");
        lsn.setCommaDays(reader.readLine());

        System.out.print("Inserisci la vasca: ");
        lsn.setTub(reader.readLine());

        System.out.print("Inserisci ora inizio (hh:mm:ss): ");
        lsn.setStartAt(stringToTime(reader.readLine()));

        System.out.print("Inserisci ora fine (hh:mm:ss): ");
        lsn.setEndAt(stringToTime(reader.readLine()));

        return lsn;
    }


    public Time stringToTime(String str) {
        String pattern = "HH:mm:ss";
        SimpleDateFormat timeFormat = new SimpleDateFormat(pattern);
        Time time = null;
        try {
            java.util.Date date = timeFormat.parse(str);

            time = new Time(date.getTime());
        } catch (ParseException e) {
            System.out.println("Utilizzare il formato 'yyyy-MM-dd.");
        }
        return time;
    }

    public Date stringToDate(String str) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            // Parsing della stringa in un oggetto Date
            date = new java.sql.Date(dateFormat.parse(str).getTime());
        } catch (ParseException e) {
            System.out.println("Utilizzare il formato 'yyyy-MM-dd.");
        }
        return date;
    }

    public LocalDateTime getDateTimeFromUser(String msg) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime = null;
        while (dateTime == null) {
            try {
                System.out.print(msg);
                String input = scanner.nextLine();
                dateTime = LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Utilizzare il formato 'yyyy-MM-dd HH:mm:ss'.");
            }
        }
        return dateTime;
    }

    public void printReport(int expected, int effective, double ratio) {
        System.out.println();
        System.out.println("Nella fascia temporale specificata si sono registrati " + effective +
                " accessi rispetto i " + expected + " previsti, ovvero il " + ratio + " %.");
    }

    public <T> void printTable(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("La lista è vuota.");
            return;
        }

        Method[] methods = list.get(0).getClass().getDeclaredMethods();

        List<Method> getters = filterGetters(methods);

        List<String> headers = new ArrayList<>();
        List<Integer> columnWidths = new ArrayList<>();

        // header and column weight
        for (Method getter : getters) {
            String header = getter.getName().substring(3);
            headers.add(header);
            int maxWidth = header.length();

            for (T item : list) {
                try {
                    String valueString = String.valueOf(getter.invoke(item));
                    maxWidth = Math.max(maxWidth, valueString.length());
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
            columnWidths.add(maxWidth);
        }

        // print table header
        for (int i = 0; i < headers.size(); i++) {
            System.out.printf("\033[35m%-" + columnWidths.get(i) + "s ", headers.get(i));
        }
        System.out.println();

        for (int width : columnWidths) {
            System.out.print("-".repeat(width) + " ");
        }
        System.out.println("\033[0m");

        // rows
        for (T item : list) {
            for (int i = 0; i < getters.size(); i++) {
                try {
                    String valueString = String.valueOf(getters.get(i).invoke(item));
                    System.out.printf("%-" + columnWidths.get(i) + "s ", valueString);
                } catch (Exception e) {
                    System.out.print("Errore" + " ".repeat(columnWidths.get(i) - 6));
                }
            }
            System.out.println();
        }
    }

    private static List<Method> filterGetters(Method[] methods) {
        return Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("get") && m.getParameterCount() == 0)
                .collect(Collectors.toList());
    }

}
