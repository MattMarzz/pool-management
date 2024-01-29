package main.java.it.uniroma2.dicii.bd.view;

public class TornelloSys extends TemplateView{
    @Override
    public int userChoice() {
        printHeader("SIMULATION OF GATE SYSTEM");
        return operationMenu("Operazioni: ",
                "Registra ccesso", "Esci");
    }

    public void printMsg(String msg) {
        System.out.println();
        System.out.println(msg);
    }
}
