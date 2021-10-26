package com.koles.android.games.io;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class KolesFileIO implements FileIO{
    AssetManager assetManager;
    String externalStoragePath;
    String internalStoragePath;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public KolesFileIO(AssetManager assetManager){
        this.assetManager = assetManager;
        this.externalStoragePath = Environment.getExternalStorageDirectory().
                getAbsolutePath() + File.separator;
        this.internalStoragePath = Environment.getStorageDirectory().
                getAbsolutePath() + File.separator;
    }

    @Override
    public InputStream readAssets(String fileName) throws IOException {
        return assetManager.open(fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }



    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }

    @Override
    public InputStream readFile(int i, String fileName)
            throws IOException {
        return new FileInputStream(internalStoragePath + fileName);
    }

    @Override
    public OutputStream writeFile(int i, String fileName)
            throws IOException {
        return new FileOutputStream(internalStoragePath + fileName);
    }
}
