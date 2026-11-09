
package com.mycompany.sistemsewalapangan;

import java.util.ArrayList;
import java.util.HashMap;

public class ManajemenSewaLapangan {

    private FasilitasUmum fasilitas;

    private ArrayList<Lapangan> daftarLapangan;
    private HashMap<String, Pelanggan> daftarPelanggan;

    private TransaksiPenyewaan transaksi;

    public ManajemenSewaLapangan() {
        fasilitas = new FasilitasUmum();
        daftarLapangan = new ArrayList<>();
        daftarPelanggan = new HashMap<>();
        transaksi = new TransaksiPenyewaan();
    }

    public void tambahLapangan(Lapangan lapangan) {
        daftarLapangan.add(lapangan);
        System.out.println("Lapangan berhasil ditambahkan!");
    }

    public void tampilkanLapangan() {
        System.out.println("\n=== DAFTAR LAPANGAN ===");

        if (daftarLapangan.isEmpty()) {
            System.out.println("Belum ada data lapangan.");
            return;
        }

        for (int i = 0; i < daftarLapangan.size(); i++) {
            System.out.println("\nNomor Lapangan: " + (i + 1));
            daftarLapangan.get(i).tampilkanInfo();
        }
    }

    public Lapangan cariLapangan(int nomor)
            throws DataPenyewaanException {

        if (nomor < 1 || nomor > daftarLapangan.size()) {
            throw new DataPenyewaanException(
                    "Nomor lapangan tidak ditemukan!");
        }

        return daftarLapangan.get(nomor - 1);
    }

    public void tambahPelanggan(Pelanggan pelanggan)
            throws DataPenyewaanException {

        if (daftarPelanggan.containsKey(pelanggan.getId())) {
            throw new DataPenyewaanException(
                    "ID pelanggan sudah terdaftar!");
        }

        daftarPelanggan.put(pelanggan.getId(), pelanggan);
        System.out.println("Pelanggan berhasil ditambahkan!");
    }

    public void tampilkanPelanggan() {
        System.out.println("\n=== DAFTAR PELANGGAN ===");

        if (daftarPelanggan.isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
            return;
        }

        for (Pelanggan pelanggan : daftarPelanggan.values()) {
            pelanggan.tampilkanInfo();
            System.out.println("------------------------");
        }
    }

    public Pelanggan cariPelanggan(String id)
            throws DataPenyewaanException {

        if (!daftarPelanggan.containsKey(id)) {
            throw new DataPenyewaanException(
                    "Pelanggan tidak ditemukan!");
        }

        return daftarPelanggan.get(id);
    }

    public void tampilkanFasilitas() {
        fasilitas.tampilkanFasilitas();
    }

   
    public void jalankanTransaksi(String id, int nomor)
            throws DataPenyewaanException {

        Pelanggan pelanggan = cariPelanggan(id);
        Lapangan lapangan = cariLapangan(nomor);

        transaksi.prosesTransaksi(pelanggan, lapangan);
    }

    public int jumlahLapangan() {
        return daftarLapangan.size();
    }

    public int jumlahPelanggan() {
        return daftarPelanggan.size();
    }


    public void tampilkanSemuaFasilitas() {

        if (daftarLapangan.isEmpty()) {
            System.out.println("Belum ada data lapangan.");
            return;
        }

        for (Lapangan lapangan : daftarLapangan) {

            System.out.println("\nPenyewa: "
                    + lapangan.getNamaPenyewa());

            lapangan.tampilkanFasilitas();

            if (lapangan instanceof DapatDiPesan) {
                DapatDiPesan pesanan =
                        (DapatDiPesan) lapangan;

                pesanan.prosesPemesanan();
            }

            if (lapangan instanceof DapatDiberiDiskon) {
                DapatDiberiDiskon diskon =
                        (DapatDiberiDiskon) lapangan;

                diskon.tampilkanDiskon();
            }
        }
    }
}
