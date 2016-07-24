import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JTextPane;

public class ShowManagerFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	int max=0;
	//method used for displaying database data in the form of tables
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames){
	    	@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
	    };

	}
	
	//a slight variation of above method...adds new columns and cell data in the output table
	public static DefaultTableModel buildTableModel1(ResultSet rs,int nseats,int bseats)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();
	    boolean flag1=true,flag2=true;

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }
	    columnNames.add("Expenditure");
	    columnNames.add("Sales");

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        Connection con;
		      int sum=0;
			try {
				con = test.getConnection();
				 Statement stmt = null;
			      stmt = con.createStatement();
			      DatabaseMetaData md = con.getMetaData();
					ResultSet rs1 = md.getTables(null, null,"expenditure", null);
					if(!rs1.next()&&flag1){
							JOptionPane.showMessageDialog(null, "No Expenditures created !");
							flag1=false;
						}
					else{
			      String sql = "SELECT type,amount FROM expenditure WHERE showid ='"+rs.getInt("id")+"' ";
			      ResultSet rs2 = stmt.executeQuery(sql);
			      if(rs2.next()){
			      sum+=rs2.getInt("amount");
			      while(rs2.next()){
			    	  sum+=rs2.getInt("amount");
			      }
			      rs2.close();
			      }
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			vector.add(sum);
		      int sales=0;
			try {
				con = test.getConnection();
				 Statement stmt = null;
			      stmt = con.createStatement();
			      DatabaseMetaData md = con.getMetaData();
					ResultSet rs1 = md.getTables(null, null,"transactions", null);
					if(!rs1.next()&&flag2){
							JOptionPane.showMessageDialog(null, "No Transactions created !");
							flag2=false;
						}
					else{
			      String sql = "SELECT price FROM transactions WHERE showid ='"+rs.getInt("id")+"' ";
			      ResultSet rs2 = stmt.executeQuery(sql);
			      if(rs2.next()){
			      sales+=rs2.getInt("price");
			      while(rs2.next()){
			    	  sales+=rs2.getInt("price");
			      }
			      rs2.close();
			      }
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	        vector.add(sales);
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames){
	    	@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
	    };

	}
	
	private JTable table;
	int showId=0;
	
	//a slight variation of above method with more columns and cell data in the output table
	public static DefaultTableModel buildTableModel2(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();
	    boolean flag=true;
	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }
	    columnNames.add("Total Sales");
	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        int sales=0;
	        Connection con;
	        try {
				con = test.getConnection();
				 Statement stmt = null;
			      stmt = con.createStatement();
			      DatabaseMetaData md = con.getMetaData();
					ResultSet rs1 = md.getTables(null, null,"transactions", null);
					if(!rs1.next()&&flag){
							JOptionPane.showMessageDialog(null, "No Transactions created !");
							flag=false;
						}
					else{
			      String sql = "SELECT price FROM transactions WHERE spid ='"+rs.getInt("id")+"' ";
			      ResultSet rs2 = stmt.executeQuery(sql);
			    if(rs2.next()){
			    	 sales+=rs2.getInt("price");
			      while(rs2.next()){
			    	  sales+=rs2.getInt("price");
			      }
			      rs2.close();
			      
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
					}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        vector.add(sales);
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames){
	    	@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
	    };

	}
	int counter=0;
	JLabel lblNewLabel;
	BufferedImage img1=null;
	BufferedImage img2=null;
	BufferedImage img3=null;
	BufferedImage img4=null;
	BufferedImage img5=null;
	
	//called every 3 seconds to change the background image
	class TimerListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent ae) {
	            counter++;
	            if(counter==6) counter=1;
	    		BufferedImage img = null;
	    		if(counter==1){
	    		    img = img1;
	    		}
	    		else if(counter==2){
	    		    img = img2;
	    		}
	    		else if(counter==3){
	    		    img = img3;
	    		}
	    		else if(counter==4){
	    		    img = img4;
	    		}
	    		else {	
	    		    img = img5;
	    		}
	    		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
	    		ImageIcon imageIcon = new ImageIcon(dimg);
	    		lblNewLabel.setIcon(imageIcon);
	        }
	    }
	
	
	int nseats=100;
    int bseats=100;
    boolean flag=false;
	public ShowManagerFrame() {
		setResizable(false);
		setForeground(Color.BLACK);
		setTitle("Show Manager Home");
		ArrayList<String> events=new ArrayList<String>();
		ArrayList<String> date=new ArrayList<String>();
		//loading the images in the following variables 
		try{
			img1=ImageIO.read(new File("auditorium-theatre.jpg"));
			img2 = ImageIO.read(new File("ra-interior.jpg"));
			img3 = ImageIO.read(new File("download.jpg"));
			img4 = ImageIO.read(new File("a.jpg"));
			img5 = ImageIO.read(new File("b.jpg"));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		Connection con;
		try {
			con = test.getConnection();
			 Statement stmt = null;
		      stmt = con.createStatement();
		      DatabaseMetaData md = con.getMetaData();
				ResultSet rs1 = md.getTables(null, null,"events", null);
				if(rs1.next()){
		      String sql = "SELECT name,datetime FROM events WHERE id >0 ";
		      ResultSet rs = stmt.executeQuery(sql);
		      if(!rs.next()) { 
		    	  events.add("No events Created, create events first !");
		    	  flag=true;
		      }
		      else{
		    	  String temp1=rs.getString("name");
		    	  String temp2=rs.getString("datetime");
		    	  date.add(temp2);
		    	  events.add(temp1);
		      while(rs.next()){
		    	  temp1=rs.getString("name");
		    	  temp2=rs.getString("datetime");
		    	  date.add(temp2);
		    	  events.add(temp1);
		      }
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
				}
				   else{
			    	   events.add("No events Created, create events first !");
			    	   flag=true;
			       }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		class TransparentButton extends JButton {
			public TransparentButton(String text) { 
			    super(text);
			    setOpaque(false); 
			    setForeground(new Color(0,0,0));
			} 
			    
			public void paint(Graphics g) { 
			    Graphics2D g2 = (Graphics2D) g.create(); 
			    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f)); 
			    super.paint(g2); 
			    g2.dispose(); 
			} 
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel home = new JPanel();
		home.setBackground(new Color(240, 128, 128));
		contentPane.add(home, "name_864480244147120");
		home.setLayout(null);
		
		Connection con1;
		try {
			con1 = test.getConnection();
			 Statement stmt1 = null;
		      stmt1 = con1.createStatement();
		      String sql = "SELECT nseats,bseats FROM showmanager WHERE id = '1'";
		      ResultSet rs = stmt1.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         nseats  = rs.getInt("nseats");
		         bseats = rs.getInt("bseats");
		      }
		      rs.close();
		      try{
		          if(stmt1!=null)
		             con1.close();
		       }catch(SQLException se){
		       }// do nothing
		       try{
		          if(con1!=null)
		             con1.close();
		       }catch(SQLException se){
		          se.printStackTrace();
		       }
			
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		TransparentButton btnAddEmployees = new TransparentButton("Add Employees");
		btnAddEmployees.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = { "Sales Person","Accountant" };
				 String selected = (String) JOptionPane.showInputDialog(home, 
					        "Type of Employee :",
					        "Employee Type",
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        options ,
					        options[0]);
				 if(selected == null) return;
				 if(selected.equals("Sales Person")){
						Connection con;
						try {
							con = test.getConnection();
							DatabaseMetaData md = con.getMetaData();
							ResultSet rs1 = md.getTables(null, null,"salesperson", null);
							if(!rs1.next()){
								try {
									Statement stmt = null;
									stmt = con.createStatement();
								    String sql = "CREATE TABLE IF NOT EXISTS " +
								                   "salesperson(id INTEGER not NULL AUTO_INCREMENT, " +
								                   " name VARCHAR(255), " + 
								                   " gender VARCHAR(255), " + 
								                   " age INTEGER, " + 
								                   " mobilenumber LONG, " + 
								                   " email VARCHAR(255), " + 
								                   " address VARCHAR(255), " + 
								                   " username VARCHAR(255), " +
								                   " password VARCHAR(255), " + 
								                   " PRIMARY KEY ( id ))"; 

								    stmt.executeUpdate(sql);
									try{
								         if(stmt!=null)
								            stmt.close();
								      }catch(SQLException se2){
								      }// nothing we can do
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
							SalesPersonAdd frame = new SalesPersonAdd();
							frame.setVisible(true);
							dispose();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
				 }
				 if(selected.equals("Accountant")){
					 Connection con;
						try {
							con = test.getConnection();
							DatabaseMetaData md = con.getMetaData();
							ResultSet rs1 = md.getTables(null, null,"accountant", null);
							if(!rs1.next()){
								try {
									Statement stmt = null;
									stmt = con.createStatement();
								    String sql = "CREATE TABLE IF NOT EXISTS " +
								                   "accountant(id INTEGER not NULL AUTO_INCREMENT, " +
								                   " name VARCHAR(255), " + 
								                   " gender VARCHAR(255), " + 
								                   " age INTEGER, " + 
								                   " mobilenumber LONG, " + 
								                   " email VARCHAR(255), " + 
								                   " address VARCHAR(255), " + 
								                   " username VARCHAR(255), " +
								                   " password VARCHAR(255), " + 
								                   " PRIMARY KEY ( id ))"; 

								    stmt.executeUpdate(sql);
									try{
								         if(stmt!=null)
								            stmt.close();
								      }catch(SQLException se2){
								      }// nothing we can do
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
							AccountantAdd frame = new AccountantAdd();
							frame.setVisible(true);
							dispose();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
				 }
			}
		});
		btnAddEmployees.setBounds(10, 83, 203, 40);
		home.add(btnAddEmployees);
		
		TransparentButton btnNewButton = new TransparentButton("Change Account Details");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowManagerDetails frame = new ShowManagerDetails();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(509, 83, 203, 40);
		home.add(btnNewButton);
		
		TransparentButton btnAddShows = new TransparentButton("Add Shows");
		btnAddShows.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddShows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						Connection con;
						try {
							con = test.getConnection();
							DatabaseMetaData md = con.getMetaData();
							ResultSet rs1 = md.getTables(null, null,"events", null);
							if(!rs1.next()){
								try {
									System.out.println("n : "+nseats+"   b: "+bseats);
									Statement stmt = null;
									stmt = con.createStatement();
								    String sql = "CREATE TABLE IF NOT EXISTS " +
								                   "events(id INTEGER not NULL AUTO_INCREMENT, " +
								                   " name VARCHAR(255), " + 
								                   " datetime DATETIME, " + 
								                   " nprice INTEGER, "+
								                   " bprice INTEGER, "+
								                   " avnseats INTEGER, "+
								                   " avbseats INTEGER, "+
								                   " PRIMARY KEY ( id ))"; 
								    stmt.executeUpdate(sql);
									try{
								         if(stmt!=null)
								            stmt.close();
								      }catch(SQLException se2){
								      }// nothing we can do
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
							AddShows frame = new AddShows();
							frame.setVisible(true);
							dispose();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}				 
			}
		});
		btnAddShows.setBounds(262, 83, 203, 40);
		home.add(btnAddShows);
		
		TransparentButton btnNewButton_1 = new TransparentButton("Log Out");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start window = new Start();
				window.main(null);
				dispose();
			}
		});
		btnNewButton_1.setBounds(556, 11, 156, 31);
		home.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 292, 417, 244);
		home.add(scrollPane);
		scrollPane.setOpaque(false);
		
		Vector<String> column = new Vector<String>();
		column.add("Events");
		column.add("Date and Time");
		  Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for(int i=0;i<events.size();i++){
	        Vector<Object> vector = new Vector<Object>();
	        if(flag) {
				vector.add("No Events Exists !");
				vector.add("Create Events first !");
				 data.add(vector);
				 break;
			}
	        vector.add(events.get(i));
	        vector.add(date.get(i));
	        data.add(vector);
		}
		DefaultTableModel listModel = new DefaultTableModel(data,column){    
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }};
		
		table = new JTable(listModel);
		table.setOpaque(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setOpaque(false);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if(flag) return;
				int i=table.getSelectedRow();
					i++;
					showId=i;
					Connection con;
					try {
						con = test.getConnection();
						 Statement stmt = null;
					      stmt = con.createStatement();
					      String sql = "SELECT avnseats,avbseats,nprice,bprice FROM events WHERE id ='"+i+"' ";
					      ResultSet rs = stmt.executeQuery(sql);
					      //STEP 5: Extract data from result set
					      int nprice = 0;
				    	  int bprice = 0;
				    	  int avnseats = 0;
				    	  int avbseats = 0;
					      while(rs.next()){
					    	  nprice=rs.getInt("nprice");
					    	  bprice=rs.getInt("bprice");
					    	  avnseats=rs.getInt("avnseats");
					    	  avbseats=rs.getInt("avbseats");
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
	        }
	    });
		
		TransparentButton btnNewButton_2 = new TransparentButton("Show Event Status");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag) {
					JOptionPane.showMessageDialog(contentPane,"No Event Selected !");
					return;
				}
				int i=table.getSelectedRow();
				i++;
				if (i==0 ) {
					JOptionPane.showMessageDialog(contentPane, "Select an event first !");
					return;
				}
				Connection con;
				try {
					con = test.getConnection();
					 Statement stmt = null;
				      stmt = con.createStatement();
				      DatabaseMetaData md = con.getMetaData();
						ResultSet rs1 = md.getTables(null, null,"events", null);
						if(rs1.next()){
				      String sql = "SELECT * FROM event_"+i+" WHERE id >0 ";
				      ResultSet rs = stmt.executeQuery(sql);
				      int avnseats = 0;
				      int avbseats = 0;
				      int vipnseats = 0;
				      int vipbseats = 0;
				      int booked = 0;
				      //STEP 5: Extract data from result set
				      while(rs.next()){
				    	  int seats = rs.getInt("seats");
				    	  if(seats<0) booked++;
				    	  else if(seats==0) avnseats++;
				    	  else if(seats==1) vipnseats++;
				    	  else if(seats==2) avbseats++;
				    	  else vipbseats++;
				      }
				      rs.close();
				      float nbooked = nseats-avnseats-vipnseats;
				      float bbooked=bseats-avbseats-vipbseats;
				      DatabaseMetaData md1 = con.getMetaData();
						ResultSet rs3 = md1.getTables(null, null,"expenditure", null);
						  int exp=0;
						if(rs3.next()){
							  sql = "SELECT amount FROM expenditure WHERE showid ='"+i+"' ";
						      ResultSet rs2 = stmt.executeQuery(sql);
						      while(rs2.next()) exp+=rs2.getInt("amount");
						      rs2.close();
						}
						
						rs3 = md1.getTables(null, null,"transactions", null);
						 int sales=0;
						if(rs3.next()){
				      sql = "SELECT price FROM transactions WHERE showid ='"+i+"' ";
				      ResultSet rs4 = stmt.executeQuery(sql);				     
				      while(rs4.next()) sales+=rs4.getInt("price");
				      rs4.close();
						}
				      JOptionPane.showMessageDialog(contentPane, "Available Normal Seats : "+avnseats+
				    		  "\nAvailable Balcony Seats : "+avbseats+
				    		  "\n\nBooked Normal Seats : "+(int)nbooked+
				    		  "\nBooked Balcony Seats : "+(int)bbooked+
				    		  "\n\nVIP Normal Seats : "+vipnseats+
				    		  "\nVIP Balcony Seats : "+vipbseats+
				    		  "\n\nPercentage of seats booked : "+100*(booked)/(float)(avnseats+avbseats+booked)+
				    		  "\n\nTotal Expenditure of Event : "+exp+
				    		  "\nTotal Sales of Event : "+sales);
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(466, 431, 246, 47);
		home.add(btnNewButton_2);
		
		TransparentButton btnShowEventSales = new TransparentButton("Show Event Sales");
		btnShowEventSales.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnShowEventSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag) {
					JOptionPane.showMessageDialog(contentPane,"No Event Selected !");
					return;
				}
				int i=table.getSelectedRow();
				i++;
				if (i==0 ) {
					JOptionPane.showMessageDialog(contentPane, "Select an event first !");
					return;
				}
				Connection con;
				try {
					con = test.getConnection();
					 Statement stmt = null;
				      stmt = con.createStatement();
				      DatabaseMetaData md = con.getMetaData();
						ResultSet rs1 = md.getTables(null, null,"events", null);
						if(rs1.next()){
							  DatabaseMetaData md1 = con.getMetaData();
								ResultSet rs3 = md1.getTables(null, null,"transactions", null);
								if(!rs3.next()){
									JOptionPane.showMessageDialog(contentPane, "No Transactions created !");
									return;
								}
				      String sql = "SELECT seattype,seatid,spid,price FROM transactions WHERE showid ='"+i+"' ";
				      ResultSet rs = stmt.executeQuery(sql);
				      Table dialog = new Table(new JTable(buildTableModel(rs)));
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
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
						}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowEventSales.setBounds(466, 292, 246, 47);
		home.add(btnShowEventSales);
		
		TransparentButton btnShowEventExpenditures = new TransparentButton("Show Event Expenditures");
		btnShowEventExpenditures.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnShowEventExpenditures.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(flag) {
					JOptionPane.showMessageDialog(contentPane,"No Event Selected !");
					return;
				}
				int i=table.getSelectedRow();
				i++;
				if (i==0 ) {
					JOptionPane.showMessageDialog(contentPane, "Select an event first !");
					return;
				}
				Connection con;
				try {
					con = test.getConnection();
					 Statement stmt = null;
				      stmt = con.createStatement();
							  DatabaseMetaData md1 = con.getMetaData();
								ResultSet rs3 = md1.getTables(null, null,"expenditure", null);
								if(!rs3.next()){
									JOptionPane.showMessageDialog(contentPane, "No Expenditures created !");
									return;
								}
				      String sql = "SELECT type,amount FROM expenditure WHERE showid ='"+i+"' ";
				      ResultSet rs = stmt.executeQuery(sql);
				      Table dialog = new Table(new JTable(buildTableModel(rs)));
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);

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
			}
		});
		btnShowEventExpenditures.setBounds(466, 350, 246, 47);
		home.add(btnShowEventExpenditures);
		
		TransparentButton btnNewButton_3 = new TransparentButton("Show Yearly Balancesheet");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag) {
					JOptionPane.showMessageDialog(contentPane,"No Events Created !");
					return;
				}
				Connection con;
				try {
					JSpinner spinner = new JSpinner();
					spinner.setModel(new SpinnerNumberModel(2000, 1, 214748764, 1));
					spinner.setBounds(188, 132, 57, 27);

				      JPanel myPanel = new JPanel();
				      myPanel.add(new JLabel("Year"));
				      myPanel.add(spinner);

				      int result = JOptionPane.showConfirmDialog(contentPane, myPanel, 
				               "Enter Year", JOptionPane.OK_CANCEL_OPTION);
				      if(result==JOptionPane.CANCEL_OPTION) return;
				      if(result==JOptionPane.OK_OPTION){
					con = test.getConnection();
					 Statement stmt = null;
				      stmt = con.createStatement();
				      DatabaseMetaData md = con.getMetaData();
						ResultSet rs1 = md.getTables(null, null,"events", null);
						if(rs1.next()){
				      String sql = "SELECT id,name,datetime FROM events WHERE year(datetime)='"+spinner.getValue()+"' ";
				      ResultSet rs = stmt.executeQuery(sql);
				      if(!rs.next()){
				    	  JOptionPane.showMessageDialog(contentPane,"No Events Available for year " + spinner.getValue());
				    	  return;
				      }				      
				      rs.previous();
				      Table dialog = new Table(new JTable(buildTableModel1(rs,nseats,bseats)));
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);

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
						}
				      }
				      } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(509, 181, 203, 40);
		home.add(btnNewButton_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 252, 702, 4);
		home.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 150, 702, 4);
		home.add(separator_1);
		

		TransparentButton btnTransactions = new TransparentButton("Transactions");
		btnTransactions.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag) {
					JOptionPane.showMessageDialog(contentPane,"No Events Created !");
					return;
				}
				JSpinner spinner = new JSpinner();
				try {
					Connection con;
					 con = test.getConnection();
					 Statement stmt = null;
					 DatabaseMetaData md = con.getMetaData();
						ResultSet rs1 = md.getTables(null, null,"salesperson", null);
						if(!rs1.next()){JOptionPane.showMessageDialog(contentPane, "No Sales Person Account created !");return;}
				      stmt = con.createStatement();
				      String sql = "SELECT id FROM salesperson WHERE id > 0 ";
				      ResultSet rs = stmt.executeQuery(sql);
				      while(rs.next()) max+=rs.getInt("id");
				}
				catch(Exception eq){
					eq.printStackTrace();
				}
				spinner.setModel(new SpinnerNumberModel(0, 0, max, 1));
				spinner.setBounds(188, 132, 100, 50);
				
				 JComponent field = ((JSpinner.DefaultEditor) spinner.getEditor());
			      Dimension prefSize = field.getPreferredSize();
			      prefSize = new Dimension(50, prefSize.height);
			      field.setPreferredSize(prefSize);
				
			      JPanel myPanel = new JPanel(){
			    	  @Override
			            public Dimension getPreferredSize() {
			                return new Dimension(350, 35);
			            }
			      };
			      myPanel.add(new JLabel("Enter Sales Person id ( 0 for all transactions)"));
			      myPanel.add(spinner);
			      
			      JOptionPane jop = new JOptionPane();
			      jop.setBounds(250,450,500,250);
			      
			      int i = JOptionPane.showConfirmDialog(contentPane, myPanel, 
			               "Sales Person id", JOptionPane.OK_CANCEL_OPTION);
			      
			      if(i==JOptionPane.CANCEL_OPTION) return;
			      Connection con;
					try {
						con = test.getConnection();
						 Statement stmt = null;
					      stmt = con.createStatement();
								  DatabaseMetaData md1 = con.getMetaData();
									ResultSet rs3 = md1.getTables(null, null,"transactions", null);
									if(!rs3.next()){
										JOptionPane.showMessageDialog(contentPane, "No transactions created !");
										return;
									}
									String sql;
						if(i==0) sql = "SELECT * FROM transactions ";
						else sql = "SELECT * FROM transactions WHERE spid ='"+i+"' ";
					      ResultSet rs = stmt.executeQuery(sql);
					      Table dialog = new Table(new JTable(buildTableModel(rs)));
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);

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
			      
			}
		});
		btnTransactions.setBounds(10, 181, 203, 40);
		home.add(btnTransactions);
		
		TransparentButton btnSalesPerEmployee = new TransparentButton("Sales per Employee");
		btnSalesPerEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSalesPerEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag) {
					JOptionPane.showMessageDialog(contentPane,"No Events Created !");
					return;
				}
						Connection con;
						try {
							con = test.getConnection();
							 Statement stmt = null;
						      stmt = con.createStatement();
						      DatabaseMetaData md = con.getMetaData();
								ResultSet rs1 = md.getTables(null, null,"salesperson", null);
								if(rs1.next()){
						      String sql = "SELECT id,name FROM salesperson WHERE id > 0 ";
						      ResultSet rs = stmt.executeQuery(sql);
						      Table dialog = new Table(new JTable(buildTableModel2(rs)));
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);

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
								}
						      }
						      catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			}
		});
		btnSalesPerEmployee.setBounds(262, 181, 203, 40);
		home.add(btnSalesPerEmployee);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 455, 56);
		home.add(scrollPane_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setOpaque(false);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 26));
		textPane.setEditable(false);
		scrollPane_1.setViewportView(textPane);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		
		TransparentButton btnShowSeatStatus = new TransparentButton("Show Seat Status");
		btnShowSeatStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnShowSeatStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showId==0) {
					JOptionPane.showMessageDialog(contentPane, "No event selected !");
					return;
				}
				JPanel panel=new JPanel();
				JPanel panel1=new JPanel(new BorderLayout());
				JPanel panel2=new JPanel();
				panel.setLayout(new GridLayout(0,Start.getCols()));
				panel2.setLayout(new GridLayout(0,1));
				Connection con;
				try {
					con = test.getConnection();
					 Statement stmt = null;
				      stmt = con.createStatement();
				      String sql = "SELECT id,seats FROM event_"+showId+" WHERE id >0 ";
				      ResultSet rs = stmt.executeQuery(sql);
				      //STEP 5: Extract data from result set
				      int id=0;
				      int type=0;
				      while(rs.next()){
				    	  JButton b=new JButton();
				    	  id=rs.getInt("id");
				    	  b.setText(Integer.toString(id));
				    	  Color bg = null;
				    	  type=rs.getInt("seats");
				    	  if(type==0)bg=Color.BLUE;
				    	  else if(type==1) bg=Color.green;
				    	  else if(type==2) bg=Color.ORANGE;
				    	  else if(type==3) bg=Color.pink;
				    	  else {
				    		  b.setEnabled(false);
				    		  bg=Color.darkGray;
				    	  }
				    	  b.setBackground(bg);
				    	  panel.add(b);
				      }
				      rs.close();
				      for(int t=-1;t<4;t++){
				    	  JPanel pan=new JPanel();
				    	  JButton bu=new JButton();
				    	  Color bg = null;
				    	  if(t==0){bg=Color.BLUE;          pan.add(new JLabel("Available Normal Seat---",SwingConstants.CENTER));}
				    	  else if(t==1) {bg=Color.green;   pan.add(new JLabel("VIP Normal Seat-----------",SwingConstants.CENTER));}
				    	  else if(t==2) {bg=Color.ORANGE;  pan.add(new JLabel("Available Balcony Seat--",SwingConstants.CENTER));}
				    	  else if(t==3) {bg=Color.pink;    pan.add(new JLabel("VIP Balcony Seat----------",SwingConstants.CENTER));}
				    	  else if(t==-1){bg=Color.darkGray;pan.add(new JLabel("Booked Seat----------------",SwingConstants.CENTER));}
				    	  bu.setBackground(bg);
				    	  pan.add(bu);
				    	  panel2.add(pan);
				      }
				      panel1.add(new JLabel("All Eyes This Way Please !",SwingConstants.CENTER),BorderLayout.NORTH);
				      panel1.add(panel2,BorderLayout.WEST);
				      panel1.add(panel,BorderLayout.CENTER);
				      //JOptionPane.showConfirmDialog(null,panel1,"Seat Status",JOptionPane.DEFAULT_OPTION,
				        //        JOptionPane.PLAIN_MESSAGE);
				      SeatStatus frame = new SeatStatus(panel1);
						frame.setVisible(true);
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
			}
		});
		btnShowSeatStatus.setBounds(466, 489, 246, 47);
		home.add(btnShowSeatStatus);
		
		Timer timer = new Timer(3000,new TimerListener());
		timer.start();
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 732, 557);
		home.add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("auditorium-theatre.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
		try {
			con = test.getConnection();
			 Statement stmt = null;
		      stmt = con.createStatement();
		      DatabaseMetaData md = con.getMetaData();
				ResultSet rs1 = md.getTables(null, null,"showmanager", null);
				if(rs1.next()){
		      String sql = "SELECT name,gender FROM showmanager WHERE id >0 ";
		      ResultSet rs = stmt.executeQuery(sql);
		      String gender=null,name=null;
		      if(!rs.next()) { 
		      }
		      else{
		    	  gender=rs.getString("gender");
		    	  if(gender.equals("Male")) gender = "Mr. ";
		    	  else gender= "Ms. ";
		    	  name=rs.getString("name");
		      }
		      rs.close();
		      textPane.setText("Welcome "+gender+name+" !");
		      //textPane.setBounds(10, 11, textPane.getText().length()*15+5, 47);
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
				   else{
			       }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
