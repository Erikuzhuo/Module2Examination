package ContactsManagement.Data;

import ContactsManagement.Model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactFile {
    private static final String PARENT_PATH = "src/ContactsManagement/Data";
    public static void writeListToFile(List<Person> contacts) {
        File file = new File(PARENT_PATH,"contacts.csv");
        try {
            if(!file.exists()) {
                file.createNewFile();
                System.out.println("File does not exist. already created new file!");
            }else System.out.println("File already exist! Writing.....");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(contacts);
            System.out.println("File has been Updated");
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Person> readListFromFile() {
        List<Person> contactList = new ArrayList<>();
        File file = new File(PARENT_PATH,"contacts.csv");
        try {
            if(!file.exists()) {
                file.createNewFile();
                System.out.println("File does not exist. already created new file!");
            }else System.out.println("File already exist! Reading.......");
            if (file.length() > 0) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                contactList = (List<Person>) objectInputStream.readObject();
                objectInputStream.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return contactList;
    }
    public static void clearFile(){
        File file = new File("contacts.csv");
        try {
            FileOutputStream fileOutputStream   = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeByte(0);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
