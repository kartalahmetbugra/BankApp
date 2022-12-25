import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class DataExtraction extends Rates {

    public void getData() {
        try {
            Document doc = Jsoup.connect("https://www.yapikredi.com.tr/yatirimci-kosesi/doviz-bilgileri").get();//Verileri çektiğimiz sitenin adresi
            for (Element row : doc.select("div.table-radius ")//verilerin bulunduğu division
            ) {
                String kur = row.select("tr:nth-child(1) td:nth-child(3)").first().text();
                setDolarAlis(kur);
                String kur2 = row.select("tr:nth-child(1) td:nth-child(4)").first().text();
                setDolarSatis(kur2);
                String kur3 = row.select("tr:nth-child(2) td:nth-child(3)").first().text();
                setEuroAlis(kur3);
                String kur4 = row.select("tr:nth-child(2) td:nth-child(4)").first().text();
                setEuroSatis(kur4);
                String kur5 = row.select("tr:nth-child(3) td:nth-child(3)").first().text();
                setAltinAlis(kur5);
                String kur6 = row.select("tr:nth-child(3) td:nth-child(4)").first().text();
                setAltinSatis(kur6);
                String kur7 = row.select("tr:nth-child(4) td:nth-child(3)").first().text();
                setSterlinAlis(kur7);
                String kur8 = row.select("tr:nth-child(4) td:nth-child(4)").first().text();
                setSterlinSatis(kur8);
                yazdir();//Rates classındaki yazdir metodunu çağırarak güncel döviz kurlarını ekrana yazdırdık
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
