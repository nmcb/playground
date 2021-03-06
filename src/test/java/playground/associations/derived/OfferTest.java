package playground.associations.derived;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OfferTest {

    @Test
    public void getUnfulfilledValue() {
        Offer offer = new Offer(100);
        offer.addQuotes(25, 25, 25, 5);

        assertEquals(new Double(100), offer.getUnfulfilledValue());

        offer.addVouchers(30);
        assertEquals(new Double(70), offer.getUnfulfilledValue());

        offer.addVouchers(30);
        assertEquals(new Double(40), offer.getUnfulfilledValue());

        offer.addVouchers(30);
        assertEquals(new Double(25), offer.getUnfulfilledValue());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.print(gson.toJson(offer));
    }
}
