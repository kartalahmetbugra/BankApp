import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class SendMoney extends DBcon {

    SendMoneyPrint sendMoney;

    public void pinfo() { // interface ve polyymoprhism
        sendMoney.pinfo();// interface ve polyymoprhism
    }

    public void sendMoney() throws SQLException, IOException {
        SendMoney sendMoney = new SendMoney(); // interface ve polyymoprhism
        sendMoney.sendMoney = new SendMoneyPrint();

        double trBalance2 = 0;
        Connection c = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
        Statement st = c.createStatement();
        String url = "select * from customerbank Where CustomerID='" + customerNumber + "'";
        ResultSet rs = st.executeQuery(url);
        while (rs.next()) {
            setTrBalance(rs.getDouble("TRBalance"));
        }
        System.out.println("Hesap Bakiyeniz: " + getTrBalance());
        System.out.println("Para göndermek istediğiniz hesabın müşteri numarasını giriniz:");
        Scanner scan = new Scanner(System.in);
        int customernumber2 = scan.nextInt();
        String url8 = "select * from customerbank Where CustomerID='" + customernumber2 + "'";
        ResultSet rs2 = st.executeQuery(url8);
        while (rs2.next()) {
            trBalance2 = rs2.getDouble("TRBalance");
        }
        System.out.println("Göndermek istediğiniz miktarı giriniz: ");
        double money = scan.nextDouble();
        if (getTrBalance() < money) {
            System.out.println("Hesabınızdaki paradan daha fazlasını gönderemezsiniz lütfen tekrar deneyiniz...");
            sendMoney();
        } else {
            setTrBalance(getTrBalance() - money);
            double trBalance3 = trBalance2 + money;
            java.util.Date simdikiZaman = new Date();
            String x = simdikiZaman.toString();
            // String islem22 = "\nHesaptan "+money+"₺ çekildi ";
            String islem23 = "\nKalan hesap bakiyesi: " + getTrBalance() + "₺ ";
            String islem21 = "\n" + customernumber2 + " Numaralı hesaba " + money + "₺ aktarıldı ";
            File file = new File(customerNumber + ".txt");
            if (!file.exists()) {//oldu mu olmadı mı kontrol
                file.createNewFile();
            }
            FileWriter fWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.append(islem21 + x.substring(0, 19));
            // bWriter.append(islem22 + x.substring(0,19));
            bWriter.append(islem23 + x.substring(0, 19));
            bWriter.append("\n");
            bWriter.close();
            try {
                Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                Statement sta = d.createStatement();
                String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                String url3 = " UPDATE customerbank SET TRBalance='" + trBalance3 + "'WHERE CustomerID='" + customernumber2 + "'";
                System.out.println("Güncel Bakiyeniz: " + getTrBalance());
                sta.executeUpdate(url2);
                sta.executeUpdate(url3);
                sendMoney.pinfo();// interface ve polyymoprhism
            } catch (Exception e) {
                System.out.println("Güncelleme olmadı.");
            }
        }
    }
}
