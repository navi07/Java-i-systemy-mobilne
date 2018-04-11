import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class FulfillmentCenterContainerTest {

    @Test
    public void addCenterNew() {
        FulfillmentCenterContainer fulfillmentCenterContainer = new FulfillmentCenterContainer();
        fulfillmentCenterContainer.addCenter("NewMagazine", 555);
        assertNotNull(fulfillmentCenterContainer.fulfillmentCenterMap.get("NewMagazine"));
    }

    @Test
    public void addCenterExisting() {
        FulfillmentCenterContainer fulfillmentCenterContainer = new FulfillmentCenterContainer();
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 100);
        fulfillmentCenterContainer.addCenter(fulfillmentCenter);
        assertNotNull(fulfillmentCenterContainer.fulfillmentCenterMap.get("Magazine"));
        assertEquals(fulfillmentCenterContainer.fulfillmentCenterMap.get("Magazine"), fulfillmentCenter);
    }

    @Test
    public void removeCenter() {
        FulfillmentCenterContainer fulfillmentCenterContainer = new FulfillmentCenterContainer();
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 100);
        fulfillmentCenterContainer.addCenter(fulfillmentCenter);
        assertNotNull(fulfillmentCenterContainer.fulfillmentCenterMap.get("Magazine"));
        fulfillmentCenterContainer.removeCenter("Magazine");
        assertEquals(fulfillmentCenterContainer.fulfillmentCenterMap.toString(), "{}");
    }

    @Test
    public void findEmpty() {
        FulfillmentCenterContainer fulfillmentCenterContainer = new FulfillmentCenterContainer();
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 100);
        fulfillmentCenterContainer.addCenter(fulfillmentCenter);
        assertEquals(fulfillmentCenterContainer.findEmpty().toString(),
                "["+fulfillmentCenterContainer.fulfillmentCenterMap.get("Magazine").toString()+"]");
        fulfillmentCenter.setCurrentRepositoryCapacity(5);
        assertNotEquals(fulfillmentCenterContainer.findEmpty().toString(),
                "["+fulfillmentCenterContainer.fulfillmentCenterMap.get("Magazine").toString()+"]");
    }

    @Test
    public void summary() {
        FulfillmentCenterContainer fulfillmentCenterContainer = new FulfillmentCenterContainer();
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 100);
        fulfillmentCenterContainer.addCenter(fulfillmentCenter);
        String expectedOutput = "Magazine -> 0.0% fill" + System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        fulfillmentCenterContainer.summary();
        assertEquals(expectedOutput, outContent.toString());
        fulfillmentCenter.setCurrentRepositoryCapacity(50);
        fulfillmentCenterContainer.summary();
        expectedOutput += "Magazine -> 50.0% fill" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}