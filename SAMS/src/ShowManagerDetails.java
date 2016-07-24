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
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class ShowManagerDetails extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public ShowManagerDetails() {
		setResizable(false);
		setTitle("Edit Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 553);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 102, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 102, 51));
		panel.setLayout(null);
		panel.setBounds(10, 11, 537, 403);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Login Details :");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(219, 11, 250, 29);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(20, 51, 158, 28);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(188, 51, 339, 29);
		panel.add(textField);
		
		JLabel label_2 = new JLabel("Gender");
		label_2.setBounds(20, 90, 46, 27);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Age");
		label_3.setBounds(20, 128, 158, 28);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Mobile Number");
		label_4.setBounds(20, 167, 97, 28);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Email");
		label_5.setBounds(20, 210, 97, 28);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Address");
		label_6.setBounds(20, 249, 97, 28);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Username");
		label_7.setBounds(20, 288, 97, 28);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Password");
		label_8.setBounds(20, 327, 97, 28);
		panel.add(label_8);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(188, 167, 339, 29);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(188, 210, 339, 29);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(188, 249, 339, 29);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(188, 288, 339, 29);
		panel.add(textField_5);
		
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(18, 18, 80, 1));
		spinner_1.setBounds(188, 132, 57, 27);
		panel.add(spinner_1);
		
		String[] gender = {"Male","Female"}; //get month names
		SpinnerListModel genderModel = new SpinnerListModel(gender);
		JSpinner spinner = new JSpinner(new SpinnerListModel(new String[] {"Male", "Female"}));
		spinner.setToolTipText("");
		spinner.setBounds(188, 91, 83, 29);
		panel.add(spinner);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(188, 329, 339, 29);
		panel.add(passwordField);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(50, 1, 10000, 1));
		spinner_2.setBounds(188, 369, 57, 29);
		panel.add(spinner_2);
		
		JLabel lblNewLabel = new JLabel("Normal Seats");
		lblNewLabel.setBounds(20, 376, 97, 14);
		panel.add(lblNewLabel);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowManagerFrame frame = new ShowManagerFrame();
				frame.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(296, 476, 198, 37);
		contentPane.add(button_1);
		
		JButton button = new JButton("Update");
		button.setBounds(62, 476, 158, 37);
		contentPane.add(button);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(50, 1, 10000, 1));
		spinner_3.setBounds(470, 369, 57, 29);
		panel.add(spinner_3);
		
		JLabel lblBalconySeats = new JLabel("Balcony Seats");
		lblBalconySeats.setBounds(302, 376, 97, 14);
		panel.add(lblBalconySeats);
		
		JLabel lblNumberOfSeats = new JLabel("Number of Seats in a row ");
		lblNumberOfSeats.setBounds(30, 425, 170, 26);
		contentPane.add(lblNumberOfSeats);
		
		Connection con;
		try {
			con = test.getConnection();
			Statement stmt = null;
		      stmt = con.createStatement();
		      DatabaseMetaData md = con.getMetaData();
				ResultSet rs1 = md.getTables(null, null,"events", null);
				if(rs1.next()){
		      String sql = "SELECT name,gender,age,mobilenumber,email,address,username ,password,nseats,bseats FROM showmanager WHERE id = '1'";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      String name = null;
		      String Gender = null;
		      int age = 0;
		      long mobilenumber = 0;
		      String email = null;
		      String address = null;
		      String username = null;
		      String password = null;
		      int nseats = 1000,bseats=1000;
		      while(rs.next()){
		         //Retrieve by column name
		    	  name = rs.getString("name");
			      Gender = rs.getString("gender");
			      age = rs.getInt("age");
			      mobilenumber = rs.getLong("mobilenumber");
			      email = rs.getString("email");
			      address = rs.getString("address");
		         username  = rs.getString("username");
		         password = rs.getString("password");
		         nseats=rs.getInt("nseats");
		         bseats=rs.getInt("bseats");
		      }
		      rs.close();
		      textField.setText(name);
		      spinner.setValue(Gender);
			spinner_1.setValue(age);
			textField_2.setText(Long.toString(mobilenumber));
			textField_3.setText(email);
			textField_4.setText(address);
			textField_5.setText(username);
			passwordField.setText(password);
			spinner_2.setValue(nseats);
			spinner_3.setValue(bseats);
			
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
		       }
				}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setModel(new SpinnerNumberModel(Start.getCols(), new Integer(1), null, new Integer(1)));
		spinner_4.setBounds(199, 425, 58, 26);
		contentPane.add(spinner_4);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Connection con = test.getConnection();
						Statement stmt = null;
						int id = 1;
						String name = textField.getText();
						String gender = (String)spinner.getValue();
						int age = (int)spinner_1.getValue();
						long mobileNumber;
						try {
							mobileNumber = Long.parseLong(textField_2.getText());
						} catch (java.lang.NumberFormatException ea) {
							JOptionPane.showMessageDialog(panel, "Entered input for Mobile Number is not a number ,enter a valid input for Mobile Number");
							return;
						}
						String email = textField_3.getText();
						String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
					    Boolean b = email.matches(EMAIL_REGEX);
					    System.out.println("is e-mail: "+email+" :Valid = " + b);
						if(!b) {
							JOptionPane.showMessageDialog(contentPane, "The email format is invalid !");
							return;
						}		   
						String address = textField_4.getText();
						String username = textField_5.getText();
						String password = passwordField.getText();
						if(username.equals("")&&password.equals("")) {
							JOptionPane.showMessageDialog(contentPane, "Username and password field cannot be empty !");
							return;
						}
						int nseats = (int) spinner_2.getValue();
						int bseats = (int) spinner_3.getValue();
						stmt = con.createStatement();
					    String sql = "INSERT INTO showmanager(id,name,gender,age,mobilenumber,email,address,username,password,nseats,bseats) " +
					                   "VALUES ('"+id+"','"+name+"','"+gender+"','"+age+"','"+mobileNumber+"','"+email+"','"+address+"','"+username+"','"+password+"','"+nseats+"','"+bseats+"') " +
					                   		"ON DUPLICATE KEY UPDATE `name` = '"+name+"',`gender` = '"+gender+"',`age`= '"+age+"' , `mobilenumber` = '"+mobileNumber+"',"
					                   				+ " `email` ='"+email+"',`address` = '"+address+"',`username`= '"+username+"',`password`='"+password+"'  ,`nseats`='"+nseats+"',`bseats`='"+bseats+"'";
					    stmt.executeUpdate(sql);
					    Start.setCols((int)spinner_4.getValue());
					    JOptionPane.showMessageDialog(contentPane, "The Details have been updated successfully !");
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
	}
}
