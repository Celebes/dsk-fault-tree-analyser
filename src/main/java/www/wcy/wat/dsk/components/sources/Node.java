package www.wcy.wat.dsk.components.sources;

import java.math.BigDecimal;

/**
 * Created by Krzysztof Jedynak on 2015-05-18.
 */
public class Node {

    private String message;
    private BigDecimal probability;

    public Node(String message, BigDecimal probability) {
        this.message = message;
        this.probability = probability;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
