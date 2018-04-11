public class Item implements Comparable<Item> {

    private String name;
    private ItemCondition itemCondition;
    private double mass;
    private int quantity;

    public Item(){

        this.name = "";
        this.itemCondition = itemCondition.NEW;
        this.mass = 0.0;
        this.quantity = 0;
    }


    public Item(String name, ItemCondition itemCondition, double mass, int quantity) {
        try {
            if(mass <=0 || quantity <=0) {
                throw new NumberFormatException();
            }
            else{
                    this.name = name;
                    this.itemCondition = itemCondition;
                    this.mass = mass;
                    this.quantity = quantity;
                }
            }
        catch(NumberFormatException e){
            System.out.println("Enter the correct value !");
            throw new NumberFormatException();
        }

    }

    public void print(){
        System.out.println("Item : " + name + " , Condition : " + itemCondition + " , Mass : "
        + mass + " , Quantity : " + quantity);
    }

    @Override
    public String toString(){
        return ("Item : " + name + " , Condition : " + itemCondition + " , Mass : "
                + mass + " , Quantity : " + quantity + "\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCondition getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(ItemCondition itemCondition) {
        this.itemCondition = itemCondition;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Item o){
        int comparisonItem = name.compareTo(o.getName());
            return comparisonItem;
    }
}
