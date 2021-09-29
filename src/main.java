import java.util.ArrayList;
public class main {

	public static void main(String[] args) {
        var rows = new ArrayList<Item>() {
            {
                add(new Item(1, 0, 5, true));
                add(new Item(2, 16, 18, false));
                add(new Item(3, 12, 27, false));
                add(new Item(4, 20, 30, false));
                add(new Item(5, 10, 7, true));
                add(new Item(6, 13, 1, false));
                add(new Item(7, 2, 18, false));
                add(new Item(8, 25, 9, false));
                add(new Item(9, 10, 3, false));
                add(new Item(10, 1, 2, false));

            }
        };
        var kmeans = new Kmeans(rows, 30);

        kmeans.runKmeans();
	}
}