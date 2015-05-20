package www.wcy.wat.dsk.components.events;

import java.util.List;

import www.wcy.wat.dsk.components.gates.FtaAbstractGate;
import www.wcy.wat.dsk.components.sources.FtaAbstractNode;
import www.wcy.wat.dsk.components.sources.FtaNodeType;
import www.wcy.wat.dsk.utils.FTAUtils;

import com.mxgraph.analysis.mxAnalysisGraph;
import com.mxgraph.model.mxGeometry;

public abstract class FtaAbstractEvent extends FtaAbstractNode implements
		FtaEvent {
	private static final long serialVersionUID = -476387158373440699L;
	protected double probability;

	public FtaAbstractEvent(Object value, mxGeometry geometry, String style) {
		super(value, geometry, style);
		this.ftaNodeType = FtaNodeType.EVENT;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	/**
	 * Set probability from previous node if event isn't basic event.
	 * 
	 * @param aGraph
	 */
	public void transitionProbabiltyFromPreviousNode(mxAnalysisGraph aGraph) {
		List<Object> children = FTAUtils.getAllChildrenForCurrentNode(this,
				aGraph);
		if (!children.isEmpty()) {
			this.probability = ((FtaAbstractGate) children.get(0))
					.getProbability();
		}
	}

}