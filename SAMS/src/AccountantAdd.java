import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AccountantAdd extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AccountantAdd() {
		setTitle("Add Accountant");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 474);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 192, 203));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Login Details :");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(209, 11, 250, 29);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(10, 51, 158, 28);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setColumns(10);
		textField.setBounds(178, 51, 339, 29);
		contentPane.add(textField);
		
		JLabel label_2 = new JLabel("Gender");
		label_2.setBounds(10, 90, 46, 27);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Age");
		label_3.setBounds(10, 128, 158, 28);
		contentPane.add(label_3);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(18, 18, 80, 1));
		spinner_1.setBounds(178, 129, 57, 27);
		contentPane.add(spinner_1);
		
		String[] gender = {"Male","Female"}; //get month names
		SpinnerListModel genderModel = new SpinnerListModel(gender);
		JSpinner spinner = new JSpinner(new SpinnerListModel(new String[] {"Male", "Female"}));
		spinner.setToolTipText("");
		spinner.setBounds(178, 89, 83, 29);
		contentPane.add(spinner);
		
		JLabel label_4 = new JLabel("Mobile Number");
		label_4.setBounds(10, 167, 97, 28);
		contentPane.add(label_4);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setColumns(10);
		textField_1.setBounds(178, 167, 339, 29);
		contentPane.add(textField_1);
		
		JLabel label_5 = new JLabel("Email");
		label_5.setBounds(10, 210, 97, 28);
		contentPane.add(label_5);
		
		textField_2 = new JTextField();
		textField_2.setText((String) null);
		textField_2.setColumns(10);
		textField_2.setBounds(178, 207, 339, 29);
		contentPane.add(textField_2);
		
		JLabel label_6 = new JLabel("Address");
		label_6.setBounds(10, 249, 97, 28);
		contentPane.add(label_6);
		
		textField_3 = new JTextField();
		textField_3.setText((String) null);
		textField_3.setColumns(10);
		textField_3.setBounds(178, 249, 339, 29);
		contentPane.add(textField_3);
		
		JLabel label_7 = new JLabel("Username");
		label_7.setBounds(10, 288, 97, 28);
		contentPane.add(label_7);
		
		textField_4 = new JTextField();
		textField_4.setText((String) null);
		textField_4.setColumns(10);
		textField_4.setBounds(178, 288, 339, 29);
		contentPane.add(textField_4);
		
		JLabel label_8 = new JLabel("Password");
		label_8.setBounds(10, 327, 97, 28);
		contentPane.add(label_8);
		
		passwordField = new JPasswordField();
		passwordField.setText((String) null);
		passwordField.setBounds(178, 329, 339, 29);
		contentPane.add(passwordField);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = test.getConnection();
					Statement stmt = null;
					String name = textField.getText();
					String gender = (String)spinner.getValue();
					int age = (int)spinner_1.getValue();
					long mobileNumber;
					try {
						mobileNumber = Long.parseLong(textField_1.getText());
					} catch (java.lang.NumberFormatException ea) {
						JOptionPane.showMessageDialog(contentPane, "Entered input for Mobile Number is not a number ,enter a valid input for Mobile Number");
						return;
					}
					String email = textField_2.getText();
					String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
				    Boolean b = email.matches(EMAIL_REGEX);
				    System.out.println("is e-mail: "+email+" :Valid = " + b);
					if(!b) {
						JOptionPane.showMessageDialog(contentPane, "The email format is invalid !");
						return;
					}
					String address = textField_3.getText();
					String username = textField_4.getText();
					String password = passwordField.getText();
					if(username.equals("")&&password.equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Username and password field cannot be empty !");
						return;
					}
					Connection conn;
					try {
						conn = test.getConnection();
						Statement stmt1 = null;
					      stmt1 = conn.createStatement();
					      String sql = "SELECT username ,password FROM accountant WHERE id > 0";
					      ResultSet rs = stmt1.executeQuery(sql);
					      while(rs.next()){
						       String tu=rs.getString("username");
						       String tp=rs.getString("password");
						       if(tu.equals(username)||tp.equals(password)){
						    	   JOptionPane.showMessageDialog(contentPane, "Username or Password already exists !");
						    	   return;
						       }
						      }
						      rs.close();
					 try{
				          if(stmt1!=null)
				             conn.close();
				       }catch(SQLException se){
				       }// do nothing
				       try{
				          if(conn!=null)
				             conn.close();
				       }catch(SQLException se){
				          se.printStackTrace();
				       }
				} catch (Exception e1) {
					e1.printStackTrace();
				}
					
					stmt = con.createStatement();
				    String sql = "INSERT INTO accountant(name,gender,age,mobilenumber,email,address,username,password) " +
				                   "VALUES ('"+name+"','"+gender+"','"+age+"','"+mobileNumber+"','"+email+"','"+address+"','"+username+"','"+password+"') " ;
				    stmt.executeUpdate(sql);
				    ShowManagerFrame frame = new ShowManagerFrame();
				    frame.setVisible(true);
					dispose();
				    try{
				         if(stmt!=null)
				            con.close();
				      }catch(SQLException se){
				      }// do nothing
				      try{
				         if(con!=null)
				            con.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(41, 375, 158, 37);
		contentPane.add(btnAdd);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowManagerFrame frame = new ShowManagerFrame();
				frame.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(275, 375, 198, 37);
		contentPane.add(button_1);
	}

}
