package com.hfad.testo;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class PastaFragment extends Fragment
{
    private PastaDataBaseHelper db;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pasta));
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);*/

        View pastaRecycler = inflater.inflate(R.layout.pasta_fragment, container, false);
        db= new PastaDataBaseHelper(getActivity());
        CaptionedImagesAdapterTwo adapter = new CaptionedImagesAdapterTwo(db.selectAll());
        rv=pastaRecycler.findViewById(R.id.pasta_recycler);
        rv.setAdapter(adapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        /*RecyclerView pastaRecycler = (RecyclerView) inflater.inflate(R.layout.pasta_fragment, container, false);
        String [] pastaNames = new String[Pasta.pastas.length];
        for (int i = 0; i<pastaNames.length; i++)
        {
            pastaNames[i] = Pasta.pastas[i].getName();
        }
        int [] pastaImages = new int[Pasta.pastas.length];
        for(int i = 0; i<pastaImages.length; i++)
        {
            pastaImages[i] = Pasta.pastas[i].getImageResourceId();
        }
        CaptionedImagesAdapterTwo adapterTwo = new CaptionedImagesAdapterTwo(pastaNames, pastaImages);
        pastaRecycler.setAdapter(adapterTwo);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pastaRecycler.setLayoutManager(layoutManager);*/
        return pastaRecycler;
    }
}