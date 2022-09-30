package com.company;

// Import Libraries to use GUI
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//  Extends Frame class to AdminPageFrame to use Frame methods and Features
class AdminPageFrame extends JFrame implements ActionListener {
//    Declare and initialize Fields
    private final int hGap = 20;
    private final int vGap = 20;
    int main_id = 1;

//    Declare Frame Fields to use
    JLabel label_heading, label1, label2, label3, label4, label5, label6;
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2, b3, b4;
    JPanel contentPane, p1, p2, p3, p4;
    JTable table;
    Choice c;

//    Create Method to use Labels
    public void adminLabel(){
        label_heading = new JLabel("BOOK SHOP", JLabel.CENTER);
        label_heading.setForeground(new Color(252, 139, 1));
        label1 = new JLabel("ID");
        label2 = new JLabel("Book Name");
        label3 = new JLabel("Author");
        label4 = new JLabel("Categories");
        label5 = new JLabel("Quantity");
        label6 = new JLabel("Price");
    }

//    Create Method to use TextFileds
    public void adminTextField(){
        t1 = new JTextField();
        t1.setEditable(false);
        t1.setBackground(Color.WHITE);
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
    }

//    Create Method to use Choice
    public void adminChoice(){
        c = new Choice();
        c.add("Programming");
        c.add("Maths");
    }

//    Create Method to use Buttons
    public void adminButton(){
        b1 = new JButton("Save");
        b1.setPreferredSize(new Dimension(100, 30));
        b1.addActionListener(this);

        b2 = new JButton("Delete");
        b2.setPreferredSize(new Dimension(100, 30));
        b2.addActionListener(this);

        b3 = new JButton("Reset");
        b3.setPreferredSize(new Dimension(100, 30));
        b3.addActionListener(this);

        b4 = new JButton("Logout");
        b4.setPreferredSize(new Dimension(100, 30));
        b4.addActionListener(this);
    }

//    Create Method to use Table
    public void adminTable(){
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

    //   Set table ID
        model = (DefaultTableModel) table.getModel();
        main_id = table.getRowCount();
        main_id++;
        t1.setText(String.valueOf(main_id));
    }

//    Create Method to use Panels
    public void adminPanel(){
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(hGap, vGap, hGap, vGap));

        p1 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        p1.setPreferredSize(new Dimension(1000, 40));
        p1.add(b4);
        contentPane.add(p1);

        p2 = new JPanel(new GridLayout(2, 6, 10, 10));
        p2.setPreferredSize(new Dimension(1000,80));
        p2.add(label1);
        p2.add(label2);
        p2.add(label3);
        p2.add(label4);
        p2.add(label5);
        p2.add(label6);
        p2.add(t1);
        p2.add(t2);
        p2.add(t3);
        p2.add(c);
        p2.add(t4);
        p2.add(t5);
        contentPane.add(p2);

        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(500,60));
        p3.setOpaque(true);
        p3.add(b1);
        p3.add(b2);
        p3.add(b3);
        contentPane.add(p3);

        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(800, 350));
        p4 = new JPanel();
        p4.setOpaque(true);
        p4.add(pane);
        contentPane.add(p4);
    }

//    Create a Constructor to add panels in Frame
    AdminPageFrame() {
        super("Book Shop Software");
        //  Close method is used to close frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        Calling all methods
        adminLabel();
        adminTextField();
        adminChoice();
        adminButton();
        adminTable();
        adminPanel();

        add(contentPane, BorderLayout.CENTER);

//        Set Frame Properties
        setBackground(new Color(208, 208, 208, 255));
        setSize(1100, 700);
        setLocation(95, 150);
        setResizable(false);
    }

    //  Method to Perform Action on the Frame
    @Override
    public void actionPerformed(ActionEvent e) {
        int id, qty, price;
        String name, author, cate;
        String btn = e.getActionCommand();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        main_id = table.getRowCount();

//        Condition to check which button is press
        if (btn.equals("Save")){
            if(t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty() || t5.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Fill All the Fields","Alert", JOptionPane.WARNING_MESSAGE);
            }
            else {
                id = Integer.parseInt(t1.getText());
                name = t2.getText();
                author = t3.getText();
                cate = c.getItem(c.getSelectedIndex());
                qty = Integer.parseInt(t4.getText());
                price = Integer.parseInt(t5.getText());

                AdminPage obj = new AdminPage(id, name, author, cate, qty, price);
                obj.save();
                model.addRow(new Object[] {t1.getText(), t2.getText(), t3.getText(), c.getItem(c.getSelectedIndex()) , t4.getText(), t5.getText()});

                main_id = table.getRowCount();
                main_id++;
                t1.setText(String.valueOf(main_id));
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
            }
        }
        else if (btn.equals("Delete"))
        {
            if(table.getSelectedRowCount() == 1) {
                int j = table.getSelectedRow();

                id = Integer.parseInt(model.getValueAt(j, 0).toString());
                name = model.getValueAt(j, 1).toString();
                author = model.getValueAt(j, 2).toString();
                cate = model.getValueAt(j, 3).toString();
                qty = Integer.parseInt(model.getValueAt(j, 4).toString());
                price = Integer.parseInt(model.getValueAt(j, 5).toString());

                AdminPage obj2 = new AdminPage(id, name, author, cate, qty, price);
                obj2.delete();
                model.removeRow(j);
                JOptionPane.showMessageDialog(null, "Selected row deleted successfully");

                main_id--;
                t1.setText(String.valueOf(main_id));
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
            }
            else if (table.getRowCount() == 0){
                JOptionPane.showMessageDialog(this, "Table is Empty.");
            }
            else {
                JOptionPane.showMessageDialog(this, "Please Select Single Row For Delete.","Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (btn.equals("Reset")){
            if(t2.getText().isEmpty() && t3.getText().isEmpty() && t4.getText().isEmpty() && t5.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Fields is Already Empty");
            }
            else {
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
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