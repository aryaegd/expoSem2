package Model;

public class Persetujuan {
    private String kodeBrg;
    private String status;

    public Persetujuan(String kodeBrg, String status) {
        this.kodeBrg = kodeBrg;
        this.status = status;
    }

    public String getKodeBrg() {
        return kodeBrg;
    }

    public void setKodeBrg(String kodeBrg) {
        this.kodeBrg = kodeBrg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
