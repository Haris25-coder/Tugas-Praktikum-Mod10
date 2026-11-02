
package com.mycompany.sistemsewalapangan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

public class SIstemSewaLapangan {

    static ArrayList<Lapangan> daftarSewa = new ArrayList<>();

    static HashMap<String, String> daftarPelanggan = new HashMap<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int pilihan = 0;

        do {
            System.out.println("\n==================================");
            System.out.println("       SISTEM SEWA LAPANGAN");
            System.out.println("==================================");
            System.out.println("1. Tambah Penyewaan");
            System.out.println("2. Tampilkan Semua Penyewaan");
            System.out.println("3. Abstract Method & Interface");
            System.out.println("4. Tambah Data Pelanggan");
            System.out.println("5. Cari Data Pelanggan");
            System.out.println("6. Tampilkan Semua Pelanggan");
            System.out.println("7. Cari Data Penyewaan");
            System.out.println("8. Keluar");
            System.out.println("==================================");
            System.out.print("Pilih menu: ");

            try {
                pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {

                    case 1:
                        tambahPenyewaan(input);
                        break;

                    case 2:
                        tampilkanPenyewaan();
                        break;

                    case 3:
                        jalankanInterface();
                        break;

                    case 4:
                        tambahPelanggan(input);
                        break;

                    case 5:
                        cariPelanggan(input);
                        break;

                    case 6:
                        tampilkanPelanggan();
                        break;

                    case 7:
                        cariPenyewaan(input);
                        break;

                    case 8:
                        System.out.println(
                                "Terima kasih telah menggunakan program."
                        );
                        break;

                    default:
                        System.out.println("Menu tidak tersedia!");
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "ERROR: Input harus berupa angka!"
                );

                input.nextLine();

            } catch (DataPenyewaanException e) {

                System.out.println(
                        "ERROR: " + e.getMessage()
                );

            } finally {

                System.out.println(
                        "[FINALLY] Proses menu telah diperiksa."
                );
            }

        } while (pilihan != 8);

        input.close();
    }

    public static void validasiData(String data, String keterangan)
            throws DataPenyewaanException {

        if (data == null || data.trim().isEmpty()) {
            throw new DataPenyewaanException(
                    keterangan + " tidak boleh kosong!"
            );
        }
    }

    public static void tambahPenyewaan(Scanner input)
            throws DataPenyewaanException {

        System.out.println("\n=== TAMBAH PENYEWAAN ===");

        System.out.print("Nama penyewa: ");
        String nama = input.nextLine();

        validasiData(nama, "Nama penyewa");

        System.out.print("Tanggal sewa: ");
        String tanggal = input.nextLine();

        validasiData(tanggal, "Tanggal sewa");

        System.out.print("Durasi sewa (jam): ");
        int durasi = input.nextInt();
        input.nextLine();

        if (durasi <= 0) {
            throw new DataPenyewaanException(
                    "Durasi harus lebih dari 0!"
            );
        }

        System.out.println("1. Futsal");
        System.out.println("2. Badminton");
        System.out.print("Pilih jenis lapangan: ");

        int jenis = input.nextInt();
        input.nextLine();

        if (jenis == 1) {

            System.out.print("Ukuran lapangan: ");
            String ukuran = input.nextLine();

            validasiData(ukuran, "Ukuran lapangan");

            daftarSewa.add(
                    new Futsal(nama, tanggal, durasi, ukuran)
            );

        } else if (jenis == 2) {

            System.out.print("Tipe lapangan: ");
            String tipe = input.nextLine();

            validasiData(tipe, "Tipe lapangan");

            daftarSewa.add(
                    new Badminton(nama, tanggal, durasi, tipe)
            );

        } else {

            throw new DataPenyewaanException(
                    "Jenis lapangan tidak tersedia!"
            );
        }

        System.out.println("Data penyewaan berhasil ditambahkan!");
        System.out.println(
                "Jumlah data: " + daftarSewa.size()
        );
    }

    public static void tampilkanPenyewaan() {

        System.out.println("\n=== DAFTAR PENYEWAAN ===");

        if (daftarSewa.isEmpty()) {
            System.out.println("Belum ada data penyewaan!");
            return;
        }

        for (int i = 0; i < daftarSewa.size(); i++) {

            System.out.println("\nData ke-" + (i + 1));
            System.out.println("----------------------");

            daftarSewa.get(i).tampilkanInfo();
        }

        System.out.println(
                "\nTotal penyewaan: " + daftarSewa.size()
        );
    }

    public static void jalankanInterface() {

        System.out.println("\n=== ABSTRACT METHOD & INTERFACE ===");

        if (daftarSewa.isEmpty()) {
            System.out.println("Belum ada data penyewaan!");
            return;
        }

        for (int i = 0; i < daftarSewa.size(); i++) {

            Lapangan lapangan = daftarSewa.get(i);

            System.out.println(
                    "\nPenyewa: " + lapangan.getNamaPenyewa()
            );

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

    public static void tambahPelanggan(Scanner input)
            throws DataPenyewaanException {

        System.out.println("\n=== TAMBAH PELANGGAN ===");

        System.out.print("Masukkan ID pelanggan: ");
        String id = input.nextLine();

        validasiData(id, "ID pelanggan");

        if (daftarPelanggan.containsKey(id)) {
            throw new DataPenyewaanException(
                    "ID pelanggan sudah terdaftar!"
            );
        }

        System.out.print("Masukkan nama pelanggan: ");
        String nama = input.nextLine();

        validasiData(nama, "Nama pelanggan");

        daftarPelanggan.put(id, nama);

        System.out.println("Pelanggan berhasil ditambahkan!");
        System.out.println(
                "Jumlah pelanggan: " + daftarPelanggan.size()
        );
    }

    public static void cariPelanggan(Scanner input)
            throws DataPenyewaanException {

        System.out.println("\n=== CARI PELANGGAN ===");

        System.out.print("Masukkan ID pelanggan: ");
        String id = input.nextLine();

        validasiData(id, "ID pelanggan");

        if (daftarPelanggan.containsKey(id)) {

            System.out.println(
                    "ID pelanggan: " + id
            );

            System.out.println(
                    "Nama pelanggan: "
                    + daftarPelanggan.get(id)
            );

        } else {

            throw new DataPenyewaanException(
                    "Pelanggan dengan ID " + id
                    + " tidak ditemukan!"
            );
        }
    }

    public static void tampilkanPelanggan() {

        System.out.println("\n=== DAFTAR PELANGGAN ===");

        if (daftarPelanggan.isEmpty()) {
            System.out.println("Belum ada pelanggan!");
            return;
        }

        for (Map.Entry<String, String> data
                : daftarPelanggan.entrySet()) {

            System.out.println(
                    "ID: " + data.getKey()
                    + " | Nama: " + data.getValue()
            );
        }

        System.out.println(
                "Total pelanggan: " + daftarPelanggan.size()
        );
    }

    public static void cariPenyewaan(Scanner input)
            throws DataPenyewaanException {

        System.out.println("\n=== CARI PENYEWAAN ===");

        System.out.print("Masukkan nama penyewa: ");
        String nama = input.nextLine();

        validasiData(nama, "Nama penyewa");

        boolean ditemukan = false;

        for (int i = 0; i < daftarSewa.size(); i++) {

            Lapangan lapangan = daftarSewa.get(i);

            if (lapangan.getNamaPenyewa()
                    .equalsIgnoreCase(nama)) {

                lapangan.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            throw new DataPenyewaanException(
                    "Data penyewaan tidak ditemukan!"
            );
        }
    }
}
