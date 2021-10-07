import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Kmeans {

    ArrayList<Item> items;
    int iterationsQuantity; 

    public Kmeans(ArrayList<Item> items, int iterationsQuantity) {
        this.items = items;
        this.iterationsQuantity = iterationsQuantity;
    }

    public void runKmeans() {
        List<Item> initialCentroids = items
            .stream()
            .filter(item -> item.isCentroidInicial())
            .map(item -> new Item(item.getId(), item.getId(), item.getY(), item.isCentroidInicial()))
            .toList();

        for(int i = 0; i < initialCentroids.size(); i++) {
            initialCentroids.get(i).setGroup(i+1);
        }

        boolean isEqualThanLastOne = false;
        int iteration = 0;
        while(iteration < iterationsQuantity && !isEqualThanLastOne) {
            for (Item item : items) {
                ArrayList<Double> distances = new ArrayList<Double>(initialCentroids.size()); 
                for (Item ic : initialCentroids) {
                    distances.add(findDistance(item.getX(), ic.getX(), item.getY(), ic.getY()));
                }
                double obj = Collections.min(distances);
                int index = distances.indexOf(obj);
                item.setLastgroup(item.getGroup());
                item.setGroup(index+1);
                distances.clear();
            }
            for (Item ic : initialCentroids) {
                var groupedItems = items.stream().filter(item -> item.getGroup() == ic.getGroup()).toList();
                
                int averageX = Math.round(groupedItems.stream().mapToDouble(m -> m.x).average().getAsDouble());
                int averageY = Math.round(groupedItems.stream().mapToDouble(m -> m.y).average().getAsDouble());

                if(averageX != ic.getX() || averageY != ic.getY()) {
                    ic.setX(averageX);
                    ic.setY(averageY);
                }
            }
            
            int equalCount = 0;
            for (int i = 0; i < items.size();i++) {
            	if(items.get(i).getLastgroup() == items.get(i).getGroup())
            		equalCount++;
            }
            if(equalCount == items.size()){
                isEqualThanLastOne = true;
            }
            iteration++;
        }
        for (Item item : initialCentroids) {
            System.out.println("CENTROIDES " + item.getId() + " " + item.getX() + " " + item.getY());
        }
        for (Item ic : initialCentroids) {
            System.out.print("Grupo "+ ic.getGroup() + ": { ");
            for (Item item : items) {
                if(item.getGroup() == ic.getGroup()) System.out.print(item.getId() + " ");
            }
            System.out.print("}\n");
        }
    }

    private double findDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
