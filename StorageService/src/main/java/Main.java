public class Main {

    public static void main(String[] args) {

        /** newItem() */
        Item item = new Item("Monitor", ItemCondition.NEW, 15, 3);
        Item item2 = new Item("PC", ItemCondition.USED, 30, 2);
        Item item3 = new Item("Smartphone", ItemCondition.NEW, 45, 1);

        /** item.print() */
        //item.print();

        /** Magazines */
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("Magazine", 100);
        FulfillmentCenterContainer fulfillmentCenterContainer = new FulfillmentCenterContainer();

        /** AddProduct(Item) */
        fulfillmentCenter.addProduct(item);
        //fulfillmentCenter.addProduct(item2);
        fulfillmentCenter.addProduct(item3);
        //fulfillmentCenter.addProduct(item); // Add the same item

        /** getProduct(Item) */
        //fulfillmentCenter.getProduct(item);

        /** removeProduct(Item) */
        fulfillmentCenter.removeProduct(item2);

        /** search(String) */
        //System.out.println(fulfillmentCenter.search(""));

        /** searchPartial(String) */
        //fulfillmentCenter.searchPartial("");

        /** countByCondition(ItemCondition */
        //System.out.println(fulfillmentCenter.countByCondition(ItemCondition.NEW));

        /** summary() */
        fulfillmentCenter.summary();

        /** sortByName() */
        //fulfillmentCenter.sortByName();

        /** sortByAmount() */
        //fulfillmentCenter.sortByAmount();

        /** max() */
        //System.out.println(fulfillmentCenter.max());

        /** -------MAP------- */

        /** addCenter(String, double)
         *  or addCenter(existingMagazine) */
        fulfillmentCenterContainer.addCenter("NewMagazine", 555);
        fulfillmentCenterContainer.addCenter("OldMagazine", 200);
        fulfillmentCenterContainer.addCenter(fulfillmentCenter);

        /** removeCenter(String) */
        //fulfillmentCenterContainer.removeCenter("OldMagazine");

        /** findEmpty() */
        //System.out.println(fulfillmentCenterContainer.findEmpty());

        /** summary() */
        //fulfillmentCenterContainer.summary();

    }
}
