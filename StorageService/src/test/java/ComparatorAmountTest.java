import org.junit.Test;

import static org.junit.Assert.*;

public class ComparatorAmountTest {

    @Test
    public void compareDifferentQuantity() {
        Item item = new Item("Monitor", ItemCondition.NEW, 15, 3);
        Item item2 = new Item("PC", ItemCondition.USED, 30, 2);
        ComparatorAmount comparatorAmount = new ComparatorAmount();
        assertEquals(comparatorAmount.compare(item, item2), item.getQuantity() - item2.getQuantity());
    }

    @Test
    public void compareSameQuantity() {
        Item item = new Item("PC", ItemCondition.USED, 30, 2);
        Item item2 = new Item("PC", ItemCondition.USED, 30, 2);
        Item item3 = new Item("TV", ItemCondition.NEW, 30, 4);
        Item item4 = new Item("PC", ItemCondition.USED, 30, 4);
        ComparatorAmount comparatorAmount = new ComparatorAmount();
        assertEquals(comparatorAmount.compare(item, item2), 0);
        assertNotEquals(comparatorAmount.compare(item3, item4), 0);
    }
}