import Entidades.Tweet;
import Entidades.User;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import uy.edu.um.prog2.adt.Hash.MyClosedHashImpl;
import uy.edu.um.prog2.adt.LinkedList.MyLinkedListImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {
    public static void main(String[] args) {
        String filePath1 = "CSV/src/Recursos/drivers.txt";
        String filePath2 = "CSV/src/Recursos/f1_dataset_test.csv";

        MyLinkedListImpl<String> activePilots = new MyLinkedListImpl<>();
        MyClosedHashImpl<String, User> userRegistry = new MyClosedHashImpl<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                activePilots.add(line);
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CSVReader reader = new CSVReader(new FileReader(filePath2))) {
            String[] nextRow;
            reader.readNext(); // Skip the header line

            while ((nextRow = reader.readNext()) != null) {
                User user = new User(Long.parseLong(nextRow[0]), nextRow[1]);
                Tweet tweet = new Tweet(Long.parseLong(nextRow[0]), nextRow[10], nextRow[11], Boolean.parseBoolean(nextRow[13]), user);
                System.out.println("|" + tweet.getContent() + "-|" + tweet.getSource() + "|" + tweet.getId() + "|" + tweet.isRetweet());
                System.out.println(user.getId());
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }}

    /*public void registerUser(long id,String name){
        //if ()

            User user = new User(Long.parseLong(nextRow[0]), nextRow[1]);
    }
}
*/


