package io.tollefsen.fant.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.tollefsen.fant.R;

/**
 * A fragment representing a list of Items.
 */
public class SellableFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.fragment_sellable_list, container, false);
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        SellableViewModel model = ViewModelProviders.of(this.getActivity()).get(SellableViewModel.class);
        model.getSellables().observe(this, sellables ->
                view.setAdapter(new SellableRecyclerViewAdapter(sellables)));
        return view;
    }
}
