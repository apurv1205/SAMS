import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class AccountantEdit extends JFrame {
	
	private int id;
	private JPanel contentPane;
	
	private JSpinner spinner_1;
	private JSpinner spinner;
	private JTextField tfName;
	private JTextField tfMobile;
	private JTextField tfEmail;
	private JTextField tfAddress;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	
	
	private String name;
	private String gender;
	private String email;
	private String address;
	private String username;
	private String password;
	private int age;
	private int mobile;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesPersonEdit frame = new SalesPersonEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	
	public int getId(){
		return id;
	}
	
	public void setId(int a){
		id = a;
	}
	
	public void getData(){
		try {
			Connection con = test.getConnection();
			Statement stmt = null;
//			String name = tfName.getText();
//			String gender = (String)spinner.getValue();
//			int age = (int)spinner_1.getValue();
//			long mobileNumber;
			
			stmt = con.createStatement();
		    
			//String sql = "INSERT INTO salesperson(name,gender,age,mobilenumber,email,address,username,password) " +
		    //               "VALUES ('"+name+"','"+gender+"','"+age+"','"+mobileNumber+"','"+email+"','"+address+"','"+username+"','"+password+"') " ;
		    
			String sql = "SELECT name,username,password,email,address,age,mobilenumber,gender FROM accountant WHERE `id`='"+ id +"'";
			
			ResultSet rs = stmt.executeQuery(sql);
		     
			while(rs.next()){
				name = rs.getString("name");
				username = rs.getString("username");
				password = rs.getString("password");
				email = rs.getString("email");
				address = rs.getString("address");
				gender = rs.getString("gender");
				age = rs.getInt("age");
				mobile = rs.getInt("mobilenumber");
			}
			
			rs.close();
			
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
	
	/**
	 * Create the frame.
	 */
	public AccountantEdit(int a) {
		setTitle("Edit Details");
		setResizable(false);
		
		setId(a);
		
		getData();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 474);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
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
		
		tfName = new JTextField();
		tfName.setText(name);
		tfName.setColumns(10);
		tfName.setBounds(178, 51, 339, 29);
		contentPane.add(tfName);
		
		JLabel label_2 = new JLabel("Gender");
		label_2.setBounds(10, 90, 46, 27);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Age");
		label_3.setBounds(10, 128, 158, 28);
		contentPane.add(label_3);
		
		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(18, 18, 80, 1));
		spinner_1.setValue(age);
		spinner_1.setBounds(178, 129, 57, 27);
		contentPane.add(spinner_1);
		
		String[] genderarr = {"Male","Female"}; //get month names
		SpinnerListModel genderModel = new SpinnerListModel(genderarr);
		spinner = new JSpinner(new SpinnerListModel(new String[] {"Male", "Female"}));
		spinner.setToolTipText("");
		spinner.setBounds(178, 89, 83, 29);
		spinner.setValue(gender);
		contentPane.add(spinner);
		
		JLabel label_4 = new JLabel("Mobile Number");
		label_4.setBounds(10, 167, 97, 28);
		contentPane.add(label_4);
		
		tfMobile = new JTextField();
		tfMobile.setText(Integer.toString(mobile));
		tfMobile.setColumns(10);
		tfMobile.setBounds(178, 167, 339, 29);
		contentPane.add(tfMobile);
		
		JLabel label_5 = new JLabel("Email");
		label_5.setBounds(10, 210, 97, 28);
		contentPane.add(label_5);
		
		tfEmail = new JTextField();
		tfEmail.setText(email);
		tfEmail.setColumns(10);
		tfEmail.setBounds(178, 207, 339, 29);
		contentPane.add(tfEmail);
		
		JLabel label_6 = new JLabel("Address");
		label_6.setBounds(10, 249, 97, 28);
		contentPane.add(label_6);
		
		tfAddress = new JTextField();
		tfAddress.setText(address);
		tfAddress.setColumns(10);
		tfAddress.setBounds(178, 249, 339, 29);
		contentPane.add(tfAddress);
		
		JLabel label_7 = new JLabel("Username");
		label_7.setBounds(10, 288, 97, 28);
		contentPane.add(label_7);
		
		tfUsername = new JTextField();
		tfUsername.setText(username);
		tfUsername.setColumns(10);
		tfUsername.setBounds(178, 288, 339, 29);
		contentPane.add(tfUsername);
		
		JLabel label_8 = new JLabel("Password");
		label_8.setBounds(10, 327, 97, 28);
		contentPane.add(label_8);
		
		tfPassword = new JPasswordField();
		tfPassword.setText(password);
		tfPassword.setBounds(178, 329, 339, 29);
		contentPane.add(tfPassword);
		
		JButton btnAdd = new JButton("Update");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = test.getConnection();
					Statement stmt = null;
					String name = tfName.getText();
					String gender = (String)spinner.getValue();
					int age = (int)spinner_1.getValue();
					long mobileNumber;
					
					try {
						mobileNumber = Long.parseLong(tfMobile.getText());
					} catch (java.lang.NumberFormatException ea) {
						JOptionPane.showMessageDialog(contentPane, "Entered input for Mobile Number is not a number ,enter a valid input for Mobile Number");
						return;
					}
					
					String email = tfEmail.getText();
					String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
				    Boolean b = email.matches(EMAIL_REGEX);
				    System.out.println("is e-mail: "+email+" :Valid = " + b);
					if(!b) {
						JOptionPane.showMessageDialog(contentPane, "The email format is invalid !");
						return;
					}
					String address = tfAddress.getText();
					String username = tfUsername.getText();
					String password = tfPassword.getText();
					if(username.equals("")&&password.equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Username and password field cannot be empty !");
						return;
					}
					
					stmt = con.createStatement();
				    
//					String sql = "INSERT INTO salesperson(name,gender,age,mobilenumber,email,address,username,password) " +
//				                   "VALUES ('"+name+"','"+gender+"','"+age+"','"+mobileNumber+"','"+email+"','"+address+"','"+username+"','"+password+"') " ;
					String sql = "INSERT INTO accountant(id,name,gender,age,mobilenumber,email,address,username,password) " +
			                   "VALUES ('"+id+"','"+name+"','"+gender+"','"+age+"','"+mobileNumber+"','"+email+"','"+address+"','"+username+"','"+password+"') " +
			                   		"ON DUPLICATE KEY UPDATE `name` = '"+name+"',`gender` = '"+gender+"',`age`= '"+age+"' , `mobilenumber` = '"+mobileNumber+"',"
			                   				+ " `email` ='"+email+"',`address` = '"+address+"',`username`= '"+username+"',`password`='"+password+"'";
					
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(contentPane, "The Details have been updated successfully !");
				    AccountantHome home = new AccountantHome(id);
				    home.setVisible(true);
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
				AccountantHome home = new AccountantHome(id);
				home.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(315, 375, 158, 37);
		contentPane.add(button_1);
	}
}