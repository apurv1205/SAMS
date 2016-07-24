import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddShows extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.


	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	int nseats=100;
    int bseats=100;
	public AddShows() {
		setResizable(false);
		setTitle("Add Shows");
		Connection con;
		try {
			con = test.getConnection();
			 Statement stmt = null;
		      stmt = con.createStatement();
		      String sql = "SELECT nseats,bseats FROM showmanager WHERE id = '1'";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         nseats  = rs.getInt("nseats");
		         bseats = rs.getInt("bseats");
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
		       }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 491);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 192, 203));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddShows = new JLabel("Add Shows :");
		lblAddShows.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAddShows.setBounds(245, 11, 197, 34);
		contentPane.add(lblAddShows);
		
		JLabel lblShowName = new JLabel("Show Name");
		lblShowName.setBounds(10, 76, 103, 14);
		contentPane.add(lblShowName);
		
		textField = new JTextField();
		textField.setBounds(174, 70, 449, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		Date date = new Date();
        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setSize(210, 27);
        dateTimePicker.setLocation(174, 118);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateTimePicker.setFormats( sdf
        		);
        dateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
        dateTimePicker.setDate(date);
        contentPane.add(dateTimePicker);
        
        JLabel lblDateAndTime = new JLabel("Date and Time");
        lblDateAndTime.setBounds(10, 124, 103, 14);
        contentPane.add(lblDateAndTime);
        
        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(0, 0, bseats, 1));
        spinner.setBounds(423, 230, 165, 27);
        contentPane.add(spinner);
        
        JLabel lblNormalSeats = new JLabel("Normal Seats");
        lblNormalSeats.setBounds(10, 233, 93, 14);
        contentPane.add(lblNormalSeats);
        
        JSpinner spinner_1 = new JSpinner();
        spinner_1.setModel(new SpinnerNumberModel(0, 0, nseats, 1));
        spinner_1.setBounds(90, 230, 165, 27);
        contentPane.add(spinner_1);
        
        JLabel label_1 = new JLabel("Normal Seats");
        label_1.setBounds(10, 331, 93, 14);
        contentPane.add(label_1);
        
        JSpinner spinner_2 = new JSpinner();
        spinner_2.setModel(new SpinnerNumberModel(new Integer(100), new Integer(0), null, new Integer(1)));
        spinner_2.setBounds(90, 328, 165, 27);
        contentPane.add(spinner_2);
        
        JLabel label_2 = new JLabel("Balcony Seats");
        label_2.setBounds(320, 331, 93, 14);
        contentPane.add(label_2);
        
        JSpinner spinner_3 = new JSpinner();
        spinner_3.setModel(new SpinnerNumberModel(new Integer(120), new Integer(0), null, new Integer(1)));
        spinner_3.setBounds(423, 328, 165, 27);
        contentPane.add(spinner_3);
        
        JButton btnNewButton = new JButton("Add");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int vipnseats=(int)spinner_1.getValue();
        		int vipbseats=(int)spinner.getValue();
        		int nprice = (int)spinner_2.getValue();
        		int bprice = (int)spinner_3.getValue();
        		int avnseats=nseats-vipnseats;
        		int avbseats=bseats-vipbseats;
        		try {
					Connection con = test.getConnection();
					Statement stmt = null;
					String name = textField.getText();
					
					String datetime=dateTimePicker.getEditor().getText();
					Date current = new Date();
					long diff=(sdf.parse(datetime).getTime()-current.getTime());
					if(diff<0) {
						JOptionPane.showMessageDialog(contentPane, "Event date-time is not valid !");
						return;
					}
					stmt = con.createStatement();
				    String sql = "INSERT INTO events(name,datetime,nprice,bprice,avnseats,avbseats) " +
				                   "VALUES ('"+name+"','"+datetime+"','"+nprice+"','"+bprice+"','"+avnseats+"','"+avbseats+"') " ;
				    stmt.executeUpdate(sql);
					int id=0;
					 sql = "SELECT id FROM events WHERE `name`='"+name+"' ";
				      ResultSet rs = stmt.executeQuery(sql);
				      //STEP 5: Extract data from result set
				      while(rs.next()){
				         id = rs.getInt("id");
				      }	
				      System.out.println(id);
				        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				      String sql1 = "CREATE TABLE event_"+Integer.toString(id)+"(id INTEGER not NULL AUTO_INCREMENT, " +
			                   " seats INTEGER, "+
			                   " PRIMARY KEY ( id ) )"; 
			    stmt.executeUpdate(sql1);
			    int totalSeats=0;
			    totalSeats=nseats+bseats;
			    int seats=0;
			    for(int i=1;i<=totalSeats;i++){
			    if(i<=avnseats) seats=0;
			    else if(i-avnseats<=vipnseats) seats=1;
			    else if(i-avnseats-vipnseats<=avbseats) seats=2;
			    else seats=3;
			    String sql2 = "INSERT INTO event_"+Integer.toString(id)+"(seats) " +
		                   "VALUES ('"+seats+"') " ;
			    	stmt.executeUpdate(sql2);			      
			    }
			    JOptionPane.showMessageDialog(contentPane, "The Show has been added successfully !");
			       setCursor(Cursor.getDefaultCursor());
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
        btnNewButton.setBounds(80, 386, 155, 42);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Cancel");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ShowManagerFrame frame = new ShowManagerFrame();
				frame.setVisible(true);
				dispose();
        	}
        });
        btnNewButton_1.setBounds(386, 386, 165, 42);
        contentPane.add(btnNewButton_1);
        
        JLabel lblNewLabel = new JLabel("V.I.P Seats");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(10, 186, 103, 20);
        contentPane.add(lblNewLabel);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 217, 613, 2);
        contentPane.add(separator);
        
        JLabel lblBalconySeats = new JLabel("Balcony Seats");
        lblBalconySeats.setBounds(320, 233, 93, 14);
        contentPane.add(lblBalconySeats);
        
        JLabel lblTicketPrice = new JLabel("Ticket Price");
        lblTicketPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTicketPrice.setBounds(10, 284, 103, 20);
        contentPane.add(lblTicketPrice);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 315, 613, 2);
        contentPane.add(separator_1);
       

	}
}
