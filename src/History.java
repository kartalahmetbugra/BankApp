import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class History extends DBcon {
    public void historyPrint() throws IOException {
        File file = new File(customerNumber + ".txt");
        if (!file.exists()) {//oldu mu olmadı mı kontrol
            file.createNewFile();
        }
        FileReader fReader = new FileReader(file);
        String line;
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            System.out.println(line);
        }
        bReader.close();
    }
}
