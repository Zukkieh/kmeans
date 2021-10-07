public class Item {
    private int id;
    private double x, y;
    private boolean isCentroidInicial;
    private int group, lastgroup;

    
    public void setLastgroup(int lastgroup) {
		this.lastgroup = lastgroup;
	}

	public int getId() {
		return id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public boolean isCentroidInicial() {
		return isCentroidInicial;
	}

	public int getGroup() {
		return group;
	}

	public int getLastgroup() {
		return lastgroup;
	}

	public Item(int id, double x, double y, boolean isCentroidInicial) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.isCentroidInicial = isCentroidInicial;
        this.group = 0;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
