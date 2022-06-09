package com.jack.mainactivity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {

    Button button;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        button = findViewById(R.id.button_take_picture);
        imageview = findViewById(R.id.imageview);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            pictureActivityResult.launch(intent);
        });

    }

    ActivityResultLauncher<Intent> pictureActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if (result.getData() != null) {


                        if (result.getData().getStringExtra("data") != null) {
                            byte[] bytes = result.getData().getByteArrayExtra("imageBytes");
                            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            imageview.setImageBitmap(bmp);

                        }
                    }
                }
            });
}