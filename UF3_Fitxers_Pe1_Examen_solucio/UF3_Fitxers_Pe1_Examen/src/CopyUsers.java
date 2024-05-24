
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CopyUsers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //instantiate main class
        CopyUsers ap = new CopyUsers();
        //create test data
        List<User> originalData = ap.loadData();
        //display original data
        //en original data tenemos todos los datos de los usuarios
        System.out.println("Original data");
        ap.displayList(originalData);
        //ask file name
        System.out.print("Input public data file name: ");
        String publicFilename = scan.next();
        System.out.print("Input private data file name: ");
        String privateFilename = scan.next();
        //write data to file
        int elemsWritten = ap.writeToFile(publicFilename, privateFilename, originalData);
        //TO DO show the numbers of items save
        System.out.println("Number of elements written to files: " + elemsWritten);
        //END TO DO

        //read data from file
        System.out.println("Read data");
        List<User> readData = ap.readFromFile(publicFilename, privateFilename);
        //display read data
        ap.displayList(readData);
    }

    /**
     * displays a list of User
     *
     * @param data the list of User to display
     */
    public void displayList(List<User> data) {
        for (User t : data) {
            System.out.println(t);
        }
    }

    /**
     * writes User data to two files (public and private data)
     *
     * @param pubFilename the file name to write public data
     * @param privFilename the file name to write private data
     * @param data the list to be written to file
     * @return the number of elements actually written to file
     */
    public int writeToFile(String pubFilename, String privFilename, List<User> data) {
        int counter = 0;
        //TODO write the list to file
        try {
            DataOutputStream dosPub = new DataOutputStream(new FileOutputStream(pubFilename));
            DataOutputStream dosPriv = new DataOutputStream(new FileOutputStream(privFilename));

            for (User user : data) {
                // Escribir datos públicos en un archivo
                dosPub.writeInt(user.getid());
                dosPub.writeUTF(user.getName());
                dosPriv.writeUTF(user.getpassword());
                dosPriv.writeDouble(user.getbalance());
                counter++;
            }
            dosPub.flush();
            dosPub.close();
            dosPriv.flush();
            dosPub.flush();
        } catch (IOException e) {
            e.printStackTrace();
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
    private List<User> readFromFile(String pubFilename, String privFilename) {
        List<User> data = null;
        File file1 = new File(pubFilename);
        File file2 = new File(privFilename);
        if (file1.exists() && file1.canRead() && file2.exists() && file2.canRead()) {
            data = new ArrayList<>();
            //TODO read the list from file
            try {
                DataInputStream disPub = new DataInputStream(new FileInputStream(pubFilename));
                DataInputStream disPriv = new DataInputStream(new FileInputStream(privFilename));
                while (disPub.available() > 0 && disPriv.available() > 0) {
                    int id=disPub.readInt();
                    String name = disPub.readUTF();
                    String passwd = disPriv.readUTF();
                    double balance = disPriv.readDouble();
                    data.add(new User(id,name,passwd,balance));
                }
                disPub.close();
                disPriv.close();
                //END TODO
            } catch (IOException ex) {
                Logger.getLogger(CopyUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
            //END TODO
        }
        return data;
    }

    /**
     * loads test data
     *
     * @return list of double with some data
     */
    public List<User> loadData() {
        List<User> data = new ArrayList<>();
        data.add(new User(1, "name01", "PW21", 1001.0));
        data.add(new User(2, "name02", "PW22", 1002.0));
        data.add(new User(3, "name03", "PW22", 1003.0));
        data.add(new User(4, "name04", "PW23", 1004.0));
        data.add(new User(5, "name05", "PW37", 1005.0));
        data.add(new User(6, "name06", "PW26", 1006.0));
        return data;
    }

}
