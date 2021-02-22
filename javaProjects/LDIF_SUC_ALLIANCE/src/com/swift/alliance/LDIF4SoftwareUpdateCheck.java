package com.swift.alliance;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JScrollPane;


import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;


import java.awt.event.ActionListener;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class LDIF4SoftwareUpdateCheck {
//Private members
	private JFrame frame;
	private static JTextField textField_1;
	private static FileDialog fd;
	private static JTextArea textArea;
//Public members	
	static JTextField textField;	
	static JLabel lblNewLabel = new JLabel("");
	
	static String SelectedFile;
	static LDIF4SoftwareUpdateCheck window1;
	static DefaultComboBoxModel<String> ds;
	static Regular_Update obj_ru;
	static Yearly_Update obj_yu;
	

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LDIF4SoftwareUpdateCheck window = new LDIF4SoftwareUpdateCheck();
					window1=window;
					//own_obj = window;
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public LDIF4SoftwareUpdateCheck() {
		initialize();
	}
	
	public void ShowFileContent() {
		StringBuilder sb;
		int first,next,arr_count=1,end_bb,k;
		
		try {
			if (fd != null)
			{
				
				FileReader fr = new FileReader(fd.getDirectory()+fd.getFile());
				BufferedReader br = new BufferedReader(fr);
				String buf = "";
				int i;
				lblNewLabel.setText("");
				while((i=br.read())!=-1){  
			        if (i <= 5000) {
					   
					buf = buf + (char)i;
					if ((char)i== '>') {
						buf = buf + "\n";
					}
			        }
			        else {
			        	lblNewLabel.setText("Selected file is not a text file");
			        	buf = " ";
			        	//textArea.setText("Selected Input file is not a text file");
			        	//System.out.println("Input file is not a text file");
			        	break;
			        }
			        }  
				textArea.setText(buf);	
				br.close();
				fr.close();
				sb = new StringBuilder(buf);
				first=sb.indexOf("n=\"");            // check for return of -1
				end_bb=sb.lastIndexOf("n=\"");
				
				if ((first < 0 || end_bb <0) )
				{
					return;
				}
				next=first;
				while (next < end_bb) {					
					k=sb.indexOf("n=\"",next+9);
					arr_count++;
					next=k;		
					//System.out.println("next now=" + next);
					
				}				
				//String[] version_arr1 = new String[arr_count+1];
				ds=new DefaultComboBoxModel<String>();
				arr_count=2;
				//version_arr1[1] = sb.substring(first+3,first+9);
				ds.addElement(sb.substring(first+3,first+9));
				next=first;
				while (next < end_bb) {					
					k=sb.indexOf("n=\"",next+9);
					//version_arr1[arr_count] = sb.substring(k+3,k+9);
					ds.addElement(sb.substring(k+3,k+9));
					//comboBox_2.insertItemAt(sb.substring(k+3,k+9), arr_count);
					//System.out.println(arr_count);
					//System.out.println(version_arr[arr_count]);
					arr_count++;
					next=k;		
					//System.out.println("next now=" + next);
					
				}	
				
			}
			else
			lblNewLabel.setText("File not selected");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			if (textField.getText() == "")
			lblNewLabel.setText("Select the file");
			else
				lblNewLabel.setText("File not found or file not selected");
			
		}	
	}
	
	
    public void reg_upd_fn() {
    	
    	if (ds==null) {
			lblNewLabel.setText("First click on show file content button.");
			return;
		}
		StringBuilder sb;
		int first,next,arr_count=1,end_bb,k;
		String[] version_arr = new String[50];			
		
		try {
			if (fd != null)
			{
				
				FileReader fr = new FileReader(fd.getDirectory()+fd.getFile());
				BufferedReader br = new BufferedReader(fr);
				String buf = "";
				int i;
				lblNewLabel.setText("");
				while((i=br.read())!=-1){  
			        if (i <= 5000) {
					   
					buf = buf + (char)i;
					if ((char)i== '>') {
						buf = buf + "\n";
					}
			        }
			        else {
			        	lblNewLabel.setText("Selected file is not a text file");
			        	buf = " ";
			        	//textArea.setText("Selected Input file is not a text file");
			        	//System.out.println("Input file is not a text file");
			        	break;
			        }
			          }  
				
				sb = new StringBuilder(buf);
				first=sb.indexOf("n=\"");            // check for return of -1
				end_bb=sb.lastIndexOf("n=\"");
				br.close();
				fr.close();
				
				next=first;
				while (next < end_bb) {					
					k=sb.indexOf("n=\"",next+9);
					arr_count++;
					next=k;		
					//System.out.println("next now=" + next);
					
				}				
				String[] version_arr1 = new String[arr_count+1];
				arr_count=2;
				version_arr1[1] = sb.substring(first+3,first+9);
				next=first;
				while (next < end_bb) {					
					k=sb.indexOf("n=\"",next+9);
					version_arr1[arr_count] = sb.substring(k+3,k+9);
					//comboBox_2.insertItemAt(sb.substring(k+3,k+9), arr_count);
					//System.out.println(arr_count);
					//System.out.println(version_arr[arr_count]);
					arr_count++;
					next=k;		
					//System.out.println("next now=" + next);
					
				}	
				obj_ru = new Regular_Update(ds,textArea, window1);
				obj_ru.setVisible(true);
				
			}
			else
			lblNewLabel.setText("File not selected");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			if (textField.getText() == "")
			lblNewLabel.setText("Select the file");
			else
				lblNewLabel.setText("File not found or file not selected");
			
		}
    	
    	
    }
	
    
    public void yea_upd_fn() {
    	obj_yu = new Yearly_Update(window1);
		obj_yu.setVisible(true);
    }
    /**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//GUI components
		
//JFrame settings
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 555, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("SWIFT Alliance LDIF editor");		
//Menu for the Frame		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
				
		JMenu mnAbout = new JMenu("Info");		
		menuBar.add(mnAbout);
				
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Version 2.0\nEditing tool for LDIFF file which is used by the\n feature \"Software Update Version Check\" in\n Swift Alliance Products");
					}
				});
		mnAbout.add(mntmAbout);			
//JLabel for file name		
		JLabel lblSelectedFile = new JLabel("LDIF File");
		lblSelectedFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectedFile.setBounds(10, 11, 60, 14);
		frame.getContentPane().add(lblSelectedFile);
//File name JTextField		
		textField = new JTextField();
		textField.setEditable(true);
		textField.setBounds(80, 8, 330, 17);
		frame.getContentPane().add(textField);
		textField.setColumns(10);		
//Select Button			
		JButton btnSelectFile = new JButton("Select");
		btnSelectFile.setActionCommand("LDIF File");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				if (ae.getActionCommand()== "LDIF File")
				{
					if (obj_ru != null) {
						obj_ru.setVisible(false);
					}
					fd = new FileDialog(frame, "Select LDIF file", 0);
					fd.setVisible(true);
					textField.setText(fd.getFile());
					textField_1.setText(fd.getDirectory());
					SelectedFile = fd.getDirectory()+fd.getFile();
				}
			}
			});
		btnSelectFile.setBounds(420, 7, 107, 23);
		frame.getContentPane().add(btnSelectFile);
// JLabel for directory path		
		JLabel lblDirectoryPath = new JLabel("Directory");
		lblDirectoryPath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDirectoryPath.setBounds(10, 36, 60, 14);
		frame.getContentPane().add(lblDirectoryPath);
//JTextField for Directory path		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(80, 36, 330, 17);
		frame.getContentPane().add(textField_1);
// Show File Content			
		JButton btnDisplayFileContent = new JButton("Show file content");
		btnDisplayFileContent.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
						
		if (ae.getActionCommand()== "Show file content")
		{
			ShowFileContent();		
		}
	}
});
		btnDisplayFileContent.setBounds(10, 64, 146, 23);
		frame.getContentPane().add(btnDisplayFileContent);		
//Regular Update		
		JButton btnAdd = new JButton("Regular Update");
		btnAdd.addActionListener(new ActionListener() {	
		public void actionPerformed(ActionEvent arg0) {	
			reg_upd_fn();
			}
		});
		btnAdd.setBounds(10, 98, 146, 23);
		frame.getContentPane().add(btnAdd);		
// Yearly Update		
		JButton btnYearlyUpdate = new JButton("Yearly Update");
		btnYearlyUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "This will be implemented in next release of the tool.\n Coming Soon.");
				yea_upd_fn();
			}
		});
		btnYearlyUpdate.setBounds(176, 98, 123, 23);
		frame.getContentPane().add(btnYearlyUpdate);	
//JLable for feedback		
		//JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(166, 63, 361, 24);
		frame.getContentPane().add(lblNewLabel);		
//JScrollPane for JTextArea		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 133, 517, 429);
		frame.getContentPane().add(scrollPane);
//JTextArea for file content		
		textArea = new JTextArea(5,30);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		scrollPane.setViewportView(textArea);
		textArea.setAutoscrolls(true);
		textArea.setBorder(UIManager.getBorder("ScrollPane.border"));
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setForeground(new Color(0, 0, 255));
	
	}// end of initialize function
}// class end

/* Not used code
 * //static  StringBuilder sb;
	//static String[] version_arr = new String[20];
	//static LDIF4SoftwareUpdateCheck own_obj1;....* 
 * 
 * 
 * //JScrollPane scroll = new JScrollPane(textArea);
		//frame.getContentPane().add(scroll);  
		//JScrollPane jsp = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//JScrollPane jsp = new JScrollPane();
		//jsp.getViewport().add(textArea);
		//frame.getContentPane().add(jsp);
 * 
 * 
 */
