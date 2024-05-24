
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CopyModule {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //instantiate main class
        CopyModule ap = new CopyModule();
        //create test data
        List<Module> originalData = ap.loadData();
        //display original data
        System.out.println("Original data");
        ap.displayList(originalData);
        //ask file name
        System.out.print("Input public data file name: ");
        String publicFilename = scan.next();

        //write data to file
        int elemsWritten = ap.writeToFile(publicFilename, originalData);
        //show the number of elements written by console
        //TO DO
        System.out.println("Number of bytes written: " + elemsWritten);
        //END TO DO
        //read data from file
        System.out.println("Read data");
        List<Module> readData = ap.readFromFile(publicFilename);
        //display read data
        ap.displayList(readData);
    }

    /**
     * displays a list of employee
     *
     * @param data the list of employee to display
     */
    public void displayList(List<Module> data) {
        for (Module t : data) {
            System.out.println(t);
        }
    }

    /**
     * writes employee data to two files (public and private data)
     *
     * @param filename
     * @param data the list to be written to file
     * @return the number of elements actually written to file
     */
    public int writeToFile(String filename, List<Module> data) {
        int counter = 0;
        //TODO write the list to file
        Scanner entrada = new Scanner(System.in);
        System.out.println("Quines assignatures vols gravar al fitxer? (GS/GM)");
        String opcio = entrada.nextLine();
        File file = new File(filename);
        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Module elem : data) {
                if (elem.getType().equalsIgnoreCase(opcio)) {
                    oos.writeObject(elem);
                    counter++;
                }
            }
            oos.flush();
            oos.close();
            System.out.println("S'han escrit " + counter + " mòduls de tipus " + opcio + " al fitxer " + filename);
        } catch (IOException ex) {
            System.out.println("Error al leer el fichero");
        }
        //END TODO
        return counter;
    }

    /**
     * reads a list of double from file
     *
     * @param filename the file name to read from
     * @return the list of double read from file
     */
    private List<Module> readFromFile(String pubFilename) {
        List<Module> data = new ArrayList<>();
        File file = new File(pubFilename);
        //comprovar si existe
        //si existe cargar al list
        //TODO read the list from file
        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            while (true) {
                Module module = (Module) ois.readObject();
                data.add(module);
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el fichero");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CopyModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //END TODO

        return data;
    }

    /**
     * loads test data
     *
     * @return list of double with some data
     */
    public List<Module> loadData() {
        List<Module> data = new ArrayList<>();
        data.add(new Module("M03", "ProgramaciÃ³", "GS", 99));
        data.add(new Module("M02", "Base dades", "GS", 297));
        data.add(new Module("M04", "Llenguatges", "GM", 198));
        data.add(new Module("M05", "xXXX", "GM", 200));
        data.add(new Module("id5", "name05", "GM", 200));
        data.add(new Module("id7", "name06", "GM", 170));
        return data;
    }

}
