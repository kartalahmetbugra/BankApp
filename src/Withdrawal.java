import java.sql.*;
import java.util.Scanner;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Withdrawal extends DBcon {

    Scanner scan = new Scanner(System.in);

    WithdrawalPrint withdrawal;

    public void pinfo() { // interface ve polyymoprhism
        withdrawal.pinfo();// interface ve polyymoprhism
    }

    public void paraCek() throws SQLException, IOException {
        Withdrawal withdrawal = new Withdrawal(); // interface ve polyymoprhism
        withdrawal.withdrawal = new WithdrawalPrint();

        Scanner scan = new Scanner(System.in);
        Connection c = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
        Statement st = c.createStatement();
        String url = "select * from customerbank Where CustomerID='" + customerNumber + "'";
        ResultSet rs = st.executeQuery(url);
        while (rs.next()) {
            setTrBalance(rs.getDouble("TRBalance"));
            setDebt(rs.getDouble("Debt"));
            setLimit(rs.getDouble("GuncelLimitt"));
        }
        System.out.println("Bakiyeniz: "+getTrBalance());
        System.out.println("\nNe kadar çekmek istersiniz? ");
        double money = scan.nextDouble();
        if (money > getTrBalance()) {
            getTrBalance();
            System.out.println("Hesaptaki para: " + getTrBalance());
            System.out.println("Hesaptaki paradan fazlasını çekiyorsunuz hesabınız eksiye düşecek devam etmek istiyor musunuz?");
            System.out.println("Devam etmek istiyorsanız 1" + "\nDevam etmek etmek istemiyorsanız 2");
            int a = scan.nextInt();
            if (a == 1) {
                setDebt(getDebt() + money - getTrBalance());//mevcut borcun üstüne borç ekledik
                setTrBalance(0);
                if (getDebt() < getLimit()) {
                    System.out.println("Borcunuz: " + getDebt());
                    System.out.println("Bakiyeniz:" + 0);
                    setLimit(getLimit() - getDebt());
                    try {
                        Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                        Statement sta = d.createStatement();
                        String url4 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                        System.out.println("Güncel Bakiyeniz: " + getTrBalance());
                        String url3 = " UPDATE customerbank SET Debt='" + getDebt() + "'WHERE CustomerID='" + customerNumber + "'";
                        String url5 = " UPDATE customerbank SET GuncelLimitt='" + getLimit() + "'WHERE CustomerID='" + customerNumber + "'";
                        sta.executeUpdate(url4);
                        sta.executeUpdate(url3);
                        sta.executeUpdate(url5);
                        withdrawal.pinfo();// interface ve polyymoprhism
                    } catch (Exception e) {
                        System.out.println("Güncelleme olmadı.");
                    }
                    Date simdikiZaman = new Date();
                    String x = simdikiZaman.toString();
                    String islem = "Hesaptan " + money + "₺ çekildi ";
                    String islem2 = "\nKalan hesap bakiyesi: " + getTrBalance() + "₺ ";
                    String islem3 = "\nGüncel borç: " + getDebt() + "₺ ";
                    String islem4 = "\nGüncel Limit: " + getLimit() + "₺ ";
                    File file = new File(customerNumber + ".txt");
                    if (!file.exists()) {//oldu mu olmadı mı kontrol
                        file.createNewFile();
                    }
                    FileWriter fWriter = new FileWriter(file, true);
                    BufferedWriter bWriter = new BufferedWriter(fWriter);
                    bWriter.append(islem + x.substring(0, 19));
                    bWriter.append(islem2 + x.substring(0, 19));
                    bWriter.append(islem3 + x.substring(0, 19));
                    bWriter.append(islem4 + x.substring(0, 19));
                    bWriter.append("\n");
                    bWriter.close();
                } else if (getLimit() < money) {
                    System.out.println("Limitiniz Yetersiz");
                }
            } else if (a == 2) {

            }
        } else {
            setTrBalance(getTrBalance() - money);
            try {
                Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                Statement sta = d.createStatement();
                String url5 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                System.out.println("Güncel Bakiyeniz: " + getTrBalance()+"\n");
                sta.executeUpdate(url5);
                withdrawal.pinfo();// interface ve polyymoprhism
            } catch (Exception e) {
                System.out.println("Güncelleme olmadı.");
            }
            Date simdikiZaman = new Date();
            String x = simdikiZaman.toString();
            String islem = "Hesaptan " + money + "₺ çekildi ";
            String islem2 = "\nKalan hesap bakiyesi: " + getTrBalance() + "₺ ";
            File file = new File(customerNumber + ".txt");
            if (!file.exists()) {//oldu mu olmadı mı kontrol
                file.createNewFile();
            }
            FileWriter fWriter = new FileWriter(file, true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.append(islem + x.substring(0, 19));
            bWriter.append(islem2 + x.substring(0, 19));
            bWriter.append("\n");
            bWriter.close();
        }
    }

}
