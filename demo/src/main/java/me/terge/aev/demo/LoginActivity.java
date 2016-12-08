package me.terge.aev.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vip.xioix.aev.library.ValidatorHelper;
import vip.xioix.aev.library.validator.DigitLengthRangeValidator;
import vip.xioix.aev.library.validator.EmailValidator;
import vip.xioix.aev.library.validator.EmptyValidator;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInput()){
                    Toast.makeText(LoginActivity.this, "校验通过", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean checkInput() {
        return checkEmailInput() && checkPwdInput();
    }

    private boolean checkPwdInput() {
        return ValidatorHelper.from(mPasswordView)
                .set(new EmptyValidator("请输入密码"))
                .and(new DigitLengthRangeValidator("密码要求6~20位",6,20))
                .fix("")
                .check(true);
    }

    private boolean checkEmailInput() {
        return ValidatorHelper.from(mEmailView)
                .set(new EmptyValidator("请输入邮箱"))
                .and(new EmailValidator("邮箱格式有误"))
                .check(true);
    }





}

