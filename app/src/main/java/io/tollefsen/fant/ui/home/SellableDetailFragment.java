package io.tollefsen.fant.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import io.tollefsen.fant.R;


public class SellableDetailFragment extends Fragment {

    ImageView photo;
    TextView description;
    TextView price;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SellableViewModel model = ViewModelProviders.of(this.getActivity()).get(SellableViewModel.class);
        model.getSelected().observe(this, sellable -> {
            String url = "http://158.38.101.138/api/fant/photo/" + sellable.getPhotos().get(0);
            Picasso.get().load(url).into(photo);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(sellable.getTitle());
            description.setText(sellable.getDescription());

            price.setText(String.format(Locale.getDefault(),"%.2f",sellable.getPrice()));
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_sellable_detail, container, false);

        photo = result.findViewById(R.id.photo);
        description = result.findViewById(R.id.description);
        price = result.findViewById(R.id.price);

        return  result;
    }
}
