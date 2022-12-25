import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Entrance {
    public static final String ANSI_YEŞİL = "\u001B[32m";
    public static final String ANSI_MOR = "\u001B[35m";

    public void entrance() throws SQLException, IOException {

        for (int i = 0; i <= 20; i++) {
            System.out.print(" ");//Yazımızın ortalı olması için boşluk koyduk
        }

        System.out.println(ANSI_YEŞİL + "**********İşimize gelirse Bankasına Hoşgeldiniz**********\n");
        for (int i = 0; i <= 10; i++) {
            System.out.print(" ");//Yazımızın ortalı olması için boşluk koyduk
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Güncel Döviz Kurları \n");
        DataExtraction rates = new DataExtraction();//DataExtraction classından rates nesnesi oluşturduk
        rates.getData();//Dataextraction classındaki getData metodunu çalıştırdık
        System.out.println("\n");
        for (int i = 0; i <= 12; i++) {
            System.out.print(" ");
        }

        System.out.println(ANSI_YEŞİL + "**HOŞGELDİNİZ**\n\nGiriş yapmak için 1" +
                "\nKayıt olmak için 2'ye basınız ");//kullanıcı var olan bir kullanıcı mı farklı bir kullanıcı mı onu seçmesini istedik

        int procces = scan.nextInt();

        switch (procces) {
            case 1:
                DBcon login = new DBcon();
                login.login();//Eğer var olan bir kullanıcı ise DBcon classındaki Login metodunu çağırdık
                break;
            case 2:
                DBcon register = new DBcon();
                register.kayitOl();//Eğer yeni bir kullanıcı ise DBcon classındaki register metodunu çağırdık
                entrance();
                break;
        }
    }
}
