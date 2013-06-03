package playground;

import static junit.framework.Assert.*;
import org.junit.Test;

public class QuoteTest {

    @Test
    public void testVoucherApplication() {

        Quote quote = new Quote("quote", 100);
        assertEquals(new Double(100), quote.getUnfulfilledValue());

        quote.apply(new Voucher("v1", 50));
        assertEquals(new Double(50), quote.getUnfulfilledValue());

        quote.apply(new Voucher("v2", 50));
        assertEquals(new Double(0), quote.getUnfulfilledValue());
    }
}
