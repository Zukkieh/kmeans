public class Item {
    public int id;
    public double x;
    public double y;
    public boolean isCentroidInicial;
    public int group;

    public Item(int id, double x, double y, boolean isCentroidInicial) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.isCentroidInicial = isCentroidInicial;
        this.group = 0;
    }

    public void setCentroidInicial(boolean isCentroidInicial) {
        this.isCentroidInicial = isCentroidInicial;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
