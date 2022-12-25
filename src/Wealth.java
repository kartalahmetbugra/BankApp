import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Wealth extends DBcon {
    public void wealth() throws SQLException, IOException {
        Connection c = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
        Statement st = c.createStatement();
        String url = "select * from customerbank Where CustomerID='" + customerNumber + "'";
        ResultSet rs = st.executeQuery(url);
        while (rs.next()) {
            setTrBalance(rs.getDouble("TRBalance"));
            setEuroBalance(rs.getDouble("EuroBalance"));
            setDlrBalance(rs.getDouble("DlrBalance"));
            setGoldBalance(rs.getDouble("GldBalance"));
            setStrBalance(rs.getDouble("StrBalance"));
            setDebt(rs.getDouble("Debt"));
            setLimit(rs.getDouble("GuncelLimitt"));
            setWage(rs.getDouble("Wage"));
        }
        showWealthInfos();
        System.out.println("Menuye dönmek için 1'e basın");
        Scanner scan = new Scanner(System.in);
        int menu = scan.nextInt();
        if (menu == 1) {
            Menu menu1 = new Menu();
            menu1.menu();
        } else {
            System.out.println("HATALI TUŞALAMA YAPTINIZ TEKRAR DENEYİNİZ");
            wealth();
        }
    }

    public void showWealthInfos() {//Hesaptaki bakiyeleri yazdırdık
        for (int i = 0; i < 10; i++) {
            System.out.print(" ");
        }
        System.out.println("NET VARLIKLARINIZ");
        System.out.println("TRY: " + getTrBalance() + " ₺");
        System.out.println("EURO: " + getEuroBalance() + " €");
        System.out.println("DLR: " + getDlrBalance() + " $");
        System.out.println("STR: " + getStrBalance() + " £");
        System.out.println("ALTIN: " + getGoldBalance() + " XAUTRY");
        System.out.println("BORÇ: " + getDebt() + " ₺");
    }
}
