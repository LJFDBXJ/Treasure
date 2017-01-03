package com.feicuiedu.hunttreasure.user.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.feicuiedu.hunttreasure.R;
import com.feicuiedu.hunttreasure.commons.RegexUtils;
import com.feicuiedu.hunttreasure.custom.AlertDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_Username)
    EditText mEtUsername;
    @BindView(R.id.et_Password)
    EditText mEtPassword;
    @BindView(R.id.btn_Login)
    Button mBtnLogin;
    private String mUsername;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // toolbar
        setSupportActionBar(mToolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.login);
        }

        mEtUsername.addTextChangedListener(textWatcher);
        mEtPassword.addTextChangedListener(textWatcher);

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mUsername = mEtUsername.getText().toString();
            mPassword = mEtPassword.getText().toString();
            boolean canLogin = !(TextUtils.isEmpty(mUsername)||TextUtils.isEmpty(mPassword));
            mBtnLogin.setEnabled(canLogin);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_Login)
    public void onClick() {

        if (RegexUtils.verifyUsername(mUsername)!=RegexUtils.VERIFY_SUCCESS){

            AlertDialogFragment.getInstances(
                    getString(R.string.username_error),
                    getString(R.string.username_rules))
                    .show(getSupportFragmentManager(),"usernameError");

            return;
        }
        if (RegexUtils.verifyPassword(mPassword)!=RegexUtils.VERIFY_SUCCESS){
            AlertDialogFragment.getInstances(
                    getString(R.string.password_error),
                    getString(R.string.password_rules))
                    .show(getSupportFragmentManager(),"passwordError");
            return;
        }

        // 去做业务逻辑的处理了
    }
}
