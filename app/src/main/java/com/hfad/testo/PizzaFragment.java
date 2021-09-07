package com.hfad.testo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;
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


/**
 * A fragment representing a list of Items.
 */
public class PizzaFragment extends Fragment {
    public static final int REQUUEST_CODE=1;
    private DBHelper db;
    private AppCompatButton add;
    private RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pizzas));
//        setListAdapter(adapter);
//        return super.onCreateView(inflater, container, savedInstanceState);

        View pizzaRecycler = inflater.inflate(R.layout.pizza_fragment, container, false);
//        String[] pizzaNames = new String[Pizza.pizzas.length];
//        for (int i = 0; i < pizzaNames.length; i++) {
//            pizzaNames[i] = Pizza.pizzas[i].getName();
//        }
//        int[] pizzaImages = new int[Pizza.pizzas.length];
//        for (int i = 0; i < pizzaImages.length; i++) {
//            pizzaImages[i] = Pizza.pizzas[i].getImageResourceId();
//        }
        db=new DBHelper(getActivity());
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(db.selectAll());
        rv=pizzaRecycler.findViewById(R.id.pizza_recycler);
        rv.setAdapter(adapter);
//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position);
                getActivity().startActivity(intent);
            }
        });
        add=pizzaRecycler.findViewById(R.id.btAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PizzaAddActivity.class);
                startActivityForResult(intent,REQUUEST_CODE);
            }
        });
        return pizzaRecycler;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUUEST_CODE && resultCode == Activity.RESULT_OK) {

        }
    }
}