package com.hfad.testo;

public class Pizza
{
    private long id;
    private String name;
    private int imageResourceId;
//    public static final Pizza[] pizzas = {new Pizza("Diavolo", R.drawable.diavolo),
//            new Pizza("Funghi", R.drawable.funghi)};

    public Pizza(long id, String name, int imageResourceId) {
        this.id = id;
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    private Pizza(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public long getId() {
        return id;
    }
}
