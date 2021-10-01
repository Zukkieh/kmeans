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
            .filter(item -> item.isCentroidInicial)
            .map(item -> new Item(item.id, item.x, item.y, item.isCentroidInicial))
            .toList();

        for(int i = 0; i < initialCentroids.size(); i++) {
            initialCentroids.get(i).group = i+1;
        }

        boolean isEqualThanLastOne = false;
        int iteration = 0;
        while(iteration < iterationsQuantity && !isEqualThanLastOne) {
            for (Item item : items) {
                ArrayList<Integer> distances = new ArrayList<Integer>(initialCentroids.size()); 
                for (Item ic : initialCentroids) {
                    distances.add((int)findDistance(item.x, ic.x, item.y, ic.y));
                }
                var obj = Collections.min(distances);
                var index = distances.indexOf(obj);
                item.group = index + 1;
                distances.clear();
            }
            var equalCount = 0;
            for (Item ic : initialCentroids) {
                var groupedItems = items.stream().filter(item -> item.group == ic.group).toList();
                
                var averageX = Math.round(groupedItems.stream().mapToDouble(m -> m.x).average().getAsDouble());
                var averageY = Math.round(groupedItems.stream().mapToDouble(m -> m.y).average().getAsDouble());

                if(averageX == ic.x && averageY == ic.y) {
                    equalCount++;
                }
                else{
                    ic.x = averageX;
                    ic.y = averageY;
                }
            }
            if(equalCount == initialCentroids.size()){
                isEqualThanLastOne = true;
            }
            iteration++;
        }
        for (Item item : initialCentroids) {
            System.out.println("CENTROIDES " + item.id + " " + item.x + " " + item.y);
        }
        for (Item ic : initialCentroids) {
            System.out.print("Grupo "+ ic.group + ": { ");
            for (Item item : items) {
                if(item.group == ic.group) System.out.print(item.id + " ");
            }
            System.out.print("}\n");
        }
    }

    private long findDistance(double x1, double x2, double y1, double y2) {
        return Math.round(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
    }

}
