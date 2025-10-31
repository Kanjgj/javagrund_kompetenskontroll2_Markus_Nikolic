package org.example;

public class Product {
    private final int id;
    private final String namn;
    private final String kategori;
    private final double pris;

    public Product(int id, String namn, String kategori, double pris) {
        this.id = id;
        this.namn = namn;
        this.kategori = kategori;
        this.pris = pris;
    }

    public int getId() { return id; }
    public String getNamn() { return namn; }
    public String getKategori() { return kategori; }
    public double getPris() { return pris; }

    @Override
    public String toString() {
        return namn + " (" + kategori + ") - " + pris + " kr";
    }
}
