import java.util.Comparator;

public class ComparatorAmount implements Comparator<Item> {

    public int compare(Item o1, Item o2) {

        int result = o1.getQuantity() - o2.getQuantity();
        if(result == 0){
            return o1.compareTo(o2);
        }
        return result;
    }

}

