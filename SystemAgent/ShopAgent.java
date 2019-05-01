import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class ShopAgent extends Agent {

public void setup() {
	ShopGui m_frame;
	
	m_frame = new ShopGui(this);
    m_frame.setVisible( true );
	// Register the book-selling service in the yellow pages
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			ServiceDescription sd = new ServiceDescription();
			sd.setType("shop-list");
			sd.setName("JADE-shop");
			dfd.addServices(sd);
		
				try {
					DFService.register(this, dfd);
				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				addBehaviour(new CyclicBehaviour() {

					public void action(){
						
						ACLMessage msgR = myAgent.receive();
						if (msgR!=null) {
							
						System.out.println("Shops receive request from "+msgR.getSender());
						//----
						
						//-----
						FileInputStream fis = null;
						try {
							
							fis = new FileInputStream(new File("myowl.owl"));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						InputStreamReader isr = null;
						isr = new InputStreamReader(fis);
						
						
						BufferedReader buff=new BufferedReader(isr);
						String value1;
						String value2 = new String();
						try {
							while ((value1 = buff.readLine())!=null) {
								
								value2+=value1+"\n";
								
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
				ACLMessage reply=msgR.createReply();
				reply.setContent(value2);
				reply.setEncoding(value2);
				
				
				myAgent.send(reply);
				System.out.println("Your shop are updated : "+new Date());
				System.out.println("----------------------------------------------------------");
				try {
					fis.close();
					buff.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						} else {
							block();
						}
					}
						
				});
}

public void killAgent() {
	this.addBehaviour(new OneShotBehaviour() {
		
		@Override
		public void action() {
			// TODO Auto-generated method stub
			myAgent.doDelete();
		}
	});
	// Deregister with the DF
			try {
				DFService.deregister(this);
				System.out.println(getLocalName()+" DEREGISTERED WITH THE DF");
			} catch (FIPAException e) {
				e.printStackTrace();
			}
}
}
