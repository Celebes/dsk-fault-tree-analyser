package com.mxgraph.examples.swing.editor;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.TransferHandler;

import www.wcy.wat.dsk.components.events.FtaAbstractEvent;
import www.wcy.wat.dsk.components.sources.CustomGraphActions;

import com.mxgraph.examples.swing.editor.EditorActions.HistoryAction;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxResources;

public class EditorPopupMenu extends JPopupMenu
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3132749140550242191L;

	public EditorPopupMenu(BasicGraphEditor editor)
	{
		boolean selected = !editor.getGraphComponent().getGraph()
				.isSelectionEmpty();
		
		boolean oneSelected = (editor.getGraphComponent().getGraph().getSelectionCount() == 1);
		
		boolean probabilityCanBeSet = false;
		
		if(selected && oneSelected) {
			Object selectedCell = editor.getGraphComponent().getGraph().getSelectionCell();
			if(selectedCell instanceof FtaAbstractEvent) {
				mxCell mxSelectedCell = (mxCell) selectedCell;
				if(mxSelectedCell.getEdgeCount() == 1) {
					probabilityCanBeSet = true;		// ustaw p-stwo tylko dla lisci
				}
			}
		}

		add(editor.bind(mxResources.get("undo"), new HistoryAction(true),
				"/com/mxgraph/examples/swing/images/undo.gif"));

		addSeparator();

		add(
				editor.bind(mxResources.get("cut"), TransferHandler
						.getCutAction(),
						"/com/mxgraph/examples/swing/images/cut.gif"))
				.setEnabled(selected);
		add(
				editor.bind(mxResources.get("copy"), TransferHandler
						.getCopyAction(),
						"/com/mxgraph/examples/swing/images/copy.gif"))
				.setEnabled(selected);
		add(editor.bind(mxResources.get("paste"), TransferHandler
				.getPasteAction(),
				"/com/mxgraph/examples/swing/images/paste.gif"));

		addSeparator();

		add(
				editor.bind(mxResources.get("delete"), mxGraphActions
						.getDeleteAction(),
						"/com/mxgraph/examples/swing/images/delete.gif"))
				.setEnabled(selected);

		addSeparator();

		// Creates the format menu
		JMenu menu = (JMenu) add(new JMenu(mxResources.get("format")));

		EditorMenuBar.populateFormatMenu(menu, editor);

		// Creates the shape menu
		menu = (JMenu) add(new JMenu(mxResources.get("shape")));

		EditorMenuBar.populateShapeMenu(menu, editor);
		
		addSeparator();
		
		/*
		 * USTAWIANIE PRAWDOPODOBIENSTWA
		 */
		add(
				editor.bind(mxResources.get("setProbability"), CustomGraphActions.getSetProbabilityAction())).setEnabled(probabilityCanBeSet);

		/*
		 * ------------------------------
		 */
		addSeparator();

		add(
				editor.bind(mxResources.get("edit"), mxGraphActions
						.getEditAction())).setEnabled(selected);

		addSeparator();

		add(editor.bind(mxResources.get("selectVertices"), mxGraphActions
				.getSelectVerticesAction()));
		add(editor.bind(mxResources.get("selectEdges"), mxGraphActions
				.getSelectEdgesAction()));

		addSeparator();

		add(editor.bind(mxResources.get("selectAll"), mxGraphActions
				.getSelectAllAction()));
	}

}
