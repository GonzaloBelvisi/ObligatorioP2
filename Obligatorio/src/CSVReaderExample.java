import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReaderExample {
    public static void main(String[] args) {
        String filePath = "f1_dataset_test.csv";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextRow;
            List<String> itemList = new ArrayList<>();

            reader.readNext(); //Salteamos la header line
            reader.readNext(); //Salteamos la header line

            while((nextRow = reader.readNext()) != null){
                    itemList.addAll(Arrays.asList(nextRow));
                System.out.println(itemList);
                break;
            }

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
