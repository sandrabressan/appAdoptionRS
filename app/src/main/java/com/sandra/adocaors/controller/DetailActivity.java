package com.sandra.adocaors.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandra.adocaors.R;

public class DetailActivity extends AppCompatActivity {

    TextView detailLocal, detailName, detailDesc;
    ImageView detailImage;
    Button btnVoltarHome, btnAdotar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailLocal = findViewById(R.id.detailLocal);
        detailName = findViewById(R.id.detailName);
        detailImage = findViewById(R.id.detailImage);
        detailDesc = findViewById(R.id.detailDesc);

        btnVoltarHome = findViewById(R.id.btnVoltarHome);
        btnVoltarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarHome = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intentVoltarHome);
            }
        });

        btnAdotar = findViewById(R.id.btnAdotar);
        btnAdotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdotar = new Intent(DetailActivity.this, LoginActivity.class);
                intentAdotar.putExtra("Local", detailLocal.getText().toString());
                intentAdotar.putExtra("Name", detailName.getText().toString());
                intentAdotar.putExtra("Image", getIntent().getIntExtra("Image", 0));
                intentAdotar.putExtra("Desc", detailDesc.getText().toString());
                startActivity(intentAdotar);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            detailLocal.setText(extras.getString("Local"));
            detailName.setText(extras.getString("Name"));
            detailImage.setImageResource(extras.getInt("Image"));
            detailDesc.setText(extras.getString("Desc"));

            boolean adopted = extras.getBoolean("Adopted", false);
            if (adopted) {
                showAdoptionAlertDialog();
            }
        }
    }

    private void showAdoptionAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Animal Adotado");
        builder.setIcon(ContextCompat.getDrawable(DetailActivity.this, R.drawable.bandeira_rs));
        builder.setMessage("Parabéns! Você adotou " + detailName.getText().toString() + "!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
