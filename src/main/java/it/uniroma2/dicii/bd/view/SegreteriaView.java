package main.java.it.uniroma2.dicii.bd.view;


import main.java.it.uniroma2.dicii.bd.model.Member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
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
                        "Visualizza membri iscritti ad un corso", "Effettua report", "Esci");
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

    public String getDesiredIn(String title, String inMsg) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        printHeader(title);

        System.out.print(inMsg);
        return reader.readLine();
    }

    public LocalDateTime getDateTimeFromUser(String msg) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime = null;
        while (dateTime == null) {
            try {
                System.out.print(msg);
                String input = scanner.nextLine();
                input = input.replace(' ', 'T');
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
            System.out.printf("%-" + columnWidths.get(i) + "s ", headers.get(i));
        }
        System.out.println();

        for (int width : columnWidths) {
            System.out.print("-".repeat(width) + " ");
        }
        System.out.println();

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
