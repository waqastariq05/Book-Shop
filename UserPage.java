package com.company;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class UserPage {
    public void addToBill(String client_name, String data){
        File file = new File("D:\\BookShop\\src\\com\\company\\Files\\" + client_name + ".txt");
        try {
            FileWriter w1 = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(w1);
            bw.write(data);
            bw.close();
            w1.close();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
    }
}
