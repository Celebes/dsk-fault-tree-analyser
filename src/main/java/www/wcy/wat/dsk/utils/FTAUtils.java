package www.wcy.wat.dsk.utils;

import java.util.ArrayList;
import java.util.List;

import com.mxgraph.analysis.mxAnalysisGraph;
import com.mxgraph.model.mxCell;

public class FTAUtils {

	/**
	 * Get all children for current node.
	 * @param currentNode
	 * @param aGraph
	 * @return
	 */
	public static List<Object> getAllChildrenForCurrentNode(Object currentNode,
			mxAnalysisGraph aGraph) {

		Object[] inCurrentNodeEdges = aGraph.getEdges(currentNode, null, true,
				false, false, true);
		List<Object> children = new ArrayList<Object>();
		
		for (Object currentEdge : inCurrentNodeEdges) {
			children.add(((mxCell) currentEdge).getSource());
		}
		
		return children;
	}

}
