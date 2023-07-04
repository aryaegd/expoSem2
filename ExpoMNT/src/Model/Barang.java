package Model;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import javafx.beans.property.*;

@XStreamAlias("barang")
public class Barang {
    private StringProperty kode;
    private StringProperty nama;
    private StringProperty jenis;
    private DoubleProperty berat;
    private StringProperty lokasi;

    public Barang(String kode, String nama, String jenis, double berat, String lokasi) {
        this.kode = new SimpleStringProperty(kode);
        this.nama = new SimpleStringProperty(nama);
        this.jenis = new SimpleStringProperty(jenis);
        this.berat = new SimpleDoubleProperty(berat);
        this.lokasi = new SimpleStringProperty(lokasi);
    }

    public StringProperty kodeProperty() {
        return kode;
    }

    public String getKode() {
        return kode.get();
    }

    public void setKode(String kode) {
        this.kode.set(kode);
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public StringProperty jenisProperty() {
        return jenis;
    }

    public String getJenis() {
        return jenis.get();
    }

    public void setJenis(String jenis) {
        this.jenis.set(jenis);
    }

    public DoubleProperty beratProperty() {
        return berat;
    }

    public double getBerat() {
        return berat.get();
    }

    public void setBerat(double berat) {
        this.berat.set(berat);
    }

    public StringProperty lokasiProperty() {
        return lokasi;
    }

    public String getLokasi() {
        return lokasi.get();
    }

    public void setLokasi(String lokasi) {
        this.lokasi.set(lokasi);
    }
}
