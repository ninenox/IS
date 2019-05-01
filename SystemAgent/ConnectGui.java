import jade.core.behaviours.OneShotBehaviour;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JSeparator;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;


public class ConnectGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	static JButton button;
	static final String FILE_ENCODE = "UTF-8";
	JComboBox comboBox;
	String query;
	String query2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectGui frame = new ConnectGui();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public ConnectGui() {
		
		setTitle("ยินดีต้อนรับเข้าสู่ระบบเอเจนท์ร้านค้า");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("IP Host");
		label.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label.setBounds(89, 119, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("ชื่อร้านค้า (English)");
		label_1.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label_1.setBounds(62, 155, 99, 14);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(171, 153, 119, 20);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(171, 117, 119, 20);
		contentPane.add(textField_1);
		
		button = new JButton("เชื่อมต่อ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectHost();
			
			}
		});
		button.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		button.setBounds(171, 184, 119, 23);
		contentPane.add(button);
		
		JLabel label_2 = new JLabel("เชื่อมต่อระบบเอเจนท์โฮส");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label_2.setBounds(41, 188, 124, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("รายละเอียดฐานข้อมูล");
		label_3.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label_3.setBounds(10, 268, 130, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("ฐานข้อมูลที่ใช้งาน");
		label_4.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label_4.setBounds(244, 268, 99, 14);
		contentPane.add(label_4);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Opencart", "Joomla virtuemart"}));
		comboBox.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		comboBox.setBounds(244, 292, 117, 20);
		contentPane.add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(76, 293, 124, 20);
		contentPane.add(textField_2);
		
		JLabel label_5 = new JLabel("ตำแหน่ง");
		label_5.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label_5.setBounds(10, 296, 46, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("user");
		label_6.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label_6.setBounds(10, 327, 46, 14);
		contentPane.add(label_6);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(76, 324, 124, 20);
		contentPane.add(textField_3);
		
		JButton button_1 = new JButton("สร้างไฟล์ owl");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOwl();
			}
		});
		button_1.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		button_1.setBounds(244, 323, 117, 23);
		contentPane.add(button_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(76, 353, 124, 20);
		contentPane.add(textField_4);
		
		JLabel label_7 = new JLabel("password");
		label_7.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label_7.setBounds(10, 356, 67, 14);
		contentPane.add(label_7);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 218, 396, 14);
		contentPane.add(separator);
		
		JLabel label_8 = new JLabel("สร้างไฟล์ owl เพื่อให้เอเจนท์มาเก็บข้อมูลสินค้า");
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("PSLxBundit", Font.PLAIN, 17));
		label_8.setBounds(108, 230, 235, 14);
		contentPane.add(label_8);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ConnectGui.class.getResource("/img/shop.jpg")));
		lblNewLabel.setBounds(95, 0, 206, 108);
		contentPane.add(lblNewLabel);
	}
	
	public void connectHost(){
		File yourFile = new File("runagent.bat");
		if(!yourFile.exists()) {
		    
				try {
					yourFile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		String pathProgram=System.getProperty("user.dir");
		pathProgram.replace("\\","\\\\");
		
		FileOutputStream oFile = null;
		try {
			oFile = new FileOutputStream(yourFile, false);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintWriter printwrite=new PrintWriter(oFile);
		printwrite.println("set CLASSPATH=.;%CLASSPATH% \n cd "+pathProgram+" \n d: \n java -cp "+pathProgram+"\\lib\\jade.jar;"+pathProgram+"\\lib\\jena-core-2.7.4.jar;"+pathProgram+"\\lib\\slf4j-api-1.6.4.jar;"+pathProgram+" jade.Boot -container -host "+textField_1.getText()+" "+textField.getText()+":ShopAgent \n pause;");
		
		printwrite.close();
		
		try {
			Desktop.getDesktop().open(new File("runagent.bat"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void MakeOwlFile(Model loadmodel){
		JFrame myframe = null;
		File owlFile=new File("myowl.owl");
		if (!owlFile.exists()) {
			try {
				owlFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		try {
			 FileOutputStream fo=new FileOutputStream(owlFile);
			OutputStreamWriter osw=new OutputStreamWriter(fo,"UTF-8");
			loadmodel.write(osw,"RDF/XML-ABBREV"); //N-TRIPLE
			try {
				osw.close();
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(myframe,"เกิดข้อผิดพลาด ไม่สามารถสร้างไฟล์ได้","ข้อความ",JOptionPane.PLAIN_MESSAGE);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(myframe,"ระบบได้ทำการสร้างไฟล์สำเร็จแล้ว","ข้อความ",JOptionPane.PLAIN_MESSAGE);
	}

	public void makeOwl(){
		OntClass ont0=null;
		OntClass ont1=null;
		OntClass ont2=null;
		OntClass ont3=null;
		OntClass ont4=null;
		OntClass ont5=null;
		OntClass ont6=null;
		OntClass ont7=null;
		OntClass ont8 = null;
		OntClass myont=null;
	String s0="";
	String s1="";
	String s2="";
	String s3="";
	String s4="";
	String s5="";
	String s6="";
	String s7="";
	String s8="";
		
		String host;
		  try {
			  String x;
			  String hostIP=null;
			  try {
				hostIP = InetAddress.getLocalHost().getHostAddress();
				System.out.println(hostIP);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
if (comboBox.getSelectedIndex()==0){
			   query = "Select product.product_id,product_description.name,product.quantity,product.image,product.price,category_description.name FROM product,product_description,category_description,product_to_category WHERE product.product_id=product_to_category.product_id and product.product_id=product_description.product_id and category_description.category_id=product_to_category.category_id and product_description.language_id=2"; 
 query2 ="Select category_description.name From category_description Where language_id=1";
} else if (comboBox.getSelectedIndex()==1){
	 query = "Select jos_vm_product.product_id,jos_vm_product.product_name,jos_vm_product.product_in_stock,jos_vm_product.product_full_image,jos_vm_product_price.product_price,jos_vm_category.category_name FROM jos_vm_product,jos_vm_product_price,jos_vm_category,jos_vm_product_category_xref WHERE jos_vm_product.product_id=jos_vm_product_category_xref.product_id and jos_vm_category.category_id=jos_vm_product_category_xref.category_id and jos_vm_product.product_id=jos_vm_product_price.product_id"; 
	 query2 ="Select category_name From jos_vm_category ";
}

						  try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Connection con = null;
		try {
			con = DriverManager.getConnection ("jdbc:mysql://"+textField_2.getText()+"?user="+textField_3.getText()+"&password="+textField_4.getText()+"&characterSet=utf8&useUnicode=true&characterEncoding=utf-8&characterSetResults=utf8");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Statement stmt = null;
		  Statement stmt2 = null;
		try {
			stmt = con.createStatement();
			stmt2 = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  ResultSet rs = null;
		  ResultSet rs2 = null;
		try {
			rs = stmt.executeQuery(query);
			rs2 =stmt2.executeQuery(query2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
			  if (textField_2.getText().indexOf("/")!=-1){
			  int firstSlash=textField_2.getText().indexOf("/");
			   x=textField_2.getText().substring(firstSlash, textField_2.getText().length());
			  } else {
			   x=textField_2.getText();
			  }
			  String Opencart="/index.php?route=product/product&product_id=";
			  String Joomla="/index.php?page=shop.product_details&flypage=flypage.tpl&category_id=8&option=com_virtuemart&Itemid=64&product_id=";
			  host = "http://"+hostIP+x;
			   
			  String Source = null;
			  String SourceImg=null;
			  if (comboBox.getSelectedIndex()==0){
				    Source=host+Opencart;
				    SourceImg=host+"/image/";
	} else if (comboBox.getSelectedIndex()==1){
		  Source=host+Joomla;
		  SourceImg=host+"/components/com_virtuemart/shop_image/product/";
	}
			  String NS = host+"/product#";
			 
			  String NsCl=host+"#";
			 
			  
			  OntModel model = ModelFactory.createOntologyModel();
			  OntClass product = model.createClass( NsCl + "Product" );
			  OntClass vocab = model.createClass(NsCl+"Vocab");
			  
			 
			  String categorys = null;
			 
			 
			  List rowValues = new ArrayList();
			  while (rs2.next()) {	
				
				 rowValues.add(rs2.getString(1));
						  }
			  String[] catebuff = (String[]) rowValues.toArray(new String[rowValues.size()]);
			  
			  for (int i =0; i < catebuff.length; i++){
			        
				  if (i==0) {
				   ont0 = model.createClass( NsCl + catebuff[0].replace("&amp;","And").replaceAll("\\s","_") );
				   s0=catebuff[i];
				   product.addSubClass(ont0);
				  } else
				  if (i==1) {
					  ont1 = model.createClass( NsCl + catebuff[1].replace("&amp;","And").replaceAll("\\s","_") );
					  s1=catebuff[i];
					  product.addSubClass(ont1);
					  
				  } else
				  if (i==2) {
					  ont2 = model.createClass( NsCl + catebuff[2].replace("&amp;","And").replaceAll("\\s","_") );
					  s2=catebuff[i];
					  product.addSubClass(ont2);
				  } else
				  if (i==3) {
					  ont3 = model.createClass( NsCl + catebuff[3].replace("&amp;","And").replaceAll("\\s","_") );
					  s3=catebuff[i];
					  product.addSubClass(ont3);
				  } else
				  
				  if (i==4) {
					  ont4 = model.createClass( NsCl + catebuff[4].replace("&amp;","And").replaceAll("\\s","_") );
					  s4=catebuff[i];
					  product.addSubClass(ont4);
				  } else
				  if (i==5) {
					  ont5 = model.createClass( NsCl + catebuff[5].replace("&amp;","And").replaceAll("\\s","_") );
					  s5=catebuff[i];
					  product.addSubClass(ont5);
				  } else
				  if (i==6) {
					  ont6 = model.createClass( NsCl + catebuff[6].replace("&amp;","And").replaceAll("\\s","_") );
					  s6=catebuff[i];
					  product.addSubClass(ont6);
				  } else
				  if (i==7) {
					  ont7 = model.createClass( NsCl + catebuff[7].replace("&amp;","And").replaceAll("\\s","_") );
					  s7=catebuff[i];
					  product.addSubClass(ont7);
				  } else
				  if (i==8) {
					  ont8 = model.createClass( NsCl + catebuff[8].replace("&amp;","And").replaceAll("\\s","_") );
					  s8=catebuff[i];
					  product.addSubClass(ont8);
				  }
				 
			    }
			 
			  DatatypeProperty hasName= model.createDatatypeProperty(NS+"hasName");
			  hasName.addDomain(product);
			  hasName.addRange(XSD.xstring);
			  ObjectProperty hasSynonym= model.createObjectProperty(NS+"hasSynonym");
			  hasSynonym.addDomain(product);
			  hasSynonym.addRange(vocab);
			  
			  DatatypeProperty hasLink= model.createDatatypeProperty(NS+"hasLink");
			  hasLink.addDomain(product);
			  hasLink.addRange(XSD.xstring);
			  DatatypeProperty hasShopName= model.createDatatypeProperty(NS+"hasShopName");
			  hasShopName.addDomain(product);
			  hasShopName.addRange(XSD.xstring);
			  DatatypeProperty hasPrice=model.createDatatypeProperty(NS+"hasPrice");
			  hasPrice.addDomain(product);
			  hasPrice.addRange(XSD.xdouble);
			  DatatypeProperty hasImage=model.createDatatypeProperty(NS+"hasImage");
			  hasImage.addDomain(product);
			  hasImage.addRange(XSD.xstring);
			  DatatypeProperty hasQuantity=model.createDatatypeProperty(NS+"hasQuantity");
			  hasQuantity.addDomain(product);
			  hasQuantity.addRange(XSD.xint);
			 
			  while (rs.next()) {
			  		  
			  // create an empty Model
			  String instanceProduct=host+"/product_id/"+rs.getString(1);
			  String Vocab=host+"/product_vocab/"+rs.getString(2);
			  String VocabFilter=Vocab.replaceAll(" ", "_");
			  String personURI    = Source+rs.getString(1);
			  String nameor  = rs.getString(2);
			  String name = null;
			try {
				name = new String( nameor.getBytes(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  String categoryItem  = rs.getString(6);
			  double price = Double.parseDouble(rs.getString(5));
			  String image = SourceImg+rs.getString(4);
			  int quantity =Integer.parseInt(rs.getString(3));
		 
			 if (categoryItem.equals(s0)) {
				  myont=ont0;
			 } else
			 if (categoryItem.equals(s1)) {
				  myont=ont1;
			 } else
			 if (categoryItem.equals(s2)) {
				  myont=ont2;
			 } else
			 if (categoryItem.equals(s3)) {
				  myont=ont3;
			 } else
			 if (categoryItem.equals(s4)) {
				  myont=ont4;
			 } else
			 if (categoryItem.equals(s5)) {
				  myont=ont5;
			 } else
			 if (categoryItem.equals(s6)) {
				  myont=ont6;
			 } else
			 if (categoryItem.equals(s7)) {
				  myont=ont7;
			 } else
			 if (categoryItem.equals(s8)) {
				  myont=ont8;
			 }
			 
				   model.createIndividual(instanceProduct, myont)
		         	                
					
						   .addLiteral(hasName, nameor)
						   
						   .addLiteral(hasLink,personURI )
						   .addLiteral(hasShopName, host)
						   .addLiteral(hasPrice,price)
						   .addLiteral(hasImage, image)
						   .addLiteral(hasQuantity, quantity);
				   
				   
		  }
	MakeOwlFile(model);
		 
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //end while
		  
		  }
}
