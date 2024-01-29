package main.java.it.uniroma2.dicii.bd.view;


import main.java.it.uniroma2.dicii.bd.model.Member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
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

    public <T> void printTable(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("La lista è vuota.");
            return;
        }

        Method[] methods = list.get(0).getClass().getDeclaredMethods();

        List<Method> getters = filterGetters(methods);

        // table header
        for (Method getter : getters) {
            System.out.print(getter.getName().substring(3) + "\t");
        }
        System.out.println();


        for (Method getter : getters) {
            System.out.print("--------");
        }
        System.out.println();

        // rows
        for (T item : list) {
            for (Method getter : getters) {
                try {
                    System.out.print(getter.invoke(item) + "\t");
                } catch (Exception e) {
                    System.out.print("Errore" + "\t");
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
