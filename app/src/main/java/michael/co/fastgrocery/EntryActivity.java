package michael.co.fastgrocery;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;

import michael.co.viewmodel.ViewModelName;

public class EntryActivity extends AppCompatActivity {
    // views as members:
    private EditText etGetName;
    private MaterialSwitch msIAmNotARobotConfirmation;
    private MaterialButton btnLetsShopEntry;
    // live data:
    private ViewModelName viewModelName;
    // constructor:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entry);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // methods calling:
        initializeViews();
        setupViewModel();
    }

    private void setupViewModel() {
        viewModelName = new ViewModelProvider(this).get(ViewModelName.class);
        viewModelName.getMutableLiveData().observe(EntryActivity.this, nameStore -> {
            if (nameStore != null)
                etGetName.setText(String.valueOf(nameStore));
            else
                etGetName.setText(String.valueOf(null));
        });
    }

    private void initializeViews() { // method which binds xml and views
        etGetName = (EditText)findViewById(R.id.etGetName);
        msIAmNotARobotConfirmation = (MaterialSwitch)findViewById(R.id.msIAmNotARobotConfirmation);
        btnLetsShopEntry = (MaterialButton)findViewById(R.id.btnLetsShopEntry);
        // after enabling views, get button enabled and its function:
        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnLetsShopEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etGetName.getText().toString().isEmpty()){
                    Toast.makeText(EntryActivity.this, "Enter your name before proceeding", Toast.LENGTH_SHORT).show();
                }
                else if (!msIAmNotARobotConfirmation.isChecked()){
                    Toast.makeText(EntryActivity.this, "We need to make sure that you are not a robot", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(EntryActivity.this, MainActivity.class);
                    viewModelName.getMutableLiveData().observe(EntryActivity.this, name -> {
                        intent.putExtra("USER_NAME", String.valueOf(name));
                    });
                    startActivity(intent);
                }
            }
        });
        etGetName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModelName.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}