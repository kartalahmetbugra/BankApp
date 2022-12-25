import java.sql.*;
import java.util.Scanner;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit extends DBcon {

    public Connection baglantı;

    public void Info() {
        System.out.println("Para yatırıldı");
    }

    public void deposit() throws SQLException, IOException {
        Date simdikiZaman = new Date();
        String x = simdikiZaman.toString();


        Scanner scan = new Scanner(System.in);
        Connection c = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
        Statement st = c.createStatement();
        String url = "select * from customerbank Where CustomerID='" + customerNumber + "'";
        ResultSet rs = st.executeQuery(url);

        while (rs.next()) {
            setTrBalance(rs.getDouble("TRBalance"));//Müşterinin hesabındaki bakiyesini çektik
        }
        System.out.println("Şuanki bakiyeniz: " + getTrBalance() + "\n");

        System.out.println("Yatırmak istediğiniz tutarı giriniz: ");
        double money = scan.nextDouble();
        setTrBalance(getTrBalance() + money);
        Info();//Info metodunu çağırdık
        System.out.println("Güncel Bakiyeniz: " + getTrBalance());


        try {
            Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
            Statement sta = d.createStatement();
            String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";//müşterinin bakiyesini databasede güncelledik
            sta.executeUpdate(url2);
        } catch (Exception e) {
            System.out.println("Güncelleme olmadı.");
        }

        File file = new File(customerNumber + ".txt");

        if (!file.exists()) {//oldu mu olmadı mı kontrol
            file.createNewFile();
        }

        String islem = "Hesaba " + money + "₺ yatırıldı ";
        FileWriter fWriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        bWriter.append(islem + x.substring(0, 19));
        bWriter.append("\n");
        bWriter.close();
    }
}