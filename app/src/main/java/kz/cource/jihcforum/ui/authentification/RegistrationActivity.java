package kz.cource.jihcforum.ui.authentification;

import static kz.cource.jihcforum.ui.database.StoreDatabase.COLUMN_EMAIL;
import static kz.cource.jihcforum.ui.database.StoreDatabase.COLUMN_NAME;
import static kz.cource.jihcforum.ui.database.StoreDatabase.COLUMN_PASSWORD;
import static kz.cource.jihcforum.ui.database.StoreDatabase.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import kz.cource.jihcforum.R;
import kz.cource.jihcforum.ui.database.StoreDatabase;

public class RegistrationActivity extends AppCompatActivity {

    EditText et_name, et_email, et_password, et_bd;
    Button btn_tirkelu;
    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btn_tirkelu= findViewById(R.id.btn_tirkelu);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_bd = findViewById(R.id.et_bd);
        et_password = findViewById(R.id.et_password);
        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();

        btn_tirkelu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_name.getText())){
                    et_name.setError("Fill in completely");
                    return;
                }
                if (TextUtils.isEmpty(et_email.getText())){
                    et_email.setError("Fill in completely");
                    return;
                }
                if (TextUtils.isEmpty(et_password.getText())){
                    et_password.setError("Fill in completely");
                    return;
                }
                if (TextUtils.isEmpty(et_bd.getText())){
                    et_bd.setError("Fill in completely");
                    return;
                }

                ContentValues values = new ContentValues();
                values.put(COLUMN_NAME, et_name.getText().toString());
                values.put(COLUMN_EMAIL, et_email.getText().toString());
                values.put(COLUMN_PASSWORD, et_password.getText().toString());
                sqLiteDatabase.insert(TABLE_NAME, null, values);

                Intent registrationIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(registrationIntent);

            }
        });
    }
}