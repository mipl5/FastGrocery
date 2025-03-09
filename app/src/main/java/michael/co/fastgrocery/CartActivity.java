package michael.co.fastgrocery;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import michael.co.viewmodel.IntNumberOfMulViewModel;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rvCart;
    private MaterialTextView tvHowManyMultiply;
    private MaterialButton btnIncMul;
    private ImageButton ivBtnBuyNow;
    private ImageButton ivBtnGoBack;
    private MaterialButton btnDecMul;
    private IntNumberOfMulViewModel intNumberOfMulViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupViewModel();
        initializeViews();
    }

    private void setupViewModel() {
        intNumberOfMulViewModel = new ViewModelProvider(this).get(IntNumberOfMulViewModel.class);
    }

    private void initializeViews() {
        rvCart = (RecyclerView)findViewById(R.id.rvCart);
        tvHowManyMultiply = (MaterialTextView)findViewById(R.id.tvHowManyMultiply);
        btnIncMul = (MaterialButton)findViewById(R.id.btnIncMul);
        ivBtnBuyNow = (ImageButton) findViewById(R.id.ivBtnBuyNow);
        ivBtnGoBack = (ImageButton)findViewById(R.id.ivBtnGoBack);
        btnDecMul = (MaterialButton)findViewById(R.id.btnDecMul);
        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnIncMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intNumberOfMulViewModel.increment();
                intNumberOfMulViewModel.getMutableLiveData().observe(CartActivity.this, number ->{
                    tvHowManyMultiply.setText(String.valueOf(number.getNumberMul()));
                });
            }
        });
        btnDecMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intNumberOfMulViewModel.decrement();
                intNumberOfMulViewModel.getMutableLiveData().observe(CartActivity.this, number ->{
                    tvHowManyMultiply.setText(String.valueOf(number.getNumberMul()));
                });
            }
        });
        ivBtnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // temp empty space
            }
        });
        ivBtnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}