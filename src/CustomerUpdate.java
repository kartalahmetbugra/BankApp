import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class CustomerUpdate extends DBcon {
    private int pw;

    public int getPw() {
        return pw;
    }

    public void setPw(int pw) {
        if (pw >= 999 && pw <= 9999) {
            this.pw = pw;
        } else {
            System.out.println("Hatalı Şifre Tanımı");
            sifre();

        }

    }

    public void sifre() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Şifrenizi Giriniz (4 haneli sayı):");
        setPw(scan.nextInt());
    }

    public void customerUpdate() throws SQLException, IOException {
        Connection c = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
        Statement st = c.createStatement();
        String url = "select * from customerbank Where CustomerID='" + customerNumber + "'";
        ResultSet rs = st.executeQuery(url);

        while (rs.next()) {
            setEmail(rs.getString("Email"));
            setPhoneNumber(rs.getString("PhoneNumber"));
            setWage(rs.getInt("Wage"));
        }

        System.out.println("Güncel email adresiniz: " + getEmail());
        System.out.println("Güncel telefon numaranız: " + getPhoneNumber());
        System.out.println("Güncel maaşınız: " + getWage());
        System.out.println("Güncel Limitiniz: " + 3 * getWage());
        Scanner scan = new Scanner(System.in);
        System.out.println("Hangi bilgileri güncellemek istiyorsunuz?\n1-TELEFON\n2-Mail\n3-Şifre\n4-Maaş\n5-Menü");
        int secim = scan.nextInt();

        switch (secim) {

            default:
                System.out.println("Hatalı tuşlama yaptınız.\nTekrar deneyiniz.");
                customerUpdate();
                break;

            case 1:
                System.out.println("Telefon Numaranızı giriniz: ");
                Scanner scanner = new Scanner(System.in);
                String telefonNo;
                telefonNo = scanner.nextLine();
                try {
                    Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                    Statement sta = d.createStatement();
                    String url2 = " UPDATE customerbank SET PhoneNumber='" + telefonNo + "'WHERE CustomerID='" + customerNumber + "'";
                    System.out.println("TEBRİKLER\nTelefon numaranızı güncellediniz ");
                    sta.executeUpdate(url2);
                } catch (Exception e) {
                    System.out.println("Güncelleme olmadı.");
                }
                customerUpdate();
                break;

            case 2:
                String mail;
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Mail adresinizi giriniz: ");
                mail = scanner2.nextLine();
                try {
                    Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                    Statement sta = d.createStatement();
                    String url3 = " UPDATE customerbank SET Email='" + mail + "'WHERE CustomerID='" + customerNumber + "'";
                    System.out.println("TEBRİKLER\nMail adresinizi güncellediniz");
                    sta.executeUpdate(url3);
                } catch (Exception e) {
                    System.out.println("Güncelleme olmadı.");
                }
                customerUpdate();
                break;

            case 3:
                sifre();

                try {
                    Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                    Statement sta = d.createStatement();
                    String url3 = " UPDATE customerbank SET Password='" + getPw() + "'WHERE CustomerID='" + customerNumber + "'";
                    System.out.println("TEBRİKLER\nŞifrenizi güncellediniz\nGüncel şifreniz:  " + getPw());
                    sta.executeUpdate(url3);
                } catch (Exception e) {
                    System.out.println("Güncelleme olmadı.");
                }
                customerUpdate();
                break;

            case 4:
                System.out.println("Maaşınızı giriniz: ");
                Scanner scanner5 = new Scanner(System.in);
                int maas;
                maas = scanner5.nextInt();
                try {
                    Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                    Statement sta = d.createStatement();
                    String url2 = " UPDATE customerbank SET Wage='" + maas + "'WHERE CustomerID='" + customerNumber + "'";
                    int limit = maas * 3;
                    String url6 = " UPDATE customerbank SET GuncelLimitt='" + limit + "'WHERE CustomerID='" + customerNumber + "'";
                    System.out.println("TEBRİKLER\nMaaşınızı güncellediniz ");
                    sta.executeUpdate(url2);
                    sta.executeUpdate(url6);
                } catch (Exception e) {
                    System.out.println("Güncelleme olmadı.");
                }
                customerUpdate();
                break;

            case 5:
                Menu menu = new Menu();
                menu.menu();
                break;
        }
    }
}