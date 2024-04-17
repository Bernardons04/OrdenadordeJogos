package packageLeitor;

import com.mycompany.main.Item;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVLeitor {
    private static final String path = 
            "./src/main/java/packageLeitor/JogosDesordenados.csv";

    public static List<Item> readCSV() {
        List<Item> itemList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String jogos = data[0].trim();
                    String categoria = data[1].trim();
                    double avaliacao = Double.parseDouble(data[2].trim());
                    itemList.add(new Item(jogos, categoria, avaliacao));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return itemList;
    }
}
