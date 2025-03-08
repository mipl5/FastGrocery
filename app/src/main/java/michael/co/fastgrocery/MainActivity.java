package michael.co.fastgrocery;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import michael.co.model.Item;
import michael.co.model.Items;

public class MainActivity extends AppCompatActivity {
    // view class members:
    private RecyclerView rvItems;
    private MaterialButton btnAddToCart;
    private Toolbar toolbar;
    private Items items;
    private ItemsAdapter itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
        getAllItems();
        setRecyclerView();
    }

    private void setRecyclerView() {
        ItemsAdapter.OnItemClickListener listener = new ItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Item item) {
                Toast.makeText(MainActivity.this, "Name: " + item.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        ItemsAdapter.OnItemLongClickListener longListener = new ItemsAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(Item item) {
                Toast.makeText(MainActivity.this,
                        "Price: $" + String.valueOf(item.getPrice()),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        };
        itemsAdapter = new ItemsAdapter(MainActivity.this, items, R.layout.item_single_layout, listener, longListener);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getAllItems() {
        items = new Items();
        items.add(new Item("Banana", 13.4));
        items.add(new Item("Orange", 5.6));
    }

    private void initializeViews() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvItems = (RecyclerView)findViewById(R.id.rvItems);
        btnAddToCart = (MaterialButton)findViewById(R.id.btnAddToCart);
        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.mnu_get_cart){
            //
        }
        else if (id == R.id.mnu_get_my_profile){
            //
        }
        else if (id == R.id.mnu_get_exit){
            finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }
}