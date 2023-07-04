package Model;

public class Barang {
    private String kodeBrg;
    private String namaBrg;
    private String jenisBrg;
    private double beratBrg;
    private String lokasiBrg;
    private Double hargaBrg;

    public Barang(String kodeBrg, String namaBrg, String jenisBrg, double beratBrg, String lokasiBrg, Double hargaBrg) {
        this.kodeBrg = kodeBrg;
        this.namaBrg = namaBrg;
        this.jenisBrg = jenisBrg;
        this.beratBrg = beratBrg;
        this.lokasiBrg = lokasiBrg;
        this.hargaBrg = hargaBrg;
    }

    public String getKodeBrg() {
        return kodeBrg;
    }

    public void setKodeBrg(String kodeBrg) {
        this.kodeBrg = kodeBrg;
    }

    public String getNamaBrg() {
        return namaBrg;
    }

    public void setNamaBrg(String namaBrg) {
        this.namaBrg = namaBrg;
    }

    public String getJenisBrg() {
        return jenisBrg;
    }

    public void setJenisBrg(String jenisBrg) {
        this.jenisBrg = jenisBrg;
    }

    public double getBeratBrg() {
        return beratBrg;
    }

    public void setBeratBrg(double beratBrg) {
        this.beratBrg = beratBrg;
    }

    public String getLokasiBrg() {
        return lokasiBrg;
    }

    public void setLokasiBrg(String lokasiBrg) {
        this.lokasiBrg = lokasiBrg;
    }

    public Double getHargaBrg() {
        return hargaBrg;
    }

    public void setHargaBrg(Double hargaBrg) {
        this.hargaBrg = hargaBrg;
    }

    
}