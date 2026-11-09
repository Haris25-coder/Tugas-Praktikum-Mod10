
package com.mycompany.sistemsewalapangan;

public class FasilitasUmum {

    private String fasilitasLapangan;
    private String fasilitasTambahan;

    public FasilitasUmum() {
        this.fasilitasLapangan = "Lampu dan tempat parkir";
        this.fasilitasTambahan = "Ruang tunggu dan toilet";
    }

    public void tampilkanFasilitas() {
        System.out.println("\n=== FASILITAS SISTEM SEWA ===");
        System.out.println("Fasilitas Lapangan : " + fasilitasLapangan);
        System.out.println("Fasilitas Tambahan : " + fasilitasTambahan);
    }
}
