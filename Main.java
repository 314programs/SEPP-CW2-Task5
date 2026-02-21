import javax.swing.*;

public class Main {
    //Assume that verified business numbers are stored in this array and is updated beforehand
    static String[] verifiedBusinessNumbers;
    static Business[] businesses = new Business[10];
    static int businessCount = 0;

    //Build max heap
    private static void heapify(String[] arr, int n, int i){
        int largest = i;
        //Left and right child indices
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        //Left/right child is larger than root
        if(l < n && arr[l].compareTo(arr[largest]) > 0) {
            largest = l;
        }
        if(r < n && arr[r].compareTo(arr[largest]) > 0) {
            largest = r;
        }
        //Recursive call if largest is not root
        if(largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    private static void heapSort(){
        int n = verifiedBusinessNumbers.length;
        
        //Heapify non root nodes, build max heap
        for(int i = n / 2 - 1; i >= 0; i--) {
            heapify(verifiedBusinessNumbers, n, i);
        }
        
        //Heapify root nodes and swap with last element till sorted
        for(int i = n - 1; i > 0; i--) {
            String temp = verifiedBusinessNumbers[0];
            verifiedBusinessNumbers[0] = verifiedBusinessNumbers[i];
            verifiedBusinessNumbers[i] = temp;
            heapify(verifiedBusinessNumbers, i, 0);
        }
    }

    //Binary search the sorted array to check for the valid buisness number
    //Time complexity of O(log n), fast for millions of business numbers
    private static boolean isValidBusinessNumber(String number) {
        int l = 0;
        int r = verifiedBusinessNumbers.length - 1;

        while(l <= r) {
            int m = l + (r - l) / 2;
            if(verifiedBusinessNumbers[m].equals(number)) {
                return true;
            }
            else if(verifiedBusinessNumbers[m].compareTo(number) < 0) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        return false;
    }

    //Store EP's register data using this class
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
        //A rather simple email validation system that checks if "@" and "." are present with no spaces
        if(email.contains("@") && email.contains(".") && !email.contains(" ")) {
            return true;
        }
        return false;
    }

    //Main method to run the program with a simple Swing UI for the register form
    //Uncomment the main method to run the program with UI


    /* 
    public static void main(String[] args)
    {   
        //Heapsort has an average, worst, and best case time complexity of O(n log n) and a space complexity of O(1)
        //Heapsort can also make it easy to add new business numbers to the array when needed
        //Sorting the verified business numbers to allow for binary search
        heapSort();

        //Swing GUI for register form
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLayout(null);

        //Each field has a label and a text field for user input
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
        
        //A submit button for the form
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(50, 350, 100, 30);
        frame.add(submitButton);

        //A label to display messages to the user after submitting the form
        JLabel buttonText = new JLabel();
        buttonText.setBounds(50, 375, 300, 200);
        frame.add(buttonText);

        frame.setVisible(true);

        submitButton.addActionListener(e -> {
            //Some fields not filled
            if(nameField.getText().isEmpty() || businessNumberField.getText().isEmpty() || contactEmailField.getText().isEmpty() || descriptionField.getText().isEmpty()) {
                buttonText.setText("Please fill in all fields.");
                return;
            }
            else{
                //Check if the business number is valid and if the email is valid. 
                //If both are valid, add the business to the array and display a success message. 
                if(isValidBusinessNumber(businessNumberField.getText()) && isValidEmail(contactEmailField.getText())) {
                    buttonText.setText("Business added successfully!");
                    Business newBusiness = new Main().new Business(nameField.getText(), businessNumberField.getText(), contactEmailField.getText(), descriptionField.getText());
                    businesses[businessCount] = newBusiness;
                    businessCount++;
                }
                //Invalid email address or business number, display appropriate error message
                else if(!isValidEmail(contactEmailField.getText())) {
                    buttonText.setText("Invalid email address. Please try again.");
                    
                }
                else {
                    buttonText.setText("Invalid business number. Please try again.");
                }
            }
        });
    }
        */

}