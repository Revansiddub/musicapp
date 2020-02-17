package com.gsatechworld.musicapp.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.databinding.DataBindingUtil;

import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.core.base.BaseActivity;
import com.gsatechworld.musicapp.databinding.ActivityLoginBinding;
import com.gsatechworld.musicapp.modules.home.HomeActivity;

import static com.gsatechworld.musicapp.utilities.Constants.TRAINER;
import static java.util.Objects.requireNonNull;

public class LoginActivity extends BaseActivity implements OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private ActivityLoginBinding binding;

    /* ------------------------------------------------------------- *
     * Overriding Base Activity Methods
     * ------------------------------------------------------------- */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding layout file with JAVA class*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        /*Setting listeners to the view*/
        binding.buttonLogin.setOnClickListener(this);
    }

    /* ------------------------------------------------------------- *
     * Overriding OnClickListener Method
     * ------------------------------------------------------------- */

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonLogin) {
            startActivity(new Intent(this, HomeActivity.class));
        }
    }
}
