package me.kverna.hjornetapp.ui.item;

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

import me.kverna.hjornetapp.R;


public class ItemDetailFragment extends Fragment {

    ImageView photo;
    TextView title;
    TextView description;
    TextView price;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemViewModel model = ViewModelProviders.of(this.getActivity()).get(ItemViewModel.class);
        model.getSelected().observe(this, item -> {
            String url = "http://192.168.1.87:8080/api/item/image?id=" + item.getId();
            Picasso.get().load(url).into(photo);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(item.getTitle());

            description.setText(item.getDescription());
            price.setText(String.format(Locale.getDefault(),"%.2f",item.getPrice()));
            title.setText(item.getTitle());
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_item_detail, container, false);

        photo = result.findViewById(R.id.photo);
        description = result.findViewById(R.id.description);
        price = result.findViewById(R.id.price);
        title = result.findViewById(R.id.title);

        return  result;
    }
}
