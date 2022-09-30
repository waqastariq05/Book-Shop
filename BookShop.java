package com.company;

// Import Libraries to use GUI
import javax.imageio.*;
import java.io.*;

// For Frame
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Extends Frame class to BookLoading to use Frame methods and Features
class BookShop extends Frame{
    int i = 0;
    //    initialize Label, Panel, Image, File, and Fonts
    Label top_label, bottom_label;
    JPanel top, center, bottom;
    JProgressBar progressBar;
    Font f1, f2;
    Image icon, image;

    //    Call a BookLoading Class Constructor
    BookShop() {
        super("Book Shop Management System");
        //  Frame Icon
        icon = Toolkit.getDefaultToolkit().getImage("D:\\BookShop\\src\\com\\company\\images\\3.png");
        //  Create objects of Font
        f1 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        f2 = new Font(Font.SANS_SERIF, Font.BOLD, 16);

        //  Window method is used to close frame
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //  create objects of Labels and set color and font
        top_label = new Label("Book Shop Management System");
        top_label.setFont(f1);
        top_label.setForeground(Color.WHITE);
        top_label.setBackground(new Color(255, 92, 6));

        bottom_label = new Label();
        bottom_label.setFont(f2);
        bottom_label.setForeground(Color.WHITE);
        bottom_label.setBackground(new Color(255, 92, 6));

        //  create objects of ProgressBar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);

        //  File class is used to upload image
        try {
            File originalImage = new File("D:\\BookShop\\src\\com\\company\\images\\book.png");
            image = ImageIO.read(originalImage);
        } catch (IOException e) {
            System.out.println("Cannot load the image");
            System.exit(0);
        }

        //  create objects of Panel
        top = new JPanel();
        top.add(top_label);

        bottom = new JPanel(new GridLayout(2,1,5,0));
        bottom.add(bottom_label);
        bottom.add(progressBar);
        bottom.setPreferredSize(new Dimension(520,40));

        //  Add Panels to the Frame
        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);

        //  Set Frame Properties
        setIconImage(icon);
        setBackground(new Color(255, 92, 6));
        setSize(520, 300);
        setLocation(370, 340);
        setVisible(true);
        setResizable(false);
    }

    // Method is used to set Image
    public void paint(Graphics g) {
        g.drawImage(image, 160, 65, null);
    }

    // Iterate Method is used to set Loading
    public void iterate() {
        try {
            for (i = 0; i <= 100; i++){
                Thread.sleep(20);
                progressBar.setValue(i);
                bottom_label.setText("Loading  " + Integer.toString(i) + "%");
            }
        } catch (Exception e) {
            System.out.println("sorry");
        }
        LoginPanel A = new LoginPanel();
        A.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        BookShop B = new BookShop();
        B.iterate();
    }
}
