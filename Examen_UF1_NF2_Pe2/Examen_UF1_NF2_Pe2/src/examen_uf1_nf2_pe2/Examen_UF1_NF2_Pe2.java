/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen_uf1_nf2_pe2;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author sergipons
 */
public class Examen_UF1_NF2_Pe2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creem estructures
        String[][] drugNames = {
            {"Paracetamol", "Ibuprofeno", "Omeprazol", "Amoxicilina"},
            {"Loratadina", "Simvastatina", "Metformina", "Cetirizina"},
            {"Atorvastatina", "Aspirina", "Naproxeno", "Escitalopram"},
            {"Losartán", "Sildenafil", "Montelukast", "Clopidogrel"}
        };
        Double[][] drugPrices = {
            {9.50, 6.75, 12.99, 11.25},
            {5.80, 18.25, 7.99, 8.50},
            {15.75, 4.99, 7.25, 14.50},
            {10.80, 23.99, 9.75, 20.50}
        };
        Integer[][] drugStocks = {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
        };
        System.out.println("Taula medicaments");
        showDrugs(drugNames);
        System.out.println("Taula preus");
        showPrice(drugPrices);
        System.out.println("Taula stocks");
        showStock(drugStocks);
        boolean continueProgram = true;
        Double totalSold = 0.0;
        do {
            String[] prescriptions = readPrescription();
            String[] drugsSold = sellDrugs(drugNames, drugStocks, prescriptions);
            totalSold += generateTicket(drugNames, drugPrices, drugsSold);
            System.out.println("Taula medicaments");
            showDrugs(drugNames);
            System.out.println("Taula preus");
            showPrice(drugPrices);
            System.out.println("Taula stocks");
            showStock(drugStocks);
            continueProgram = confirm("Another client (y/n)?: ");
        } while (continueProgram);
        System.out.format("Total sold: %7.2f%n", totalSold);
    }

    public static boolean confirm(String message) {
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        char c = Character.toLowerCase(scan.next().charAt(0));
        return (c == 'y');
    }

    public static int[] searchDrug(String[][] drugs, String name) {
        int[] position = {-2, -2};
        for (int i = 0; i < drugs.length; i++) {
            for (int j = 0; j < drugs[0].length; j++) {
                if (drugs[i][j].equalsIgnoreCase(name)) {
                    position[0] = i;
                    position[1] = j;
                    break;
                }
            }
        }
        return position;
    }

    public static void showDrugs(String[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.format("\t%10s", table[i][j]);
            }
            System.out.println("");
        }
    }

    public static void showPrice(Double[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.format("\t%10.2f", table[i][j]);
            }
            System.out.println("");
        }
    }

    public static void showStock(Integer[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.format("\t%10d", table[i][j]);
            }
            System.out.println("");
        }
    }

    public static String[] readPrescription() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number of prescriptions: ");
        int n = sc.nextInt();
        String[] prescriptions = new String[n];
        for (int i = 0; i < prescriptions.length; i++) {
            System.out.format("Input prescription number %d: ", i);
            prescriptions[i] = sc.next();
        }
        return prescriptions;
    }

    public static String[] sellDrugs(String[][] drugs, Integer[][] stocks, String[] list) {
        String[] sold = new String[list.length];
        int counter = 0;
        for (String drug : list) {
            int[] pos = searchDrug(drugs, drug);
            int row = pos[0];
            int col = pos[1];
            if ((row >= 0) && (col >= 0)) {
                if (stocks[row][col] > 0) {
                    sold[counter] = drug;
                    stocks[row][col]--;
                    counter++;
                }
            }
        }
        return sold;
    }

    public static Double generateTicket(String[][] drugs, Double[][] prices, String[] sold) {
        StringBuilder sb = new StringBuilder();
        Double total = 0.0;
        sb.append("===Ticket===\n");
        for (String drug : sold) {
            int[] pos = searchDrug(drugs, drug);
            int row = pos[0];
            int col = pos[1];
            if ((row >= 0) && (col >= 0)) {
                Double price = prices[row][col];
                total += price;
                sb.append(String.format("%10s\t%7.2f%n", drug, price));
            }
        }
        sb.append(String.format("Total: %7.2f%n", total));
        System.out.println(sb.toString());
        return total;
    }

}
