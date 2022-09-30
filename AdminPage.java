package com.company;

// Import Libraries
import javax.swing.*;
import java.io.*;
import java.io.IOException;

public class AdminPage {
//  Declare Book Fields
    private int id;
    private String name, author, cate;
    private int qty, price;

//    Create Constructor to set Fields
    public AdminPage(int id, String name, String author, String cate, int qty, int price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.cate = cate;
        this.qty = qty;
        this.price = price;
    }

//    Save method is used to save record in file
    void save(){
        File file = new File("D:\\BookShop\\src\\com\\company\\Files\\record.txt");
        try {
            FileWriter w1 = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(w1);
            bw.write(id + ";" + name + ";" + author + ";" + cate + ";" + qty + ";" + price + "\n");
            JOptionPane.showMessageDialog(null, "Your Product is Added");
            bw.close();
            w1.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

//    Delete method is used to Delete Selected record in file
    void delete() {
        String lineToRemove = id + ";" + name + ";" + author + ";" + cate + ";" + qty + ";" + price;
        System.out.println(lineToRemove);
        File File = new File("D:\\BookShop\\src\\com\\company\\Files\\record.txt");
        File tempFile = new File("D:\\BookShop\\src\\com\\company\\Files\\data.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(File));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals(lineToRemove)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();
            //Delete the original file
            if (!File.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(File)) {
                System.out.println("Could not rename file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
