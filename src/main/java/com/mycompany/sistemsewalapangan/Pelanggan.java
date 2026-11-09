
package com.mycompany.sistemsewalapangan;

public class Pelanggan {

    private String id;
    private String nama;
    private String nomorTelepon;

    public Pelanggan(String id, String nama, String nomorTelepon) {
        this.id = id;
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void tampilkanInfo() {
        System.out.println("ID Pelanggan : " + id);
        System.out.println("Nama         : " + nama);
        System.out.println("No. Telepon  : " + nomorTelepon);
    }
}
