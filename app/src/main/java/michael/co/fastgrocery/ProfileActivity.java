package michael.co.fastgrocery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textview.MaterialTextView;

public class ProfileActivity extends AppCompatActivity {
    private ImageView ivPictureProfile;
    private MaterialTextView tvUserNameTitle;
    private CheckBox cbVerifiedUser;
    private ImageButton ivBtnGoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        ivPictureProfile = (ImageView)findViewById(R.id.ivPictureProfile);
        ivPictureProfile.setImageResource(R.drawable.default_avatar);
        tvUserNameTitle = (MaterialTextView)findViewById(R.id.tvUserNameTitle);
        tvUserNameTitle.setText(getIntent().getStringExtra("USER_NAME"));
        cbVerifiedUser = (CheckBox)findViewById(R.id.cbVerifiedUser);
        cbVerifiedUser.setChecked(true);
        cbVerifiedUser.setEnabled(false);
        ivBtnGoBack = (ImageButton)findViewById(R.id.ivBtnGoBack);
        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        ivBtnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}