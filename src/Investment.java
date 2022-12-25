import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class Investment extends DBcon {
    public static double dolarBuy, dolarSell;
    public static double goldBuy, goldSell;
    public static double sterlinBuy, sterlinSell;
    public static double euroBuy, euroSell;

    InvestmentPrint investment;

    public void pinfo() { // interface ve polyymoprhism
        investment.pinfo();
    }

    public void investment() throws SQLException, IOException {
        Investment investment = new Investment();
        investment.investment = new InvestmentPrint();


        rates();
        Connection c = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
        Statement st = c.createStatement();
        String url = "select * from customerbank Where CustomerID='" + customerNumber + "'";
        ResultSet rs = st.executeQuery(url);


        while (rs.next()) {
            setTrBalance(rs.getDouble("TRBalance"));
            setDlrBalance(rs.getDouble("DlrBalance"));
            setEuroBalance(rs.getDouble("EuroBalance"));
            setGoldBalance(rs.getDouble("GldBalance"));
            setStrBalance(rs.getDouble("StrBalance"));
        }
        Scanner scan = new Scanner(System.in);

        System.out.println("Hangi para birimiyle işlem yapmak istiyorsunuz?");
        System.out.println("1-Dolar");
        System.out.println("2-Euro");
        System.out.println("3-Altın");
        System.out.println("4-Sterlin");
        System.out.println("5-Menü");
        int secim = scan.nextInt();

        switch (secim) {
            case 1:
                System.out.println("1-Dolar ALış");
                System.out.println("2-Dolar Satış");
                secim = scan.nextInt();
                switch (secim) {
                    case 1: // DOLAR ALMA
                        System.out.println("Ne kadarlık dolar almak istiyorsunuz?");
                        double tutar = scan.nextDouble();

                        if (tutar > getTrBalance()) {
                            System.out.println("Bakiye yetersiz");
                            investment();
                        } else {
                            setDlrBalance((tutar / dolarBuy) + getDlrBalance());
                            setTrBalance(getTrBalance() - tutar);
                            System.out.println("Güncel Bakiyeniz: " + getTrBalance());
                            System.out.println("Güncel Dolar Miktarınız: " + getDlrBalance());
                            try {
                                Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                                Statement sta = d.createStatement();
                                String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                String url3 = " UPDATE customerbank SET DlrBalance='" + getDlrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                sta.executeUpdate(url2);
                                sta.executeUpdate(url3);
                                investment.pinfo();// Interface polymorphism
                            } catch (Exception e) {
                                System.out.println("Güncelleme olmadı.");
                            }
                            File file = new File(customerNumber + ".txt");
                            if (!file.exists()) {//oldu mu olmadı mı kontrol
                                file.createNewFile();
                            }
                            java.util.Date simdikiZaman = new Date();
                            String x = simdikiZaman.toString();
                            String islem = "Dolar miktarınız: " + getDlrBalance() + "$ ";
                            String islem2 = "\nGüncel Bakiyeniz: " + getTrBalance() + "₺ ";
                            FileWriter fWriter = new FileWriter(file, true);
                            BufferedWriter bWriter = new BufferedWriter(fWriter);
                            bWriter.append(islem + x.substring(0, 19));
                            bWriter.append(islem2 + x.substring(0, 19));
                            bWriter.append("\n");
                            bWriter.close();
                        }

                        investment();
                        break;
                    case 2://Dolar satma
                        System.out.println("Ne kadar dolar satmak istiyorsunuz ?");
                        tutar = scan.nextDouble();
                        if (tutar > getDlrBalance()) {
                            System.out.println("Bakiye yetersiz");
                            investment();
                        } else {
                            setDlrBalance(getDlrBalance() - tutar);
                            setTrBalance(getTrBalance() + (tutar * dolarSell));
                            System.out.println("Güncel Bakiyeniz: " + getTrBalance());
                            System.out.println("Güncel Dolar Miktarınız: " + getDlrBalance());
                            try {
                                Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                                Statement sta = d.createStatement();
                                String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                String url3 = " UPDATE customerbank SET DlrBalance='" + getDlrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                sta.executeUpdate(url2);
                                sta.executeUpdate(url3);
                                investment.pinfo();// Interface polymorphism
                            } catch (Exception e) {
                                System.out.println("Güncelleme olmadı.");
                            }
                            File file = new File(customerNumber + ".txt");
                            if (!file.exists()) {//oldu mu olmadı mı kontrol
                                file.createNewFile();
                            }
                            java.util.Date simdikiZaman = new Date();
                            String x = simdikiZaman.toString();
                            String islem3 = "\nDolar miktarınız: " + getDlrBalance() + "$ ";
                            String islem4 = "\nGüncel Bakiyeniz: " + getTrBalance() + "₺ ";
                            FileWriter fWriter = new FileWriter(file, true);
                            BufferedWriter bWriter = new BufferedWriter(fWriter);
                            bWriter.append(islem3 + x.substring(0, 19));
                            bWriter.append(islem4 + x.substring(0, 19));
                            bWriter.append("\n");
                            bWriter.close();
                        }
                        investment();
                        break;
                    default:
                        investment();
                        break;
                }
            case 2://Euro
                System.out.println("1-Euro ALış");
                System.out.println("2-Euro Satış");
                secim = scan.nextInt();
                switch (secim) {
                    case 1://Alım
                        System.out.println("Ne kadarlık Euro almak istiyorsunuz?");
                        double tutar = scan.nextDouble();

                        if (tutar > getTrBalance()) {
                            System.out.println("Bakiye yetersiz");
                            investment();
                        } else {
                            setEuroBalance((tutar / euroBuy) + getEuroBalance());
                            setTrBalance(getTrBalance() - tutar);
                            System.out.println("Güncel Bakiyeniz: " + getTrBalance());
                            System.out.println("Güncel Euro Miktarınız: " + getEuroBalance());
                            try {
                                Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                                Statement sta = d.createStatement();
                                String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                String url3 = " UPDATE customerbank SET EuroBalance='" + getEuroBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                sta.executeUpdate(url2);
                                sta.executeUpdate(url3);
                                investment.pinfo();// Interface polymorphism
                            } catch (Exception e) {
                                System.out.println("Güncelleme olmadı.");
                            }
                            File file = new File(customerNumber + ".txt");
                            if (!file.exists()) {//oldu mu olmadı mı kontrol
                                file.createNewFile();
                            }
                            java.util.Date simdikiZaman = new Date();
                            String x = simdikiZaman.toString();
                            String islem5 = "\nEuro miktarınız: " + getEuroBalance() + "€ ";
                            String islem6 = "\nGüncel Bakiyeniz: " + getTrBalance() + "₺ ";
                            FileWriter fWriter = new FileWriter(file, true);
                            BufferedWriter bWriter = new BufferedWriter(fWriter);
                            bWriter.append(islem5 + x.substring(0, 19));
                            bWriter.append(islem6 + x.substring(0, 19));
                            bWriter.append("\n");
                            bWriter.close();
                        }
                        investment();
                        break;
                    case 2://Satım
                        System.out.println("Ne kadar euro satmak istiyorsunuz ?");
                        tutar = scan.nextDouble();
                        if (tutar > getEuroBalance()) {
                            System.out.println("Bakiye yetersiz");
                            investment();
                        } else {
                            setEuroBalance(getEuroBalance() - tutar);
                            setTrBalance(getTrBalance() + (tutar * euroSell));
                            System.out.println("Güncel Bakiyeniz: " + getTrBalance());
                            System.out.println("Güncel Euro Miktarınız: " + getEuroBalance());
                            try {
                                Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                                Statement sta = d.createStatement();
                                String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                String url3 = " UPDATE customerbank SET EuroBalance='" + getEuroBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                sta.executeUpdate(url2);
                                sta.executeUpdate(url3);
                                investment.pinfo();// Interface polymorphism
                            } catch (Exception e) {
                                System.out.println("Güncelleme olmadı.");
                            }
                            File file = new File(customerNumber + ".txt");
                            if (!file.exists()) {//oldu mu olmadı mı kontrol
                                file.createNewFile();
                            }
                            java.util.Date simdikiZaman = new Date();
                            String x = simdikiZaman.toString();
                            String islem5 = "\nEuro miktarınız: " + getEuroBalance() + "€ ";
                            String islem6 = "\nGüncel Bakiyeniz: " + getTrBalance() + "₺ ";
                            FileWriter fWriter = new FileWriter(file, true);
                            BufferedWriter bWriter = new BufferedWriter(fWriter);
                            bWriter.append(islem5 + x.substring(0, 19));
                            bWriter.append(islem6 + x.substring(0, 19));
                            bWriter.append("\n");
                            bWriter.close();
                        }
                        investment();
                        break;
                    default:
                        break;
                }
            case 3://Altın
                System.out.println("1-Altın ALış");
                System.out.println("2-Altın Satış");
                secim = scan.nextInt();
                switch (secim) {
                    case 1://Alım
                        System.out.println("Ne kadarlık Altın almak istiyorsunuz?");
                        double tutar = scan.nextDouble();

                        if (tutar > getTrBalance()) {
                            System.out.println("Bakiye yetersiz");
                            investment();
                        } else {
                            setGoldBalance((tutar / goldBuy) + getGoldBalance());
                            setTrBalance(getTrBalance() - tutar);
                            System.out.println("Güncel Bakiyeniz: " + getTrBalance());
                            System.out.println("Güncel Altın Miktarınız: " + getGoldBalance());
                            try {
                                Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                                Statement sta = d.createStatement();
                                String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                String url3 = " UPDATE customerbank SET GldBalance='" + getGoldBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                sta.executeUpdate(url2);
                                sta.executeUpdate(url3);
                                investment.pinfo();// Interface polymorphism
                            } catch (Exception e) {
                                System.out.println("Güncelleme olmadı.");
                            }
                            File file = new File(customerNumber + ".txt");
                            if (!file.exists()) {//oldu mu olmadı mı kontrol
                                file.createNewFile();
                            }
                            java.util.Date simdikiZaman = new Date();
                            String x = simdikiZaman.toString();
                            String islem7 = "\nAltın miktarınız: " + getGoldBalance() + "XAUTRY ";
                            String islem8 = "\nGüncel Bakiyeniz: " + getTrBalance() + "₺ ";
                            FileWriter fWriter = new FileWriter(file, true);
                            BufferedWriter bWriter = new BufferedWriter(fWriter);
                            bWriter.append(islem7 + x.substring(0, 19));
                            bWriter.append(islem8 + x.substring(0, 19));
                            bWriter.append("\n");
                            bWriter.close();
                        }
                        investment();
                        break;
                    case 2://Satım
                        System.out.println("Ne kadar Altın satmak istiyorsunuz ?");
                        tutar = scan.nextDouble();
                        if (tutar > getGoldBalance()) {
                            System.out.println("Bakiye yetersiz");
                            investment();
                        } else {
                            setGoldBalance(getGoldBalance() - tutar);
                            setTrBalance(getTrBalance() + (tutar * goldSell));
                            System.out.println("Güncel Bakiyeniz: " + getTrBalance());
                            System.out.println("Güncel Altın Miktarınız: " + getGoldBalance());
                            try {
                                Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                                Statement sta = d.createStatement();
                                String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                String url3 = " UPDATE customerbank SET GldBalance='" + getGoldBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                sta.executeUpdate(url2);
                                sta.executeUpdate(url3);
                                investment.pinfo();// Interface polymorphism
                            } catch (Exception e) {
                                System.out.println("Güncelleme olmadı.");
                            }
                            File file = new File(customerNumber + ".txt");
                            if (!file.exists()) {//oldu mu olmadı mı kontrol
                                file.createNewFile();
                            }
                            java.util.Date simdikiZaman = new Date();
                            String x = simdikiZaman.toString();
                            String islem8 = "\nAltın miktarınız: " + getGoldBalance() + "XAUTRY ";
                            String islem9 = "\nGüncel Bakiyeniz: " + getTrBalance() + "₺ ";
                            FileWriter fWriter = new FileWriter(file, true);
                            BufferedWriter bWriter = new BufferedWriter(fWriter);
                            bWriter.append(islem8 + x.substring(0, 19));
                            bWriter.append(islem9 + x.substring(0, 19));
                            bWriter.append("\n");
                            bWriter.close();
                        }
                        investment();
                        break;
                    default:
                        break;
                }
            case 4://Sterlin
                System.out.println("1-Sterlin ALış");
                System.out.println("2-Sterlin Satış");
                secim = scan.nextInt();
                switch (secim) {
                    case 1://Alım
                        System.out.println("Ne kadarlık Sterlin almak istiyorsunuz?");
                        double tutar = scan.nextDouble();

                        if (tutar > getTrBalance()) {
                            System.out.println("Bakiye yetersiz");
                            investment();
                        } else {
                            setStrBalance((tutar / sterlinBuy) + getStrBalance());
                            setTrBalance(getTrBalance() - tutar);
                            System.out.println("Güncel Bakiyeniz: " + getTrBalance());
                            System.out.println("Güncel Sterlin Miktarınız: " + getStrBalance());
                            try {
                                Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                                Statement sta = d.createStatement();
                                String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                String url3 = " UPDATE customerbank SET StrBalance='" + getStrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                sta.executeUpdate(url2);
                                sta.executeUpdate(url3);
                                investment.pinfo();// Interface polymorphism
                            } catch (Exception e) {
                                System.out.println("Güncelleme olmadı.");
                            }
                            File file = new File(customerNumber + ".txt");
                            if (!file.exists()) {//oldu mu olmadı mı kontrol
                                file.createNewFile();
                            }
                            java.util.Date simdikiZaman = new Date();
                            String x = simdikiZaman.toString();
                            String islem10 = "\nSterlin miktarınız: " + getStrBalance() + "£ ";
                            String islem11 = "\nGüncel Bakiyeniz: " + getTrBalance() + "₺ ";
                            FileWriter fWriter = new FileWriter(file, true);
                            BufferedWriter bWriter = new BufferedWriter(fWriter);
                            bWriter.append(islem10 + x.substring(0, 19));
                            bWriter.append(islem11 + x.substring(0, 19));
                            bWriter.append("\n");
                            bWriter.close();
                        }
                        investment();
                        break;
                    case 2://Satım
                        System.out.println("Ne kadar Sterlin satmak istiyorsunuz ?");
                        tutar = scan.nextDouble();
                        if (tutar > getStrBalance()) {
                            System.out.println("Bakiye yetersiz");
                            investment();
                        } else {
                            setStrBalance(getStrBalance() - tutar);
                            setTrBalance(getTrBalance() + (tutar * sterlinSell));
                            System.out.println("Güncel Bakiyeniz: " + getTrBalance());
                            System.out.println("Güncel Sterlin Miktarınız: " + getStrBalance());
                            try {
                                Connection d = DriverManager.getConnection("jdbc:MySQL://localhost:3306/bankdb?user=root&password=2446338Ahmet.");
                                Statement sta = d.createStatement();
                                String url2 = " UPDATE customerbank SET TRBalance='" + getTrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                String url3 = " UPDATE customerbank SET StrBalance='" + getStrBalance() + "'WHERE CustomerID='" + customerNumber + "'";
                                sta.executeUpdate(url2);
                                sta.executeUpdate(url3);
                                investment.pinfo();// Interface polymorphism
                            } catch (Exception e) {
                                System.out.println("Güncelleme olmadı.");
                            }
                            File file = new File(customerNumber + ".txt");
                            if (!file.exists()) {//oldu mu olmadı mı kontrol
                                file.createNewFile();
                            }
                            java.util.Date simdikiZaman = new Date();
                            String x = simdikiZaman.toString();
                            String islem12 = "\nSterlin miktarınız: " + getStrBalance() + "£ ";
                            String islem13 = "\nGüncel Bakiyeniz: " + getTrBalance() + "₺ ";
                            FileWriter fWriter = new FileWriter(file, true);
                            BufferedWriter bWriter = new BufferedWriter(fWriter);
                            bWriter.append(islem12 + x.substring(0, 19));
                            bWriter.append(islem13 + x.substring(0, 19));
                            bWriter.append("\n");
                            bWriter.close();
                        }
                        investment();
                        break;
                    default:
                        break;
                }
            case 5:
                Menu menu = new Menu();
                menu.menu();
                break;
        }

    }


    public void rates() {//Kurlardak, , ifadelerini . haline getirdik
        try {
            Document doc = Jsoup.connect("https://www.yapikredi.com.tr/yatirimci-kosesi/doviz-bilgileri").get();
            for (Element row : doc.select("div.table-radius ")
            ) {
                String kur = row.select("tr:nth-child(1) td:nth-child(3)").first().text();
                String degisim = kur.replace(",", ".");
                dolarBuy = Double.parseDouble(degisim);

                String kur2 = row.select("tr:nth-child(1) td:nth-child(4)").first().text();
                String degisim2 = kur2.replace(",", ".");
                dolarSell = Double.parseDouble(degisim2);

                String kur3 = row.select("tr:nth-child(2) td:nth-child(3)").first().text();
                String degisim3 = kur3.replace(",", ".");
                euroBuy = Double.parseDouble(degisim3);

                String kur4 = row.select("tr:nth-child(2) td:nth-child(4)").first().text();
                String degisim4 = kur4.replace(",", ".");
                euroSell = Double.parseDouble(degisim4);

                String kur5 = row.select("tr:nth-child(3) td:nth-child(3)").first().text();
                String degisim5 = kur5.replace(".", "");
                degisim5 = degisim5.replace(",", ".");
                goldBuy = Double.parseDouble(degisim5);

                String kur6 = row.select("tr:nth-child(3) td:nth-child(4)").first().text();
                String degisim6 = kur6.replace(".", "");
                degisim6 = degisim6.replace(",", ".");
                goldSell = Double.parseDouble(degisim6);

                String kur7 = row.select("tr:nth-child(4) td:nth-child(3)").first().text();
                String degisim7 = kur7.replace(",", ".");
                sterlinBuy = Double.parseDouble(degisim7);

                String kur8 = row.select("tr:nth-child(4) td:nth-child(4)").first().text();
                String degisim8 = kur8.replace(",", ".");
                sterlinSell = Double.parseDouble(degisim8);

                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
