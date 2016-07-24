
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class AccountantHome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	private int id=0;
	int showId=0;
	public int getId(){
		return id;
	}
	
	public void setId(int a){
		id = a;
	}
	boolean flag=false;
	private JTable table;
	int counter=0;
	JLabel lblNewLabel;
	BufferedImage img1=null;
	BufferedImage img2=null;
	BufferedImage img3=null;
	BufferedImage img4=null;
	BufferedImage img5=null;
	
	//called every 3 seconds to change the background image stored in img1,img2,img3,img4,img5
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
	public AccountantHome(int id) {
		setResizable(false);
		setTitle("Accountant Home");
		ArrayList<String> events=new ArrayList<String>();
		ArrayList<String> date=new ArrayList<String>();
		//loading images for background in these class variables
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
		    	  events.add("No events Created !");
		    	  date.add("Create events first !");
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
					events.add("No events created, create events first !");
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
		setId(id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 457);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 142, 463, 269);
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setOpaque(false);
		scrollPane.setOpaque(false);
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
		
		TransparentButton btnNewButton = new TransparentButton("Add Expenditures");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		btnNewButton.addActionListener(new ActionListener() {
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
				try {
					Connection con = test.getConnection();
					DatabaseMetaData md = con.getMetaData();
					ResultSet rs1 = md.getTables(null, null,"expenditure", null);
					if(!rs1.next()){
							Statement stmt = null;
							stmt = con.createStatement();
						    String sql = "CREATE TABLE IF NOT EXISTS " +
						                   " expenditure(id INTEGER not NULL AUTO_INCREMENT, " +
						                   " accountantid INTEGER, " +
						                   " showid INTEGER, " +
						                   " type VARCHAR(255), " + 
						                   " amount INTEGER, " + 
						                   " PRIMARY KEY ( id ))"; 

						    stmt.executeUpdate(sql);
					}
						JTextField xField = new JTextField(10);
						JSpinner spinner = new JSpinner();
						spinner.setModel(new SpinnerNumberModel(100, 0, 214748764, 1));
						spinner.setBounds(188, 132, 57, 27);

					      JPanel myPanel = new JPanel();
					      myPanel.add(new JLabel("Type"));
					      myPanel.add(xField);
					      myPanel.add(new JLabel("Amount"));
					      myPanel.add(spinner);

					      int result = JOptionPane.showConfirmDialog(contentPane, myPanel, 
					               "Expenditure Details", JOptionPane.OK_CANCEL_OPTION);
					      if (result == JOptionPane.OK_OPTION ) {
					         System.out.println("Username: " + xField.getText());
					         System.out.println("Password: " + spinner.getValue());
					         if(xField.getText().isEmpty()) {
					        	 JOptionPane.showMessageDialog(contentPane, "Type cannot be empty !");
					        	 return;
					         }
					         Statement stmt = con.createStatement();
							    String sql = "INSERT INTO expenditure(accountantid,showid,type,amount) " +
							                   "VALUES ('"+id+"','"+i+"','"+xField.getText()+"','"+spinner.getValue()+"') " ;
							    stmt.executeUpdate(sql);
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
							      JOptionPane.showMessageDialog(contentPane, "Expenditure added Successfully !");
					      }
						} catch (Exception e2) {
							//e2.printStackTrace();
						}
			}
		});
		btnNewButton.setBounds(522, 236, 215, 66);
		contentPane.add(btnNewButton);
		
		TransparentButton btnGoBack = new TransparentButton("Log out");
		btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start window = new Start();
				window.main(null);
				dispose();
			}
		});
		btnGoBack.setBounds(628, 11, 109, 23);
		contentPane.add(btnGoBack);
		
		TransparentButton btnNewButton_1 = new TransparentButton("Change Login Details");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountantEdit edit = new AccountantEdit(id);
				edit.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(522, 45, 215, 32);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 463, 66);
		contentPane.add(scrollPane_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 26));
		textPane.setEditable(false);
		scrollPane_1.setViewportView(textPane);
		textPane.setOpaque(false);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		
		Timer timer = new Timer(3000,new TimerListener());
		timer.start();
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 757, 428);
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
				ResultSet rs1 = md.getTables(null, null,"accountant", null);
				if(rs1.next()){
		      String sql = "SELECT name,gender FROM accountant WHERE id = '"+id+"' ";
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
