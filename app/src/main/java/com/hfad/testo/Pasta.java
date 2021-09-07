package com.hfad.testo;

public class Pasta
{
    private long id;
    private String name;
    private int imageResourceId;

    public static final Pasta[] pastas =
            {
                    new Pasta("Spaghetti Bolognese", R.drawable.spaghettibolognese),
                    new Pasta("Lasagne", R.drawable.lasagne)
            };
    Pasta(String name, int imageResourceId)
    {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }
    public String getName()
    {
        return name;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public long getId() {
        return id;
    }
}
