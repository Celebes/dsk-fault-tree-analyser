package www.wcy.wat.dsk.components.sources;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import www.wcy.wat.dsk.components.events.FtaEvent;
import www.wcy.wat.dsk.core.ProbabilityDialog;

import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.view.mxGraph;

public class CustomGraphActions extends mxGraphActions {
	
	static final Action setProbabilityAction = new SetProbabilityAction("setProbability");

	public static class SetProbabilityAction extends AbstractAction {
		private static final long serialVersionUID = -794399835353378305L;
		
		public SetProbabilityAction(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			mxGraph graph = getGraph(e);
			FtaEvent ftaEvent = (FtaEvent)graph.getSelectionCell();
			//System.out.println("ftaEvent = " + ftaEvent);
			//System.out.println("ftaEvent prob = " + ftaEvent.getProbability());
			if(graph != null) {
				System.out.println(graph.getSelectionCell());
				new ProbabilityDialog(ftaEvent);
			}
		}
	}
	
	public static Action getSetProbabilityAction() {
		return setProbabilityAction;
	}
}
