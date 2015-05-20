package www.wcy.wat.dsk.components.gates;

import java.util.List;

import www.wcy.wat.dsk.components.events.FtaAbstractEvent;
import www.wcy.wat.dsk.utils.FTAUtils;

import com.mxgraph.analysis.mxAnalysisGraph;
import com.mxgraph.model.mxGeometry;

/*
 * name = OR
 */
public class OrGate extends FtaAbstractGate {
	private static final long serialVersionUID = -3355073339051692635L;

	public OrGate(Object value, mxGeometry geometry, String style) {
		super(value, geometry, style);
	}
	
	public void executeLogic(mxAnalysisGraph aGraph) {
		double orProbability = 0.0d;
		double andProbability = 1.0d;
		List<Object> children = FTAUtils.getAllChildrenForCurrentNode(this, aGraph);
		
		for(Object child : children){
			if(child instanceof FtaAbstractEvent){
				orProbability += ((FtaAbstractEvent) child).getProbability();
				andProbability *= ((FtaAbstractEvent) child).getProbability();
				System.out.println("or temp: " + orProbability);
				System.out.println("and temp: " + andProbability);
			}
		}
		this.setProbability(orProbability - andProbability);
		System.out.println("Wyliczono prawd. bramki OR = " + getProbability()
				+ " z liczb¹ wejœæ = " + children.size());
	}
	
}
