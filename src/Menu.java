import java.io.IOException;
import java.util.Scanner;
import java.sql.*;

public class Menu extends DBcon {
    public void menu() throws SQLException, IOException {

        Scanner scan = new Scanner(System.in);
        for (int i = 0; i <= 5; i++) {
            System.out.print(" ");
        }
        DBcon db = new DBcon();

        System.out.println("Sen ne olsun istiyorsun?\n");//Kullanıcıya hangi işlemi yapmak istediğini soruyoruz
        System.out.println("1-PARA YATIR       6-BORÇ YATIR\n" +
                "2-PARA ÇEK         7-KREDİ ÇEK\n" +
                "3-YATIRIM          8-HAVALE\n" +
                "4-VARLIKLARIM      9-BİLGİ GÜNCELLEME\n" +
                "5-İŞLEM GEÇMİŞİ    10-ÇIKIŞ\n"
        );
        int choose = scan.nextInt();
        switch (choose) {
            default:
                System.out.println("Hatalı giriş yaptınız tekrar deneyiniz");
                menu();//Hatalı bir tuşlama yapıldığında tekrar menuye atıyoruz
            case 1:
                Deposit deposit = new Deposit();
                deposit.deposit();//Para yatırma işlemini seçtiğinde Deposit classındaki deposit metodunu çalıştırdık
                menu();
                break;
            case 2:
                Withdrawal withdrawal = new Withdrawal();
                withdrawal.paraCek();//Para çekme işlemini seçtiğinde Withdrawal classındaki paraCek metodunu çalıştırdık
                menu();
                break;
            case 3:
                Investment investment = new Investment();
                investment.investment();// yatırım işlemini seçtiğinde Investment classındaki investment metodunu çalıştırdık
                menu();
                break;
            case 4:
                Wealth wealth = new Wealth();
                wealth.wealth();//Varlıklarım seçeneğini seçtiğinde Wealth classındaki Wealth metodunu çalıştırdık
                menu();
                break;
            case 5:
                History history = new History();
                history.historyPrint();//İşlem geçmişini seçtiğinde History Classındaki historyPrint metodunu çalıştırdık
                menu();
                break;
            case 6:
                PayDebt payDebt = new PayDebt();
                payDebt.payDebt();//Borç ödeme seçtiğinde PayDebt classındaki PayDebt metodunu çalıştırdık
                menu();
                break;
            case 7:
                GetCredit getCredit = new GetCredit();
                getCredit.getCredit();//Kredi çek seçeneği seçtiğinde GetCredit classındaki getCredit metodunu çalıltırdık
                menu();
                break;
            case 8:
                SendMoney sendMoney = new SendMoney();
                sendMoney.sendMoney();//Havale seçeneğini seçtiğinde SendMoney classındaki sendMoneu metodunu çalıltırdık
                menu();
                break;
            case 9:
                CustomerUpdate customerUpdate = new CustomerUpdate();
                customerUpdate.customerUpdate();//Bilgi güncelleme seçildiğinde CustomerUpdate classındaki customerUpdate metodunu çalıltırdık
                menu();
                break;
            case 10:
                System.exit(0);//Çıkış yapmak için
                break;
        }
    }
}
