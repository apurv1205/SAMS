import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Table extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public Table(JTable temp) {
		setTitle("Data Table");
		setBounds(100, 100, 757, 461);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 721, 370);
		contentPanel.add(scrollPane);
		
		table = temp;
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(308, 380, 137, 42);
		contentPanel.add(btnNewButton);
	}
}
