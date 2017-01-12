package com.foi.air1603.password_verification_module;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foi.air1603.password_verification_module.presenter.PasswordMainAcitivityPresenter;
import com.foi.air1603.password_verification_module.presenter.PasswordMainAcitivityPresenterImpl;
import com.foi.air1603.password_verification_module.view.PasswordView;

/**
 * Created by Korisnik on 12-Jan-17.
 */

public class PasswordMainActivity extends AppCompatActivity implements PasswordView {

    public PasswordMainActivity activity;
    private Button bPotvrdi;
    private EditText passInput;
    private PasswordMainAcitivityPresenter presenter;
    private String pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pass = getIntent().getStringExtra("pass");
        setContentView(R.layout.activity_password);
        passInput = (EditText) this.findViewById(R.id.etPasswordV);
        bPotvrdi = (Button) this.findViewById(R.id.bPotvrdi);
        presenter = new PasswordMainAcitivityPresenterImpl(this);
        bPotvrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flag = presenter.checkInputPass();
                if(!flag){
                    Toast.makeText(getApplicationContext(),
                            "Lozinka nije točna", Toast.LENGTH_LONG).show();
                } else {

                }

            }
        });
    }



    @Override
    public String getPassFromEditText() {
            return passInput.getText().toString();
    }

    @Override
    public String getPassFromApp() {
        return pass;
    }

}
