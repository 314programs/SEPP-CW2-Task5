import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import javax.swing.*;

public class Main {
    static List<String> verifiedBusinessNumbers = new ArrayList<>();
    static List<Business> businesses = new ArrayList<>();

    public class Business {
        String name;
        String businessNumber;
        String contactEmail;
        String description;

        public Business(String name, String businessNumber, String contactEmail, String description) {
            this.name = name;
            this.businessNumber = businessNumber;
            this.contactEmail = contactEmail;
            this.description = description;
        }
    }

    public static boolean isValidEmail(String email){
        if(email.contains("@") && email.contains(".") && !email.contains(" ")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        verifiedBusinessNumbers.add("123456789");
        verifiedBusinessNumbers.add("987654321");
        verifiedBusinessNumbers.add("111222333");
        
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 50, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(50, 75, 300, 30);
        frame.add(nameField);   

        JLabel businessNumberLabel = new JLabel("Business Number");
        businessNumberLabel.setBounds(50, 100, 150, 30);
        frame.add(businessNumberLabel);

        JTextField businessNumberField = new JTextField();
        businessNumberField.setBounds(50, 125, 300, 30);
        frame.add(businessNumberField);   

        JLabel contactEmailLabel = new JLabel("Contact Email");
        contactEmailLabel.setBounds(50, 150, 150, 30);
        frame.add(contactEmailLabel);

        JTextField contactEmailField = new JTextField();
        contactEmailField.setBounds(50, 175, 300, 30);
        frame.add(contactEmailField);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(50, 200, 100, 30);
        frame.add(descriptionLabel);

        JTextArea descriptionField = new JTextArea();
        descriptionField.setBounds(50, 225, 300, 100);
        frame.add(descriptionField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(50, 350, 100, 30);
        frame.add(submitButton);

        JLabel buttonText = new JLabel();
        buttonText.setBounds(50, 375, 300, 200);
        frame.add(buttonText);

        frame.setVisible(true);

        submitButton.addActionListener(e -> {
            if(nameField.getText().isEmpty() || businessNumberField.getText().isEmpty() || contactEmailField.getText().isEmpty() || descriptionField.getText().isEmpty()) {
                buttonText.setText("Please fill in all fields.");
                return;
            }
            else{
                if(verifiedBusinessNumbers.contains(businessNumberField.getText()) && isValidEmail(contactEmailField.getText())) {
                    buttonText.setText("Business added successfully!");
                    Business newBusiness = new Main().new Business(nameField.getText(), businessNumberField.getText(), contactEmailField.getText(), descriptionField.getText());
                    businesses.add(newBusiness);
                }
                else if(!isValidEmail(contactEmailField.getText())) {
                    buttonText.setText("Invalid email address. Please try again.");
                    
                }
                else {
                    buttonText.setText("Invalid business number. Please try again.");
                }
            }
        });
    }
}