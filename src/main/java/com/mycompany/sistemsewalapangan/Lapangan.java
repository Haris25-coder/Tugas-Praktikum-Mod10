
package com.mycompany.sistemsewalapangan;

public abstract class Lapangan {

    private String namaPenyewa;
    private String tanggal;
    private int durasi;

    private static int jumlahSewa = 0;

    public Lapangan(String namaPenyewa, String tanggal, int durasi) {
        this.namaPenyewa = namaPenyewa;
        this.tanggal = tanggal;
        this.durasi = durasi;
        jumlahSewa++;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public static int getJumlahSewa() {
        return jumlahSewa;
    }

    public abstract void tampilkanFasilitas();

    public void tampilkanInfo() {
        System.out.println("Nama Penyewa : " + namaPenyewa);
        System.out.println("Tanggal      : " + tanggal);
        System.out.println("Durasi       : " + durasi + " jam");
    }

    public int hitungHarga(int hargaPerJam) {
        return durasi * hargaPerJam;
    }

    public int hitungHarga(int hargaPerJam, int diskon) {
        return (durasi * hargaPerJam) - diskon;
    }
}
