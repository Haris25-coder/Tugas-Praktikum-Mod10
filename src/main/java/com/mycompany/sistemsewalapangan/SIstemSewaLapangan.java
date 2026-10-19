
package com.mycompany.sistemsewalapangan;

import java.util.Scanner;

public class SIstemSewaLapangan {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Lapangan[] daftarSewa = new Lapangan[10];

        int jumlahData = 0;
        int pilihan;

        do {
            System.out.println("\n==============================");
            System.out.println("      SISTEM SEWA LAPANGAN");
            System.out.println("==============================");
            System.out.println("1. Tambah Penyewaan");
            System.out.println("2. Tampilkan Semua Data");
            System.out.println("3. Abstract Method & Interface");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");

            if (!input.hasNextInt()) {
                System.out.println("Masukkan angka menu yang valid.");
                input.nextLine();
                pilihan = 0;
                continue;
            }

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                case 1:
                    if (jumlahData >= daftarSewa.length) {
                        System.out.println("Data sudah penuh!");
                        break;
                    }

                    System.out.print("Nama penyewa: ");
                    String nama = input.nextLine();

                    System.out.print("Tanggal sewa: ");
                    String tanggal = input.nextLine();

                    System.out.print("Durasi (jam): ");
                    if (!input.hasNextInt()) {
                        System.out.println("Durasi harus berupa angka.");
                        input.nextLine();
                        break;
                    }

                    int durasi = input.nextInt();
                    input.nextLine();

                    if (nama.trim().isEmpty()
                            || tanggal.trim().isEmpty()
                            || durasi <= 0) {
                        System.out.println("Data tidak valid!");
                        break;
                    }

                    System.out.println("1. Futsal");
                    System.out.println("2. Badminton");
                    System.out.print("Jenis lapangan: ");

                    int jenis = input.nextInt();
                    input.nextLine();

                    if (jenis == 1) {
                        System.out.print("Ukuran lapangan: ");
                        String ukuran = input.nextLine();

                        if (ukuran.trim().isEmpty()) {
                            System.out.println("Ukuran tidak boleh kosong.");
                            break;
                        }

                        daftarSewa[jumlahData] =
                                new Futsal(nama, tanggal, durasi, ukuran);
                        jumlahData++;

                        System.out.println("Data Futsal berhasil ditambahkan.");

                    } else if (jenis == 2) {
                        System.out.print("Tipe lapangan: ");
                        String tipe = input.nextLine();

                        if (tipe.trim().isEmpty()) {
                            System.out.println("Tipe tidak boleh kosong.");
                            break;
                        }

                        daftarSewa[jumlahData] =
                                new Badminton(nama, tanggal, durasi, tipe);
                        jumlahData++;

                        System.out.println(
                                "Data Badminton berhasil ditambahkan."
                        );

                    } else {
                        System.out.println("Jenis lapangan tidak tersedia.");
                    }
                    break;

                case 2:
                    if (jumlahData == 0) {
                        System.out.println("Belum ada data.");
                        break;
                    }

                    for (int i = 0; i < jumlahData; i++) {
                        System.out.println("\nData ke-" + (i + 1));
                        daftarSewa[i].tampilkanInfo();
                    }

                    System.out.println(
                            "Total penyewaan: "
                            + Lapangan.getJumlahSewa()
                    );
                    break;

                case 3:
                    if (jumlahData == 0) {
                        System.out.println("Tambahkan data dahulu.");
                        break;
                    }

                    for (int i = 0; i < jumlahData; i++) {
                        Lapangan lapangan = daftarSewa[i];

                        System.out.println(
                                "\nPenyewa: " + lapangan.getNamaPenyewa()
                        );

                        // Abstract method
                        lapangan.tampilkanFasilitas();

                        // Pengecekan interface pertama
                        if (lapangan instanceof DapatDiPesan) {
                            DapatDiPesan pesanan =
                                    (DapatDiPesan) lapangan;
                            pesanan.prosesPemesanan();
                        }

                        // Pengecekan interface kedua
                        if (lapangan instanceof DapatDiberiDiskon) {
                            DapatDiberiDiskon diskon =
                                    (DapatDiberiDiskon) lapangan;
                            diskon.tampilkanDiskon();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Program selesai. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 4);

        input.close();
    }
}
