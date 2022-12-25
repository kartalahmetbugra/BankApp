public class Rates {
    private static String dolarAlis, dolarSatis;
    private String euroAlis, euroSatis;
    private String altinAlis, altinSatis;
    private String sterlinAlis, sterlinSatis;

    public static final String ANSI_KIRMIZI = "\u001B[31m";
    public static final String ANSI_YEŞİL = "\u001B[32m";

    public String getSterlinAlis() {
        return sterlinAlis;
    }

    public void setSterlinAlis(String sterlinAlis) {
        this.sterlinAlis = sterlinAlis;
    }

    public String getSterlinSatis() {
        return sterlinSatis;
    }

    public void setSterlinSatis(String sterlinSatis) {
        this.sterlinSatis = sterlinSatis;
    }

    public void setDolarAlis(String dolarAlis) {
        this.dolarAlis = dolarAlis;
    }

    public void setDolarSatis(String dolarSatis) {
        this.dolarSatis = dolarSatis;
    }

    public void setEuroAlis(String euroAlis) {
        this.euroAlis = euroAlis;
    }

    public void setEuroSatis(String euroSatis) {
        this.euroSatis = euroSatis;
    }

    public void setAltinAlis(String altinAlis) {
        this.altinAlis = altinAlis;
    }

    public void setAltinSatis(String altinSatis) {
        this.altinSatis = altinSatis;
    }

    public String getDolarAlis() {
        return dolarAlis;
    }

    public String getDolarSatis() {
        return dolarSatis;
    }

    public String getEuroAlis() {
        return euroAlis;
    }

    public String getEuroSatis() {
        return euroSatis;
    }

    public String getAltinAlis() {
        return altinAlis;
    }

    public String getAltinSatis() {
        return altinSatis;
    }

    public void yazdir() {//Güncel döviz kurlarını yazdırdık
        System.out.println(ANSI_KIRMIZI + "Dolar Alış: " + getDolarAlis() + "  Dolar Satış: " + getDolarSatis());
        System.out.println("Euro Alış: " + getEuroAlis() + "  Euro Satış: " + getEuroSatis());
        System.out.println("Altın Alış(gram): " + getAltinAlis() + "  Altın Satış(gram): " + getAltinSatis());
        System.out.println("Sterlin Alış: " + getSterlinAlis() + "  Sterlin Satış: " + getSterlinSatis());
    }
}
