package com.swift.alliance;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JScrollPane;


import java.awt.Color;
import javax.swing.JTextField;
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

	private JFrame frame;
	public static JTextField textField;
	private JTextField textField_1;
	private FileDialog fd;
	private JTextArea textArea;
	static public String SelectedFile;
	//static LDIF4SoftwareUpdateCheck own_obj;....

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LDIF4SoftwareUpdateCheck window = new LDIF4SoftwareUpdateCheck();
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 543, 621);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("SWIFT Alliance LDIF editor");
		
		JLabel lblSelectedFile = new JLabel("LDIF File");
		lblSelectedFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectedFile.setBounds(10, 11, 60, 14);
		frame.getContentPane().add(lblSelectedFile);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
			}
		});
		textField.setBounds(80, 8, 330, 17);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSelectFile = new JButton("Select");
		btnSelectFile.setActionCommand("LDIF File");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				if (ae.getActionCommand()== "LDIF File")
				{
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
		
		JButton btnAdd = new JButton("Regular Update");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 //System.out.println("Regular Update event");
				Regular_Update obj_ru = new Regular_Update();
				obj_ru.setVisible(true);
			}
		});
		btnAdd.setBounds(10, 64, 130, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnYearlyUpdate = new JButton("Yearly Update");
		btnYearlyUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "This will be implemented in next release of the tool.\n Coming Soon.");
				
				Yearly_Update obj_yu = new Yearly_Update();
				obj_yu.setVisible(true);
			}
		});
		btnYearlyUpdate.setBounds(166, 64, 130, 23);
		frame.getContentPane().add(btnYearlyUpdate);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(166, 98, 259, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnDisplayFileContent = new JButton("Show file content");
		btnDisplayFileContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				if (ae.getActionCommand()== "Show file content")
				{
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
			}
		});
		btnDisplayFileContent.setBounds(10, 99, 146, 23);
		frame.getContentPane().add(btnDisplayFileContent);
		//JScrollPane scroll = new JScrollPane(textArea);
		//frame.getContentPane().add(scroll);  
		//JScrollPane jsp = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//JScrollPane jsp = new JScrollPane();
		//jsp.getViewport().add(textArea);
		//frame.getContentPane().add(jsp);
	
	      
		JLabel lblDirectoryPath = new JLabel("Directory");
		lblDirectoryPath.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDirectoryPath.setBounds(10, 36, 60, 14);
		frame.getContentPane().add(lblDirectoryPath);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(80, 36, 330, 17);
		frame.getContentPane().add(textField_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 133, 517, 429);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea(5,30);
		scrollPane.setViewportView(textArea);
		textArea.setAutoscrolls(true);
		textArea.setBorder(UIManager.getBorder("ScrollPane.border"));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setEditable(false);
		textArea.setForeground(new Color(30, 144, 255));
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAbout = new JMenu("Info");
		
		menuBar.add(mnAbout);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Version 1.0\nEditing tool for LDIFF file which is used by the\n feature \"Software Update Version Check\" in\n Swift Alliance Products");
			}
		});
		mnAbout.add(mntmAbout);
		
		
		
		
		
		
	}
}
