import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.PreparedStatement;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mysql.jdbc.PreparedStatement;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class SalesPersonHome extends JFrame {
	
	private Connection con;
	private int id;
	private int showId = 0;
	private JPanel contentPane;
	private Date current;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesPersonHome frame = new SalesPersonHome();
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
	int counter=0;
	JLabel lblNewLabel;
	BufferedImage img1=null;
	BufferedImage img2=null;
	BufferedImage img3=null;
	BufferedImage img4=null;
	BufferedImage img5=null;
	
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
	public void setId(int a){
		id = a;
	}
	
	/**
	 * Create the frame.
	 */
	ArrayList<Integer> seatids = new ArrayList<Integer>();
	ArrayList<Integer> seattypes = new ArrayList<Integer>();
	boolean flag = false;
	private JTable table;
	boolean clicked=false;
	public SalesPersonHome(int a) {
		setResizable(false);
		setTitle("Sales Person Home");
		ArrayList<String> events=new ArrayList<String>();
		ArrayList<String> date=new ArrayList<String>();
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
//		Connection con;
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
		setId(a);
		System.out.println("Id : " + id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TransparentButton btnNewButton = new TransparentButton("Change Account Details");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesPersonEdit edit = new SalesPersonEdit(id);
				edit.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(504, 11, 210, 29);
		contentPane.add(btnNewButton);
		
		TransparentButton btnBookNewTickets = new TransparentButton("Book New Ticket(s)");
		btnBookNewTickets.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBookNewTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Show : " + showId);
				if(showId==0){
					JOptionPane.showMessageDialog(contentPane,"No Event Selected !");
					return;
				}
				try {
					int n=JOptionPane.OK_OPTION;
					Connection con;
					 con = test.getConnection();
					 Statement stmt = null;
				      stmt = con.createStatement();
				      String sql = "SELECT name,datetime,avnseats,avbseats,nprice,bprice FROM events WHERE id ='"+showId+"' ";
				      ResultSet rs = stmt.executeQuery(sql);
				      //STEP 5: Extract data from result set
				      int nprice = 0;
			    	  int bprice = 0;
			    	  int temp1 = 0;
			    	  int temp2 = 0;
			    	  String name = null;
			    	  String datetime=null;
					  current = new Date();
			    	  
				      while(rs.next()){
				    	  name = rs.getString("name");
				    	  datetime = rs.getString("datetime");
				    	  nprice=rs.getInt("nprice");
				    	  bprice=rs.getInt("bprice");
				    	  temp1=rs.getInt("avnseats");
				    	  temp2=rs.getInt("avbseats");
				      }
				      rs.close();
					  long diff=(sdf.parse(datetime).getTime()-current.getTime());
					  if(diff<0) {
						  JOptionPane.showMessageDialog(contentPane, "Event is not Available !");
						  return;
					  }
				     /* JSpinner normal = new JSpinner();
				      JSpinner balcony = new JSpinner();
				      
				      normal.setModel(new SpinnerNumberModel(0, 0, temp1, 1));
				      balcony.setModel(new SpinnerNumberModel(0, 0, temp2, 1));
				      
				      JPanel myPanel = new JPanel();
				      myPanel.add(new JLabel("Normal Seats"));
				      myPanel.add(normal);
				      myPanel.add(new JLabel("Balcony Seats"));
				      myPanel.add(balcony);
				      
				      int result = JOptionPane.showConfirmDialog(contentPane, myPanel, 
				               "Login Details", JOptionPane.OK_CANCEL_OPTION);
				      int normalseat = 0;
				      int balconyseat = 0;
				      if (result == JOptionPane.OK_OPTION) {
				    	  normalseat = (int) normal.getValue();
				    	  balconyseat = (int)balcony.getValue();
				         System.out.println("Normal Seats: " + normalseat);
				         System.out.println("Balcony Seats: " + balconyseat);
				      }
				      if(result==JOptionPane.CANCEL_OPTION) return;*/
				      JPanel panel=new JPanel();
						JPanel panel1=new JPanel(new BorderLayout());
						JPanel panel2=new JPanel();
						panel.setLayout(new GridLayout(0,Start.getCols()));
						panel2.setLayout(new GridLayout(0,1));
						try {
							con = test.getConnection();
							 stmt = null;
						      stmt = con.createStatement();
						      sql = "SELECT id,seats FROM event_"+showId+" WHERE id >0 ";
						      rs = stmt.executeQuery(sql);
						      int id=0;
						      int type=0;
						      while(rs.next()){
						    	  JButton b=new JButton();
						    	  b.addActionListener(new ActionListener() {
						  			public void actionPerformed(ActionEvent e) {
						  				JButton button = (JButton)e.getSource();
						  				button.setEnabled(false);
						  	            int seatid = Integer.parseInt(button.getText());
						  	            seatids.add(seatid);
						  	            int seattype=Integer.parseInt(button.getName());
						  	            seattypes.add(seattype);
						  			}
						    	  });
						    	  id=rs.getInt("id");
						    	  b.setText(Integer.toString(id));
						    	  Color bg = null;
						    	  type=rs.getInt("seats");
						    	  b.setName(Integer.toString(type));
						    	  if(type==0)bg=Color.BLUE;
						    	  else if(type==1) {bg=Color.green;b.setEnabled(false);}
						    	  else if(type==2) bg=Color.ORANGE;
						    	  else if(type==3) {bg=Color.pink;b.setEnabled(false);}
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
						      JScrollPane pane=new JScrollPane();
						      pane.setViewportView(panel1);
						      JOptionPane pane1 = new JOptionPane(pane,JOptionPane.OK_CANCEL_OPTION,
						                JOptionPane.PLAIN_MESSAGE);
						      JDialog dialog = pane1.createDialog(null, "Seat Status");
						      dialog.setSize(1200,581);
						      dialog.show(); 
						     /*n= JOptionPane.showConfirmDialog(null,pane,"Seat Status",JOptionPane.OK_CANCEL_OPTION,
						                JOptionPane.PLAIN_MESSAGE);*/
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(n==JOptionPane.OK_OPTION){
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				      try {
							con = test.getConnection();
							DatabaseMetaData md = con.getMetaData();
							ResultSet rs1 = md.getTables(null, null,"transactions", null);
							if(!rs1.next()){
//								System.out.println("yoyo");
								try {
								    sql = "CREATE TABLE IF NOT EXISTS " +
								                   "transactions(id INTEGER not NULL AUTO_INCREMENT, " +
								                   "showid INTEGER, " +
								                   "showdatetime DATETIME, " +
								                   "dateofbooking DATETIME, " +
								                   "seattype INTEGER, " +
								                   "seatid INTEGER, " +
								                   "price INTEGER, " +
								                   "spid INTEGER, " +
								                   "PRIMARY KEY ( id ))"; 

								    stmt.executeUpdate(sql);
								} catch (Exception e2) {
									e2.printStackTrace();
								}
							
				       
							}
							Document document1 = new Document();
							
							try {
								PdfWriter.getInstance(document1, new FileOutputStream("last_transaction.pdf"));
							} catch (FileNotFoundException | DocumentException e4) {
								// TODO Auto-generated catch block
								e4.printStackTrace();
							}
							document1.open();

							///////////////////           INSERTING 
							
							try{
							    //sql = "SELECT avnseats,avbseats,nprice,bprice FROM events WHERE id ='"+showId+"' ";
							    String curr = sdf.format(current);
							    int seatid=0;
							    for(int j=0;j<seatids.size();j++){
							    	seatid=seatids.get(j);
							    	System.out.println("Yhe seatid : "+seatid);
							    	if(seattypes.get(j)==0){
							   //for(int i = 0;i < normalseat;i++){//event_showid
								    sql = "INSERT INTO transactions(showid,showdatetime,dateofbooking,seattype,seatid,price,spid) " + "VALUES ('"+showId+"','"+datetime+"','"+curr+"','0','"+seatid+"','"+nprice+"','"+id+"') " ;
							    	stmt.executeUpdate(sql);
							    	
							    	sql = "INSERT INTO event_"+showId+"(id,seats) " +
							                   "VALUES ('"+seatid+"','0') " +
							                   		"ON DUPLICATE KEY UPDATE `id` = '"+seatid+"',`seats` = '-1'";
									stmt.executeUpdate(sql);
									sql = "INSERT INTO events(id,name,datetime,nprice,bprice,avnseats,avbseats) " +
				                   "VALUES ('"+showId+"','"+name+"','"+datetime+"','"+nprice+"','"+bprice+"','"+temp1+"','"+temp2+"') " +
							                   		"ON DUPLICATE KEY UPDATE `id`='"+showId+"',`name` = '"+name+"',`datetime` = '"+datetime+"',`nprice` = '"+nprice+"',"
							                   				+ "`bprice` = '"+bprice+"',`avnseats` = '"+(--temp1)+"',`avbseats` = '"+temp2+"'";
									
									stmt.executeUpdate(sql);
									sql = "SELECT id FROM transactions";
									rs = stmt.executeQuery(sql);
									int tid=0;
									while(rs.next()) tid=rs.getInt("id"); 
									document1.add(new Paragraph("Transaction ID : "+tid+
							        		"\nEvent ID : "+showId+
							        		"\nEvent Name : "+name+
							        		"\nEvent Time and Date : "+datetime+
							        		"\nSeat Type : Normal"+
							        		"\nSeat Number : "+seatid+
							        		"\nBooking Date : "+curr+
							        		"\nAmount : "+bprice + "\n\n"));
									document1.add(new LineSeparator());


									//if(seatids.size() == 0)document1.add(new Paragraph("No Seats Booked"));
									/*JOptionPane.showMessageDialog(contentPane, "Transaction Id : "+tid+
											"\nEvent Name : "+name+
											"\nEvent Time and Date : "+datetime+
											"\nEvent Id : "+showId+
											"\nSeat Type : Normal"+
											"\nSeat Number : "+seatid+
											"\nAmount : "+nprice);*/
							    }
							   // for(int i = 0;i < balconyseat;i++){
							    	if(seattypes.get(j)==2){
								    sql = "INSERT INTO transactions(showid,showdatetime,dateofbooking,seattype,seatid,price,spid) " + "VALUES ('"+showId+"','"+datetime+"','"+curr+"','2','"+seatid+"','"+bprice+"','"+id+"') " ;
							    	stmt.executeUpdate(sql);
							    	
							    	sql = "INSERT INTO event_"+showId+"(id,seats) " +
							                   "VALUES ('"+seatid+"','2') " +
							                   		"ON DUPLICATE KEY UPDATE `id` = '"+seatid+"',`seats` = '-2'";
									stmt.executeUpdate(sql);
							    	
							    	sql = "INSERT INTO events(id,name,datetime,nprice,bprice,avnseats,avbseats) " +
							                   "VALUES ('"+showId+"','"+name+"','"+datetime+"','"+nprice+"','"+bprice+"','"+temp1+"','"+temp2+"') " +
										                   		"ON DUPLICATE KEY UPDATE `id`='"+showId+"',`name` = '"+name+"',`datetime` = '"+datetime+"',`nprice` = '"+nprice+"',"
										                   				+ "`bprice` = '"+bprice+"',`avnseats` = '"+(temp1)+"',`avbseats` = '"+(--temp2)+"'";
												
									stmt.executeUpdate(sql);
									sql = "SELECT id FROM transactions";
									rs = stmt.executeQuery(sql);
									int tid=0;
									while(rs.next()) tid=rs.getInt("id");
									document1.add(new Paragraph("Transaction ID : "+tid+
							        		"\nEvent ID : "+showId+
							        		"\nEvent Name : "+name+
							        		"\nEvent Time and Date : "+datetime+
							        		"\nSeat Type : Balcony"+
							        		"\nSeat Number : "+seatid+
							        		"\nBooking Date : "+curr+
							        		"\nAmount : "+bprice + "\n\n"));
									document1.add(new LineSeparator());

									/*JOptionPane.showMessageDialog(contentPane, "Transaction Id : "+tid+
											"\nEvent Name : "+name+
											"\nEvent Time and Date : "+datetime+
											"\nEvent Id : "+showId+
											"\nSeat Type : Balcony"+
											"\nSeat Number : "+seatid+
											"\nAmount : "+bprice);*/
							    }
							    
							    }
							    if(seatids.size() == 0)document1.add(new Paragraph("No Seats Booked"));
							    document1.close();
							    if (Desktop.isDesktopSupported()) {
								    File myFile = new File("last_transaction.pdf");
									//JOptionPane.showMessageDialog(null,seatids.size());
								    if(seatids.size() != 0)Desktop.getDesktop().open(myFile);
								}
							    try{
							         if(stmt!=null)
							            stmt.close();
							     }catch(Exception se2){
							    	 se2.printStackTrace();
							     }// nothing we can do
							     try{
							        if(con!=null)
							           con.close();
							     }catch(Exception se){
							        se.printStackTrace();
							     }
							    
							}catch(Exception ep){
								ep.printStackTrace();
							}
								
						
					  }catch(Exception e3){
				    	  e3.printStackTrace();
				      }
						}	
				  }catch(Exception e0){
					  e0.printStackTrace();
				  }
				dispose();
				SalesPersonHome frame=new SalesPersonHome(id);
				frame.setVisible(true);
				
			}
		});
		btnBookNewTickets.setBounds(504, 392, 298, 46);
		contentPane.add(btnBookNewTickets);
		
		TransparentButton btnCancelBooking = new TransparentButton("Cancel Booking");
		btnCancelBooking.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag) {
					JOptionPane.showMessageDialog(contentPane,"No Events Created !");
					return;
				}
				try {
					Connection con;
					 con = test.getConnection();
						DatabaseMetaData md = con.getMetaData();
						ResultSet rs1 = md.getTables(null, null,"transactions", null);
						if(!rs1.next()){
							JOptionPane.showMessageDialog(contentPane,"No Transactions created!");
							return;
						}
					 Statement stmt = null;
				      stmt = con.createStatement();
				      String sql = "SELECT id FROM transactions WHERE id > 0 ";
				      ResultSet rs = stmt.executeQuery(sql);
				       int maxTransactions = 0;
				       ArrayList<Integer> ids=new ArrayList<Integer>();
				      while(rs.next()){
				    	  maxTransactions=rs.getInt("id");
				    	  ids.add(maxTransactions);
				      }
				      SpinnerModel model=new SpinnerListModel(ids);
				       JSpinner spinner = new JSpinner();
						spinner.setModel(model);
										      
						 JComponent field = ((JSpinner.DefaultEditor) spinner.getEditor());
					      Dimension prefSize = field.getPreferredSize();
					      prefSize = new Dimension(30, prefSize.height);
					      field.setPreferredSize(prefSize);
						
				      JPanel myPanel = new JPanel();
				      myPanel.add(new JLabel("Transaction Id :     "));
				      myPanel.add(spinner);
				      
				      int result = JOptionPane.showConfirmDialog(contentPane, myPanel, 
				               "Transaction Id Required", JOptionPane.OK_CANCEL_OPTION);
				      if (result == JOptionPane.CANCEL_OPTION) {
				      return;
				      }
				      int tid=0;
				      if (result == JOptionPane.OK_OPTION) {
					      tid=(int)spinner.getValue();
					  	Document document1 = new Document();						
						try {
							PdfWriter.getInstance(document1, new FileOutputStream("last_transaction.pdf"));
						} catch (FileNotFoundException | DocumentException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						}
						document1.open();

				      try {
							con = test.getConnection();
							DatabaseMetaData md1 = con.getMetaData();
							ResultSet rs11 = md1.getTables(null, null,"transactions", null);
							if(!rs11.next()){
								JOptionPane.showMessageDialog(contentPane,"No Transactions created!");
								return;
							}
							
							///////////////////           INSERTING 
							
							try{
								con = test.getConnection();
								stmt = null;
							    stmt = con.createStatement();
							    //sql = "SELECT avnseats,avbseats,nprice,bprice FROM events WHERE id ='"+showId+"' ";
							    current = new Date();
							    String curr = sdf.format(current);
							    
							    
							    sql = "SELECT showid,showdatetime,seattype,seatid,price,spid FROM transactions WHERE id = '"+tid+"' ";
						    	rs = stmt.executeQuery(sql);
						    	System.out.println("Step 1");
						    	if(!rs.next()){
						    		JOptionPane.showMessageDialog(contentPane, "The Ticket is already Cancelled !");
						    		return;
						    	}
						    	int showId = rs.getInt("showid");
						    	int seatid = rs.getInt("seatid");
						    	int seattype = rs.getInt("seattype");
						    	int price = rs.getInt("price");
						    	int spid = rs.getInt("spid");
						    	String datetime = rs.getString("showdatetime");
						    	System.out.println("Step 0");
						    	
						    	long diff = (sdf.parse(datetime).getTime()-current.getTime())/(1000*60*60);
						    	System.out.println("diff :  "+diff);
						    	if(diff >= 72)price = 5;
						    	else if(diff < 72 && diff >= 24) {if(seattype == 0)price = 10;
						    	else price = 15;
						    	}
						    	else if(diff < 24 && diff >= 0)price /= 2;
						    							    	
						    	else {
						    		JOptionPane.showMessageDialog(null, "Invalid Request (Show date Passed)");
						    		return;
						    	}
						    	
								sql = "SELECT name,datetime,avnseats,avbseats,nprice,bprice FROM events WHERE id ='"+showId+"' ";
							      ResultSet rs2 = stmt.executeQuery(sql);
							      //STEP 5: Extract data from result set
							      int nprice = 0;
						    	  int bprice = 0;
						    	  int temp1 = 0;
						    	  int temp2 = 0;
						    	  String name = null;
						    	  String datetime1=null;
						    	  
							      while(rs2.next()){
							    	  name = rs2.getString("name");
							    	  datetime1 = rs2.getString("datetime");
							    	  nprice=rs2.getInt("nprice");
							    	  bprice=rs2.getInt("bprice");
							    	  temp1=rs2.getInt("avnseats");
							    	  temp2=rs2.getInt("avbseats");
							      }
							      int n=JOptionPane.showConfirmDialog(contentPane, "Cancel the following booking ?\n\nTransaction Id : "+tid+
											"\nEvent Name : "+name+
											"\nEvent Time and Date : "+datetime+
											"\nEvent Id : "+showId+
											"\nSeat Type : "+seattype+
											"\nSeat Number : "+seatid+
											"\nRefund Amount : "+price,"Proceed ?",JOptionPane.YES_NO_OPTION);
							      if(n==JOptionPane.YES_OPTION) {
									    
								   sql = "INSERT INTO transactions(showid,showdatetime,dateofbooking,seattype,seatid,price,spid) " + "VALUES ('"+showId+"','"+datetime+"','"+curr+"','"+seattype+"','"+seatid+"','"+price+"','"+id+"') " ;				    	
								   stmt.executeUpdate(sql);
								if(seattype == 0){
									sql = "INSERT INTO event_"+showId+"(id,seats) " +
							                   "VALUES ('"+seatid+"','0') " +
							                   		"ON DUPLICATE KEY UPDATE `id` = '"+seatid+"',`seats` = '0'";
							    	
									stmt.executeUpdate(sql);
									sql = "INSERT INTO events(id,name,datetime,nprice,bprice,avnseats,avbseats) " +
			                   "VALUES ('"+showId+"','"+name+"','"+datetime+"','"+nprice+"','"+bprice+"','"+temp1+"','"+temp2+"') " +
						                   		"ON DUPLICATE KEY UPDATE `id`='"+showId+"',`name` = '"+name+"',`datetime` = '"+datetime+"',`nprice` = '"+nprice+"',"
						                   				+ "`bprice` = '"+bprice+"',`avnseats` = '"+(++temp1)+"',`avbseats` = '"+temp2+"'";
									stmt.executeUpdate(sql);
									
									sql = "DELETE FROM transactions " +
							                   "WHERE id = '"+tid+"'";
							      stmt.executeUpdate(sql);
								}
								else{
									sql = "INSERT INTO event_"+showId+"(id,seats) " +
							                   "VALUES ('"+seatid+"','0') " +
							                   		"ON DUPLICATE KEY UPDATE `id` = '"+seatid+"',`seats` = '2'";
							    	
									stmt.executeUpdate(sql);
									sql = "INSERT INTO events(id,name,datetime,nprice,bprice,avnseats,avbseats) " +
							                   "VALUES ('"+showId+"','"+name+"','"+datetime+"','"+nprice+"','"+bprice+"','"+temp1+"','"+temp2+"') " +
										                   		"ON DUPLICATE KEY UPDATE `id`='"+showId+"',`name` = '"+name+"',`datetime` = '"+datetime+"',`nprice` = '"+nprice+"',"
										                   				+ "`bprice` = '"+bprice+"',`avnseats` = '"+(temp1)+"',`avbseats` = '"+(++temp2)+"'";
									stmt.executeUpdate(sql);
									
									sql = "DELETE FROM transactions " +
							                   "WHERE id = '"+tid+"'";
							      stmt.executeUpdate(sql);
								}
								sql = "SELECT id FROM transactions";
								rs = stmt.executeQuery(sql);
								int tidi=0;
								String type=null;
								if(seattype==0) type="Normal";
								if(seattype==2) type="Balcony";
								while(rs.next()) tidi=rs.getInt("id"); 
								document1.add(new Paragraph("Cancellation Transaction ID : "+tidi+
						        		"\nEvent ID : "+showId+
						        		"\nEvent Name : "+name+
						        		"\nEvent Time and Date : "+datetime+
						        		"\nSeat Type : "+type+
						        		"\nSeat Number : "+seatid+
						        		"\nCancellation Date : "+curr+
						        		"\nRefund Amount : "+price + "\n\n"));
								document1.add(new LineSeparator());
							    document1.close();
							    if (Desktop.isDesktopSupported()) {
								    File myFile = new File("last_transaction.pdf");
									Desktop.getDesktop().open(myFile);
								}
							/*	JOptionPane.showMessageDialog(contentPane, "Transaction Id : "+tidi+
										"\nEvent Name : "+name+
										"\nEvent Time and Date : "+datetime+
										"\nEvent Id : "+showId+
										"\nSeat Type : "+type+
										"\nSeat Number : "+seatid+
										"\nRefund Amount : "+price);*/
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
							     }
									dispose();
									SalesPersonHome frame=new SalesPersonHome(id);
									frame.setVisible(true);
							      }
							}catch(Exception ep){
								System.out.println("2");
								ep.printStackTrace();
							}						
					  }catch(Exception e3){
				    	  e3.printStackTrace();
				      }
				      }
				  }catch(Exception e0){
					  e0.printStackTrace();
				  }
			}
		});
		btnCancelBooking.setBounds(504, 45, 298, 46);
		contentPane.add(btnCancelBooking);
		
		TransparentButton btnLogOut = new TransparentButton("Log Out");
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start window = new Start();
				window.main(null);
				dispose();
			}
		});
		btnLogOut.setBounds(724, 11, 78, 29);
		contentPane.add(btnLogOut);
		
		JTextArea txtrNoShowSelected = new JTextArea();
		txtrNoShowSelected.setOpaque(false);
		txtrNoShowSelected.setFont(new Font("Moire", Font.BOLD, 17));
		txtrNoShowSelected.setForeground(Color.WHITE);
		txtrNoShowSelected.setText("No Show Selected !");
		txtrNoShowSelected.setEditable(false);
		txtrNoShowSelected.setBounds(504, 137, 298, 242);
		contentPane.add(txtrNoShowSelected);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBounds(10, 137, 449, 301);
		contentPane.add(scrollPane);
		
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
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 449, 80);
		contentPane.add(scrollPane_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setOpaque(false);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 26));
		textPane.setEditable(false);
		scrollPane_1.setViewportView(textPane);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		
		Timer timer = new Timer(3000,new TimerListener());
		timer.start();
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 822, 461);
		contentPane.add(lblNewLabel);
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
				ResultSet rs1 = md.getTables(null, null,"salesperson", null);
				if(rs1.next()){
		      String sql = "SELECT name,gender FROM salesperson WHERE id ='"+id+"' ";
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
