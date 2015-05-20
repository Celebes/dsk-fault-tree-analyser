package www.wcy.wat.dsk.utils;

import www.wcy.wat.dsk.components.events.FtaAbstractEvent;

import com.mxgraph.model.mxCell;

/**
 * Created by Krzysztof Jedynak on 2015-05-18.
 */
public class GuiBuilderUtils {

	/**
	 * Generate HTML view result for FTA problem.
	 * 
	 * @param vertices
	 * @param resultFTA
	 * @param message
	 */
	public static void generateHTMLResultFTA(Object[] vertices,
			double resultFTA, StringBuilder message) {

		message.append(CommonVariables.HTML_TAG_OPEN
				+ CommonVariables.HTML_TAG_TABLE_OPEN
				+ CommonVariables.HTML_TAG_TABLE_ROW_OPEN
				+ CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN
				+ CommonVariables.HTML_TAG_STRONG_OPEN
				+ CommonVariables.FTA_RESULT
				+ CommonVariables.HTML_TAG_STRONG_CLOSED
				+ CommonVariables.HTML_TAG_TABLE_COLUMN_CLOSED
				+ CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN + resultFTA
				+ CommonVariables.HTML_TAG_TABLE_COLUMN_CLOSED
				+ CommonVariables.HTML_TAG_TABLE_ROW_CLOSED);

		StringBuilder labelResult = new StringBuilder();
		labelResult.append(CommonVariables.HTML_TAG_TABLE_ROW_OPEN);
		labelResult.append(CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN);
		labelResult.append(CommonVariables.HTML_TAG_STRONG_OPEN);
		labelResult.append(CommonVariables.NODE_NAME);
		labelResult.append(CommonVariables.HTML_TAG_STRONG_CLOSED);
		labelResult.append(CommonVariables.HTML_TAG_TABLE_COLUMN_CLOSED);
		labelResult.append(CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN);
		labelResult.append(CommonVariables.HTML_TAG_STRONG_OPEN);
		labelResult.append(CommonVariables.NODE_PROBABILITY);
		labelResult.append(CommonVariables.HTML_TAG_STRONG_CLOSED);

		// labelResult.append(CommonVariables.HTML_TAG_TABLE_CLOSED);

		message.append(labelResult);

		for (Object vertex : vertices) {
			if (vertex instanceof FtaAbstractEvent) {
				StringBuilder vertexInfoRow = new StringBuilder();
				vertexInfoRow.append(CommonVariables.HTML_TAG_TABLE_ROW_OPEN);
				String vertexName = (String) ((mxCell) vertex).getValue();
				String vertexProbability = ""
						+ ((FtaAbstractEvent) vertex).getProbability();
				vertexInfoRow
						.append(CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN);
				vertexInfoRow.append(vertexName);
				vertexInfoRow
						.append(CommonVariables.HTML_TAG_TABLE_COLUMN_CLOSED);
				vertexInfoRow
						.append(CommonVariables.HTML_TAG_TABLE_COLUMN_OPEN);
				vertexInfoRow.append(vertexProbability);
				vertexInfoRow
						.append(CommonVariables.HTML_TAG_TABLE_COLUMN_CLOSED);
				vertexInfoRow.append(CommonVariables.HTML_TAG_TABLE_ROW_CLOSED);
				message.append(vertexInfoRow);

			}

		}

		message.append(CommonVariables.HTML_TAG_TABLE_CLOSED);
		message.append(CommonVariables.HTML_TAG_CLOSED);

	}
}
