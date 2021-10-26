package com.koles.android.games.main;

import android.app.Activity;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.koles.android.games.io.KolesFileIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main extends Activity {
    private TextView textView;
    AssetManager assetManager;
    KolesFileIO kolesFileIO;
    OutputStream outputStream;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        textView = new TextView(this);
        textView.setText("Hello Mr. Snake");
        assetManager = getAssets();
        kolesFileIO = new KolesFileIO(assetManager);
        try {
            outputStream = kolesFileIO.writeFile("Hello.txt");
            String path = outputStream.getClass().getName().toString();
            System.out.println(path + " - KolesFileIO v1");
        }catch(IOException e){
            try{
                outputStream = kolesFileIO.writeFile(1, "Hello.txt");
                String path = outputStream.getClass().getName().toString();
                System.out.println(path + " - KolesFileIO v2");
            }catch(IOException ex){
                System.out.println(ex.getMessage() + " - ХЗ");
            }
            System.out.println(e.getMessage() + "111");
        }

        setContentView(textView);
    }


}
