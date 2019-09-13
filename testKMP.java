import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Author Philipp Smponias
 */
public class testKMP {
    KnuthMorrisPratt k1;
    String prefix = "lalelullale";

    @Before
    public void initial(){
        k1 = new KnuthMorrisPratt("lalelulalelullalulalelullalel");
        k1.init(prefix);
    }

    @Test
    public void hasPrefix() throws Exception {
        assertEquals(17,k1.checkPrefix(prefix));
    }

    @Test(expected = NoSuchPrefixException.class)
    public void noPrefix() throws  Exception {
        k1.checkPrefix("Hallo");
    }


    @After
    public void clean(){
        k1 = null;
    }

}
