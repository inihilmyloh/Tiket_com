package model;

public class Transaksi_dewa {
    private String namapelanggan;
    private double totalharga;
    private String Tanggal;
    private int jumlahtiket;
    private int stock;
    private String jenistiket;

    public String getNamapelanggan() {
        return namapelanggan;
    }

    public void setNamapelanggan(String namapelanggan) {
        this.namapelanggan = namapelanggan;
    }

    public double getTotalharga() {
        return totalharga;
    }

    public void setTotalharga(double totalharga) {
        this.totalharga = totalharga;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String Tanggal) {
        this.Tanggal = Tanggal;
    }

    public int getJumlahtiket() {
        return jumlahtiket;
    }

    public void setJumlahtiket(int jumlahtiket) {
        this.jumlahtiket = jumlahtiket;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getJenistiket() {
        return jenistiket;
    }

    public void setJenistiket(String jenistiket) {
        this.jenistiket = jenistiket;
    }
    
}
