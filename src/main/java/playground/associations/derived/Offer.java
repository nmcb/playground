package playground.associations.derived;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Offer {

    private Double totalValue;

    private List<Quote> quotes = new LinkedList<Quote>();

    private List<Voucher> vouchers = new LinkedList<Voucher>();

    private static AtomicInteger quoteCounter = new AtomicInteger(0);

    private static AtomicInteger voucherCounter = new AtomicInteger(0);

    public Offer(double totalValue) {
        this.totalValue = totalValue;
    }

    public void addQuotes(double... quotes) {
        for (double quote : quotes) {
            String name = String.format("quote-%05d", quoteCounter.incrementAndGet());
            this.quotes.add(new Quote(name , quote));
        }
    }

    public void addVouchers(double... vouchers) {
        for (double voucher : vouchers) {
            String name = String.format("voucher-%05d", voucherCounter.incrementAndGet());
            this.vouchers.add(new Voucher(name, voucher));
        }
    }


    public Double getTotalValue() {
        return totalValue;
    }

    public Double getUnfulfilledValue() {
        distributeVouchers();
        double value = getTotalValue();
        for (Voucher voucher : vouchers) {
            value -= voucher.getAppliedValue();
        }
        return value;
    }

    private void distributeVouchers() {
        for (Voucher voucher : vouchers) {
            for (Quote quote : quotes) {
                if (isEligible(voucher, quote)) {
                    quote.apply(voucher);
                    if (!voucher.hasRemainingValue()) {
                        break;
                    }
                }
            }
        }
    }

    private boolean isEligible(Voucher voucher, Quote quote) {
        boolean eligible = true;
        eligible &= voucher.hasRemainingValue();
        eligible &= quote.getUnfulfilledValue() > 0;
        eligible &= !quote.hasAppliedVoucher(voucher);
        eligible &= quote.getValue() > 10;
        return eligible;
    }
}
