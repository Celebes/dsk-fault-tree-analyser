package www.wcy.wat.dsk.core;

import java.util.ArrayList;

import com.mxgraph.analysis.mxAnalysisGraph;

public class FTAnalyzer {

	public static boolean analyzeFTA(mxAnalysisGraph aGraph) {
		
		// vertices = wierzcholki
		Object[] vertices = aGraph.getChildVertices(aGraph.getGraph().getDefaultParent());
		int vertexNum = vertices.length;
		
		if (vertexNum == 0)
		{
			JDialogHelper.showDialog(TitleType.ERROR, "Brak wierzcho³ków");
			throw new IllegalArgumentException();
		}
		
		System.out.println("liczba wierzcholkow = " + vertexNum);
		
		return false;
	}
	
	// przykladowa metoda na ktorej mozna sie wzorowac - do pozniejszego usuniecia
	// pochodzi z klasy mxGraphStructure
	
	public static boolean isConnected(mxAnalysisGraph aGraph)
	{
		Object[] vertices = aGraph.getChildVertices(aGraph.getGraph().getDefaultParent());
		int vertexNum = vertices.length;

		if (vertexNum == 0)
		{
			throw new IllegalArgumentException();
		}

		//data preparation
		int connectedVertices = 1;
		int[] visited = new int[vertexNum];
		visited[0] = 1;

		for (int i = 1; i < vertexNum; i++)
		{
			visited[i] = 0;
		}

		ArrayList<Object> queue = new ArrayList<Object>();
		queue.add(vertices[0]);

		//repeat the algorithm until the queue is empty
		while (queue.size() > 0)
		{
			//cut out the first vertex
			Object currVertex = queue.get(0);
			queue.remove(0);

			//fill the queue with neighboring but unvisited vertexes
			Object[] neighborVertices = aGraph.getOpposites(aGraph.getEdges(currVertex, null, true, true, false, true), currVertex, true,
					true);

			for (int j = 0; j < neighborVertices.length; j++)
			{
				//get the index of the neighbor vertex
				int index = 0;

				for (int k = 0; k < vertexNum; k++)
				{
					if (vertices[k].equals(neighborVertices[j]))
					{
						index = k;
					}
				}

				if (visited[index] == 0)
				{
					queue.add(vertices[index]);
					visited[index] = 1;
					connectedVertices++;
				}
			}
		}

		// if we visited every vertex, the graph is connected
		if (connectedVertices == vertexNum)
		{
			return true;
		}
		else
		{
			return false;
		}
	};
	
}
