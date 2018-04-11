import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class FulfillmentCenterTest {

    @Test
    public void getCurrentRepositoryCapacity() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 100);
        Item item = new Item("Monitor", ItemCondition.NEW, 15, 3);
        double oldCapacity = fulfillmentCenter.getCurrentRepositoryCapacity();
        fulfillmentCenter.addProduct(item);
        double newCapacity = fulfillmentCenter.getCurrentRepositoryCapacity();
        assertNotEquals(oldCapacity, newCapacity);
        assertTrue(newCapacity == 3);
    }

    @Test
    public void setCurrentRepositoryCapacity() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        double oldCapacity = fulfillmentCenter.getCurrentRepositoryCapacity();
        fulfillmentCenter.setCurrentRepositoryCapacity(100);
        double newCapacity = fulfillmentCenter.getCurrentRepositoryCapacity();
        assertNotEquals(oldCapacity, newCapacity);
        assertTrue(newCapacity == 100);
    }

    @Test
    public void getRepositoryName() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 100);
        assertEquals(fulfillmentCenter.getRepositoryName(), "Magazine");
    }

    @Test
    public void setRepositoryName() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        String oldName = fulfillmentCenter.getRepositoryName();
        fulfillmentCenter.setRepositoryName("Repo");
        String newName = fulfillmentCenter.getRepositoryName();
        assertNotEquals(oldName, newName);
        assertEquals(newName, "Repo");
        assertNotNull(newName);
    }

    @Test
    public void getMaxRepositoryCapacity() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 200);
        double oldCapacity = fulfillmentCenter.getMaxRepositoryCapacity();
        fulfillmentCenter.setMaxRepositoryCapacity(999);
        double newCapacity = fulfillmentCenter.getMaxRepositoryCapacity();
        assertNotEquals(oldCapacity, newCapacity);
        assertTrue(newCapacity == 999);
    }

    @Test
    public void setMaxRepositoryCapacity() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 100);
        double oldCapacity = fulfillmentCenter.getMaxRepositoryCapacity();
        fulfillmentCenter.setMaxRepositoryCapacity(500);
        double newCapacity = fulfillmentCenter.getMaxRepositoryCapacity();
        assertNotEquals(oldCapacity, newCapacity);
        assertTrue(newCapacity == 500);
        assertNotNull(newCapacity);
    }

    @Test
    public void getItems() {
        Item item = new Item("Monitor", ItemCondition.NEW, 15, 3);
        Item item2 = new Item("PC", ItemCondition.USED, 30, 2);
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        fulfillmentCenter.items.add(item);
        fulfillmentCenter.items.add(item2);
        List<Item> actual = fulfillmentCenter.getItems();
        List<Item> expected = new ArrayList<>();
        expected.add(item);
        expected.add(item2);
        assertEquals(actual, expected);
        assertThat(actual, is(expected));
    }

    @Test
    public void setItems() {
        Item item = new Item("TV", ItemCondition.NEW, 15, 3);
        Item item2 = new Item("PC", ItemCondition.USED, 30, 2);
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        List<Item> actual = new ArrayList<>();
        actual.add(item);
        actual.add(item2);
        List<Item> newList = fulfillmentCenter.getItems();
        assertNotEquals(actual, newList);
        fulfillmentCenter.setItems(actual);
        newList = fulfillmentCenter.getItems();
        assertEquals(actual, newList);
    }

    @Test
    public void addProduct() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        fulfillmentCenter.setMaxRepositoryCapacity(1);
        Item item = new Item("PC", ItemCondition.USED, 30, 2);
        Item item2 = new Item("PC", ItemCondition.USED, 30, 2);
        String expectedOutput = "Failed to add item because You exceeded maximum repository capacity!" + System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        fulfillmentCenter.addProduct(item);
        assertEquals(expectedOutput, outContent.toString());

        fulfillmentCenter.setMaxRepositoryCapacity(5);
        fulfillmentCenter.addProduct(item);
        fulfillmentCenter.addProduct(item2);
        assertNotNull(fulfillmentCenter.getItems());
    }

    @Test
    public void getProduct() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 100);
        Item item = new Item("PC", ItemCondition.USED, 30, 2);
        fulfillmentCenter.addProduct(item);
        fulfillmentCenter.getProduct(item);
        assertTrue(item.getQuantity() == 1);
        fulfillmentCenter.getProduct(item);
        assertEquals(fulfillmentCenter.items.toString(), "[]");
    }

    @Test
    public void removeProduct() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 100);
        Item item = new Item("PC", ItemCondition.USED, 30, 2);
        fulfillmentCenter.addProduct(item);
        assertNotNull(fulfillmentCenter.getItems());
        fulfillmentCenter.removeProduct(item);
        assertEquals(fulfillmentCenter.getItems().toString(), "[]");
    }

    @Test
    public void search() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        fulfillmentCenter.setMaxRepositoryCapacity(5);
        Item item = new Item("PC", ItemCondition.USED, 30, 2);
        fulfillmentCenter.items.add(item);
        assertEquals(fulfillmentCenter.search("PC"), item);
        assertEquals(fulfillmentCenter.search("TV"), null);
    }

    @Test
    public void searchPartial() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        fulfillmentCenter.setMaxRepositoryCapacity(5);
        Item item = new Item("PC", ItemCondition.USED, 30, 2);
        fulfillmentCenter.addProduct(item);
        String expectedOutput = "Item : PC , Condition : Used , Mass : 30.0 , Quantity : 2" + System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        fulfillmentCenter.searchPartial("PC");
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void countByCondition() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        fulfillmentCenter.setMaxRepositoryCapacity(10);
        Item item = new Item("Monitor", ItemCondition.NEW, 15, 3);
        Item item2 = new Item("PC", ItemCondition.USED, 30, 2);
        Item item3 = new Item("Smartphone", ItemCondition.NEW, 45, 1);
        fulfillmentCenter.items.add(item);
        fulfillmentCenter.items.add(item2);
        fulfillmentCenter.items.add(item3);
        assertEquals(fulfillmentCenter.countByCondition(ItemCondition.NEW), 2);
        assertEquals(fulfillmentCenter.countByCondition(ItemCondition.USED), 1);
    }

    @Test
    public void summary() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        fulfillmentCenter.setMaxRepositoryCapacity(10);
        Item item = new Item("Monitor", ItemCondition.NEW, 15, 3);
        Item item2 = new Item("PC", ItemCondition.USED, 30, 2);
        fulfillmentCenter.items.add(item);
        fulfillmentCenter.items.add(item2);
        String expectedOutput = "Item : Monitor , Condition : New , Mass : 15.0 , Quantity : 3" +
                System.lineSeparator() +
                "Item : PC , Condition : Used , Mass : 30.0 , Quantity : 2" +
                System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        fulfillmentCenter.summary();
        assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    public void sortByName() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        fulfillmentCenter.setMaxRepositoryCapacity(10);
        Item item = new Item("B", ItemCondition.NEW, 15, 3);
        Item item2 = new Item("A", ItemCondition.USED, 30, 2);
        fulfillmentCenter.items.add(item);
        fulfillmentCenter.items.add(item2);
        String expectedOutput = "[Item : A , Condition : Used , Mass : 30.0 , Quantity : 2\n" +
                ", Item : B , Condition : New , Mass : 15.0 , Quantity : 3\n" +
                "]" + System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        fulfillmentCenter.sortByName();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void sortByAmount() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter();
        fulfillmentCenter.setMaxRepositoryCapacity(10);
        Item item = new Item("PC", ItemCondition.NEW, 15, 3);
        Item item2 = new Item("TV", ItemCondition.USED, 30, 2);
        fulfillmentCenter.items.add(item);
        fulfillmentCenter.items.add(item2);
        String expectedOutput = "[Item : TV , Condition : Used , Mass : 30.0 , Quantity : 2\n" +
                ", Item : PC , Condition : New , Mass : 15.0 , Quantity : 3\n" +
                "]" + System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        fulfillmentCenter.sortByAmount();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void max() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 1000);
        Item item = new Item("PC", ItemCondition.USED, 30, 25);
        Item item2 = new Item("TV", ItemCondition.NEW, 45, 99);
        fulfillmentCenter.addProduct(item);
        fulfillmentCenter.addProduct(item2);
        assertEquals(fulfillmentCenter.max(), fulfillmentCenter.items.get(1));
    }

    @Test
    public void compare() {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 1000);
        Item item = new Item("PC", ItemCondition.USED, 30, 25);
        Item item2 = new Item("TV", ItemCondition.NEW, 45, 99);
        Item item3 = new Item("PC", ItemCondition.USED, 30, 25);
        assertNotEquals(fulfillmentCenter.compare(item, item2), 0);
        assertEquals(fulfillmentCenter.compare(item, item3), 0);
        assertEquals(fulfillmentCenter.compare(item, null), -1);
    }

}