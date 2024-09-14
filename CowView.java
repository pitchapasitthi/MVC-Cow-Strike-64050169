import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class CowView {
    private JFrame frame;
    private JTextField idField;
    private JButton submitButton;
    private JTextArea cowInfo;
    private JButton milkButton;
    private JButton feedLemonButton;
    private JButton resetButton;

    public CowView() {
        // สร้างหน้าต่างหลัก
        frame = new JFrame("Cow Information System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // เพิ่มขนาดหน้าต่างให้ใหญ่ขึ้น
        frame.setLayout(new BorderLayout());

        // พื้นที่ด้านบนสำหรับใส่รหัส
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));
        JLabel idLabel = new JLabel("Enter Cow ID (8 digits):");
        idLabel.setFont(new Font("Arial", Font.BOLD, 18)); // เพิ่มขนาดตัวอักษร
        idField = new JTextField();
        idField.setFont(new Font("Arial", Font.PLAIN, 16));
        topPanel.add(idLabel);
        topPanel.add(idField);

        // พื้นที่กลางสำหรับแสดงข้อมูลของวัว
        cowInfo = new JTextArea(10, 30);
        cowInfo.setFont(new Font("Arial", Font.PLAIN, 16)); // เพิ่มขนาดตัวอักษรในช่องแสดงข้อมูล
        cowInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cowInfo);

        // พื้นที่ด้านล่างสำหรับปุ่มต่าง ๆ
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(submitButton);

        milkButton = new JButton("Milk the Cow");
        milkButton.setFont(new Font("Arial", Font.BOLD, 16));
        milkButton.setEnabled(false); // ปิดการใช้งานปุ่มก่อนรับข้อมูล
        buttonPanel.add(milkButton);

        feedLemonButton = new JButton("Feed Lemon");
        feedLemonButton.setFont(new Font("Arial", Font.BOLD, 16));
        feedLemonButton.setEnabled(false); // ปิดการใช้งานปุ่มก่อนรับข้อมูล
        buttonPanel.add(feedLemonButton);

        resetButton = new JButton("Reset BSOD Cows");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setEnabled(false); // ปิดการใช้งานปุ่มก่อนรับข้อมูล
        buttonPanel.add(resetButton);

        // จัดวางองค์ประกอบต่าง ๆ บนหน้าต่าง
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // แสดงหน้าต่าง
        frame.setVisible(true);
    }

    // ฟังก์ชันเชื่อมต่อ ActionListener
    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addMilkListener(ActionListener listener) {
        milkButton.addActionListener(listener);
    }

    public void addFeedLemonListener(ActionListener listener) {
        feedLemonButton.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) {
        resetButton.addActionListener(listener);
    }

    // ฟังก์ชันแสดงข้อมูลของวัว
    public void displayCowInfo(String info) {
        cowInfo.setText(info);
    }

    // เปิดใช้งานปุ่มรีดนม
    public void enableMilkButton() {
        milkButton.setEnabled(true);
    }

    // เปิดใช้งานปุ่มป้อนมะนาว
    public void enableFeedLemonButton() {
        feedLemonButton.setEnabled(true);
    }

    // เปิดใช้งานปุ่ม Reset
    public void enableResetButton() {
        resetButton.setEnabled(true);
    }

    public String getIdInput() {
        return idField.getText();
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
