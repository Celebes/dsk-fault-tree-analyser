package www.wcy.wat.dsk.components.gates;

import www.wcy.wat.dsk.components.events.FtaAbstractEvent;

import com.mxgraph.model.mxGeometry;

/*
 * name = AND
 */
public class AndGate extends FtaAbstractGate {
	private static final long serialVersionUID = -6735302398895779700L;

	public AndGate(Object value, mxGeometry geometry, String style) {
		super(value, geometry, style);
	}

	public void executeLogic() {
		double andProbability = 1.0d;
		for (Object child : super.children) {
			if (child instanceof FtaAbstractEvent) {
				andProbability *= ((FtaAbstractEvent) child).getProbability();
			}
		}
		this.setProbability(andProbability);
		System.out.println("Wyliczono prawd. bramki AND = " + getProbability()
				+ " z liczb¹ wejœæ = " + super.getChildCount());
	}

}
