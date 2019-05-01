import javax.swing.JFrame;


import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import jade.wrapper.StaleProxyException;


public class HostAgent extends Agent {
protected JFrame m_frame = null;
PlatformController container;
AgentController guest;
	public void setup(){
		setupUI();
	}
	private void setupUI() {
        m_frame = new MainGui(this);
        m_frame.setVisible( true );
       
    }
	public void createAgent() {
		container = getContainerController();
		
		try {
			guest = container.createNewAgent("RequestAgent", "RequestAgent",null);
		
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //สร้าง agent แบบยังไม่รัน
			try {
				guest.start();
			} catch (StaleProxyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //รันให้ agent ทำงาน
			
	
	}
	public void endAgent(){

 try {
	guest.kill();
	
} catch (StaleProxyException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
}
