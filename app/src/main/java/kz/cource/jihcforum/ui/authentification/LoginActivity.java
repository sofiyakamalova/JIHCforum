package kz.cource.jihcforum.ui.authentification;

import static kz.cource.jihcforum.ui.database.StoreDatabase.COLUMN_EMAIL;
import static kz.cource.jihcforum.ui.database.StoreDatabase.COLUMN_NAME;
import static kz.cource.jihcforum.ui.database.StoreDatabase.COLUMN_PASSWORD;
import static kz.cource.jihcforum.ui.database.StoreDatabase.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import kz.cource.jihcforum.MainActivity;
import kz.cource.jihcforum.R;
import kz.cource.jihcforum.ui.database.StoreDatabase;
import kz.cource.jihcforum.ui.profile.ProfileFragment;

public class LoginActivity extends AppCompatActivity {

    Button buttonlogin, buttontirkelu;

    EditText et_email, et_password;

    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonlogin = findViewById(R.id.btn_login);
        buttontirkelu = findViewById(R.id.btn_tirkelu);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();

        buttontirkelu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrationIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(registrationIntent);
            }
        });

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(et_email.getText())){
                    et_email.setError(getResources().getString(R.string.full_enter));
                    return;
                }
                if (TextUtils.isEmpty(et_password.getText())){
                    et_password.setError(getResources().getString(R.string.full_enter));
                    return;
                }

                String uEmail = et_email.getText().toString();
                String uPass = et_password.getText().toString();

                Cursor loginCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                        COLUMN_EMAIL+"=? AND "+COLUMN_PASSWORD+"=?", new String[]{uEmail, uPass});

                if (loginCursor != null && loginCursor.getCount() > 0){
                    loginCursor.moveToFirst();
                    @SuppressLint("Range") String userName = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_NAME));

                    Toast.makeText(LoginActivity.this, "Welcome, " +userName, Toast.LENGTH_LONG).show();
                    Intent registrationIntent = new Intent(LoginActivity.this, MainActivity.class);

                    registrationIntent.putExtra("name", userName);
                    registrationIntent.putExtra("email", uEmail);
                    registrationIntent.putExtra("password", uPass);

                    startActivity(registrationIntent);
                }else{
                    Toast.makeText(LoginActivity.this, R.string.incorrect,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }




}






















