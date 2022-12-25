import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBcon extends CustomerInfos {
    public static final String ANSI_YEŞİL = "\u001B[32m";
    public static final String ANSI_MOR = "\u001B[35m";
    int sayac = 0;
    static int customerNumber;// diğer classlardan erişebilmek için static yaptık

    public void Info() {
        System.out.println("Başarıyla Giriş yaptınız.\n");
    }

    public void login() throws SQLException, IOException {
        int pw = 0;
        Scanner scan = new Scanner(System.in);

        System.out.print(ANSI_MOR + "\nHoşgeldiniz Lütfen Müşteri Numaranızı Giriniz: ");//Kullanıcıdan müşteri numarasını istedik
        customerNumber = scan.nextInt();
        System.out.print("\nŞifrenizi giriniz: ");//Kullanıcıdan şifresini istedik
        int girilemSifre = scan.nextInt();
        Connection c = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
        Statement st = c.createStatement();
        String url = "select * from customerbank Where CustomerID='" + customerNumber + "'";
        ResultSet rs = st.executeQuery(url);

        while (rs.next()) {
            pw = rs.getInt("Password");
            setNameSurname(rs.getString("Name_Surname"));
            setEmail(rs.getString("Email"));
            setPhoneNumber(rs.getString("PhoneNumber"));
            setId(customerNumber);
            setTrBalance(rs.getDouble("TRBalance"));
            setEuroBalance(rs.getDouble("EuroBalance"));
            setStrBalance(rs.getDouble("StrBalance"));
            setGoldBalance(rs.getDouble("Gldbalance"));
            setDlrBalance(rs.getDouble("DlrBalance"));
            setDebt(rs.getDouble("Debt"));
            setWage(rs.getDouble("Wage"));

        }

        if (girilemSifre == pw) {
            Info();//Eğer şifre doğruysa Info metodunu çalıştıracak
            Menu monu = new Menu();
            monu.menu();//monu nesnesi üzerinden menu metodunu çağırdık

        } else {
            System.out.println("HATALI GİRİŞ YAPTINIZ!!");//eğer hatalı giriş yapıyorsa 3 deneme hakkının sonunda programı sonlandıracak
            sayac++;
            switch (sayac) {
                case 1:
                    System.out.println("Hatalı giriş yaptınız 2 deneme hakkınız kaldı");
                    break;
                case 2:
                    System.out.println("Hatalı giriş yaptınız 1 deneme hakkınız kaldı");
                    break;
                case 3:
                    System.out.println("Hatalı giriş yaptınız deneme hakkınız kalmadı");
                    System.exit(0);
                    break;
            }
            login();
        }


    }

    public void kayitOl() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
        Statement st = c.createStatement();
        Scanner scan = new Scanner(System.in);
        System.out.print("Adınız Soyadınız: ");
        String name = scan.nextLine();
        System.out.print("\nMail adresiniz: ");
        String email = scan.nextLine();
        System.out.print("\nTelefon Numaranız: ");
        String phoneNu = scan.nextLine();
        int trBalance = 0;
        int euroBalance = 0;
        int strBalance = 0;
        int gldBalance = 0;
        int dlrBalance = 0;
        int debt = 0;
        System.out.print("\nMaaşınız ne kadar? ");
        double wage = scan.nextDouble();
        setLimit(wage * 3);
        System.out.print("\nŞifreniz ne olsun? ");
        int pw = scan.nextInt();
        String query = "INSERT INTO customerbank (Name_Surname,Email,PhoneNumber,Wage,Password,GuncelLimitt) VALUES('" + name + "','" + email + "','" + phoneNu + "'," + wage + "," + pw + ", " + getLimit() + ")";
        int sonuc = st.executeUpdate(query);
        String url = "select * from customerbank Where Name_Surname='" + name + "'";
        ResultSet rs = st.executeQuery(url);
        while (rs.next()) {
            String YeniMusteri = "Müşteri Numaranız: " + rs.getInt("CustomerID");
            System.out.println(YeniMusteri);
        }
        st.close();
    }
}