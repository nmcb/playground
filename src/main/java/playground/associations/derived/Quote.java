package playground.associations.derived;

import java.util.HashSet;
import java.util.Set;

public class Quote {

    private String name;

    private Double value;

    private Set<Voucher> appliedVouchers = new HashSet<Voucher>();

    public Quote(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public void apply(Voucher voucher) {
        if (getUnfulfilledValue() <= 0) {
            throw new RuntimeException("value already fullfilled");
        }

        if (voucher.getRemainingValue() <= 0) {
            throw new RuntimeException("contains no remaining value");
        }

        appliedVouchers.add(voucher);
        voucher.applies(this, Math.min(getUnfulfilledValue(), voucher.getRemainingValue()));
    }

    public boolean hasAppliedVoucher(Voucher voucher) {
        return appliedVouchers.contains(voucher);
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public Double getUnfulfilledValue() {
        Double unfulfilled = getValue();
        for (Voucher voucher : appliedVouchers) {
            unfulfilled -= voucher.hasAppliedTo(this);
        }
        return unfulfilled;
    }
}
