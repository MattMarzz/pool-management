package main.java.it.uniroma2.dicii.bd.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public abstract class TemplateView {

    public abstract int userChoice();

    protected void printHeader(String headerText) {
        int width = 50;
        String border = new String(new char[width]).replace("\0", "-");

        System.out.println();
        System.out.println(border);
        int leftPadding = (width - headerText.length()) / 2;
        for (int i = 0; i < leftPadding; i++) {
            System.out.print(" ");
        }
        System.out.println(headerText);
        System.out.println(border);
    }

    protected int operationMenu(String title, String... options) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println();
        System.out.println("--- " + title + " ---");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i]);
        }

        do {
            System.out.println();
            System.out.print("Scegli un'opzione (1-" + options.length + "): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > options.length) {
                    System.out.println("Per favore inserisci un numero tra 1 e " + options.length);
                    choice = 0;
                }
            } else {
                System.out.println("Input non valido. Per favore inserisci un numero.");
                scanner.next();
            }
        } while (choice == 0);
        return choice;
    }

    public String getDesiredIn(String title, String inMsg) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        printHeader(title);

        System.out.print(inMsg);
        return reader.readLine();
    }

}
