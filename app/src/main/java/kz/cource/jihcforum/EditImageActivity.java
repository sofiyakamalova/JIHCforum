package kz.cource.jihcforum;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class EditImageActivity extends Activity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView backgroundImageView;
    private ImageView userPhotoImageView;
    private Button saveButton;

    private Uri backgroundImageUri;
    private Uri userPhotoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);

        backgroundImageView = findViewById(R.id.backgroundImageView);
        userPhotoImageView = findViewById(R.id.userPhotoImageView);
        saveButton = findViewById(R.id.saveButton);

        backgroundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(PICK_IMAGE_REQUEST);
            }
        });

        userPhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(PICK_IMAGE_REQUEST + 1);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Сохранение изменений и передача результатов обратно во фрагмент профиля
                Intent resultIntent = new Intent();
                resultIntent.putExtra("backgroundUri", backgroundImageUri);
                resultIntent.putExtra("userPhotoUri", userPhotoUri);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void openGallery(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                switch (requestCode) {
                    case PICK_IMAGE_REQUEST:
                        backgroundImageView.setImageBitmap(bitmap);
                        backgroundImageUri = imageUri;
                        break;
                    case PICK_IMAGE_REQUEST + 1:
                        userPhotoImageView.setImageBitmap(bitmap);
                        userPhotoUri = imageUri;
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
