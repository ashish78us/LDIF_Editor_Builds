package com.swift.alliance;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Regular_Update extends JFrame {

	/**
	 * 
	 */
	//testing staging and commit principle
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_2;
	final int MAX_LENGTH = 99;
	//private static String rg_fileContents;
	private static String product_name = "AWP";
	private static String search_builder = "SupportedSoftware p=\"AWP\"";
	private static String software_type = "Software";
	private static String rg_prod_version= null;
	private static String rg_sb ="";
	private static int k=0,m_stop_desc=0,l_start_desc=0, a_from_update=0, b_to_update=0, c_mand=0, d_sof_sec_start=0,d_sof_sec_stop=0;
	private static int update_start=0, update_end=0;
	private static StringBuilder sb ;
	private static String soft_sec="software";

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Regular_Update frame = new Regular_Update();
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
	public Regular_Update() {
		super("Regular Update");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 100, 346, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(0, 311, 340, 19);
		contentPane.add(label);
		
		
		JLabel lblTillDate = new JLabel("Till Date");
		lblTillDate.setBounds(10, 81, 46, 14);
		contentPane.add(lblTillDate);
		
		JLabel lblFromDate = new JLabel("From Date");
		lblFromDate.setBounds(168, 81, 79, 14);
		contentPane.add(lblFromDate);
		
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
			public void keyPressed(KeyEvent arg0) {
				String till_date = textField.getText(); 
				int length = till_date.length();
				//char c = arg0.getKeyChar();
				if (arg0.getKeyChar() >= '0' && arg0.getKeyChar()<='9')
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
					if (arg0.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || arg0.getExtendedKeyCode() == KeyEvent.VK_DELETE )
					{
						textField.setEditable(true);
					}
					else
						textField.setEditable(false);
					//}
				}
			}
		});
		
		textField.setBounds(64, 78, 94, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setToolTipText("Format: YYYYMMDD");
		
		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_1.setEditable(true);
				label.setText("");
			}
		});
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
		textField_1.setColumns(10);
		textField_1.setBounds(229, 78, 94, 20);
		textField_1.setToolTipText("Format: YYYYMMDD");
		contentPane.add(textField_1);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 106, 94, 14);
		contentPane.add(lblDescription);
		
		JTextArea textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setEditable(true);
				label.setText("");
			}
		});
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				String description = textArea.getText(); 
				int length = description.length();
				label.setText("Description length="+ length);
				
				
				if (length >= 100)
					{
						textArea.setEditable(false);
						label.setText("Maximum character length (100) reached.");	
						
					}
					else 
						{
							textArea.setEditable(true);
							label.setText("Description length="+ length);
						}
				if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode() == KeyEvent.VK_DELETE )
				{
					label.setText("Description length="+ length);
					textArea.setEditable(true);
					
				}
				
					
				
			}
		});
		
		textArea.setRows(2);
		textArea.setColumns(50);
		textArea.setWrapStyleWord(true);
		textArea.setName("");
		textArea.setLocale(Locale.UK);
		textArea.setLineWrap(true);
		textArea.setTabSize(1);
		textArea.setBounds(10, 131, 313, 48);
		contentPane.add(textArea);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label.setText("");
			}
		});
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString() == "Alliance Web Platform")
				{product_name = "AWP"; 
				search_builder = "SupportedSoftware p=\"AWP\"";
				//System.out.println("In AWP");
				}
				else if (comboBox.getSelectedItem().toString() == "Alliance Access")
					{product_name = "SAA";
					search_builder = "SupportedSoftware p=\"SAA\"";
					//System.out.println("In SAA");
					}
					
					else if (comboBox.getSelectedItem().toString() == "Alliance Gateway")
						{product_name = "SAG";
						search_builder = "SupportedSoftware p=\"SAG\"";
						//System.out.println("In SAG");
						}
						else if (comboBox.getSelectedItem().toString() == "SNL")
							{product_name = "SNL";
							search_builder = "SupportedSoftware p=\"SNL\"";
							//System.out.println("In SNL");
							}
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Alliance Web Platform", "Alliance Access", "Alliance Gateway", "SNL"}));
		comboBox.setBounds(144, 11, 179, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Select Product");
		lblNewLabel.setBounds(10, 14, 94, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMandatory = new JLabel("Mandatory");
		lblMandatory.setBounds(10, 190, 66, 14);
		contentPane.add(lblMandatory);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Yes");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				label.setText("");
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(101, 186, 57, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label.setText("");
			}
		});
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(170, 186, 109, 23);
		contentPane.add(rdbtnNo);
		
		JLabel lblVersion = new JLabel("Version");
		lblVersion.setBounds(10, 39, 46, 31);
		contentPane.add(lblVersion);
		
		textField_2 = new JTextField(null);
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
				
					if (((arg0.getKeyChar() >= '0' && arg0.getKeyChar()<='9') ||  arg0.getKeyChar()=='.') && length < 6)
					{
							textField_2.setEditable(true);
							label.setText("");
					}
					         
					
					else 
						{
							textField_2.setEditable(false);
							label.setText("Character not allowed OR length(6) exceeded.");
							
						}
					
					if (arg0.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || arg0.getExtendedKeyCode() == KeyEvent.VK_DELETE )
						{
					
							textField_2.setEditable(true);
							label.setText("");
					
						}
					
			}
			
		});
		
		
		textField_2.setBounds(64, 42, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSoftwareType = new JLabel("Software Type");
		lblSoftwareType.setBounds(10, 215, 94, 14);
		contentPane.add(lblSoftwareType);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label.setText("");
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"software", "security"}));
		comboBox_1.setBounds(101, 216, 89, 20);
		contentPane.add(comboBox_1);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				software_type = comboBox_1.getSelectedItem().toString();
				}			
		});
		
		
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
				int j= sb.indexOf(search_builder);
				if (j==-1)	{
					//JOptionPane.showMessageDialog(null, "Select correct product for this LDIF file");
					label.setText("File does not match with selected product.");
					return;
				}
				//else if (sb.toString() != "") {
				if (textField_2.getText().length() != 0)
						{
						k = sb.indexOf("n=\""+rg_prod_version);//check later the boundary. Should not return -1
						//System.out.println("Version="+rg_prod_version+".");
						
						}
				else
						{
							label.setText("Version field is mandatory.");
							return;
						}
				if (k== -1)
						{
							label.setText("Version not found.");
							return;
						}
								
				update_start = sb.lastIndexOf("<", k);				
				update_end= sb.indexOf(">",k);				
				m_stop_desc = sb.lastIndexOf("\" m=",k);
				l_start_desc = sb.lastIndexOf("d=",k);
				a_from_update=sb.lastIndexOf("f=\"", k);
				b_to_update=sb.lastIndexOf("t=\"", k);					
				c_mand = sb.lastIndexOf("m=\"", k);
				d_sof_sec_start = sb.indexOf("k=\"", k);
				d_sof_sec_stop = sb.indexOf("/>", k);
				
				if (l_start_desc >= 0 && m_stop_desc >=0)
				{textArea.setText(sb.substring(l_start_desc+3,m_stop_desc));} ///setting description
				else {
					JOptionPane.showMessageDialog(null, "Search encountered issue. Info: This version number does not exist in file.");
					return;
				}
				
				textField_1.setText(sb.substring(a_from_update+3,a_from_update+11)); /// setting from date 	
				textField.setText(sb.substring(b_to_update+3,b_to_update+11)); /// setting to date
				
				if (Integer.parseInt(sb.substring(c_mand+3, c_mand+4))==1){					
					rdbtnNewRadioButton.setSelected(true);
										
				}
				else {
				rdbtnNo.setSelected(true);
				
				}
				
				if (sb.substring(d_sof_sec_start+3, d_sof_sec_stop-1).contains(soft_sec)){
					comboBox_1.setSelectedItem("software");
					}
				else {
					comboBox_1.setSelectedItem("security");
					
				}
				//}//else if
				k=0;
				update_start=0;
				update_end=0;
				rg_sb = "";
				label.setText("");
				//System.out.println(sb.toString());
				
			}
		});
		btnSearch.setBounds(168, 42, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label.setText("");
				if (sb == null) {
					label.setText("First do search operation on the version.");
					return;
				}
				int j= sb.indexOf(search_builder);
				if (j==-1)	{
					//JOptionPane.showMessageDialog(null, "Select correct product for this LDIF file");
					label.setText("File does not match with selected product.");
					return;
					
				}
				
				rg_prod_version = textField_2.getText();
				if (textField_2.getText()!= "")
					{
					k = sb.indexOf("n=\""+rg_prod_version);//check later the boundary. Should not return -1
					
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
			
				
				k = sb.indexOf("n=\""+rg_prod_version);//check later the boundary. Should not return -1
				//m = sb.indexOf("/>",k); //check later the boundary. Should not return -1
				if (k != -1) {
				update_start = sb.lastIndexOf("<", k);
				//System.out.println(sb.substring(update_start,update_start+1));
				update_end= sb.indexOf(">",k);
				// Add code to check the start and end int for -1 if the version to be modified is not found
				sb.delete(update_start, update_end+1);
				
				try {
					//FileWriter rg_fw_mod = new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile, false);
					//BufferedWriter rg_bw_mod = new BufferedWriter(rg_fw_mod.sb.toString());
					PrintWriter rg_pr_mod = new PrintWriter(new BufferedWriter(new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile)))	;
					rg_pr_mod.write(sb.toString());
					rg_pr_mod.close();
					k=0;
					update_start=0;
					update_end=0;
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
			}
		});
		btnDelete.setBounds(116, 267, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///use file writer to modify the file
				//rg_sb = sb.toString();
				if (sb == null) {
					label.setText("First do search operation on the version.");
					return;
				}
				label.setText("");
				int j= sb.indexOf(search_builder);
				if (j==-1)	{
					//JOptionPane.showMessageDialog(null, "Select correct product for this LDIF file");
					label.setText("File does not match with selected product.");
					return;
					
				}
				label.setText("");
				rg_prod_version = textField_2.getText();
				if (textField_2.getText()!= "")
					{
					k = sb.indexOf("n=\""+rg_prod_version);//check later the boundary. Should not return -1
					
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
				
				
				
				k = sb.indexOf("n=\""+rg_prod_version);//check later the boundary. Should not return -1
				//m = sb.indexOf("/>",k); //check later the boundary. Should not return -1
				if (k != -1) {
				update_start = sb.lastIndexOf("<", k);
				//System.out.println(sb.substring(update_start,update_start+1));
				update_end= sb.indexOf(">",k);
				int int_mand=2;
				String str_softsec = "";
				if (comboBox_1.getSelectedIndex() == 0)
				{
					str_softsec = "software";
				}
				else
				{
					str_softsec = "security";
				}
				if (rdbtnNewRadioButton.isSelected())
				{
					int_mand = 1;
				}
				else if (rdbtnNo.isSelected())
				{
					int_mand = 0;
				}
				
				//check all elements are present
					//version check
				if (textField_2.getText().length() ==0 || textField.getText().length() ==0 || textField_1.getText().length() ==0 || textArea.getText().length() ==0 || int_mand == 2 )
				{
					label.setText("All parameters are mandatory.");
					return;
				}
				String tobe_updated = "<Update t=\""+ textField.getText() +"\" f=\"" + textField_1.getText() + "\" d=\"" + textArea.getText() + "\" m=\"" +  int_mand + "\" n=\"" + textField_2.getText() + "\" k=\"" + str_softsec + "\"/" ;                  
				// Add code to check the start and end int for -1 if the version to be modified is not found and correct file to update
				sb.replace(update_start,update_end, tobe_updated);
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
					update_start=0;
					update_end=0;
					rg_sb = "";
					sb = null;
					int_mand=2;
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
		btnModify.setBounds(229, 267, 89, 23);
		contentPane.add(btnModify);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(10, 316, 46, 14);
		contentPane.add(label_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label.setText("");
				String Product_version = JOptionPane.showInputDialog("Input the version after which it will be added in LDIF file");
				if (Product_version == "")
				{
					label.setText("Input asked can't be empty.");
					return;
				}
				
				 //if (sb.toString() != "") {
				
				
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
				
				int j= sb.indexOf(search_builder);
				//System.out.println(j);
				if (j==-1)	{
					JOptionPane.showMessageDialog(null, "Select correct product for this LDIF file");
					rg_sb = "";
					return;
				}
				
				k = sb.indexOf("n=\""+Product_version);//check later the boundary. Should not return -1
				//m = sb.indexOf("/>",k); //check later the boundary. Should not return -1
				if (k== -1) {
					JOptionPane.showMessageDialog(null, "Version not found. Input the correct version\n after which the update record to be added.");
					rg_sb = "";
					return;
					//k = sb.indexOf("</Updates>");
				}
				 int p = sb.indexOf("n=\""+textField_2.getText());
				 if (p != -1) {
						JOptionPane.showMessageDialog(null, "Version you want to add already exist in the LDIF file");
						rg_sb = "";
						return;
						//k = sb.indexOf("</Updates>");
					}
					k = sb.indexOf("/>",k);
				//update_start = sb.lastIndexOf("<", k);
				//System.out.println(sb.substring(update_start,update_start+1));
				//update_end= sb.indexOf(">",k);
				int int_mand = 2;
				String str_softsec = "";
				if (comboBox_1.getSelectedIndex() == 0)
				{
					str_softsec = "software";
				}
				else
				{
					str_softsec = "security";
				}
				if (rdbtnNewRadioButton.isSelected())
				{
					int_mand = 1;
				}
				else if (rdbtnNo.isSelected())
				{
					int_mand = 0;
				}
				
				if (textField_2.getText().length() ==0 || textField.getText().length() ==0 || textField_1.getText().length() ==0 || textArea.getText().length() ==0 || int_mand == 2 )
				{
					label.setText("All parameters are mandatory.");
					return;
				}
				
				String tobe_added = "<Update t=\""+ textField.getText() +"\" f=\"" + textField_1.getText() + "\" d=\"" + textArea.getText() + "\" m=\"" +  int_mand + "\" n=\"" + textField_2.getText() + "\" k=\"" + str_softsec + "\"/>" ;                  
				sb.insert(update_start+k+2, tobe_added);
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
					p=0;
					update_start=0;
					update_end=0;
					rg_sb = "";
					int_mand = 2;
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
			//}  IF condition
			}	
			
		});
		btnAdd.setBounds(10, 267, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(null);
				textField.setText(null);
				textField_1.setText(null);
				textArea.setText(null);
				rdbtnNewRadioButton.setSelected(false);
				rdbtnNo.setSelected(false);
				comboBox_1.setSelectedItem("software");
				label.setText("");
				rg_sb = "";
			}
		});
		btnClearAll.setBounds(224, 216, 89, 23);
		contentPane.add(btnClearAll);
		
		
	}
}
