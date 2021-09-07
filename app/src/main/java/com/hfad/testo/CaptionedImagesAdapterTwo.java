package com.hfad.testo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CaptionedImagesAdapterTwo extends RecyclerView.Adapter<CaptionedImagesAdapterTwo.ViewHolder>
{
    private ArrayList<Pasta> list;
    private String [] captions;
    private int [] imageIds;

    public CaptionedImagesAdapterTwo(String[] captions, int [] imageIds)
    {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    public CaptionedImagesAdapterTwo(ArrayList<Pasta> l)
    {
        list=l;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView cardView;
        public ViewHolder(CardView v)
        {
            super(v);
            cardView = v;
        }
    }

    @NonNull
    @Override
    public CaptionedImagesAdapterTwo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image_two, parent, false);
        return  new ViewHolder(cv);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position)
    {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.info_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);

        TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cardView.getContext(), PastaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position);
                cardView.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount ()
    {
        return captions.length;
    }



}
