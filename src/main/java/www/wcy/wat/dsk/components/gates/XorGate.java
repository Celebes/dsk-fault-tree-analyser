package www.wcy.wat.dsk.components.gates;

import www.wcy.wat.dsk.components.events.FtaAbstractEvent;

import com.mxgraph.model.mxGeometry;

/*
 * name = XOR
 */
public class XorGate extends FtaAbstractGate {
	private static final long serialVersionUID = -1340190658542044938L;

	public XorGate(Object value, mxGeometry geometry, String style) {
		super(value, geometry, style);
	}
	
	public void executeLogic() {
		double orProbability = 0.0d;
		double andProbability = 1.0d;
		for(Object child : super.children){
			if(child instanceof FtaAbstractEvent){
				orProbability += ((FtaAbstractEvent) child).getProbability();
				andProbability *= ((FtaAbstractEvent) child).getProbability();
			}
		}
		this.setProbability(orProbability - 2*andProbability);
		System.out.println("Wyliczono prawd. bramki XOR = " + getProbability()
				+ " z liczb¹ wejœæ = " + super.getChildCount());
	}
	
}
