package www.wcy.wat.dsk.components.sources;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.view.mxGraph;

public class customGraphActions extends mxGraphActions {

	public static class SetProbabilityAction extends AbstractAction {
		private static final long serialVersionUID = -794399835353378305L;
		
		public SetProbabilityAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			mxGraph graph = getGraph(e);
			
			if(graph != null) {
				System.out.println(graph.getSelectionCell());
			}
		}
		
	}
}
