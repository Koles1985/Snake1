package com.koles.android.games.mechanicsgame;

import com.koles.android.games.io.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Settings {

    public static boolean soundEnabled = true;
    public static int[] highScores = new int[]{100, 80, 50, 30, 10};

    public static void load(FileIO files){
        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(files.readFile(".snake")));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            for(int i = 0; i < highScores.length; i++){
                highScores[i] = Integer.parseInt(in.readLine());
            }
        }catch(IOException e){
            System.out.println(e.getMessage() + " - Settings.load() - exception");
        }catch(NumberFormatException ex){
            System.out.println(ex.getMessage() + " - Settings.load() number format exception");
        }finally {
            try{
                if(in != null){
                    in.close();
                }
            }catch(IOException e){
                System.out.println(e.getMessage() + " - Settings.load()finally");
            }
        }
    }

    public static void save(FileIO files){
        BufferedWriter out = null;
        try{
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".snake")));
            out.write(Boolean.toString(soundEnabled));
            for(int i = 0; i < highScores.length; i++){
                out.write(Integer.toString(highScores[i]));
            }
        }catch(IOException e){
            System.out.println(e.getMessage() + " - Settings.save() - exception");
        }finally{
            try{
                if(out != null){
                    out.close();
                }
            }catch(IOException e){
                System.out.println(e.getMessage() + " - Settings.save() - exception - finally");
            }
        }
    }

    public static void addScore(int score){
        for(int i = 0; i < highScores.length; i++){
            if(highScores[i] < score){
                for(int j = 4; j > i; j--){
                    highScores[j] = highScores[j - 1];
                    highScores[i] = score;
                    break;
                }
            }
        }
    }
}
