package www.wcy.wat.dsk.components.events;

import www.wcy.wat.dsk.components.sources.FtaAbstractNode;
import www.wcy.wat.dsk.components.sources.FtaNodeType;

public abstract class FtaAbstractEvent extends FtaAbstractNode implements FtaEvent {
	private static final long serialVersionUID = -476387158373440699L;
	protected double probability;
	
	public FtaAbstractEvent() {
		this.ftaNodeType = FtaNodeType.EVENT;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}
	
}