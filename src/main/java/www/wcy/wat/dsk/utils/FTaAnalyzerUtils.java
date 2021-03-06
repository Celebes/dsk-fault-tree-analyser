package www.wcy.wat.dsk.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.mxgraph.analysis.mxAnalysisGraph;
import com.mxgraph.model.mxCell;

public class FTaAnalyzerUtils {

	/**
	 * Get all children for current node.
	 * 
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

	/**
	 * Return true if event has previous node or false if event is basic event
	 * without sources.
	 * 
	 * @param evevnt
	 * @param aGraph
	 * @return
	 */
	public static boolean eventHasPrevoiusNode(Object evevnt,
			mxAnalysisGraph aGraph) {
		Object[] inCurrentNodeEdges = aGraph.getEdges(evevnt, null, true,
				false, false, true);
		if (inCurrentNodeEdges.length > 0)
			return true;
		else
			return false;

	}

	/**
	 * Round primitive double probability for view.
	 * 
	 * @param probability
	 * @return
	 */
	public static String roundProbabilityForView(double probability) {
		BigDecimal roundedProbability = new BigDecimal(probability).setScale(5, RoundingMode.HALF_DOWN);
		return roundedProbability.toString();

	}
}
