import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FulfillmentCenter {

    private String repositoryName;
    private double maxRepositoryCapacity; // Wyrażona przez ilość
    private double currentRepositoryCapacity; // Wyrażona przez ilość
    public List<Item> items = new LinkedList<>();

    public FulfillmentCenter() {
        this.repositoryName = "";
        this.maxRepositoryCapacity = 100;
    }

    public FulfillmentCenter(String repositoryName, double maxRepositoryCapacity){
        this.repositoryName = repositoryName;
        this.maxRepositoryCapacity = maxRepositoryCapacity;
    }

    public double getCurrentRepositoryCapacity() {
        return currentRepositoryCapacity;
    }

    public void setCurrentRepositoryCapacity(double currentRepositoryCapacity) {
        this.currentRepositoryCapacity = currentRepositoryCapacity;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public double getMaxRepositoryCapacity() {
        return maxRepositoryCapacity;
    }

    public void setMaxRepositoryCapacity(double maxRepositoryCapacity) {
        this.maxRepositoryCapacity = maxRepositoryCapacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addProduct(Item item){
        if((item.getQuantity() + currentRepositoryCapacity) > maxRepositoryCapacity){
            System.err.println("Failed to add item because You exceeded maximum repository capacity!");
        }
        else{
                if(compareToName(item) == 0){
                    item.setQuantity(item.getQuantity()+1);
                }
            items.add(item);
            currentRepositoryCapacity += item.getQuantity();
            }
        }

        public void getProduct(Item item){
            item.setQuantity(item.getQuantity()-1);
            currentRepositoryCapacity--;
            if(item.getQuantity() == 0){
                items.remove(item);
            }
        }

        public void removeProduct(Item item){
            items.remove(item);
            }



    public Item search(String name){
        boolean found = false;
        Item item = new Item();
        item.setName(name);
        int index;

        for(index = 0; index  < items.size(); index ++ ){
            if(compare(items.get(index), item) == 0){
                found = true;
                break;
            }
        }
        if (!found){
            return null;
        }
        return items.get(index);
    }

    public void searchPartial(String name){
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getName().contains(name)){
                items.get(i).print();
            }
        }
    }

    public int countByCondition(ItemCondition itemCondition){
        int count = 0;
        for(int i = 0; i<items.size(); i++){
            if(items.get(i).getItemCondition() == itemCondition){
                count++;
            }
        }
        return count;
    }

    public void summary(){
        for(Item item : items){
            item.print();
        }
    }

    public void sortByName()
    {
        Collections.sort(items);
        System.out.println(items);
    }

    public void sortByAmount(){
        Collections.sort(items, new ComparatorAmount());
        System.out.println(items);
    }

    public Item max(){
        return Collections.max(items, new ComparatorAmount());
    }

    public int compare(Item o1, Item o2)
    {
        if(o1 == null || o2 == null)
            return -1;
        else
            return o1.compareTo(o2);
    }

    public int compareToName(Item item) {
        int result = 1;
        Iterator<Item> iterator = items.iterator();
        while (result != 0 && iterator.hasNext()) {
            result = item.getName().compareTo(iterator.next().getName());
        }
        return result;
    }

    @Override
    public String toString() {
        return "FulfillmentCenter{" +
                "repositoryName='" + repositoryName + '\'' +
                ", maxRepositoryCapacity=" + maxRepositoryCapacity +
                ", currentRepositoryCapacity=" + currentRepositoryCapacity +
                ", items=" + items +
                '}' + '\n';
    }
}





