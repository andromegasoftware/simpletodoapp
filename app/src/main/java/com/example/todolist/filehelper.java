package com.example.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class filehelper {

       public static final String FILENAME = "listinfo.dat";

       public static void writeData(ArrayList<String> item, Context context)
       {
           try {
                   FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                   ObjectOutputStream oos = new ObjectOutputStream(fos);
                   oos.writeObject(item);
                   oos.close();
           }
           catch (FileNotFoundException e)
           {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

       public static ArrayList<String> readData(Context context)
       {
           ArrayList<String> itemList = null;
           try {
               FileInputStream fit = context.openFileInput(FILENAME);
               ObjectInputStream ois = new ObjectInputStream(fit);
               itemList = (ArrayList<String>) ois.readObject();
           } catch (FileNotFoundException e) {

               itemList = new ArrayList<>();
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }

           return itemList;
       }
}
