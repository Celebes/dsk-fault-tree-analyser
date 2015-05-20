package www.wcy.wat.dsk.core;

import java.util.ArrayList;
import java.util.List;

import www.wcy.wat.dsk.components.events.FtaAbstractEvent;
import www.wcy.wat.dsk.components.events.FtaEvent;
import www.wcy.wat.dsk.components.events.IntermediateEvent;
import www.wcy.wat.dsk.components.gates.FtaAbstractGate;
import www.wcy.wat.dsk.components.gates.FtaGate;
import www.wcy.wat.dsk.utils.CommonVariables;
import www.wcy.wat.dsk.utils.FTaAnalyzerUtils;
import www.wcy.wat.dsk.utils.GuiBuilderUtils;

import com.mxgraph.analysis.mxAnalysisGraph;
import com.mxgraph.model.mxCell;

public class FTAnalyzer {

	public static boolean analyzeFTA(mxAnalysisGraph aGraph) {

		// vertices = wierzcholki
		Object[] vertices = aGraph.getChildVertices(aGraph.getGraph()
				.getDefaultParent());
		int vertexNum = vertices.length;

		if (vertexNum == 0) {
			JDialogHelper.showDialog(TitleType.ERROR, "Brak wierzcho³ków");
			throw new IllegalArgumentException();
		}

		// znajdz korzen
		List<mxCell> liscieGrafu = new ArrayList<mxCell>();
		mxCell korzen = null;
		Object currVertex = null;

		for (int i = 0; i < vertexNum; i++) {
			currVertex = vertices[i];
			int inEdgeCount = aGraph.getEdges(currVertex, null, true, false,
					false, true).length;
			int outEdgeCount = aGraph.getEdges(currVertex, null, false, true,
					false, true).length;

			System.out.println("cell = " + (mxCell) currVertex + " | in= "
					+ inEdgeCount + " | out=" + outEdgeCount);

			if (outEdgeCount == 0 && inEdgeCount > 0) {
				korzen = (mxCell) currVertex;
			} else if (outEdgeCount == 1 && inEdgeCount == 0) {
				liscieGrafu.add((mxCell) currVertex);
			}
		}

		System.out.println("korzen = " + korzen);
		double resultFta = calculateFTA(currVertex, aGraph);
		
		// create result
		StringBuilder resultBuild = new StringBuilder();
		GuiBuilderUtils.generateHTMLResultFTA(vertices, resultFta, resultBuild);
		JDialogHelper.showDialog(TitleType.INFO, resultBuild.toString());

		return false;
	}

	/**
	 * Calculate FTA probability.
	 * 
	 * @param root
	 * @param aGraph
	 */
	public static double calculateFTA(Object root, mxAnalysisGraph aGraph) {
		System.out.println("Calculate FTA");
		System.out.println("Current Probability");
		double result = 0.0d;

		if (root instanceof FtaAbstractEvent) {
			if (FTaAnalyzerUtils.eventHasPrevoiusNode(root, aGraph)) {
				((FtaAbstractEvent) root)
						.transitionProbabiltyFromPreviousNode(aGraph);
			}
			result = ((FtaAbstractEvent) root).getProbability();
			System.out.println("Event Probability: " + result);
		} else if (root instanceof FtaGate) {
			((FtaAbstractGate) root).executeLogic(aGraph);
			result = ((FtaAbstractGate) root).getProbability();
			System.out.println("Gate Probability: " + result);

		}

		Object[] inRootEdges = aGraph.getEdges(root, null, true, false, false,
				true);
		int inRootEdgeCount = inRootEdges.length;
		System.out.println("root cell = " + (mxCell) root + " | in= "
				+ inRootEdgeCount);

		for (Object currentEdge : inRootEdges) {
			//System.out.println((mxCell) currentEdge);
			//System.out.println(((mxCell) currentEdge).getTarget());
			//System.out.println(((mxCell) currentEdge).getSource());
			calculateFTA(((mxCell) currentEdge).getSource(), aGraph);
		}

		return result;
	}

}
