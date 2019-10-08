package me.kverna.hjornetapp.ui.item;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.kverna.hjornetapp.R;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_item_list, container, false);
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ItemViewModel model = ViewModelProviders.of(this.getActivity()).get(ItemViewModel.class);
        model.getItems().observe(this, items ->
                view.setAdapter(new ItemRecyclerViewAdapter(items)));
        return view;
    }
}
