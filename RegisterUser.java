package com.company;

// Import Libraries
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class RegisterUser{
    //  Declare Users Fields
    public boolean login;
    private String username, password;

//    Used Setter to set username and password
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

//    Signup method is used to register the account and save in to file
    public void signup(){
        File file = new File("D:\\BookShop\\src\\com\\company\\Files\\user.txt");
        try {
            FileWriter w1 = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(w1);
            bw.write(username + "," + password + "\n");
            JOptionPane.showMessageDialog(null, "Success");
            bw.close();
            w1.close();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
    }

//    Login method is used to Enter the user_panel
    public void login(){
        try {
            Scanner read = new Scanner(new File("D:\\BookShop\\src\\com\\company\\Files\\user.txt"));
            read.useDelimiter("\\n|,");
            login = false;
            while (read.hasNext()) {
                String user = read.next();
                String pass = read.next();
                if (username.equals(user) && (password.equals(pass))) {
                    login = true;
                    break;
                }
            }
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
