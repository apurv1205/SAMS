import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextPane;
import javax.swing.UIManager;

public class Start {

	private JFrame frmWelcome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Start() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	//Number of columns in the grid displayed on clicking seat status button
	private static int cols=17; 
	public static int getCols() {
		return cols;
	}

	public static void setCols(int cols) {
		Start.cols = cols;
	}
	
	//for timer to keep count of current background
	int counter=0;
	JLabel lblNewLabel;
	
	//loading images in these variables for the background
	BufferedImage img1=null;
	BufferedImage img2=null;
	BufferedImage img3=null;
	BufferedImage img4=null;
	BufferedImage img5=null;
	
	//Actionlistener for the timer, it increments counter by 1 every 3 seconds
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
	boolean flag=false;
	private JTable table;
	int showId=0;
	
	
	private void initialize() {
		Timer timer = new Timer(3000,new TimerListener());
		timer.start();
		//loading background images
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
		ArrayList<String> events=new ArrayList<String>();
		ArrayList<String> date=new ArrayList<String>();
		frmWelcome = new JFrame();
		frmWelcome.setResizable(false);
		frmWelcome.setBackground(Color.WHITE);
		frmWelcome.getContentPane().setBackground(new Color(245, 222, 179));
		frmWelcome.setTitle("Welcome !");
		frmWelcome.setBounds(100, 100, 821, 621);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcome.getContentPane().setLayout(null);
		
		//entering existing events in the list model
		Connection con;
		try {
			con = test.getConnection();
			 Statement stmt = null;
		      stmt = con.createStatement();
		      DatabaseMetaData md = con.getMetaData();
				ResultSet rs1 = md.getTables(null, null,"events", null);
				if(rs1.next()){
				      String sql = "SELECT name,datetime FROM events";
				      ResultSet rs = stmt.executeQuery(sql);
		      if(!rs.next()) { 
		    	  events.add("No events Created, create events first !");
		    	  flag=true;
		      }
		      //STEP 5: Extract data from result set
		      else{
		    	  String temp1=rs.getString("name");
		    	  String temp2=rs.getString("datetime");
		    	  events.add(temp1);
		    	  date.add(temp2);
		      while(rs.next()){
		    	  temp1=rs.getString("name");
		    	  temp2=rs.getString("datetime");
		    	  events.add(temp1);
		    	  date.add(temp2);
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
		       rs1.close();
				}
				   else{
			    	   events.add("No events Created, create events first !");
			    	   flag=true;
			       }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//class for transcluscent buttons
		class TransparentButton extends JButton {
			public TransparentButton(String text) { 
			    super(text);
			    setOpaque(false); 
			} 
			    
			public void paint(Graphics g) { 
			    Graphics2D g2 = (Graphics2D) g.create(); 
			    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f)); 
			    super.paint(g2); 
			    g2.dispose(); 
			} 
		}
		TransparentButton btnLogin = new TransparentButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Show Manager",
	                    "Sales Person",
	                    "Accountant"};
				int n = JOptionPane.showOptionDialog(frmWelcome,      //n=0 for showmanager,n=1 for sales person ,n=2 for accountant
						"Select Login Account Type : ",
								"Login Question",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
				if(n==0){
					try {
						Connection con = test.getConnection();
						DatabaseMetaData md = con.getMetaData();
						ResultSet rs1 = md.getTables(null, null,"showmanager", null);
						if(!rs1.next()){
							try {
								JOptionPane.showMessageDialog(frmWelcome, "No Manager Account created, create Manager Account first !");
								Statement stmt = null;
								stmt = con.createStatement();
							    String sql = "CREATE TABLE IF NOT EXISTS " +
							                   "showmanager(id INTEGER not NULL AUTO_INCREMENT, " +
							                   " name VARCHAR(255), " + 
							                   " gender VARCHAR(255), " + 
							                   " age INTEGER, " + 
							                   " mobilenumber LONG, " + 
							                   " email VARCHAR(255), " + 
							                   " address VARCHAR(255), " + 
							                   " username VARCHAR(255), " +
							                   " password VARCHAR(255), " +
							                   " nseats INTEGER, "+
							                   " bseats INTEGER, "+
							                   " PRIMARY KEY ( id ))"; 

							    stmt.executeUpdate(sql);
								try{
							         if(stmt!=null)
							            stmt.close();
							      }catch(Exception se2){
							      }// nothing we can do
							      try{
							         if(con!=null)
							            con.close();
							      }catch(Exception se){
							        // se.printStackTrace();
							      }//end finally try
							} catch (Exception e2) {
								//e2.printStackTrace();
							}
							ShowManagerDetails frame = new ShowManagerDetails();
							frame.setVisible(true);
							frmWelcome.dispose();
						}
						else{
							JTextField xField = new JTextField(10);
						      JPasswordField yField = new JPasswordField(10);

						      JPanel myPanel = new JPanel();
						      myPanel.add(new JLabel("User Name"));
						      myPanel.add(xField);
						      myPanel.add(new JLabel("Password"));
						      myPanel.add(yField);

						      int result = JOptionPane.showConfirmDialog(frmWelcome, myPanel, 
						               "Login Details", JOptionPane.OK_CANCEL_OPTION);
						      if (result == JOptionPane.OK_OPTION) {
						         System.out.println("Username: " + xField.getText());
						         System.out.println("Password: " + yField.getText());
						      
						      Statement stmt = null;
						      stmt = con.createStatement();
						      String sql = "SELECT username ,password FROM showmanager WHERE id = '1'";
						      ResultSet rs = stmt.executeQuery(sql);
						      //STEP 5: Extract data from result set
						      String username = null;
						      String password = null;
						      while(rs.next()){
						         //Retrieve by column name
						         username  = rs.getString("username");
						         password = rs.getString("password");
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
						       if(xField.getText().equals(username) && yField.getText().equals(password)){
						    	   JOptionPane.showMessageDialog(frmWelcome,"Login Successful !");
						    	   	try {
									ShowManagerFrame frame = new ShowManagerFrame();
									frame.setVisible(true);
									frmWelcome.dispose();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
						       }
						       else JOptionPane.showMessageDialog(frmWelcome,"Login Unsuccessful Try Again!");
						}
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if(n==1){					
					try {
						Connection con = null;
						con = test.getConnection();
						  DatabaseMetaData md;
							md = con.getMetaData();
							ResultSet rs1 = md.getTables(null, null,"salesperson", null);
							if(!rs1.next()){
								JOptionPane.showMessageDialog(frmWelcome, "No Sales Person Account exists, Create Sales Person account first !");
								return;
							}
						JTextField xField = new JTextField(10);
					      JPasswordField yField = new JPasswordField(10);

					      JPanel myPanel = new JPanel();
					      myPanel.add(new JLabel("User Name"));
					      myPanel.add(xField);
					      myPanel.add(new JLabel("Password"));
					      myPanel.add(yField);

					      int result = JOptionPane.showConfirmDialog(frmWelcome, myPanel, 
					               "Login Details", JOptionPane.OK_CANCEL_OPTION);
					      if (result == JOptionPane.OK_OPTION) {
					         System.out.println("Username: " + xField.getText());
					         System.out.println("Password: " + yField.getText());
					      }
					      if(result==JOptionPane.CANCEL_OPTION) return;
					      
					      Statement stmt = null;
					      stmt = con.createStatement();
					      String sql = "SELECT id,username,password FROM salesperson WHERE `username`='"+xField.getText()+"' ";
					      ResultSet rs = stmt.executeQuery(sql);
					      //STEP 5: Extract data from result set
					      String password = null;
					      int id=0;
					      while(rs.next()){
					    	  id=rs.getInt("id");
					         password = rs.getString("password");
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
					       if(yField.getText().equals(password)){
					    	   JOptionPane.showMessageDialog(frmWelcome,"Login Successful !");
					    		SalesPersonHome frame = new SalesPersonHome(id);
								frame.setVisible(true);
					    	   frmWelcome.dispose();
					       }
					       else JOptionPane.showMessageDialog(frmWelcome,"Login Unsuccessful Try Again!");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if(n==2){
					try {
						Connection con = null;
						con = test.getConnection();
					 	  DatabaseMetaData md;
							md = con.getMetaData();
							ResultSet rs1 = md.getTables(null, null,"accountant", null);
							if(!rs1.next()){
								JOptionPane.showMessageDialog(frmWelcome, "No Accountant Account exists, Create Accountant account first !");
								return;
							}
						JTextField xField = new JTextField(10);
					      JPasswordField yField = new JPasswordField(10);

					      JPanel myPanel = new JPanel();
					      myPanel.add(new JLabel("User Name"));
					      myPanel.add(xField);
					      myPanel.add(new JLabel("Password"));
					      myPanel.add(yField);

					      int result = JOptionPane.showConfirmDialog(frmWelcome, myPanel, 
					               "Login Details", JOptionPane.OK_CANCEL_OPTION);
					      if (result == JOptionPane.OK_OPTION) {
					         System.out.println("Username: " + xField.getText());
					         System.out.println("Password: " + yField.getText());
					      }
					      if(result==JOptionPane.CANCEL_OPTION) return;
					      Statement stmt = null;
					      stmt = con.createStatement();
					      String sql = "SELECT id,password FROM accountant WHERE `username`='"+xField.getText()+"' ";
					      ResultSet rs = stmt.executeQuery(sql);
					      //STEP 5: Extract data from result set
					      String password = null;
					      int id=1;
					      while(rs.next()){
					         password = rs.getString("password");
					         id=rs.getInt("id");
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
					       if(yField.getText().equals(password)){
					    	   JOptionPane.showMessageDialog(frmWelcome,"Login Successful !");
					    	   AccountantHome frame=new AccountantHome(id);
					    	   frame.setVisible(true);
					    	   frmWelcome.dispose();
					       }
					       else JOptionPane.showMessageDialog(frmWelcome,"Login Unsuccessful Try Again!");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
					
				}
		});
		btnLogin.setBounds(593, 11, 202, 48);
		frmWelcome.getContentPane().add(btnLogin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(10, 121, 372, 450);
		frmWelcome.getContentPane().add(scrollPane);
		

		JTextArea txtrNoShowSelected = new JTextArea();
		txtrNoShowSelected.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		txtrNoShowSelected.setForeground(Color.WHITE);
		txtrNoShowSelected.setOpaque(false);
		txtrNoShowSelected.setEditable(false);
		txtrNoShowSelected.setText("No Show Selected !");
		txtrNoShowSelected.setBounds(407, 121, 388, 362);
		txtrNoShowSelected.setBorder(null);
		frmWelcome.getContentPane().add(txtrNoShowSelected);
		
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
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		table.setForeground(Color.BLACK);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JTextPane txtpnStudentsAuditoriumManagement = new JTextPane();
		txtpnStudentsAuditoriumManagement.setForeground(Color.WHITE);
		txtpnStudentsAuditoriumManagement.setEditable(false);
		txtpnStudentsAuditoriumManagement.setFont(new Font("Georgia", Font.BOLD, 34));
		txtpnStudentsAuditoriumManagement.setText("Students' Auditorium Management Software  (SAMS)");
		txtpnStudentsAuditoriumManagement.setBounds(10, 11, 573, 99);
		txtpnStudentsAuditoriumManagement.setOpaque(false);
		frmWelcome.getContentPane().add(txtpnStudentsAuditoriumManagement);
		
		TransparentButton btnShowSeatStatus = new TransparentButton("Show Seat Status");
		btnShowSeatStatus.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnShowSeatStatus.setForeground(Color.BLACK);
		btnShowSeatStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showId==0) {
					JOptionPane.showMessageDialog(frmWelcome, "No event selected !");
					return;
				}
				JPanel panel=new JPanel();
				JPanel panel1=new JPanel(new BorderLayout());
				JPanel panel2=new JPanel();
				panel.setLayout(new GridLayout(0,cols));
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
				     // JOptionPane.showConfirmDialog(null,panel1,"Seat Status",JOptionPane.DEFAULT_OPTION,
				                //JOptionPane.PLAIN_MESSAGE);
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
		btnShowSeatStatus.setBounds(407, 494, 388, 77);
		frmWelcome.getContentPane().add(btnShowSeatStatus);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 805, 582);
		frmWelcome.getContentPane().add(lblNewLabel);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("auditorium-theatre.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblNewLabel.setIcon(imageIcon);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if(flag) return;
				int i=table.getSelectedRow();
				if (i==-1) {
					txtrNoShowSelected.setText("No Shows Selected !");
				}
				else{
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
					      txtrNoShowSelected.setText("Available Seats :\n\nNormal Seats : "+avnseats+"\nBalcony Seats : "+avbseats
					      +"\n\n\n\nSeat Prices :\n\nNormal Seat Price : "+nprice+"\nBalcony Seat Price : "+bprice);
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
	        }
	    });
	}
}
