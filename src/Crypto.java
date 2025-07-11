import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static javax.swing.UIManager.setLookAndFeel;

public class Crypto extends javax.swing.JFrame {
    private JPanel panel;
    private JTextField select_file_TF;
    private JButton encode_BT;
    private JButton select_BT;
    private JTextField seed_TF;
    private JLabel seed_label;
    private JLabel file_label;

    //Encryptor obj = new Encryptor();
    protected String filePath;

    public Crypto() {
        initComponents();
        getContentPane().setBackground(new Color(65, 68, 80));

        select_BT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    filePath = Encryptor.select();
                    select_file_TF.setText(filePath);
                } catch (NullPointerException ex) {
                    select_file_TF.setText("Please select a file");
                }
            }
        });

        encode_BT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                super.mouseClicked(e);
                // Check if the seed is not null, is a valid number and a file is selected
                if (seed_TF != null && seed_TF.getText().matches("\\d*") && filePath != null) {
                    Encryptor.ciphering(filePath, seed_TF.getText(), null);
                    try {
                        filePath = null; // Reset filePath after XOR operation
                        select_file_TF.setText(""); // Clear the file path text field
                        seed_TF.setText(""); // Clear the seed text field
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Error deleting encoded file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else seed_TF.setText("Please enter a seed key and select a file");

            }
        });

    }

    public static void main(String[] args) {
        // look and feel
        try {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Crypto().setVisible(true);
            }
        });
    }

    private void initComponents() {

        // Make the GUI components
        panel = new JPanel();
        select_file_TF = new JTextField(20);
        encode_BT = new JButton("Encode");
        select_BT = new JButton("Select File");
        seed_TF = new JTextField(20);
        file_label = new JLabel("File:");
        seed_label = new JLabel("Seed:");


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stream Cypher");

        panel.setBackground(new Color(65, 68, 80));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add components to panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(file_label, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(select_file_TF, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(select_BT, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(seed_label, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(seed_TF, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(encode_BT, gbc);


        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

}
