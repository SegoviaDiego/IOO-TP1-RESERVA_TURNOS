package com.company.DAO;

import java.io.*;
import java.util.List;

public class File {

    private String path;

    public File(String path) {
        this.path = "database/" + path;
    }

    public void save(List data) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List get() {
        List data = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            data = (List) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        }
        catch (EOFException e){
            e.printStackTrace();
            return data;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return data;

    }
}
