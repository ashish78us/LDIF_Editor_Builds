package com.swift.alliance;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.util.Date;
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
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class Regular_Update extends JFrame {

	private static final long serialVersionUID = 1L;
//GUI components data members	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_2;	
	private JLabel label;
	private static JLabel lblVersion;
	private JTextArea textArea;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNo;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	
//Data members used in code	
	private static String product_name = "";
	//private static String search_builder = "SupportedSoftware p=\"AWP\"";
	private static String search_builder = "";
	private static String software_type = "Software";
	private static String rg_prod_version = "";
	private static String rg_sb = "";
	private static int k = 0, m_stop_desc = 0, l_start_desc = 0, a_from_update = 0, b_to_update = 0 ;
	private static int c_mand = 0, d_sof_sec_start = 0, d_sof_sec_stop = 0, update_start = 0, update_end = 0;
	private static StringBuilder sb;
	private static String soft_sec = "software";
	private static int curr_index = 0;
	private static String SelVerN;
	private static DefaultComboBoxModel<String> all_vers1;
	private static Highlighter high;
	private static String Date_reg;
	private static LDIF4SoftwareUpdateCheck ldsuc1;
	final int MAX_LENGTH = 99;	
	
//Constructor
	public Regular_Update(DefaultComboBoxModel<String> all_vers, JTextArea jta, LDIF4SoftwareUpdateCheck ldsuc, int ldsuc_curr_index) {
		super("Regular Update");
//Initializing data members
		all_vers1 = all_vers;
		ldsuc1=ldsuc;				
		high = jta.getHighlighter();
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date_reg = dateFormat.format(today);		
//JFrame settings				
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 100, 346, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//JLabel for selecting product		
		JLabel lblNewLabel = new JLabel("Select Product");
		lblNewLabel.setBounds(10, 14, 94, 14);
		contentPane.add(lblNewLabel);
//JComboBox for product list		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Alliance Web Platform",
				"Alliance Access", "Alliance Entry", "Alliance Gateway", "SNL" }));
		comboBox.setBounds(144, 11, 179, 20);
		contentPane.add(comboBox);
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				product_selection();
			}
		});
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				product_selection();
			}
		});
//JLabel for version drop down
		JLabel lblVersion_1 = new JLabel("Version Selection");
		lblVersion_1.setBounds(10, 45, 124, 14);
		contentPane.add(lblVersion_1);
		
//JComboBox for version drop down
		comboBox_2 = new JComboBox<String>(all_vers);
		comboBox_2.setBounds(144, 42, 86, 20);
		contentPane.add(comboBox_2);
		comboBox_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LdifSearch((String) all_vers1.getSelectedItem());
				if (ProductFileMatch(sb) !=0) {return;}
				rg_sb = "";
				sb = null;
				high_function((String) all_vers1.getSelectedItem(), "mouseClicked");
				rg_sb = "";
				sb = null;
				curr_index = all_vers1.getIndexOf(all_vers1.getSelectedItem());
			}
		});
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent vsel) {
				LdifSearch((String) all_vers1.getSelectedItem());
				if (ProductFileMatch(sb) !=0) {return;}
				rg_sb = "";
				sb = null;
				high_function((String) all_vers1.getSelectedItem(), "itemStateChanged");
				rg_sb = "";
				sb = null;
				curr_index = all_vers1.getIndexOf(all_vers1.getSelectedItem());
				
			}
		});		
//JLabel for version
		lblVersion = new JLabel("Version");
		lblVersion.setBounds(10, 73, 46, 31);
		contentPane.add(lblVersion);

// JTextField for version number
		textField_2 = new JTextField(null);
		textField_2.setBounds(64, 76, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
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

				if (((arg0.getKeyChar() >= '0' && arg0.getKeyChar() <= '9') || arg0.getKeyChar() == '.')
						&& length < 6) {
					textField_2.setEditable(true);
					label.setText("");
				}
				else {
					textField_2.setEditable(false);
					label.setText("Character not allowed OR length(6) exceeded.");

				}
				if (arg0.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
						|| arg0.getExtendedKeyCode() == KeyEvent.VK_DELETE) {

					textField_2.setEditable(true);
					label.setText("");
				}
			}
		});
//JButton for Search
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(168, 76, 89, 23);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchButtonF();
			}
		});
//JLabel for till date
		JLabel lblTillDate = new JLabel("Till Date");
		lblTillDate.setBounds(10, 115, 46, 14);
		contentPane.add(lblTillDate);
//JText for till date
		textField = new JTextField();
		textField.setBounds(64, 112, 94, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setToolTipText("Format: YYYYMMDD");
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
				text_field_const(arg0,textField);
			}
		});
//JLabel for from date
		JLabel lblFromDate = new JLabel("From Date");
		lblFromDate.setBounds(168, 115, 79, 14);
		contentPane.add(lblFromDate);
//JText for from date
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(229, 112, 94, 20);
		textField_1.setToolTipText("Format: YYYYMMDD");
		contentPane.add(textField_1);
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
				text_field_const(e,textField_1);
			}
		});
//JLabel for description
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 143, 94, 14);
		contentPane.add(lblDescription);
		
//JText for description area
		textArea = new JTextArea();
		textArea.setRows(2);
		textArea.setColumns(50);
		textArea.setWrapStyleWord(true);
		textArea.setName("");
		textArea.setLocale(Locale.UK);
		textArea.setLineWrap(true);
		textArea.setTabSize(1);
		textArea.setBounds(10, 168, 313, 48);
		contentPane.add(textArea);
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
				label.setText("Description length=" + length);
				if (length >= 100) {
					textArea.setEditable(false);
					label.setText("Maximum character length (100) reached.");
				} else {
					textArea.setEditable(true);
					label.setText("Description length=" + length);
				}
				if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
					label.setText("Description length=" + length);
					textArea.setEditable(true);
				}
			}
		});

//JLabel for Mandatory
		JLabel lblMandatory = new JLabel("Mandatory");
		lblMandatory.setBounds(10, 232, 66, 14);
		contentPane.add(lblMandatory);
//Radio Button for mandatory "Yes"
		rdbtnNewRadioButton = new JRadioButton("Yes");
		rdbtnNewRadioButton.setBounds(101, 223, 57, 23);
		contentPane.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				label.setText("");
			}
		});
//Radio Button for mandatory "No"	
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(168, 227, 46, 14);
		contentPane.add(rdbtnNo);
		rdbtnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label.setText("");
			}
		});
//Radio button group
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNo);
//JLabel for software type
		JLabel lblSoftwareType = new JLabel("Software Type");
		lblSoftwareType.setBounds(10, 257, 94, 14);
		contentPane.add(lblSoftwareType);
//JComboBox for software type
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] { "software", "security" }));
		comboBox_1.setBounds(101, 254, 89, 20);
		contentPane.add(comboBox_1);
		comboBox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label.setText("");
			}
		});		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				software_type = comboBox_1.getSelectedItem().toString();
			}
		});
//JButton for CLEAR
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.setBounds(229, 253, 89, 23);
		contentPane.add(btnClearAll);
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
				sb = null;
			}
		});
//JButton for PREVIOUS
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(61, 291, 89, 23);
		contentPane.add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previous_buttonF();
			}
		});
//JButton for NEXT
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(184, 291, 89, 23);
		contentPane.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			next_buttonF();
			}
		});
//JBUtton for Add
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(15, 325, 89, 23);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			add_buttonF();				
			}
		});
//JButton for Delete
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(125, 325, 89, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete_buttonF();
			}
		});
//JButton for Modify
		JButton btnModify = new JButton("Modify");
		btnModify.setBounds(229, 325, 89, 23);
		contentPane.add(btnModify);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modify_buttonF();
			}

		});
//JLabel for feedback		
		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(0, 359, 340, 22);
		contentPane.add(label);

	}// End of Constructor

	public void LdifSearch(String Version) {
//Version is input to this function by the callee
		if (Version == "") {label.setText("Version field cannot be empty");return; }		
		label.setText("");
		textField_2.setBackground(Color.white);
		textField_2.setText(Version);
		//System.out.println("VersionInsideSearch="+ Version);			
//Get file latest content		
		sb = OpenFileAsStringB();
//Check if file was empty		
		if (sb == null) 
		{ 
			label.setText("Error Encountered in reading file.");
			return; 
		}
//Product File vs Selected product match, version parameters check
		if (ProductFileMatch(sb) !=0) {return;}
		if (check_param_version(sb) != 0) {return;}
//Core of Search function
		update_start = sb.lastIndexOf("<", k);
		update_end = sb.indexOf(">", k);
		m_stop_desc = sb.lastIndexOf("\" m=", k);
		l_start_desc = sb.lastIndexOf("d=", k);
		a_from_update = sb.lastIndexOf("f=\"", k);
		b_to_update = sb.lastIndexOf("t=\"", k);
		c_mand = sb.lastIndexOf("m=\"", k);
		d_sof_sec_start = sb.indexOf("k=\"", k);
		d_sof_sec_stop = sb.indexOf("/>", k);		
	// Setting Version description -III of V
		if (l_start_desc >= 0 && m_stop_desc >= 0) {
			textArea.setText(sb.substring(l_start_desc + 3, m_stop_desc));
		}
		else {
			JOptionPane.showMessageDialog(null,"Search encountered issue. Info: This version number does not exist in file.");
			return;
		}
     // Setting from date - II of V
		textField_1.setText(sb.substring(a_from_update + 3, a_from_update + 11)); 
	 // Setting To date - I of V
		textField.setText(sb.substring(b_to_update + 3, b_to_update + 11)); 
     // Setting Mandatory yes or no - IV of V
		if (Integer.parseInt(sb.substring(c_mand + 3, c_mand + 4)) == 1) {
			rdbtnNewRadioButton.setSelected(true);
		} else {
			rdbtnNo.setSelected(true);
		}
	// Setting whether security or software - V of V
		if (sb.substring(d_sof_sec_start + 3, d_sof_sec_stop - 1).contains(soft_sec)) {
			comboBox_1.setSelectedItem("software");
		} else {
			comboBox_1.setSelectedItem("security");
		}
	// Resetting variables		
		  k = 0; update_start = 0; update_end = 0; 
		  label.setText("");
	}// end of LdifSearch()
	public void high_function(String prod_version_high, String operation) {
		high.removeAllHighlights();
		sb = OpenFileAsStringB();
		k = sb.indexOf("n=\"" + prod_version_high);// check later the boundary. Should not return -1
		d_sof_sec_stop = sb.indexOf("<",k);
		b_to_update = sb.lastIndexOf("/>", k);
		//System.out.println("Version="+prod_version_high+"  "+ "start=" + b_to_update + "  " + "Last=" + d_sof_sec_stop +"  "+ "IndexNo="+all_vers1.getIndexOf(prod_version_high));
		//sb= null;
		k=0;
	
		
		try {
			HighlightPainter color_high = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
			HighlightPainter red = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
			
			if (textField.getText() != "") {

			if (Integer.parseInt(textField.getText()) < Integer.parseInt(Date_reg)) {
				high.addHighlight(b_to_update-1+all_vers1.getIndexOf(prod_version_high)+11, d_sof_sec_stop+2+all_vers1.getIndexOf(prod_version_high)+7, red);
				label.setText("This version is out of support.");

			} else {
				high.addHighlight(b_to_update-1+all_vers1.getIndexOf(prod_version_high)+11, d_sof_sec_stop+2+all_vers1.getIndexOf(prod_version_high)+7, color_high);
			}
			}
		} catch (BadLocationException e3) {
			// TODO Auto-generated catch block
			/*
			 * System.out.println("a_from_update=" + a_from_update);
			 * System.out.println("b_to_update=" + b_to_update);
			 */
			e3.printStackTrace();
		}
	} // End of high_function()	
	public void text_field_const(KeyEvent arg0, JTextField textField) {
		String till_date = textField.getText();
		int length = till_date.length();
		// char c = arg0.getKeyChar();
		if (arg0.getKeyChar() >= '0' && arg0.getKeyChar() <= '9') {
			if (length < 8) {
				textField.setEditable(true);
			} else
				textField.setEditable(false);
		} else {
			if (arg0.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
					|| arg0.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
				textField.setEditable(true);
			} else
				textField.setEditable(false);
			// }
		}
	}// End of text_field_const	
	public void product_selection() {
		if (comboBox.getSelectedItem().toString() == "Alliance Web Platform") {
			product_name = "AWP";
			search_builder = "SupportedSoftware p=\"AWP\"";
			// System.out.println("In AWP");
		} else if (comboBox.getSelectedItem().toString() == "Alliance Access") {
			product_name = "SAA";
			search_builder = "SupportedSoftware p=\"SAA\"";
			// System.out.println("In SAA");
		} else if (comboBox.getSelectedItem().toString() == "Alliance Entry") {
			product_name = "SAE";
			search_builder = "SupportedSoftware p=\"SAE\"";
			// System.out.println("In SAA");
		}
		//
		else if (comboBox.getSelectedItem().toString() == "Alliance Gateway") {
			product_name = "SAG";
			search_builder = "SupportedSoftware p=\"SAG\"";
			// System.out.println("In SAG");
		} else if (comboBox.getSelectedItem().toString() == "SNL") {
			product_name = "SNL";
			search_builder = "SupportedSoftware p=\"SNL\"";
			// System.out.println("In SNL");
		}
	} // End of product_selection()
	public void searchButtonF() {
		rg_sb = "";
		sb = null;
		LdifSearch(textField_2.getText());
		rg_sb = "";
		sb = null;
		high_function(textField_2.getText(), "searchButton");
		rg_sb = "";
		sb = null;
		curr_index = all_vers1.getIndexOf(textField_2.getText());
	} // End of searchButtonF()
	public void previous_buttonF() {
		ldsuc1.lblNewLabel.setText("");
		if ((curr_index) == 0) {
			label.setText("This is the FIRST version in the file.");
			return;
		}
		rg_prod_version = all_vers1.getElementAt(curr_index - 1);				
		label.setText("");
		sb = OpenFileAsStringB();
		int j = sb.indexOf(search_builder); // checking the product in the file and selected product
		if (j == -1) {
			label.setText("File does not match with selected product.");
			rg_sb = "";
			return;
		}
		if (textField_2.getText().length() != 0 && textField_2.getText().length() >= 6) {
			k = sb.indexOf("n=\"" + rg_prod_version);// check later the boundary. Should not return -1
			} 
		else {
			label.setText("Version field is mandatory and of fixed length(6)");
			rg_sb = "";
			return;
		}
		if (k == -1) {
			label.setText("Version not found.");
			rg_sb = "";
			return;
		}
		update_start = sb.lastIndexOf("<", k);
		update_end = sb.indexOf(">", k);
		m_stop_desc = sb.lastIndexOf("\" m=", k);
		l_start_desc = sb.lastIndexOf("d=", k);
		a_from_update = sb.lastIndexOf("f=\"", k);
		b_to_update = sb.lastIndexOf("t=\"", k);
		c_mand = sb.lastIndexOf("m=\"", k);
		d_sof_sec_start = sb.indexOf("k=\"", k);
		d_sof_sec_stop = sb.indexOf("/>", k);
		if (l_start_desc >= 0 && m_stop_desc >= 0) {
			textArea.setText(sb.substring(l_start_desc + 3, m_stop_desc));
		} /// setting description
		else {
			JOptionPane.showMessageDialog(null,
					"Search encountered issue. Info: This version number does not exist in file.");
			return;
		}
		textField_2.setText(rg_prod_version);
		textField_1.setText(sb.substring(a_from_update + 3, a_from_update + 11)); /// setting from date
		textField.setText(sb.substring(b_to_update + 3, b_to_update + 11)); /// setting to date
		if (Integer.parseInt(sb.substring(c_mand + 3, c_mand + 4)) == 1) {
			rdbtnNewRadioButton.setSelected(true);
		} else {
			rdbtnNo.setSelected(true);
		}
		if (sb.substring(d_sof_sec_start + 3, d_sof_sec_stop - 1).contains(soft_sec)) {
			comboBox_1.setSelectedItem("software");
		} else {
			comboBox_1.setSelectedItem("security");
		}				
		k = 0;
		update_start = 0;
		update_end = 0;
		rg_sb = "";
		label.setText("");
		curr_index = curr_index - 1;
		high_function(rg_prod_version, "previous");
		rg_sb = "";
		sb=null;
		comboBox_2.setSelectedIndex(curr_index);
	} // End of previous_buttonF()
	public void next_buttonF() {
		ldsuc1.lblNewLabel.setText("");
		int array_count = all_vers1.getSize();
		if ((curr_index) == array_count - 1) {
			label.setText("This is the LAST version in the file.");
			return;
		}
		rg_prod_version = all_vers1.getElementAt(curr_index + 1);
		label.setText("");
		sb = OpenFileAsStringB();
		int j = sb.indexOf(search_builder); // checking the product in the file and selected product
		if (j == -1) {
			// JOptionPane.showMessageDialog(null, "Select correct product for this LDIF
			// file");
			label.setText("File does not match with selected product.");
			rg_sb = "";
			return;
		}
		// else if (sb.toString() != "") {
		if (textField_2.getText().length() != 0 && textField_2.getText().length() >= 6) {
			k = sb.indexOf("n=\"" + rg_prod_version);// check later the boundary. Should not return -1
			// System.out.println("Version="+rg_prod_version+".");

		} else {
			label.setText("Version field is mandatory and of fixed length(6)");
			rg_sb = "";
			return;
		}
		if (k == -1) {
			label.setText("Version not found.");
			rg_sb = "";
			return;
		}

		update_start = sb.lastIndexOf("<", k);
		update_end = sb.indexOf(">", k);
		m_stop_desc = sb.lastIndexOf("\" m=", k);
		l_start_desc = sb.lastIndexOf("d=", k);
		a_from_update = sb.lastIndexOf("f=\"", k);
		b_to_update = sb.lastIndexOf("t=\"", k);
		c_mand = sb.lastIndexOf("m=\"", k);
		d_sof_sec_start = sb.indexOf("k=\"", k);
		d_sof_sec_stop = sb.indexOf("/>", k);

		if (l_start_desc >= 0 && m_stop_desc >= 0) {
			textArea.setText(sb.substring(l_start_desc + 3, m_stop_desc));
		} /// setting description
		else {
			JOptionPane.showMessageDialog(null,
					"Search encountered issue. Info: This version number does not exist in file.");
			return;
		}
		textField_2.setText(rg_prod_version);
		textField_1.setText(sb.substring(a_from_update + 3, a_from_update + 11)); /// setting from date
		textField.setText(sb.substring(b_to_update + 3, b_to_update + 11)); /// setting to date

		if (Integer.parseInt(sb.substring(c_mand + 3, c_mand + 4)) == 1) {
			rdbtnNewRadioButton.setSelected(true);

		} else {
			rdbtnNo.setSelected(true);

		}

		if (sb.substring(d_sof_sec_start + 3, d_sof_sec_stop - 1).contains(soft_sec)) {
			comboBox_1.setSelectedItem("software");
		} else {
			comboBox_1.setSelectedItem("security");

		}
		// }//else if
		k = 0;
		update_start = 0;
		update_end = 0;
		rg_sb = "";
		label.setText("");
		// System.out.println(sb.toString());

		curr_index = curr_index + 1;
		high_function(rg_prod_version, "next");
		rg_sb = "";
		sb = null;
		comboBox_2.setSelectedIndex(curr_index);
		
		//all_vers1.getIndexOf(all_vers1.getSelectedItem()
	} // End of next_buttonF()
	public void add_buttonF() {
//Reset common variables
		label.setText("");
		rg_sb = "";
		sb = null;
		String Product_version_add = textField_2.getText();
		String Product_version = "";
//Get file latest content		
		sb = OpenFileAsStringB();
//Check if file was empty		
		  if (sb == null) 
		  { 
		  label.setText("Error Encountered in reading file.");
		  return; 
		  }
//Product File vs Selected product match, version parameters check
		  if (ProductFileMatch(sb) !=0) {return;}
		 // if (check_param_version(sb) != 0) {return;}
// Check if version already exist or not
		k = sb.indexOf("n=\"" + Product_version_add);
		if (k != -1) {
			JOptionPane.showMessageDialog(null, "Version already exist.");
			// Working area
			LDIF4SoftwareUpdateCheck.obj_ru.textField_2.setText(Product_version_add);	
			LDIF4SoftwareUpdateCheck.obj_ru.comboBox_2.setSelectedIndex(all_vers1.getIndexOf(Product_version_add));
			curr_index = all_vers1.getIndexOf(Product_version_add);
			LDIF4SoftwareUpdateCheck.obj_ru.LdifSearch(Product_version_add);   // to retain all the values in the GUI
			rg_sb = "";
			sb = null;
			LDIF4SoftwareUpdateCheck.obj_ru.high_function(Product_version_add, "add_v_exist");
			rg_sb = "";
			sb = null;
			return;
			
		} 
// Core of Add function	-  adding record	
		//value of k=-1 i.e. record not found and can be added now.
		else  {  
			int row_num_where_tobAdded = 0;
			int start_new_record_high = 0;
			int last_highlight = 0;
		//Looping through all the records to find the place where this record can be added
			for (int r=0;r<all_vers1.getSize();r++) {
				String version_to_compare ="";
				String version_to_add = "";
				version_to_compare = version_to_compare.concat(all_vers1.getElementAt(r).substring(0,1));
				version_to_compare = version_to_compare.concat(all_vers1.getElementAt(r).substring(2,3));
				version_to_compare = version_to_compare.concat(all_vers1.getElementAt(r).substring(4,5));
				version_to_compare = version_to_compare.concat(all_vers1.getElementAt(r).substring(5,6));
				//System.out.println(version_to_compare);
				version_to_add = version_to_add.concat(Product_version_add.substring(0,1));
				version_to_add = version_to_add.concat(Product_version_add.substring(2,3));
				version_to_add = version_to_add.concat(Product_version_add.substring(4,5));
				version_to_add = version_to_add.concat(Product_version_add.substring(5,6));
				//System.out.println(version_to_add);
				
			// IF condition if the version (at index 'r') that exist is greater than then version to be added
				if (Integer.parseInt(version_to_compare) > Integer.parseInt(version_to_add)   )
				{
				// This is the index where new record needs to be added.
					row_num_where_tobAdded = r;
				//Getting the product version one before
					Product_version = all_vers1.getElementAt(r-1);
					addVersion4AddF(sb,Product_version, Product_version_add, r);
					return;
				}
			// version will be added at the end
				else if (r>=(all_vers1.getSize()-1)) {  
					Product_version = all_vers1.getElementAt(all_vers1.getSize()-1);
					//addVersion4AddF(sb,Product_version, Product_version_add, r);					
					k = sb.indexOf("n=\""+Product_version);
				//from where the addition will take place
					k = sb.indexOf("/>",k);//from where the addition will take place
					
				//Resetting some variables	
					int int_mand = 2;
					String str_softsec = "";
			   //Fetching the value of new record 
					if (comboBox_1.getSelectedIndex() == 0)
					{str_softsec = "software";}
					else
					{str_softsec = "security";}
					if (rdbtnNewRadioButton.isSelected())
					{int_mand = 1;}
					else if (rdbtnNo.isSelected())
					{int_mand = 0;}	
				//Checking all parameters
					if (all_param_check() != 0) {return;}
				//Writing record to the file	
					String tobe_added = "<Update t=\""+ textField.getText() +"\" f=\"" + 
							textField_1.getText() + "\" d=\"" + textArea.getText() + "\" m=\"" + 
							int_mand + "\" n=\"" + textField_2.getText() + "\" k=\"" + str_softsec + "\"/>" ;                  
					//sb.insert(update_start+k+2, tobe_added);	
					sb.insert(k+2, tobe_added);
					file_write_fromSB(sb);
				//Resetting variables	
					int_mand = 2;					
					rg_sb = "";
					sb = null;	
				//Adding element from version drop down comboBox_2
					all_vers1.insertElementAt(textField_2.getText(),r+1);
				// Refresh file content
				    ldsuc1.ShowFileContent();
				//Setting curr_index for next version to highlight which will be same as the one deleted.
					curr_index = r +1; 
					LDIF4SoftwareUpdateCheck.ldsuc_curr_index = r + 1;				
				//set both version comboBox_2 and version filled same value for newly added version	
					LDIF4SoftwareUpdateCheck.obj_ru.textField_2.setText(all_vers1.getSelectedItem().toString());
					LDIF4SoftwareUpdateCheck.obj_ru.comboBox_2.setSelectedIndex(LDIF4SoftwareUpdateCheck.ldsuc_curr_index);					
				//Call search method on the version at same index as added
					LDIF4SoftwareUpdateCheck.obj_ru.LdifSearch(LDIF4SoftwareUpdateCheck.ds.getElementAt(LDIF4SoftwareUpdateCheck.ldsuc_curr_index));   // to get all the values in the GUI	// keeps sb value		    
					//LDIF4SoftwareUpdateCheck.obj_ru.curr_index = LDIF4SoftwareUpdateCheck.ldsuc_curr_index;
					k=0;
					update_start=0;
					update_end=0;
				    rg_sb = "";
				    sb = null;
				//Highlight the selected version
				    LDIF4SoftwareUpdateCheck.obj_ru.high_function(LDIF4SoftwareUpdateCheck.ds.getElementAt(curr_index),"add"); // keeps ab value
				    rg_sb = "";
				    sb = null;
				// Feedback of the operation
				    LDIF4SoftwareUpdateCheck.obj_ru.label.setText(textField_2.getText() + " added in file.");	
					return;	
					}				
			}
			return;
		}
	} // End of add_buttonF()
	public void delete_buttonF() {
	//Reset common variables
		label.setText("");
		LDIF4SoftwareUpdateCheck.lblNewLabel.setText("");
		rg_sb = "";
		sb = null;
	//Get file latest content		
				sb = OpenFileAsStringB();
	//Check if file was empty		
				  if (sb == null) 
				  { 
				  label.setText("Error Encountered in reading file.");
				  return; 
				  }
	//Product File vs Selected product match, version parameters check
				  if (ProductFileMatch(sb) !=0) {return;}
				  if (check_param_version(sb) != 0) {return;}
	//Saving current index of the version to be deleted
		LDIF4SoftwareUpdateCheck.ldsuc_curr_index = all_vers1.getIndexOf(rg_prod_version);
		
	//Delete function core
		if (k != -1) {
			update_start = sb.lastIndexOf("<", k);
			// System.out.println(sb.substring(update_start,update_start+1));
			update_end = sb.indexOf(">", k);
			// Add code to check the start and end int for -1 if the version to be modified
			// is not found
			sb.delete(update_start, update_end + 1);
			file_write_fromSB(sb);
		//resetting the values of variable			
			k = 0;
			update_start = 0;
			update_end = 0;
			rg_sb = "";
			sb = null;
		//Removing element from version drop down comboBox_2
			all_vers1.removeElementAt(LDIF4SoftwareUpdateCheck.ldsuc_curr_index);
		//Refresh the content of the file
		    ldsuc1.ShowFileContent();
		//Setting curr_index for next version to highlight which will be same as the one deleted.
			curr_index = LDIF4SoftwareUpdateCheck.ldsuc_curr_index; // value saved earlier
		//Dispose current regular object to refresh the value of version drop down comboBox_2
			//LDIF4SoftwareUpdateCheck.obj_ru.dispose();
			//ldsuc1.reg_upd_fn();
		// Setting when record deleted other than last one	
		    if (LDIF4SoftwareUpdateCheck.ldsuc_curr_index < LDIF4SoftwareUpdateCheck.ds.getSize())
		    {
		//set both version comboBox_2 and version filled same value for next version
			LDIF4SoftwareUpdateCheck.obj_ru.textField_2.setText(all_vers1.getSelectedItem().toString());
			LDIF4SoftwareUpdateCheck.obj_ru.comboBox_2.setSelectedIndex(LDIF4SoftwareUpdateCheck.ldsuc_curr_index);
		//Call search method on the version at same index as deleted before (next version after deletion)
			LDIF4SoftwareUpdateCheck.obj_ru.LdifSearch(LDIF4SoftwareUpdateCheck.ds.getElementAt(LDIF4SoftwareUpdateCheck.ldsuc_curr_index));   // to get all the values in the GUI	// keeps sb value		    
		//Resetting variables
			k=0;
			update_start=0;
			update_end=0;
		    rg_sb = "";
		    sb = null;				   
		//Highlight the selected version
		    high_function(LDIF4SoftwareUpdateCheck.obj_ru.all_vers1.getElementAt(LDIF4SoftwareUpdateCheck.ldsuc_curr_index),"delete"); // keeps ab value
		    rg_sb = "";
		    sb = null;		
		    }
		 //Settings in else part for last record that was deleted.
		    else if (LDIF4SoftwareUpdateCheck.ldsuc_curr_index == LDIF4SoftwareUpdateCheck.ds.getSize()) { 
		 //After last record deleted counter is set to first record
		    	curr_index = 0;
		 //set both version comboBox_2 and version filled same value for first version
				LDIF4SoftwareUpdateCheck.obj_ru.textField_2.setText(all_vers1.getElementAt(0));
				LDIF4SoftwareUpdateCheck.obj_ru.comboBox_2.setSelectedIndex(0);
		 //Call search method on the version at same index as deleted before (next version after deletion)
				LDIF4SoftwareUpdateCheck.obj_ru.LdifSearch(LDIF4SoftwareUpdateCheck.ds.getElementAt(0));   // to get all the values in the GUI	// keeps sb value		    
			    k=0;
				update_start=0;
				update_end=0;
			    rg_sb = "";
			    sb = null;
		//Highlight the selected version
			    high_function(all_vers1.getElementAt(0),"delete"); // keeps ab value
			    rg_sb = "";
			    sb = null;			    
		    }
		    else {label.setText("There is no record to delete.");}
		} else
			label.setText("No operation done");
	} // End of delete_buttonF()
	public void modify_buttonF() {
//Reset common variables		
		label.setText("");
		rg_sb = "";
		sb = null;
//Get file latest content		
		sb = OpenFileAsStringB();
//Check if file was empty		
		  if (sb == null) 
		  { 
		  label.setText("Error Encountered in reading file.");
		  return; 
		  }
//Product File vs Selected product match, version parameters check
		  if (ProductFileMatch(sb) !=0) {return;}
		  if (check_param_version(sb) != 0) {return;}
		 
//Modify core 		
		if (k != -1) {
			update_start = sb.lastIndexOf("<", k);
			// System.out.println(sb.substring(update_start,update_start+1));
			update_end = sb.indexOf(">", k);
			int int_mand = 2;
			String str_softsec = "";
			if (comboBox_1.getSelectedIndex() == 0) {
				str_softsec = "software";
			} else {
				str_softsec = "security";
			}
			if (rdbtnNewRadioButton.isSelected()) {
				int_mand = 1;
			} else if (rdbtnNo.isSelected()) {
				int_mand = 0;
			}

// check all elements are present			
			if (all_param_check() != 0) {return;}
			
			String tobe_updated = "<Update t=\"" + textField.getText() + "\" f=\"" + textField_1.getText()
					+ "\" d=\"" + textArea.getText() + "\" m=\"" + int_mand + "\" n=\"" + textField_2.getText()
					+ "\" k=\"" + str_softsec + "\"/";
			// Add code to check the start and end int for -1 if the version to be modified
			// is not found and correct file to update
			sb.replace(update_start, update_end, tobe_updated);
			file_write_fromSB(sb);
// Resetting common variables			
			k = 0;
			update_start = 0;
			update_end = 0;
			rg_sb = "";
			sb = null;
			int_mand = 2;
// Refresh file content in GUI and highlight
			ldsuc1.ShowFileContent();
			high_function(rg_prod_version,"modify");
// Resetting as highlight function keeps sb
			rg_sb = "";
			sb = null;
//Setting final feedback
			label.setText("Record Modified");

		} else
			label.setText("Record not found");
	} // End of modify_buttonF()
	public StringBuilder OpenFileAsStringB() {
		try {

			FileReader fr_rg_sr = new FileReader(LDIF4SoftwareUpdateCheck.SelectedFile);
			BufferedReader br_rg_sr = new BufferedReader(fr_rg_sr);
			int i;
			while ((i = br_rg_sr.read()) != -1) {
				rg_sb = rg_sb + (char) i;
			}
			br_rg_sr.close();
			fr_rg_sr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
				label.setText("File I/O exception");
			else
				label.setText("File I/O exception");
		} catch (NullPointerException npe) {
			if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
				label.setText("Select the file");
			else
				label.setText("File not found or file not selected");
		}

		sb = new StringBuilder(rg_sb);
		return sb;
	} // End of OpenFileAsStringB()	
	public void file_write_fromSB(StringBuilder sb) {
		
		try {
			// FileWriter rg_fw_mod = new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile,
			// false);
			// BufferedWriter rg_bw_mod = new BufferedWriter(rg_fw_mod.sb.toString());
			PrintWriter rg_pr_mod = new PrintWriter(
					new BufferedWriter(new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile)));
			rg_pr_mod.write(sb.toString());
			rg_pr_mod.close();
			} catch (IOException e1) {
			// TODO Auto-generated catch block
			if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
				label.setText("File I/O exception");
			else
				label.setText("File I/O exception");

		} catch (NullPointerException npe) {
			if (LDIF4SoftwareUpdateCheck.textField.getText() == "")
				label.setText("Select the file");
			else
				label.setText("File not found or file not selected");
		}
		
	}// End of file_write_fromSB(StringBuilder sb)
	public int check_param_version(StringBuilder sb) {		
		rg_prod_version = textField_2.getText();
		if (textField_2.getText().length() != 0 && textField_2.getText().length() >= 6) {
			k = sb.indexOf("n=\"" + rg_prod_version);// check later the boundary. Should not return -1
			if (k == -1) {
				label.setText("Version not found.");
				rg_sb = "";
				sb = null;
				textField_2.setBackground(Color.red);
				return -1;
			}
		} else {
			label.setText("Version field is mandatory and of fixed length(6)");
			rg_sb = "";
			sb = null;
			return -2;
		}
		return 0;
	} // End of check_param(StringBuilder sb)
//Product file and Selected product match
	public int ProductFileMatch(StringBuilder sb) {
		int j = sb.indexOf(search_builder);		
		if (j == -1) {
			// JOptionPane.showMessageDialog(null, "Select correct product for this LDIF
			// file");
			label.setText("File does not match with selected product.");
			rg_sb = "";
			sb = null;
			return j;
		}
		return 0;		
	} // End of ProductFileMatch()
	public int all_param_check() {
		if ((textField_2.getText().length() == 0 || textField_2.getText().length() < 6)
				|| (textField.getText().length() == 0 || textField.getText().length() < 8)
				|| (textField_1.getText().length() == 0 || textField_1.getText().length() < 8)
				|| (textArea.getText().length() == 0) // || int_mand == 2
		) {
			label.setText("Fill all parameter.VersionLength=6, DateLength=8.");
			rg_sb = "";
			sb = null;
			return -1;
		}
		return 0;
	}
	public void addVersion4AddF(StringBuilder sb,String Product_version, String Product_version_add, int r) {
		k = sb.indexOf("n=\""+Product_version);
		//Point from where the addition will take place
			k = sb.indexOf("/>",k);
		//Resetting some variables	
			int int_mand = 2;
			String str_softsec = "";
	   //Fetching the value of new record 
			if (comboBox_1.getSelectedIndex() == 0)
			{str_softsec = "software";}
			else
			{str_softsec = "security";}
			if (rdbtnNewRadioButton.isSelected())
			{int_mand = 1;}
			else if (rdbtnNo.isSelected())
			{int_mand = 0;}	
		//Checking all parameters
			if (all_param_check() != 0) {return;}
		//Writing record to the file	
			String tobe_added = "<Update t=\""+ textField.getText() +"\" f=\"" + 
					textField_1.getText() + "\" d=\"" + textArea.getText() + "\" m=\"" + 
					int_mand + "\" n=\"" + textField_2.getText() + "\" k=\"" + str_softsec + "\"/>" ;                  
			//sb.insert(update_start+k+2, tobe_added);	
			sb.insert(k+2, tobe_added);
			file_write_fromSB(sb);
		//Resetting variables	
			int_mand = 2;					
			rg_sb = "";
			sb = null;					
		//Adding element from version drop down comboBox_2			
			all_vers1.insertElementAt(textField_2.getText(),r);
		// Refresh file content
		    ldsuc1.ShowFileContent();
		//Setting curr_index for next version to highlight which will be same as the one deleted.
			curr_index = r ; 
			LDIF4SoftwareUpdateCheck.ldsuc_curr_index = r ;					
		//set both version comboBox_2 and version filled same value for next version
			LDIF4SoftwareUpdateCheck.obj_ru.textField_2.setText(all_vers1.getSelectedItem().toString());
			LDIF4SoftwareUpdateCheck.obj_ru.comboBox_2.setSelectedIndex(LDIF4SoftwareUpdateCheck.ldsuc_curr_index);
		//Call search method on the version at same index as deleted before (next version after deletion)
			LDIF4SoftwareUpdateCheck.obj_ru.LdifSearch(LDIF4SoftwareUpdateCheck.ds.getElementAt(LDIF4SoftwareUpdateCheck.ldsuc_curr_index));   // to get all the values in the GUI	// keeps sb value		    
		//Reset Variables	
			k=0;
			update_start=0;
			update_end=0;
		    rg_sb = "";
		    sb = null;
		//Highlight the selected version
		    LDIF4SoftwareUpdateCheck.obj_ru.high_function(LDIF4SoftwareUpdateCheck.ds.getElementAt(curr_index),"add"); // keeps ab value
		    rg_sb = "";
		    sb = null;
		    LDIF4SoftwareUpdateCheck.obj_ru.label.setText(Product_version_add + " added in file.");		    
	} // End of addVersion4AddF

} // End of Class

// unused code

//FileWriter rg_fw_mod = new FileWriter(LDIF4SoftwareUpdateCheck.SelectedFile,
// false);
// BufferedWriter rg_bw_mod = new BufferedWriter(rg_fw_mod.sb.toString());
/*
System.out.println("Before if condition");
System.out.println("SizeOfModel=" + LDIF4SoftwareUpdateCheck.ds.getSize());
System.out.println("curr_index="+curr_index + "  " + "all_vers1_selectedItem=" + LDIF4SoftwareUpdateCheck.obj_ru.all_vers1.getElementAt(LDIF4SoftwareUpdateCheck.ldsuc_curr_index) );

//For testing
		   System.out.println("Inside if condition");
		   System.out.println("SizeOfModel=" + LDIF4SoftwareUpdateCheck.ds.getSize());
		   System.out.println("curr_index="+curr_index+ "  " + "all_vers1_selectedItem=" + LDIF4SoftwareUpdateCheck.ds.getElementAt(LDIF4SoftwareUpdateCheck.ldsuc_curr_index) );

System.out.println("in last record else if condition");
			    System.out.println("SizeOfModel=" + LDIF4SoftwareUpdateCheck.ds.getSize());
				System.out.println("curr_index="+curr_index+ "  " + "all_vers1_selectedItem=" + LDIF4SoftwareUpdateCheck.obj_ru.all_vers1.getElementAt(LDIF4SoftwareUpdateCheck.ldsuc_curr_index) );

//LDIF4SoftwareUpdateCheck.ds.addElement(textField_2.getText());
//Dispose current regular object to refresh the value of version drop down comboBox_2
			//LDIF4SoftwareUpdateCheck.obj_ru.dispose();
			//ldsuc1.reg_upd_fn();
			 * Regular_Update.curr_index = LDIF4SoftwareUpdateCheck.ldsuc_curr_index;
System.out.println("R=" + r + "   " + "ldsuc_curr=" + LDIF4SoftwareUpdateCheck.ldsuc_curr_index );

*
*
*/


