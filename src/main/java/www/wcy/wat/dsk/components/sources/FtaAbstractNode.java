package www.wcy.wat.dsk.components.sources;

import com.mxgraph.model.mxCell;

public class FtaAbstractNode extends mxCell implements FtaNode {
	protected FtaNodeType ftaNodeType;

	public FtaNodeType getFtaNodeType() {
		return ftaNodeType;
	}

	public void setFtaNodeType(FtaNodeType ftaNodeType) {
		this.ftaNodeType = ftaNodeType;
	}
}
