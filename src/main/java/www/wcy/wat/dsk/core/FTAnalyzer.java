package www.wcy.wat.dsk.core;

import java.util.ArrayList;
import java.util.List;

import www.wcy.wat.dsk.components.events.FtaAbstractEvent;
import www.wcy.wat.dsk.components.events.FtaEvent;
import www.wcy.wat.dsk.components.events.IntermediateEvent;
import www.wcy.wat.dsk.components.gates.FtaAbstractGate;
import www.wcy.wat.dsk.components.gates.FtaGate;
import www.wcy.wat.dsk.utils.CommonVariables;
import www.wcy.wat.dsk.utils.FTAUtils;

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
		StringBuilder resultBuild = new StringBuilder();
		double resultFta = calculateFTA(currVertex, aGraph);
		resultBuild.append(CommonVariables.HTML_TAG_OPEN
				+ CommonVariables.HTML_TAG_TABLE_OPEN
				+ CommonVariables.HTML_TAG_TABLE_ROW_OPEN
				+ CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN
				+ "FTA Result"
				+ CommonVariables.HTML_TAG_TABLE_COLUMN_CLOSES
				+ CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN
				+ resultFta
				+ CommonVariables.HTML_TAG_TABLE_COLUMN_CLOSES
				+ CommonVariables.HTML_TAG_TABLE_ROW_CLOSES);
		
		StringBuilder labelResult = new StringBuilder();
		labelResult.append(CommonVariables.HTML_TAG_TABLE_ROW_OPEN);
		labelResult.append(CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN);
		labelResult.append(CommonVariables.NODE_NAME);
		labelResult.append(CommonVariables.HTML_TAG_TABLE_COLUMN_CLOSES);
		labelResult.append(CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN);
		labelResult.append(CommonVariables.NODE_PROBABILITY);
		//labelResult.append(CommonVariables.HTML_TAG_TABLE_CLOSED);
		
		resultBuild.append(labelResult);

		for (Object vertex : vertices) {
			if (vertex instanceof FtaAbstractEvent) {
				StringBuilder vertexInfoRow = new StringBuilder();
				vertexInfoRow.append(CommonVariables.HTML_TAG_TABLE_ROW_OPEN);
				String vertexName = (String) ((mxCell) vertex).getValue();
				String vertexProbability = ""
						+ ((FtaAbstractEvent) vertex).getProbability();
				vertexInfoRow.append(CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN);
				vertexInfoRow.append(vertexName);
				vertexInfoRow.append(CommonVariables.HTML_TAG_TABLE_COLUMN_CLOSES);
				vertexInfoRow.append(CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN);	
				vertexInfoRow.append(vertexProbability);
				vertexInfoRow.append(CommonVariables.HTML_TAG_TABLE_COLUMN_CLOSES);
				vertexInfoRow.append(CommonVariables.HTML_TAG_TABLE_ROW_CLOSES);
				resultBuild.append(vertexInfoRow);
				
			}

		}
		
		resultBuild.append(CommonVariables.HTML_TAG_TABLE_CLOSED);
/*		resultBuild.append("<table border=\"1\" style=\"width:100%\">");
		resultBuild.append("<tr>");
		resultBuild.append("<td>Jill</td>");
		resultBuild.append("<td>Smith</td> ");
		resultBuild.append("<td>50</td>");
		resultBuild.append("</tr>");
		resultBuild.append("<tr>");
		resultBuild.append("<td>Eve</td>");
		resultBuild.append("<td>Jackson</td> ");
		resultBuild.append("<td>94</td>");
		resultBuild.append("</tr>");
		resultBuild.append("</table>");*/
		resultBuild.append(CommonVariables.HTML_TAG_CLOSED);
		JDialogHelper.showDialog(TitleType.INFO, resultBuild.toString());

		return false;
	}

	// przykladowa metoda na ktorej mozna sie wzorowac - do pozniejszego
	// usuniecia
	// pochodzi z klasy mxGraphStructure

	public static boolean isConnected(mxAnalysisGraph aGraph) {
		Object[] vertices = aGraph.getChildVertices(aGraph.getGraph()
				.getDefaultParent());
		int vertexNum = vertices.length;

		if (vertexNum == 0) {
			throw new IllegalArgumentException();
		}

		// data preparation
		int connectedVertices = 1;
		int[] visited = new int[vertexNum];
		visited[0] = 1;

		for (int i = 1; i < vertexNum; i++) {
			visited[i] = 0;
		}

		ArrayList<Object> queue = new ArrayList<Object>();
		queue.add(vertices[0]);

		// repeat the algorithm until the queue is empty
		while (queue.size() > 0) {
			// cut out the first vertex
			Object currVertex = queue.get(0);
			queue.remove(0);

			// fill the queue with neighboring but unvisited vertexes
			Object[] neighborVertices = aGraph.getOpposites(
					aGraph.getEdges(currVertex, null, true, true, false, true),
					currVertex, true, true);

			for (int j = 0; j < neighborVertices.length; j++) {
				// get the index of the neighbor vertex
				int index = 0;

				for (int k = 0; k < vertexNum; k++) {
					if (vertices[k].equals(neighborVertices[j])) {
						index = k;
					}
				}

				if (visited[index] == 0) {
					queue.add(vertices[index]);
					visited[index] = 1;
					connectedVertices++;
				}
			}
		}

		// if we visited every vertex, the graph is connected
		if (connectedVertices == vertexNum) {
			return true;
		} else {
			return false;
		}
	};

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
			if (FTAUtils.eventHasPrevoiusNode(root, aGraph)) {
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
			System.out.println((mxCell) currentEdge);
			System.out.println(((mxCell) currentEdge).getTarget());
			System.out.println(((mxCell) currentEdge).getSource());
			calculateFTA(((mxCell) currentEdge).getSource(), aGraph);
		}

		return result;
	}

}
