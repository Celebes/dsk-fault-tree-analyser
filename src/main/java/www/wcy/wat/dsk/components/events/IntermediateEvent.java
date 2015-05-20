package www.wcy.wat.dsk.components.events;

import java.util.List;

import www.wcy.wat.dsk.components.gates.FtaAbstractGate;
import www.wcy.wat.dsk.utils.FTAUtils;

import com.mxgraph.analysis.mxAnalysisGraph;
import com.mxgraph.model.mxGeometry;

/*
 * name = Poœrednie
 */
public class IntermediateEvent extends FtaAbstractEvent {
	private static final long serialVersionUID = 4968404833127810326L;
	
	public IntermediateEvent(Object value, mxGeometry geometry, String style) {
		super(value, geometry, style);
	}
	
	public void setIntermediateProbability(mxAnalysisGraph aGraph){
		List<Object> children = FTAUtils.getAllChildrenForCurrentNode(this, aGraph);
		if(!children.isEmpty()){
			this.probability = ((FtaAbstractGate) children.get(0)).getProbability();
		}
	}
}
