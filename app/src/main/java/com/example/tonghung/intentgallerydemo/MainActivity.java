package com.example.tonghung.intentgallerydemo;

import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tonghung.intentgallerydemo.Adapter.PictureAdapter;
import com.example.tonghung.intentgallerydemo.Database.DatabaseHelper;
import com.example.tonghung.intentgallerydemo.object.Picture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnSelect, btnSave;
    private ImageView iv;
    private EditText et;
    private ListView lv;
    private List<Picture> listPic;
    private DatabaseHelper db = new DatabaseHelper(this);
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelect = (Button) findViewById(R.id.buttonSelect);
        btnSave = (Button) findViewById(R.id.buttonSave);
        iv = (ImageView) findViewById(R.id.imageView);
        lv = (ListView) findViewById(R.id.listView);
        et = (EditText) findViewById(R.id.editTextName);

        choosePictureFromGallery();
        addPic();
        showPic();
    }

    private void choosePictureFromGallery(){
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Choose Picture"), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                iv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPic(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picture pic = new Picture();
                pic.setName(et.getText().toString());
                pic.setBitmap(bitmap);

                if(db.insertPic(pic) != -1){
                    Toast.makeText(MainActivity.this, "Insert pic successful", Toast.LENGTH_SHORT)
                            .show();
                    clearForm();
                    showPic();
                }else{
                    Toast.makeText(MainActivity.this, "Insert pic failure", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void showPic(){
        Cursor c = db.selectPic();
        listPic = new ArrayList<>();
        while(c.moveToNext()){
            listPic.add(new Picture(c.getInt(0), c.getString(1), bytes2Bitmap(c.getBlob(2))));
        }

        lv.setAdapter(new PictureAdapter(this, listPic));
    }

    private void clearForm(){
        et.setText(null);
        iv.setImageBitmap(null);
    }

    private Bitmap bytes2Bitmap(byte[] bytes){
        return bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
