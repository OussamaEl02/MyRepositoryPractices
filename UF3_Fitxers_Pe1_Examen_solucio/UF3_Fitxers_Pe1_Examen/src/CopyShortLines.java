/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author sergipons
 */
public class CopyShortLines {

    public static void main(String[] args) {
        
        int linesCopied=0;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Nom del fitxer a llegir: ");
        String inputFilename = entrada.nextLine();
        String outputFilename = inputFilename+"shortLines.txt";
        File file = new File(outputFilename);

        try{
            BufferedReader br = new BufferedReader(new FileReader(inputFilename));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename));
            
            String line;
                while ((line = br.readLine()) != null) {
                    if (line.length() < 5) {
                        bw.write(line);
                        bw.newLine();
                        linesCopied++;
                    }
                }
                bw.flush();
                bw.close();
            System.out.println("S'ha copiat correctament el fitxer. S'han copiat " + linesCopied + " línies.");
        } catch (IOException e) {
            System.err.println("Hi ha hagut un error en copiar el fitxer.");
            e.printStackTrace();
        }
    }
}