/*
 * Created by JFormDesigner on Sun Sep 18 13:43:33 GST 2016
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Assim Al-Marhuby
 */
public class MainView extends JFrame {

    // Engine class attached
    Engine engine = new Engine();

    public MainView() {
        initComponents();
    }

    private void browseButtonActionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter csvfilter = new FileNameExtensionFilter(
                "csv files (*.csv)", "csv");
        chooser.setFileFilter(csvfilter);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            inputFileField.setText(f.getAbsolutePath());
            engine.setInputFile(new File(f.getAbsolutePath()));
        } else {
            // User clicked cancel
        }

        validation();
    }

    private void fhSpinnerStateChanged(ChangeEvent e) {
        engine.setFhPerDay(Integer.parseInt(fhSpinner.getValue().toString()));
        validation();
    }

    private void fcSpinnerStateChanged(ChangeEvent e) {
        engine.setFcPerDay(Integer.parseInt(fcSpinner.getValue().toString()));
        validation();
    }

    private void processButtonActionPerformed(ActionEvent e) {
        // Process and show message
        JOptionPane.showMessageDialog(this, engine.process());
    }

    private void validation() {
        if(inputFileField.getText().length() == 0 || Integer.parseInt(fhSpinner.getValue().toString()) < 1 ||
                Integer.parseInt(fcSpinner.getValue().toString()) < 1)
            processButton.setEnabled(false);
        else
            processButton.setEnabled(true);


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        label1 = new JLabel();
        label2 = new JLabel();
        inputFileField = new JTextField();
        browseButton = new JButton();
        label3 = new JLabel();
        fhSpinner = new JSpinner();
        label4 = new JLabel();
        fcSpinner = new JSpinner();
        processButton = new JButton();
        label5 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "46dlu, $lcgap, 72dlu, $lcgap, 57dlu",
            "6*(default, $lgap), default"));

        //---- label1 ----
        label1.setText("B737 FLS Calculator (v1.0)");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1, CC.xywh(1, 1, 5, 1));

        //---- label2 ----
        label2.setText("Input File:");
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label2, CC.xy(1, 3));

        //---- inputFileField ----
        inputFileField.setEditable(false);
        contentPane.add(inputFileField, CC.xy(3, 3));

        //---- browseButton ----
        browseButton.setText("Browse...");
        browseButton.addActionListener(e -> browseButtonActionPerformed(e));
        contentPane.add(browseButton, CC.xy(5, 3));

        //---- label3 ----
        label3.setText("FH/Day");
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label3, CC.xy(1, 5));

        //---- fhSpinner ----
        fhSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        fhSpinner.addChangeListener(e -> fhSpinnerStateChanged(e));
        contentPane.add(fhSpinner, CC.xy(3, 5));

        //---- label4 ----
        label4.setText("FC/Day");
        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label4, CC.xy(1, 7));

        //---- fcSpinner ----
        fcSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        fcSpinner.addChangeListener(e -> fcSpinnerStateChanged(e));
        contentPane.add(fcSpinner, CC.xy(3, 7));

        //---- processButton ----
        processButton.setText("Abracadabra");
        processButton.setEnabled(false);
        processButton.addActionListener(e -> processButtonActionPerformed(e));
        contentPane.add(processButton, CC.xy(3, 9));

        //---- label5 ----
        label5.setText("Created by Assim Al-Marhuby");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.ITALIC));
        contentPane.add(label5, CC.xywh(1, 11, 5, 1));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("B737 FLS Calculator");
        frame.setContentPane(new MainView().getContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel label1;
    private JLabel label2;
    private JTextField inputFileField;
    private JButton browseButton;
    private JLabel label3;
    private JSpinner fhSpinner;
    private JLabel label4;
    private JSpinner fcSpinner;
    private JButton processButton;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
