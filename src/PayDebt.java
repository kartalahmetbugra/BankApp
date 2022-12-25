import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class PayDebt extends DBcon {
    public void payDebt() throws SQLException, IOException {
        Connection c = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
        Statement st = c.createStatement();
        String url = "select * from customerbank Where CustomerID='" + customerNumber + "'";
        ResultSet rs = st.executeQuery(url);
        while (rs.next()) {
            setTrBalance(rs.getDouble("TRBalance"));
            setDebt(rs.getDouble("Debt"));
            setLimit(rs.getDouble("GuncelLimitt"));
        }
        System.out.println("Hesap Bakiyeniz: " + getTrBalance());
        System.out.println("Mevcut Borc: " + getDebt());
        System.out.println("Mevcut Limit: " + getLimit());
        System.out.println("Ne kadar Ödemek istiyorsunuz? ");
        Scanner scan = new Scanner(System.in);
        double money = scan.nextDouble();
        setDebt(getDebt() - money);
        setTrBalance(getTrBalance() - money);
        setLimit(getLimit() + money);
        try {
            Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
            Statement sta = d.createStatement();
            String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
            String url3 = " UPDATE customerbank SET Debt='" + getDebt() + "'WHERE CustomerID='" + customerNumber + "'";
            String url4 = " UPDATE customerbank SET GuncelLimitt='" + getLimit() + "'WHERE CustomerID='" + customerNumber + "'";
            System.out.println("Güncel Bakiyeniz: " + getTrBalance());
            System.out.println("Güncel Borcunuz: " + getDebt());
            System.out.println("Güncel Limitimiz: " + getLimit());
            sta.executeUpdate(url2);
            sta.executeUpdate(url3);
            sta.executeUpdate(url4);
        } catch (Exception e) {
            System.out.println("Güncelleme olmadı.");
        }

        java.util.Date simdikiZaman = new Date();
        String x = simdikiZaman.toString();
        String islem15 = money + "₺ Borç ödendi ";
        String islem16 = "\nKalan hesap bakiyesi: " + getTrBalance() + "₺ ";
        String islem17 = "\nKalan Borç: " + getDebt();
        File file = new File(customerNumber + ".txt");
        if (!file.exists()) {//oldu mu olmadı mı kontrol
            file.createNewFile();
        }
        FileWriter fWriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        bWriter.append(islem15 + x.substring(0, 19));
        bWriter.append(islem16 + x.substring(0, 19));
        bWriter.append(islem17 + x.substring(0, 19));
        bWriter.append("\n");
        bWriter.close();
    }
}
