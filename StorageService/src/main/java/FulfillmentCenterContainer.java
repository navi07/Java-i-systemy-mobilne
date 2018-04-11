import java.util.*;

public class FulfillmentCenterContainer {

    public Map<String, FulfillmentCenter> fulfillmentCenterMap = new TreeMap<>();

    public void addCenter(String name, double capacity){
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(name, capacity);
        fulfillmentCenterMap.put(name, fulfillmentCenter);
    }

    public void addCenter(FulfillmentCenter fulfillmentCenter){
        fulfillmentCenterMap.put(fulfillmentCenter.getRepositoryName() ,fulfillmentCenter);
    }

    public void removeCenter(String name){
        fulfillmentCenterMap.remove(name);
    }

    public List<FulfillmentCenter> findEmpty(){
        List<FulfillmentCenter> list = new LinkedList<>();
        Set<Map.Entry<String,FulfillmentCenter>> entrySet = fulfillmentCenterMap.entrySet();
        for(Map.Entry<String,FulfillmentCenter> entry:entrySet)
        {
            if ( entry.getValue().getCurrentRepositoryCapacity() == 0 )
            {
                list.add(entry.getValue());
            }
        }
        return list;
    }


    public void summary(){
        for(String index : fulfillmentCenterMap.keySet()){
            FulfillmentCenter variable = fulfillmentCenterMap.get(index);
            System.out.println(index + " -> " + (variable.getCurrentRepositoryCapacity()/
            variable.getMaxRepositoryCapacity())*100.0 + "% fill");
        }
    }
}

