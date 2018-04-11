import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemTest {

    @Test(expected = NumberFormatException.class)
    public void ItemConstructorNegativeQuantity(){
        Item item2 = new Item("PC", ItemCondition.NEW, 15, -3);
        item2.print();
    }

    @Test(expected = NumberFormatException.class)
    public void ItemConstructorNegativeMass(){
        Item item2 = new Item("PC", ItemCondition.NEW, -15, 3);
        item2.print();
    }

    @Test
    public void print() {
        Item item = new Item("Monitor", ItemCondition.NEW, 15, 3);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        item.print();
        String expectedOutput = "Item : Monitor , Condition : New , Mass : 15.0 , Quantity : 3" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Item item = new Item();
        final Field field = item.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(item, "New");
        final String result = item.getName();
        assertTrue(result == "New");
    }

    @Test
    public void setName() throws NoSuchFieldException, IllegalAccessException {
        Item item = new Item();
        item.setName("New");
        final Field field = item.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), "New");
    }

    @Test
    public void getItemCondition() throws NoSuchFieldException, IllegalAccessException {
        Item item = new Item();
        final Field field = item.getClass().getDeclaredField("itemCondition");
        field.setAccessible(true);
        field.set(item, ItemCondition.USED);
        final ItemCondition result = item.getItemCondition();
        assertTrue(result == ItemCondition.USED);
    }

    @Test
    public void setItemCondition() throws NoSuchFieldException, IllegalAccessException {
        Item item = new Item();
        item.setItemCondition(ItemCondition.REFURBISHED);
        final Field field = item.getClass().getDeclaredField("itemCondition");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), ItemCondition.REFURBISHED);
    }

    @Test
    public void getMass() throws NoSuchFieldException, IllegalAccessException {
        Item item = new Item();
        final Field field = item.getClass().getDeclaredField("mass");
        field.setAccessible(true);
        field.set(item, 4.2);
        final double result = item.getMass();
        assertTrue(result == 4.2);
    }

    @Test
    public void setMass() throws NoSuchFieldException, IllegalAccessException {
        Item item = new Item();
        item.setMass(4.2);
        final Field field = item.getClass().getDeclaredField("mass");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), 4.2);
    }

    @Test
    public void getQuantity() throws NoSuchFieldException, IllegalAccessException {
        Item item = new Item();
        final Field field = item.getClass().getDeclaredField("quantity");
        field.setAccessible(true);
        field.set(item, 5);
        final double result = item.getQuantity();
        assertTrue(result == 5);
    }

    @Test
    public void setQuantity() throws NoSuchFieldException, IllegalAccessException {
        Item item = new Item();
        item.setQuantity(5);
        final Field field = item.getClass().getDeclaredField("quantity");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(item), 5);
    }

    @Test
    public void compareTo() {
        Item item = new Item("PC", ItemCondition.USED, 30, 2);
        Item item2 = new Item("PC", ItemCondition.USED, 30, 2);
        Item item3 = new Item("TV", ItemCondition.NEW, 30, 3);
        assertEquals(item.compareTo(item2), 0);
        assertNotEquals(item.compareTo(item3), 0);
    }


}