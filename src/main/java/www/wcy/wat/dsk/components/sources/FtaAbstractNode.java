package www.wcy.wat.dsk.components.sources;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

public class FtaAbstractNode extends mxCell implements FtaNode {
	protected FtaNodeType ftaNodeType;
	
	public FtaAbstractNode(Object value, mxGeometry geometry, String style) {
		super(value, geometry, style);
	}

	public FtaNodeType getFtaNodeType() {
		return ftaNodeType;
	}

	public void setFtaNodeType(FtaNodeType ftaNodeType) {
		this.ftaNodeType = ftaNodeType;
	}
}
