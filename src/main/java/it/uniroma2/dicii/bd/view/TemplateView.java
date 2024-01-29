package main.java.it.uniroma2.dicii.bd.view;

public class TemplateView {

    protected void printHeader(String headerText) {
        int width = 50;
        String border = new String(new char[width]).replace("\0", "-");

        System.out.println(border);
        int leftPadding = (width - headerText.length()) / 2;
        for (int i = 0; i < leftPadding; i++) {
            System.out.print(" ");
        }
        System.out.println(headerText);
        System.out.println(border);
    }
}
