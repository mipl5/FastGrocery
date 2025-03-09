package michael.co.fastgrocery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import michael.co.model.GroceryShop;
import michael.co.model.GroceryShops;
import michael.co.model.Item;

public class RelatedActivity extends AppCompatActivity {
    private RecyclerView rvGroceryShops;
    private GroceryShops groceryShops;
    private GroceryShopsAdapter groceryShopsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_related);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
        getAllGroceryShops();
        setRecyclerView();
    }

    private void initializeViews() {

    }

    private void getAllGroceryShops() {
        groceryShops = new GroceryShops();
        groceryShops.add(new GroceryShop("7Eleven", "https://www.7-eleven.com/", R.drawable.seven_eleven));
        groceryShops.add(new GroceryShop("Trader Joe's", "https://locations.traderjoes.com/", R.drawable.trader_joes));
    }

    private void setRecyclerView() {
        GroceryShopsAdapter.OnItemClickListener listener = new GroceryShopsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(GroceryShop groceryShop) {
                String data = groceryShop.getName();
                openGoogleMapsSearch(data);
            }
        };
        groceryShopsAdapter = new GroceryShopsAdapter(RelatedActivity.this, groceryShops, R.layout.grocery_shop_single_layout, listener);
        rvGroceryShops.setAdapter(groceryShopsAdapter);
        rvGroceryShops.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void openGoogleMapsSearch(String data) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(data));

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // Fallback: Open the URI in a browser if Google Maps is not available
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            startActivity(browserIntent);
        }
    }
}