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

public class RegisterActivity extends AppCompatActivity {

    private EditText editEmailCadastro, editConfirmaEmailCadastro, editSenhaCadastro, editConfirmaSenhaCadastro;
    private Button btnVoltarLogin, btnSalvar;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editEmailCadastro = findViewById(R.id.editEmailCadastro);
        editConfirmaEmailCadastro = findViewById(R.id.editConfirmaEmailCadastro);
        editSenhaCadastro = findViewById(R.id.editSenhaCadastro);
        editConfirmaSenhaCadastro = findViewById(R.id.editConfirmaSenhaCadastro);

        btnVoltarLogin = findViewById(R.id.btnVoltarLogin);
        btnVoltarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarMain = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intentVoltarMain);
            }
        });

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editEmailCadastro.getText().toString().isEmpty()){
                    ErrorMessage("Campo e-mail obrigatório!");
                } else if (editSenhaCadastro.getText().toString().isEmpty()) {
                    ErrorMessage("Campo senha obrigatório!");
                } else if (editConfirmaEmailCadastro.getText().toString().isEmpty()) {
                    ErrorMessage("Campo confirma e-mail obrigatório!");
                } else if (editConfirmaSenhaCadastro.getText().toString().isEmpty()) {
                    ErrorMessage("Campo confirma senha obrigatório!");
                } else if (!editEmailCadastro.getText().toString().equals(editConfirmaEmailCadastro.getText().toString())) {
                    ErrorMessage("Campos de e-mail diferem!");
                } else if (!editSenhaCadastro.getText().toString().equals(editConfirmaSenhaCadastro.getText().toString())) {
                    ErrorMessage("Campos de senha diferem!");

                } else{
                    userDAO = new UserDAO(RegisterActivity.this);

                    UserModel userModel = new UserModel();
                    userModel.setUsuario(editEmailCadastro.getText().toString());
                    userModel.setSenha(editSenhaCadastro.getText().toString());

                    if (userDAO.insert(userModel) != -1) {
                        alertDialogShow("Usuário salvo!", true);
                    } else {
                        alertDialogShow("Falha ao salvar usuário!", false);
                    }
                }
            }
        });
    }

    private void ErrorMessage (String message){
        alertDialogShow(message, false);
    }

    private void alertDialogShow(final String message, final boolean closeWindow) {
        AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
        alert.setTitle("Informação");
        alert.setIcon(ContextCompat.getDrawable(RegisterActivity.this, R.drawable.bandeira_rs));
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                if (closeWindow) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
        alert.create().show();
    }

}