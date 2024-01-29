package main.java.it.uniroma2.dicii.bd.view;


import main.java.it.uniroma2.dicii.bd.model.Member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        if(cell.isBlank())
            mbr.setCell(null);
        else
            mbr.setCell(cell);

        System.out.print("Inserisci telefono: ");
        String tel = reader.readLine();
        if(cell.isBlank())
            mbr.setTel(null);
        else
            mbr.setTel(tel);

        System.out.print("Inserisci email: ");
        String email = reader.readLine();
        if(cell.isBlank())
            mbr.setEmail(null);
        else
            mbr.setEmail(email);

        System.out.print("Inserisci nome del corso a cui si iscrive: ");
        String corso = reader.readLine();
        mbr.setCourse(corso);


        return mbr;
    }

}
