package www.wcy.wat.dsk.components.gates;

import java.util.List;

import www.wcy.wat.dsk.components.events.FtaAbstractEvent;
import www.wcy.wat.dsk.utils.FTaAnalyzerUtils;

import com.mxgraph.analysis.mxAnalysisGraph;
import com.mxgraph.model.mxGeometry;

/*
 * name = AND
 */
public class AndGate extends FtaAbstractGate {
	private static final long serialVersionUID = -6735302398895779700L;

	public AndGate(Object value, mxGeometry geometry, String style) {
		super(value, geometry, style);
	}

	public void executeLogic(mxAnalysisGraph aGraph) {
		double andProbability = 1.0d;
		List<Object> children = FTaAnalyzerUtils.getAllChildrenForCurrentNode(this, aGraph);
		
		for (Object child : children) {
			if (child instanceof FtaAbstractEvent) {
				andProbability *= ((FtaAbstractEvent) child).getProbability();
				System.out.println("and temp: " + andProbability);
			}
		}
		this.setProbability(andProbability);
		System.out.println("Wyliczono prawd. bramki AND = " + getProbability()
				+ " z liczb¹ wejœæ = " + children.size());
	}

}
