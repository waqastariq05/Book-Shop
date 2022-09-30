package com.company;

// Import Libraries to use GUI
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;

//  Extends Frame class to UserPageFrame to use Frame methods and Features
public class UserPageFrame extends JFrame implements ActionListener {
//    Access UserPage Class
    UserPage user;

//    Declare and initialize Fields
    private final int hGap = 20;
    private final int vGap = 20;
    private int bill_id = 1;
    private int order_id = 1;
    private int id;
    private String username;

//    Declare Frame Fields to use
    JLabel imageLabel ,name, l1, l2, l3, l4, l5, table_heading, bill_heading, total_label;
    JTextField t1, t2, t3, t4 ,t5;
    JTextArea bill;
    JButton b1, b2, b3, b4;
    JTable table;
    JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
    JPanel contentPane, contentPane1;
    Font f1, f2;
    ImageIcon imageIcon;

//    Create Method to use TextFields
    public void userTextField(){
        t1 = new JTextField();
        t1.setBackground(Color.WHITE);
        t1.setEditable(false);

        t2 = new JTextField();

        t3 = new JTextField();
        t3.setText(String.valueOf(bill_id));
        t3.setBackground(Color.WHITE);
        t3.setEditable(false);

        t4 = new JTextField(username);
        t4.setBackground(Color.WHITE);
        t4.setEditable(false);

        t5 = new JTextField();
        t5.setEditable(false);
        t5.setBackground(Color.WHITE);

        bill = new JTextArea("\t    Customer Name: " + username + "\n==========================================================\n"
                        + "        No.           " + "Product            " + "Quantity           " + "Price          "
                        + "Total\n==========================================================\n");
        bill.setBackground(Color.WHITE);
        bill.setEditable(false);
    }

//    Create Method to use Labels
    public void userLabel(){
        imageIcon = new ImageIcon("D:\\BookShop\\src\\com\\company\\images\\1.png");

        imageLabel = new JLabel(imageIcon);
        name = new JLabel( username, JLabel.LEFT);
        name.setForeground(new Color(255, 92, 6));
        name.setPreferredSize(new Dimension(850,40));
        name.setFont(f1);

        l1 = new JLabel("Book Name");
        l1.setFont(f2);
        l2 = new JLabel("Quantity");
        l2.setFont(f2);
        l3 = new JLabel("Bill Number");
        l3.setFont(f2);
        l4 = new JLabel("Client Name");
        l4.setFont(f2);
        l5 = new JLabel("Price");
        l5.setFont(f2);

        table_heading = new JLabel("Book List", JLabel.CENTER);
        table_heading.setForeground(new Color(255, 92, 6));
        table_heading.setPreferredSize(new Dimension(600,30));
        table_heading.setFont(f1);

        bill_heading = new JLabel("Books Bill", JLabel.CENTER);
        bill_heading.setForeground(new Color(255, 92, 6));
        bill_heading.setFont(f1);

        total_label = new JLabel("       Total Price:");
        total_label.setFont(f2);
    }

//    Create Method to use Buttons
    public void userButton(){
        b1 = new JButton("Add To Bill");
        b1.setPreferredSize(new Dimension(120,30));
        b1.addActionListener(this);

        b2 = new JButton("Reset");
        b2.setPreferredSize(new Dimension(120,30));
        b2.addActionListener(this);

        b3 = new JButton("Submit");
        b3.setPreferredSize(new Dimension(100,30));
        b3.addActionListener(this);

        b4 = new JButton("Logout");
        b4.setPreferredSize(new Dimension(100,30));
        b4.addActionListener(this);
        b4.setLayout(new FlowLayout(FlowLayout.TRAILING));
    }

//    Create Method to use Table
    public void userTable(){
        String[] column = {"ID", "NAME", "AUTHOR", "CATEGORIES", "QUANTITY", "PRICE"};
        table = new JTable();
        DefaultTableModel model;
        model = new DefaultTableModel(0,2);
        model.setColumnIdentifiers(column);
        table.setModel(model);

        File file = new File("D:\\BookShop\\src\\com\\company\\Files\\record.txt");
        String line;
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null)
            {
                model.addRow(line.split(";"));
            }
            reader.close();
        }
        catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Error");
            ioException.printStackTrace();
        }

//     Select Table Row
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int i = table.getSelectedRow();
                id = Integer.parseInt((model.getValueAt(i, 0).toString()));
                t1.setText(model.getValueAt(i,1).toString());
                t2.setText(model.getValueAt(i, 4).toString());
                t5.setText(model.getValueAt(i, 5).toString());
            }
        });
    }

//    Create Method to use Panels
    public void userPanel(){
        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(800,50));
        p1.add(imageLabel);
        p1.add(name);
        p1.add(b4);

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(hGap, vGap, hGap, vGap));
        contentPane.setPreferredSize(new Dimension(650,700));

        p2 = new JPanel(new GridLayout(2,3, 10,5));
        p2.setPreferredSize(new Dimension(600,80));
        p2.add(l1);
        p2.add(l2);
        p2.add(l3);
        p2.add(t1);
        p2.add(t2);
        p2.add(t3);
        contentPane.add(p2);

        p3 = new JPanel(new GridLayout(2,2,10,5));
        p3.setPreferredSize(new Dimension(600,80));
        p3.add(l4);
        p3.add(l5);
        p3.add(t4);
        p3.add(t5);
        contentPane.add(p3);

        p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p4.setPreferredSize(new Dimension(600,50));
        p4.add(b1);
        p4.add(b2);
        contentPane.add(p4);

        p5 = new JPanel();
        p5.add(table_heading);
        contentPane.add(p5);

        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(600, 300));
        p6 = new JPanel();
        p6.setOpaque(true);
        p6.add(pane);
        contentPane.add(p6);

        contentPane1 = new JPanel();
        contentPane1.setBorder(BorderFactory.createEmptyBorder(hGap, vGap, hGap, vGap));
        contentPane1.setPreferredSize(new Dimension(450, 700));

        p7 = new JPanel();
        p7.add(bill_heading);
        contentPane1.add(p7);

        p8 = new JPanel(new GridLayout(0,1,10,10));
        p8.setPreferredSize(new Dimension(350,380));
        p8.add(bill);
        contentPane1.add(p8);

        p9 = new JPanel(new GridLayout(0,1,10,10));
        p9.setPreferredSize(new Dimension(350,20));
        p9.add(total_label);
        p9.setBackground(Color.WHITE);
        contentPane1.add(p9);

        p10 = new JPanel();
        p10.add(b3);
        contentPane1.add(p10);
    }

//    Create a Constructor to add panels in Frame
    UserPageFrame(String username){
        super("Book Shop Software");
        this.username = username;

//      Use Fonte
        f1 = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        f2 = new Font(Font.DIALOG, Font.ITALIC, 14);

//      Close method is used to close frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//      Calling all methods
        userLabel();
        userTextField();
        userButton();
        userTable();
        userPanel();

//      Add panels in frame
        add(p1, BorderLayout.NORTH);
        add(contentPane, BorderLayout.WEST);
        add(contentPane1, BorderLayout.EAST);

//      Set Frame Properties
        setBackground(new Color(208, 208, 208, 255));
        setSize(1100, 700);
        setLocation(100, 150);
        setResizable(false);
    }

//  Method to Perform Action on the Frame
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        String name, client;
        int qty, price;
        int total = 0;
        int total_price = 0;
        int new_qty;
        int qty1;
        --id;
        String btn = e.getActionCommand();

        t3.setText(String.valueOf(bill_id));

//    Condition to check which button is press
        if (btn.equals("Add To Bill"))
        {
            if (t1.getText().isEmpty() || t2.getText().isEmpty() || t5.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Fill All the Fields");
            }
           else {
                name = t1.getText();
                client = t4.getText();
                qty = Integer.parseInt(t2.getText());
                price = Integer.parseInt(t5.getText());
                qty1 = Integer.parseInt(model.getValueAt(id, 4).toString());

                new_qty = qty1 - qty;
                total = qty * price;
                total_price += total;

                if (qty == 0){
                    JOptionPane.showMessageDialog(this, "Out Of Stock", "Book Shop", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    bill.append("        " + order_id + "               " + name + "                  " + qty + "                 " + price + "            " + total + "\n");
                    order_id++;
                    t1.setText("");
                    t2.setText("");
                    t5.setText("");
                    total_label.setText("       Total Price:   Rs." + total_price);
                    model.setValueAt(new_qty, id, 4);
                    JOptionPane.showMessageDialog(this, "Your product is add to bill");
                }
            }
        }
        else if (btn.equals("Reset")){
            if (t1.getText().isEmpty() || t2.getText().isEmpty() || t5.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Fields is Already Empty");
            }
            else {
                t1.setText("");
                t2.setText("");
                t5.setText("");
            }
        }
        else if (btn.equals("Submit")){
            int a = JOptionPane.showConfirmDialog(this, "Are you sure?","Book Shop", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION){
                try {
                    bill.print();
                    String text = bill.getText();
                    client = t4.getText();
                    user = new UserPage();
                    user.addToBill(client, text);
                    bill.setText("==========================================================\n"
                            + "        No.           " + "Product            " + "Quantity           " + "Price          "
                            + "Total\n==========================================================\n");
                    total_label.setText("       Total Price:   Rs." );
                }
                catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if (btn.equals("Logout")){
            int a = JOptionPane.showConfirmDialog(this, "Are You Sure?", "Admin Panel",JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION){
                dispose();
                new LoginPanel().setVisible(true);
            }
        }
    }
}
