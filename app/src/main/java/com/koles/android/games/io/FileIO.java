package com.koles.android.games.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {

    InputStream readAssets(String fileName) throws IOException;

    InputStream readFile(String fileName) throws IOException;
    InputStream readFile(int i, String fileName) throws IOException;

    OutputStream writeFile(String fileName) throws IOException;
    OutputStream writeFile(int i, String fileName)
            throws IOException;
}
