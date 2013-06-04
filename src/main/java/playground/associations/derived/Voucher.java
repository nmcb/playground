package playground.associations.derived;

import java.util.HashMap;
import java.util.Map;

public class Voucher {

    private String name;

    private Double value;

    private transient Map<Quote, Double> appliedTo = new HashMap<Quote, Double>();

    // GSON PP
    private Map<String, Double> applications = new HashMap<String, Double>();

    public Voucher(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public Double getRemainingValue() {
        double remaining = getValue();
        for (Double applied : appliedTo.values()) {
            remaining -= applied;
        }
        return remaining;
    }

    public boolean hasRemainingValue() {
        return getRemainingValue() > 0;
    }

    public Double getAppliedValue() {
        return getValue() - getRemainingValue();
    }

    public Double hasAppliedTo(Quote quote) {
        return appliedTo.containsKey(quote) ? appliedTo.get(quote) : new Double(0);
    }

    protected void applies(Quote quote, Double value) {
        appliedTo.put(quote, value);

        // GSON PP
        applications.put(quote.getName(), value);
    }
}
