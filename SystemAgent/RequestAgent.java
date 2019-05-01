import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class RequestAgent extends Agent {
	private AID[] shopAgents;
public static long timeRequest;
public static String locationOwl;
RequestAgent myclass;

public void inputtime(long x,String y){
	timeRequest=x;
	locationOwl=y;
}
	DFAgentDescription[] result = null;
	protected void setup() {
	
		addBehaviour(new TickerBehaviour(this, timeRequest) {
			protected void onTick() {
				
				// Update the list of seller agents
				DFAgentDescription template = new DFAgentDescription();
				ServiceDescription sd = new ServiceDescription();
				sd.setType("shop-list");
				template.addServices(sd);
				
					
					try {
						result = DFService.search(myAgent, template);
					} catch (FIPAException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					myAgent.addBehaviour(new RequestItem());
					addBehaviour(new OneShotBehaviour() {
						
						@Override
						public void action() {
							// TODO Auto-generated method stub
					
								sumFiles();
							
						}
					});
			}	
	});
}
	public void sumFiles(){
		OntModel sumModel = ModelFactory.createOntologyModel();
		final String FILE_ENCODE = "UTF-8";
		String files;
		File folder=new File("owl");
		File[] listFiles=folder.listFiles();
		System.out.println("Found All owl files in data folder :");
		for (int i=0;i<listFiles.length;i++){
			if (listFiles[i].isFile()) 
			   {
			   files = listFiles[i].getName();
			   if (files.endsWith(".owl") || files.endsWith(".OWL")){
				   
			   System.out.println("- "+files);
			   InputStream in= FileManager.get().open("owl/"+files);
			   
			  sumModel.read(in,"RDF/XML-ABBREV");
			  try {
				in.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   }
					      } 
		}
		
		
		System.out.println("----------------------------------------------------------");
		if (!locationOwl.isEmpty()){
			File sumFile = new File(locationOwl,"sumOwl.owl");
			if(!sumFile.exists()) {
			    
					try {
						sumFile.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} 
			
			FileOutputStream fo = null;
			try {
				fo = new FileOutputStream(sumFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			OutputStreamWriter osw = null;
			try {
				osw = new OutputStreamWriter(fo,FILE_ENCODE);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sumModel.write(osw,"RDF/XML-ABBREV"); 
			try {
				osw.close();
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void CreateFile(String name,String data,String loc) {
		
		File yourFile = new File(loc,name+".owl");
		
				if(!yourFile.exists()) {
		    
				try {
					yourFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} 		
		
		
		FileOutputStream oFile = null;
		try {
			oFile = new FileOutputStream(yourFile,false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		PrintWriter printwrite;
		try {
			printwrite = new PrintWriter(new OutputStreamWriter(oFile, "UTF-8"));
			
			printwrite.println(data);
			
			printwrite.close();
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			oFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class RequestItem extends Behaviour {
private int step=0;
private MessageTemplate mt; // The template to receive replies
private int repliesCnt = 0;
		@Override
		public void action() {
			// TODO Auto-generated method stub
			switch (step) {
			case 0: 
				System.out.println(new Date());
				System.out.println("Found all shops are in connecting :");
				shopAgents = new AID[result.length];
				
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				for (int i = 0; i < result.length; ++i) {
					shopAgents[i] = result[i].getName();
					int firstPos = shopAgents[i].getName().toString().indexOf("@");
					int lastPos = shopAgents[i].getName().toString().indexOf(":");
					String shopIP = shopAgents[i].getName().toString().substring(firstPos+1, lastPos);
					
					
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new FileReader(new File("membershops.txt")));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    //The StringBuffer will be used to create a string if your file has multiple lines
				    StringBuffer sb = new StringBuffer();
				    String line;

				    try {
						while((line = reader.readLine())!= null)
						{
						    sb.append(line);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				    //We now split the line on the "," to get a string array of the values
				   String[] memberIP = sb.toString().split(",");
				   
				    for (int j=0;j<memberIP.length;++j) {
					if (shopIP.equals(memberIP[j])) {
					System.out.println("- "+shopAgents[i].getName());
					
					msg.addReceiver(shopAgents[i]);
					} 
				    }
				}
				msg.setContent("Test");
							
				msg.setSender(getAID());
				send(msg);
				step++;
				break;
			case 1:
				ACLMessage reply = myAgent.receive();
				
				
				if (reply!=null) {
					
					reply.getEncoding();
					myclass=new RequestAgent();
					myclass.CreateFile(reply.getSender().getLocalName(),reply.getContent(),"owl");
					
					} else {
						block();
					}
				
				step++;
				break;
			}
		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return step==2;
		}
		
	}
}
