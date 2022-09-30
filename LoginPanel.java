package com.company;

// Import Libraries to use GUI
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;

//  Extends Frame class to LoginPanel to use Frame methods and Features
public class LoginPanel extends Frame implements ActionListener, ItemListener {
//    Declare Frame Fields to use
    JTextField field_1;
    JPasswordField field_2;
    JCheckBox showPassword;
    Label label_east, user_label, pass_label;
    JLabel label_west, picLabel;
    Panel panel1, panel2;
    Choice choice;
    JButton button, button2;
    Font f1, f2, f3;
    Image icon, image;


//  Create Method to use TextField and CheckBox
    public void loginTextField(){
        field_1 = new JTextField();
        field_1.setPreferredSize(new Dimension(200,40));
        field_2 = new JPasswordField();
        field_2.setPreferredSize(new Dimension(200,40));

        showPassword = new JCheckBox("Show Password");
        showPassword.setPreferredSize(new Dimension(300,30));
        showPassword.setHorizontalAlignment(SwingConstants.CENTER);
        showPassword.setBackground(new Color(208, 208, 208, 255));
        showPassword.addActionListener(this);
    }

// Create Method to use Labels and set color and font
    public void loginLabel(){
        label_west = new JLabel("<html>Large Stock <br/>Fare Price <br/>Nice Facility</html>", JLabel.CENTER);
        label_west.setFont(f1);
        label_west.setPreferredSize(new Dimension(190, 400));
        label_west.setOpaque(true);
        label_west.setBackground(new Color(255, 92, 6));
        label_west.setForeground(Color.WHITE);

        label_east = new Label("Book Shop Software", Label.CENTER);
        label_east.setFont(f1);
        label_east.setForeground(new Color(255,92,6));
        label_east.setPreferredSize(new Dimension(350,40));

        // Create Username and PassWord Label Object
        user_label = new Label("UserName");
        user_label.setPreferredSize(new Dimension(100,40));
        pass_label = new Label("Password");
        pass_label.setPreferredSize(new Dimension(100,40));
    }

//  Create Method to use Buttons
    public void loginButton(){
        button = new JButton("Login");
        button.setPreferredSize(new Dimension(100,30));
        button.addActionListener(this);
        button2 = new JButton("Register");
        button2.setPreferredSize(new Dimension(100,30));
        button2.addActionListener(this);
        button2.setVisible(false);
    }

//  Create Method to use Choice Option
    public void loginChoice(){
        choice = new Choice();
        choice.add("Admin");
        choice.add("Seller");
        choice.setPreferredSize(new Dimension(280,50));
        choice.addItemListener(this);
    }

//  Create Method to use Panel
    public void loginPanel(){
        //  create objects of Panel
        panel1 = new Panel();
        panel1.add(label_west);
        panel1.setBackground(new Color(255,92,6));

        //  create objects of Panel
        panel2 = new Panel();
        panel2.add(label_east);
        panel2.add(picLabel);
        panel2.add(choice);
        panel2.add(user_label);
        panel2.add(field_1);
        panel2.add(pass_label);
        panel2.add(field_2);
        panel2.add(showPassword);
        panel2.add(button);
        panel2.add(button2);
    }

//  Create Method to use Image
    public void loginImage(){
        //  File class is used to upload image
        try {
            File originalImage = new File("D:\\BookShop\\src\\com\\company\\images\\2.png");
            image = ImageIO.read(originalImage);
            picLabel = new JLabel(new ImageIcon(image));
            picLabel.setPreferredSize(new Dimension(350,100));
        }
        catch (IOException e) {
            System.out.println("Cannot load the image");
            System.exit(0);
        }
    }

//    Create a Constructor to add panel in Frame
    LoginPanel(){
        super("Book Shop Login Form");
    //  Frame Icon
        icon = Toolkit.getDefaultToolkit().getImage("D:\\BookShop\\src\\com\\company\\images\\3.png");

    //  Create objects of font
        f1 = new Font("Courier New", Font.BOLD, 20);
        f2 = new Font(Font.DIALOG_INPUT, Font.BOLD, 32);
        f3 = new Font(Font.SANS_SERIF , Font.ITALIC, 15);

    //  Window method is used to close frame
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    //  Calling Methods to use
        loginTextField();
        loginLabel();
        loginButton();
        loginImage();
        loginChoice();
        loginPanel();

    //  Add Panels to the Frame
        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.CENTER);

    //  Set Frame Properties
        setIconImage(icon);
        setBackground(new Color(208, 208, 208, 255));
        setSize(580, 450);
        setLocation(350,300);
        setResizable(false);
    }

    // Method is used to set Image
    public void paint(Graphics g){
        g.drawImage(image, 0,0,null);
    }

//  Method to Perform Action on the Frame
    public void actionPerformed(ActionEvent e){
    //  Get The Value
        String C = choice.getItem(choice.getSelectedIndex());
        String userValue = field_1.getText();
        String passValue = field_2.getText();
        String btn = e.getActionCommand();

        //  Condition to check Admin or User is going to Log in
        switch (C){
            case "Admin":
                //  Condition to check Password is Correct or Not
                if (btn.equals("Login")){
                    if (field_1.getText().isEmpty() || field_2.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Fill All the Fields","Alert", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        if (userValue.equals("Admin") && passValue.equals("indus")) {
                            JOptionPane.showMessageDialog(null, "Success");
                            dispose();
                            AdminPageFrame admin = new AdminPageFrame();
                            admin.setIconImage(icon);
                            admin.setVisible(true);
                        } else {
                            //show error message
                            JOptionPane.showMessageDialog(this, "Please enter valid username and password.", "Alert", JOptionPane.WARNING_MESSAGE);
                            field_1.setText("");
                            field_2.setText("");
                        }
                    }
                }
            break;
            case "Seller":
                RegisterUser ru = new RegisterUser();
                if (btn.equals("Login")){
                    if (field_1.getText().isEmpty() || field_2.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Fill All the Fields","Alert", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        ru.setUsername(userValue);
                        ru.setPassword(passValue);
                        ru.login();
                        if (ru.login){
                            JOptionPane.showMessageDialog(null, "Success");
                            UserPageFrame user = new UserPageFrame(userValue);
                            user.setIconImage(icon);
                            user.setVisible(true);
                            dispose();
                        }
                        else{
                            //show error message
                            JOptionPane.showMessageDialog(null, "Incorrect username or password.","Alert", JOptionPane.WARNING_MESSAGE);
                        }
                        field_1.setText("");
                        field_2.setText("");
                    }
                }
                else if (btn.equals("Register")){
                    if (field_1.getText().isEmpty() || field_2.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Fill All the Fields","Alert", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        ru.setUsername(userValue);
                        ru.setPassword(passValue);
                        ru.signup();
                        field_1.setText("");
                        field_2.setText("");
                    }
                }
            break;
        }
        if (e.getSource() == showPassword){
            if (showPassword.isSelected()) {
                field_2.setEchoChar((char) 0);
            } else {
                field_2.setEchoChar('*');
            }
        }
    }

//  Methods To Perform
    public void itemStateChanged(ItemEvent e) {
        String C = choice.getItem(choice.getSelectedIndex());
        if (C.equals("Seller")){
            button2.setVisible(true);
            field_1.setText("");
            field_2.setText("");
        }
        else {
            button2.setVisible(false);
            field_1.setText("");
            field_2.setText("");
        }
    }
}

