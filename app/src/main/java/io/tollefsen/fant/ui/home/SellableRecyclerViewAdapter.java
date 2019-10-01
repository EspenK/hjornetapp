package io.tollefsen.fant.ui.home;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.tollefsen.fant.R;
import io.tollefsen.fant.data.model.Sellable;

import java.util.List;

/**
 */
public class SellableRecyclerViewAdapter extends RecyclerView.Adapter<SellableRecyclerViewAdapter.ViewHolder> {

    private final List<Sellable> mValues;

    SellableViewModel model;

    public SellableRecyclerViewAdapter(List<Sellable> items) {
        mValues = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_sellable, parent, false);

        model = ViewModelProviders.of((FragmentActivity) parent.getContext()).get(SellableViewModel.class);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.format("%d",mValues.get(position).getId()));
        holder.mContentView.setText(String.format("%s",mValues.get(position).getTitle()));
        holder.mView.setOnClickListener(v -> model.setSelected(holder.mItem));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Sellable mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
