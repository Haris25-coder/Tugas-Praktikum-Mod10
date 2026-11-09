
package com.mycompany.sistemsewalapangan;

import java.util.Scanner;
import java.util.InputMismatchException;

public class SIstemSewaLapangan {

    static Scanner input = new Scanner(System.in);

    static ManajemenSewaLapangan sistem =
            new ManajemenSewaLapangan();

    public static void main(String[] args) {

        int pilihan = 0;

        do {
            System.out.println("\n================================");
            System.out.println("       SISTEM SEWA LAPANGAN");
            System.out.println("================================");
            System.out.println("1. Tambah Penyewaan");
            System.out.println("2. Tampilkan Semua Lapangan");
            System.out.println("3. Tambah Pelanggan");
            System.out.println("4. Tampilkan Semua Pelanggan");
            System.out.println("5. Cari Pelanggan");
            System.out.println("6. Tampilkan Fasilitas");
            System.out.println("7. Jalankan Transaksi");
            System.out.println("8. Tampilkan Fasilitas Lapangan");
            System.out.println("9. Keluar");
            System.out.println("================================");
            System.out.print("Pilih menu: ");

            try {

                pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {

                    case 1:
                        tambahPenyewaan();
                        break;

                    case 2:
                        sistem.tampilkanLapangan();
                        break;

                    case 3:
                        tambahPelanggan();
                        break;

                    case 4:
                        sistem.tampilkanPelanggan();
                        break;

                    case 5:
                        cariPelanggan();
                        break;

                    case 6:
                        sistem.tampilkanFasilitas();
                        break;

                    case 7:
                        jalankanTransaksi();
                        break;

                    case 8:
                        sistem.tampilkanSemuaFasilitas();
                        break;

                    case 9:
                        System.out.println(
                                "Terima kasih telah menggunakan program.");
                        break;

                    default:
                        System.out.println("Menu tidak tersedia!");
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "ERROR: Input harus berupa angka!");

                input.nextLine();

                pilihan = 0;

            } catch (DataPenyewaanException e) {

                System.out.println("ERROR: " + e.getMessage());

            } finally {

                System.out.println(
                        "[FINALLY] Proses menu telah diperiksa.");
            }

        } while (pilihan != 9);

        input.close();
    }

    public static void tambahPenyewaan()
            throws DataPenyewaanException {

        System.out.println("\n=== TAMBAH PENYEWAAN ===");

        System.out.print("Nama penyewa: ");
        String nama = input.nextLine();

        validasi(nama, "Nama penyewa");

        System.out.print("Tanggal sewa: ");
        String tanggal = input.nextLine();

        validasi(tanggal, "Tanggal sewa");

        System.out.print("Durasi (jam): ");
        int durasi = input.nextInt();
        input.nextLine();

        if (durasi <= 0) {
            throw new DataPenyewaanException(
                    "Durasi harus lebih dari 0!");
        }

        System.out.println("1. Futsal");
        System.out.println("2. Badminton");
        System.out.print("Pilih jenis lapangan: ");

        int jenis = input.nextInt();
        input.nextLine();

        Lapangan lapangan;

        if (jenis == 1) {

            System.out.print("Ukuran lapangan: ");
            String ukuran = input.nextLine();

            validasi(ukuran, "Ukuran lapangan");

            lapangan = new Futsal(
                    nama, tanggal, durasi, ukuran);

        } else if (jenis == 2) {

            System.out.print("Tipe lapangan: ");
            String tipe = input.nextLine();

            validasi(tipe, "Tipe lapangan");

            lapangan = new Badminton(
                    nama, tanggal, durasi, tipe);

        } else {

            throw new DataPenyewaanException(
                    "Jenis lapangan tidak tersedia!");
        }

        sistem.tambahLapangan(lapangan);

        System.out.println("Jumlah lapangan: "
                + sistem.jumlahLapangan());
    }

    public static void tambahPelanggan()
            throws DataPenyewaanException {

        System.out.println("\n=== TAMBAH PELANGGAN ===");

        System.out.print("ID pelanggan: ");
        String id = input.nextLine();

        validasi(id, "ID pelanggan");

        System.out.print("Nama pelanggan: ");
        String nama = input.nextLine();

        validasi(nama, "Nama pelanggan");

        System.out.print("Nomor telepon: ");
        String telepon = input.nextLine();

        validasi(telepon, "Nomor telepon");

        Pelanggan pelanggan = new Pelanggan(
                id, nama, telepon);

        sistem.tambahPelanggan(pelanggan);

        System.out.println("Jumlah pelanggan: "
                + sistem.jumlahPelanggan());
    }

    public static void cariPelanggan()
            throws DataPenyewaanException {

        System.out.println("\n=== CARI PELANGGAN ===");

        System.out.print("Masukkan ID: ");
        String id = input.nextLine();

        validasi(id, "ID pelanggan");

        Pelanggan pelanggan = sistem.cariPelanggan(id);

        System.out.println("\nData ditemukan:");
        pelanggan.tampilkanInfo();
    }

    public static void jalankanTransaksi()
            throws DataPenyewaanException {

        System.out.println("\n=== PROSES TRANSAKSI ===");

        sistem.tampilkanPelanggan();

        System.out.print("\nMasukkan ID pelanggan: ");
        String id = input.nextLine();

        validasi(id, "ID pelanggan");

        sistem.tampilkanLapangan();

        System.out.print("\nPilih nomor lapangan: ");
        int nomor = input.nextInt();
        input.nextLine();

        sistem.jalankanTransaksi(id, nomor);
    }

    public static void validasi(String data, String keterangan)
            throws DataPenyewaanException {

        if (data == null || data.trim().isEmpty()) {
            throw new DataPenyewaanException(
                    keterangan + " tidak boleh kosong!");
        }
    }
}
