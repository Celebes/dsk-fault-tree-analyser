package www.wcy.wat.dsk.components.gates;

import www.wcy.wat.dsk.components.sources.FtaAbstractNode;
import www.wcy.wat.dsk.components.sources.FtaNodeType;

public abstract class FtaAbstractGate extends FtaAbstractNode implements FtaGate {
	private static final long serialVersionUID = -2805077675566363162L;
	protected double probability;
	
	public FtaAbstractGate() {
		this.ftaNodeType = FtaNodeType.GATE;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}
	
}
