import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.StaleProxyException;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.File;
import java.awt.Color;


public class MainGui extends JFrame {

	private JPanel contentPane;
RequestAgent ragent;
HostAgent sagent;
private JTextField textField;
private JTextField textField_1;
JButton button_1;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public MainGui(HostAgent m_owner) {
		setTitle("ยินดีต้อนรับสู่ระบบเอเจนท์โฮส");
	sagent=m_owner;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(172, 115, 117, 20);
		contentPane.add(textField);
		textField.setColumns(10);
				
		JButton button = new JButton("เริ่มการทำงาน");
		button.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")){
					System.out.println("Please insert value in textbox.");
				} else {
					button_1.setEnabled(true);
				sagent.addBehaviour(new OneShotBehaviour() {
					
					@Override
					public void action() {
						
						// TODO Auto-generated method stub
					
						
						RequestAgent objRA=new RequestAgent();
						objRA.timeRequest=(Long.valueOf(textField.getText()))*1000;
						objRA.locationOwl=textField_1.getText();
						
						((HostAgent) myAgent).createAgent();
					
					}
				});
				}
			}
		});
		button.setBounds(84, 201, 135, 23);
		contentPane.add(button);
		
		JLabel label = new JLabel("เวลาในการเรียกเก็บข้อมูล ");
		label.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label.setBounds(27, 104, 149, 41);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("วินาที");
		label_1.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label_1.setBounds(304, 117, 46, 14);
		contentPane.add(label_1);
		
		button_1 = new JButton("หยุดการทำงาน");
		button_1.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setEnabled(false);
				sagent.addBehaviour( new OneShotBehaviour() {
                    public void action() {
                        ((HostAgent) myAgent).endAgent();
                    }
                } );
			}
		});
		button_1.setBounds(229, 201, 117, 23);
		contentPane.add(button_1);
		button_1.setEnabled(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(172, 156, 160, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblOwl = new JLabel("ตำแหน่งจัดเก็บไฟล์รวม owl");
		lblOwl.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		lblOwl.setBounds(27, 158, 149, 14);
		contentPane.add(lblOwl);
		
		JButton btnBrowse = new JButton("Browse...");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.showOpenDialog(null);
                File file = chooser.getSelectedFile();
                String path=file.getAbsolutePath();
                textField_1.replaceSelection(path);
                
			}
			
		});
		btnBrowse.setBounds(342, 155, 89, 23);
		contentPane.add(btnBrowse);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainGui.class.getResource("/img/host.jpg")));
		lblNewLabel.setBounds(114, 0, 205, 104);
		contentPane.add(lblNewLabel);
	}
}
