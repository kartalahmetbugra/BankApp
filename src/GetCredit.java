import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class GetCredit extends DBcon {
    public void getCredit() throws SQLException, IOException {
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
        System.out.println("Ne kadar kredi çekmek istiyorsunuz? ");
        Scanner scan = new Scanner(System.in);
        double money = scan.nextDouble();

        if (getLimit() < money) {
            System.out.println("Limitinizden fazla para çekmeye çalışıyorsunuz lütfen tekrar deneyiniz...");
            getCredit();
        } else {
            setTrBalance(getTrBalance() + money);
            setDebt(getDebt() + money);
            setLimit(getLimit() - money);
            java.util.Date simdikiZaman = new Date();
            String x = simdikiZaman.toString();
            String islem19 = "\nGüncel hesap bakiyesi: " + getTrBalance() + "₺ ";
            String islem20 = "\nGüncel Borç " + getDebt() + "₺ ";
            String islem18 = "\nGüncel Limit " + getLimit() + "₺ ";
            File file = new File(customerNumber + ".txt");

            if (!file.exists()) {//oldu mu olmadı mı kontrol
                file.createNewFile();
            }

            FileWriter fWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.append(islem19 + x.substring(0, 19));
            bWriter.append(islem20 + x.substring(0, 19));
            bWriter.append(islem18 + x.substring(0, 19));
            bWriter.append("\n");
            bWriter.close();
        }

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
    }
}
