
package com.mycompany.sistemsewalapangan;

public class TransaksiPenyewaan {

    public void prosesTransaksi(
            Pelanggan pelanggan, Lapangan lapangan) {

        if (pelanggan == null || lapangan == null) {
            System.out.println("Transaksi gagal!");
            return;
        }

        System.out.println("\n=== TRANSAKSI PENYEWAAN ===");

        System.out.println("Data pelanggan:");
        pelanggan.tampilkanInfo();

        System.out.println("\nData lapangan:");
        lapangan.tampilkanInfo();

        System.out.println("\nStatus transaksi: BERHASIL");
    }
}
