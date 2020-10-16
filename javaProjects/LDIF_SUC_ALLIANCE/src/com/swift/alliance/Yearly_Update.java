package com.swift.alliance;


import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Yearly_Update extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private static int k=0,start_fromDate=0,start_TillDate=0;
	private static StringBuilder sb ;
	private static String rg_prod_version= null;
	private static String rg_sb ="";
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Yearly_Update frame = new Yearly_Update();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Yearly_Update() {
		setTitle("Yearly Update");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 360, 219);
		setBounds(700, 500, 355, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setResizable(false);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 165, 334, 14);
		label.setForeground(Color.RED);
		contentPane.add(label);
		
		JLabel lblValidTill = new JLabel("Till Date");
		lblValidTill.setBounds(10, 48, 46, 14);
		contentPane.add(lblValidTill);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setEditable(true);
				label.setText("");
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String till_date = textField.getText(); 
				int length = till_date.length();
				//char c = arg0.getKeyChar();
				if (e.getKeyChar() >= '0' && e.getKeyChar()<='9')
				{
					if (length < 8)
					{
						textField.setEditable(true);
					}
					else
						textField.setEditable(false);	
				}
				else
				{
					if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode() == KeyEvent.VK_DELETE )
					{
						textField.setEditable(true);
					}
					else
						textField.setEditable(false);
					//}
				}
			}
		});
		textField.setBounds(66, 45, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFromDate = new JLabel("From Date");
		lblFromDate.setBounds(162, 48, 66, 14);
		contentPane.add(lblFromDate);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String from_date = textField_1.getText(); 
				int length = from_date.length();
				//char c = arg0.getKeyChar();
				if (e.getKeyChar() >= '0' && e.getKeyChar()<='9')
				{
					if (length < 8)
					{
						textField_1.setEditable(true);
					}
					else
						textField_1.setEditable(false);	
				}
				else
				{
					if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode() == KeyEvent.VK_DELETE )
					{
						textField_1.setEditable(true);
					}
					else
						textField_1.setEditable(false);
					//}
				}
			}
		});
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_1.setEditable(true);
				label.setText("");
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(221, 45, 86, 20);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("Base Release");
		lblNewLabel.setBounds(10, 14, 89, 14);
		contentPane.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField_2.setEditable(true);
				label.setText("");
			}
		});
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				String Version_fromgui = textField_2.getText(); 
				int length = Version_fromgui.length();
				
					if (((arg0.getKeyChar() >= '0' && arg0.getKeyChar()<='9') ||  arg0.getKeyChar()=='.') && length < 3)
					{
							textField_2.setEditable(true);
							label.setText("");
					}
					         
					
					else 
						{
							textField_2.setEditable(false);
							label.setText("Character not allowed OR length(3) exceeded.");
							
						}
					
					if (arg0.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || arg0.getExtendedKeyCode() == KeyEvent.VK_DELETE )
						{
					
							textField_2.setEditable(true);
							label.setText("");
					
						}
				
				
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(99, 11, 86, 20);
		contentPane.add(textField_2);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rg_prod_version = textField_2.getText();
				//System.out.println(rg_prod_version);
				//String str_version = textField_2.getText();
				label.setText("");
				try {
					
					
					FileReader fr_rg_sr = new FileReader(LDIF4SoftwareUpdateCheck.SelectedFile);
					BufferedReader br_rg_sr = new BufferedReader(fr_rg_sr);
					
					
					int i;
					
					while((i=br_rg_sr.read())!=-1){ 
						rg_sb = rg_sb + (char)i;
				        
				        }
					br_rg_sr.close();
					fr_rg_sr.close();
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
						label.setText("File I/O exception");
						else
							label.setText("File I/O exception");
					
				}catch (NullPointerException npe) {
					if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
						label.setText("Select the file");
						else
							label.setText("File not found or file not selected");
				}
				
				sb = new StringBuilder(rg_sb);
				
				if (textField_2.getText().length() != 0)
						{
						k = sb.indexOf("b=\""+rg_prod_version);//check later the boundary. Should not return -1
						//System.out.println("Version="+rg_prod_version+".");
						
						}
				else
						{
							label.setText("Version field is mandatory.");
							rg_sb = "";
							return;
						}
				if (k== -1)
						{
							label.setText("Version not found.");
							rg_sb = "";
							return;
						}
								
				//start = sb.lastIndexOf("<", k);				
				//end= sb.indexOf(">",k);				
				start_fromDate=sb.lastIndexOf("f=\"", k);
				start_TillDate=sb.lastIndexOf("t=\"", k);					
				
				
					
				textField_1.setText(sb.substring(start_fromDate+3,start_fromDate+11)); /// setting from date 	
				textField.setText(sb.substring(start_TillDate+3,start_TillDate+11)); /// setting to date
				
				
				k=0;
				start_fromDate=0;
				start_TillDate=0;
				rg_sb = "";
				label.setText("");
				rg_prod_version = "";
				//System.out.println(sb.toString());
				
			}
			
		});
		btnSearch.setBounds(211, 10, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				label.setText("");
				String Product_version = JOptionPane.showInputDialog("Input the version after which it will be added in LDIF file");
				if (Product_version == "")
				{
					label.setText("Input asked can't be empty.");
					rg_sb = "";
					return;
				}
				
				try {
					
					
					FileReader fr_rg_sr = new FileReader(LDIF4SoftwareUpdateCheck.SelectedFile);
					BufferedReader br_rg_sr = new BufferedReader(fr_rg_sr);
					
					
					int i;
					
					while((i=br_rg_sr.read())!=-1){ 
						rg_sb = rg_sb + (char)i;
				        
				        }
					br_rg_sr.close();
					fr_rg_sr.close();
					
					
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
						label.setText("File I/O exception");
						else
							label.setText("File I/O exception");
					
				}catch (NullPointerException npe) {
					if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
						label.setText("Select the file");
						else
							label.setText("File not found or file not selected");
				}
				
				sb = new StringBuilder(rg_sb);
				k = sb.indexOf("b=\""+Product_version);//check later the boundary. Should not return -1
				if (k== -1) {
					//JOptionPane.showMessageDialog(null, "Version not found. Input the correct version\n after which the update record to be added.");
					label.setText("Version not found.");
					rg_sb = "";
					return;
					//k = sb.indexOf("</Updates>");
				}
				 int p = sb.indexOf("b=\""+textField_2.getText());
				 if (p != -1) {
						//JOptionPane.showMessageDialog(null, "Version you want to add already exist in the LDIF file");
						label.setText("Version already exist");
						rg_sb = "";
						return;
						//k = sb.indexOf("</Updates>");
					}
				 
				String tobe_added = "<Release t=\""+ textField.getText() +
						"\" f=\"" + 
						textField_1.getText() + 
						"\" b=\"" + 
						textField_2.getText() +
						"\"/>" ;
				
				sb.insert(sb.indexOf(">",k)+1, tobe_added);
				//System.out.println(textArea.getText());
				//System.out.println(sb.toString());
				//System.out.println(tobe_updated);
				try {
					//FileWriter rg_fw_mod = new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile, false);
					//BufferedWriter rg_bw_mod = new BufferedWriter(rg_fw_mod.sb.toString());
					PrintWriter rg_pr_mod = new PrintWriter(new BufferedWriter(new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile)))	;
					rg_pr_mod.write(sb.toString());
					rg_pr_mod.close();
					k=0;
					
					rg_sb = "";
					
					label.setText("Record Added");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
						label.setText("File I/O exception");
						else
							label.setText("File I/O exception");
					
				}catch (NullPointerException npe) {
					if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
						label.setText("Select the file");
						else
							label.setText("File not found or file not selected");
				}
				
				sb = null;
				
			}
		});
		btnAdd.setBounds(10, 130, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				label.setText("");
				if (sb == null) {
					label.setText("First do search operation on the version.");
					return;
				}
								
				rg_prod_version = textField_2.getText();
				if (textField_2.getText()!= "")
					{
					k = sb.indexOf("b=\""+rg_prod_version);//check later the boundary. Should not return -1
					
					}
				else
					{
						label.setText("Version field is empty.");
						return;
					}
				if (k== -1)
					{
						label.setText("Version not found.");
						return;
					}
				
				k = sb.indexOf("b=\""+rg_prod_version);//check later the boundary. Should not return -1
				
				if (k != -1) {
					
					sb.delete(sb.lastIndexOf("<", k), sb.indexOf(">",k)+1);
					
					try {
						//FileWriter rg_fw_mod = new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile, false);
						//BufferedWriter rg_bw_mod = new BufferedWriter(rg_fw_mod.sb.toString());
						PrintWriter rg_pr_mod = new PrintWriter(new BufferedWriter(new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile)))	;
						rg_pr_mod.write(sb.toString());
						rg_pr_mod.close();
						k=0;
						rg_sb = "";
						label.setText("Record Deleted");
						sb = null;
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
							label.setText("File I/O exception");
							else
								label.setText("File I/O exception");
						
					}catch (NullPointerException npe) {
						if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
							label.setText("Select the file");
							else
								label.setText("File not found or file not selected");
					}
					
					
					}
					else
						label.setText("Record not found");
			
				
			}//Action performed close
		});
		btnDelete.setBounds(126, 130, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sb == null) {
					label.setText("First do search operation on the version.");
					return;
				}
				label.setText("");
				
				rg_prod_version = textField_2.getText();
				if (textField_2.getText()!= "")
					{
					k = sb.indexOf("b=\""+rg_prod_version);//check later the boundary. Should not return -1
					
					}
				else
					{
						label.setText("Version field is empty.");
						return;
					}
				if (k== -1)
					{
						label.setText("Version not found.");
						return;
					}
				
				k = sb.indexOf("b=\""+rg_prod_version);//check later the boundary. Should not return -1
				
				
				if (k != -1) {
								
					
					//check all elements are present
						//version check
					if (textField_2.getText().length() ==0 || textField.getText().length() ==0 || textField_1.getText().length() ==0 )
					{
						label.setText("All parameters are mandatory.");
						return;
					}
					
					
					
					String tobe_updated = "<Release t=\""+ textField.getText() +
							"\" f=\"" + 
							textField_1.getText() + 
							"\" b=\"" + 
							textField_2.getText() +
							"\"/>" ;
					// Add code to check the start and end int for -1 if the version to be modified is not found and correct file to update
					sb.replace(sb.lastIndexOf("<", k), sb.indexOf(">",k)+1, tobe_updated);
					//System.out.println(textArea.getText());
					//System.out.println(sb.toString());
					//System.out.println(tobe_updated);
					try {
						//FileWriter rg_fw_mod = new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile, false);
						//BufferedWriter rg_bw_mod = new BufferedWriter(rg_fw_mod.sb.toString());
						PrintWriter rg_pr_mod = new PrintWriter(new BufferedWriter(new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile)))	;
						rg_pr_mod.write(sb.toString());
						rg_pr_mod.close();
						k=0;
						
						rg_sb = "";
						sb = null;
						label.setText("Record Modified");
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
							label.setText("File I/O exception");
							else
								label.setText("File I/O exception");
						
					}catch (NullPointerException npe) {
						if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
							label.setText("Select the file");
							else
								label.setText("File not found or file not selected");
					}
					
					}
					else
						label.setText("Record not found");
				
				
				
				
				
			}
		});
		btnModify.setBounds(238, 131, 89, 23);
		contentPane.add(btnModify);
		
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(null);
				textField.setText(null);
				textField_1.setText(null);
				label.setText("");
				rg_sb = "";
			}
		});
		btnClearAll.setBounds(126, 91, 89, 23);
		contentPane.add(btnClearAll);
		
		
		
	}
}
