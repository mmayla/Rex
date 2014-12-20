package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.Option;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JButton;

import com.infinitydream.core.FileParser;
import com.infinitydream.core.Manager;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {

    private JPanel contentPane;
    private JTextField training_tb;
    private JTextField val_tb;
    private JLabel lblResultPath;
    private JTextField res_tb;
    private JButton run_btn;
    private JTextField type_tb;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    MainScreen frame = new MainScreen();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public MainScreen() {
	setResizable(false);
	setTitle("Infinity Dream Pattern Recognizer");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 203);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	GridBagLayout gbl_contentPane = new GridBagLayout();
	gbl_contentPane.columnWidths = new int[] { 172, 103, 0 };
	gbl_contentPane.rowHeights = new int[] { 15, 0, 0, 0, 0, 0, 0, 0, 0 };
	gbl_contentPane.columnWeights = new double[] { 1.0, 1.0,
		Double.MIN_VALUE };
	gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
		0.0, 0.0, 0.0, Double.MIN_VALUE };
	contentPane.setLayout(gbl_contentPane);

	JLabel lblTrainingScript = new JLabel("Training Script");
	lblTrainingScript.setHorizontalAlignment(SwingConstants.LEFT);
	GridBagConstraints gbc_lblTrainingScript = new GridBagConstraints();
	gbc_lblTrainingScript.insets = new Insets(0, 0, 5, 0);
	gbc_lblTrainingScript.gridwidth = 2;
	gbc_lblTrainingScript.anchor = GridBagConstraints.NORTHWEST;
	gbc_lblTrainingScript.gridx = 0;
	gbc_lblTrainingScript.gridy = 0;
	contentPane.add(lblTrainingScript, gbc_lblTrainingScript);

	training_tb = new JTextField();
	GridBagConstraints gbc_training_tb = new GridBagConstraints();
	gbc_training_tb.gridwidth = 2;
	gbc_training_tb.insets = new Insets(0, 0, 5, 0);
	gbc_training_tb.fill = GridBagConstraints.HORIZONTAL;
	gbc_training_tb.gridx = 0;
	gbc_training_tb.gridy = 1;
	contentPane.add(training_tb, gbc_training_tb);
	training_tb.setColumns(10);

	JLabel lblValidationScript = new JLabel("Validation Script");
	lblValidationScript.setHorizontalAlignment(SwingConstants.LEFT);
	GridBagConstraints gbc_lblValidationScript = new GridBagConstraints();
	gbc_lblValidationScript.anchor = GridBagConstraints.WEST;
	gbc_lblValidationScript.insets = new Insets(0, 0, 5, 5);
	gbc_lblValidationScript.gridx = 0;
	gbc_lblValidationScript.gridy = 2;
	contentPane.add(lblValidationScript, gbc_lblValidationScript);

	val_tb = new JTextField();
	val_tb.setColumns(10);
	GridBagConstraints gbc_val_tb = new GridBagConstraints();
	gbc_val_tb.insets = new Insets(0, 0, 5, 0);
	gbc_val_tb.gridwidth = 2;
	gbc_val_tb.fill = GridBagConstraints.HORIZONTAL;
	gbc_val_tb.gridx = 0;
	gbc_val_tb.gridy = 3;
	contentPane.add(val_tb, gbc_val_tb);

	lblResultPath = new JLabel("Result Path");
	lblResultPath.setHorizontalAlignment(SwingConstants.LEFT);
	GridBagConstraints gbc_lblResultPath = new GridBagConstraints();
	gbc_lblResultPath.anchor = GridBagConstraints.WEST;
	gbc_lblResultPath.insets = new Insets(0, 0, 5, 5);
	gbc_lblResultPath.gridx = 0;
	gbc_lblResultPath.gridy = 4;
	contentPane.add(lblResultPath, gbc_lblResultPath);

	res_tb = new JTextField();
	res_tb.setColumns(10);
	GridBagConstraints gbc_res_tb = new GridBagConstraints();
	gbc_res_tb.insets = new Insets(0, 0, 5, 0);
	gbc_res_tb.gridwidth = 2;
	gbc_res_tb.fill = GridBagConstraints.HORIZONTAL;
	gbc_res_tb.gridx = 0;
	gbc_res_tb.gridy = 5;
	contentPane.add(res_tb, gbc_res_tb);

	run_btn = new JButton("RUN");
	run_btn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		run();
	    }
	});
	GridBagConstraints gbc_run_btn = new GridBagConstraints();
	gbc_run_btn.insets = new Insets(0, 0, 0, 5);
	gbc_run_btn.gridheight = 2;
	gbc_run_btn.gridx = 0;
	gbc_run_btn.gridy = 6;
	contentPane.add(run_btn, gbc_run_btn);

	type_tb = new JTextField();
	GridBagConstraints gbc_type_tb = new GridBagConstraints();
	gbc_type_tb.gridheight = 2;
	gbc_type_tb.insets = new Insets(0, 0, 5, 0);
	gbc_type_tb.fill = GridBagConstraints.HORIZONTAL;
	gbc_type_tb.gridx = 1;
	gbc_type_tb.gridy = 6;
	contentPane.add(type_tb, gbc_type_tb);
	type_tb.setColumns(10);
    }

    private void run() {
	try {
	    String trainingpath = training_tb.getText();
	    String valpath = val_tb.getText();
	    String respath = res_tb.getText();
	    int type = Integer.parseInt(type_tb.getText());
	    String filecontent;

	    if (type == 1) {
		filecontent = Manager.run_validation1(trainingpath, valpath);
	    } else if (type == 2) {
		filecontent = Manager.run_validation2(trainingpath, valpath);
	    }else throw new Exception();
	    
	    FileParser.write(respath, filecontent);
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, "Please validate your inputs");
	    e.printStackTrace();
	}
    }
}
