
package com.mycompany.sistemsewalapangan;

public class Futsal extends Lapangan
        implements DapatDiPesan, DapatDiberiDiskon {

    private String ukuranLapangan;

    public Futsal(String namaPenyewa, String tanggal,
                  int durasi, String ukuranLapangan) {
        super(namaPenyewa, tanggal, durasi);
        this.ukuranLapangan = ukuranLapangan;
    }

    public String getUkuranLapangan() {
        return ukuranLapangan;
    }

    public void setUkuranLapangan(String ukuranLapangan) {
        if (ukuranLapangan != null && !ukuranLapangan.isEmpty()) {
            this.ukuranLapangan = ukuranLapangan;
        }
    }

    @Override
    public void tampilkanFasilitas() {
        System.out.println("Fasilitas Futsal: gawang dan bola.");
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis   : Futsal");
        System.out.println("Ukuran  : " + ukuranLapangan);
        System.out.println("Harga   : Rp" + hitungHarga());
    }

    public int hitungHarga() {
        return getDurasi() * 100000;
    }

    @Override
    public void prosesPemesanan() {
        System.out.println(
                "Pemesanan Futsal diproses untuk "
                + getNamaPenyewa()
        );
    }

    @Override
    public void tampilkanDiskon() {
        System.out.println("Promo Futsal: diskon Rp20.000.");
    }
}
