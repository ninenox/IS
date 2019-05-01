
import jade.core.behaviours.OneShotBehaviour;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import java.awt.Color;


public class ShopGui extends JFrame {

	private JPanel contentPane;
JFrame mFrame;
String iphost;
ShopAgent owner;


	/**
	 * Launch the application.
	 */
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}


	/**
	 * Create the frame.
	 */
	public ShopGui(ShopAgent m_owner) {
		
		owner=m_owner;
		setTitle("สถานะการเชื่อมต่อระบบ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button_1 = new JButton("ยกเลิกการเชื่อมต่อ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				owner.addBehaviour( new OneShotBehaviour() {
                    public void action() {
                        ((ShopAgent) myAgent).killAgent();
                       
                    }
                } );
				
				dispose();
				
			}
			
		});
		button_1.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		button_1.setBounds(85, 72, 129, 27);
		contentPane.add(button_1);
		
		JLabel label = new JLabel("ระบบได้ทำการเชื่อมต่อแล้ว");
		label.setForeground(Color.RED);
		label.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label.setBounds(85, 30, 143, 20);
		contentPane.add(label);
	}
}
