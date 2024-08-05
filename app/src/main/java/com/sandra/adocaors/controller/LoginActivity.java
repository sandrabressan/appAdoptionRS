package com.sandra.adocaors.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.sandra.adocaors.R;
import com.sandra.adocaors.model.database.UserDAO;
import com.sandra.adocaors.model.database.UserModel;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmailAcesso, editSenhaAcesso;
    private Button btnEntrar, btnRegistrar;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmailAcesso = findViewById(R.id.editEmailAcesso);
        editSenhaAcesso = findViewById(R.id.editSenhaAcesso);

        btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editEmailAcesso.getText().toString().isEmpty()){
                    alertDialogShow("Campo e-mail obrigatório!");
                } else if (editSenhaAcesso.getText().toString().isEmpty()) {
                    alertDialogShow("Campo senha obrigatório!");
                } else {

                    UserModel userModel = new UserModel();
                    userModel.setUsuario(editEmailAcesso.getText().toString());
                    userModel.setSenha(editSenhaAcesso.getText().toString());
                    userDAO = new UserDAO(LoginActivity.this);

                    if (userDAO.select(userModel)) {
                        Intent intent = new Intent(LoginActivity.this, DetailActivity.class);
                        intent.putExtra("Local", getIntent().getStringExtra("Local"));
                        intent.putExtra("Name", getIntent().getStringExtra("Name"));
                        intent.putExtra("Image", getIntent().getIntExtra("Image", 0));
                        intent.putExtra("Desc", getIntent().getStringExtra("Desc"));
                        intent.putExtra("Adopted", true);
                        startActivity(intent);
                    } else {
                        alertDialogShow("Usuário ou senha inválidos!");
                    }
                }
            }
        });

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    private void alertDialogShow(final String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
        alert.setTitle("Erro");
        alert.setIcon(ContextCompat.getDrawable(LoginActivity.this, R.drawable.bandeira_rs));
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.create().show();
    }
}