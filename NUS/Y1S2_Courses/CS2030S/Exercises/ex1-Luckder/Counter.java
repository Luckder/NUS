public class Counter {

    private int id;
    private boolean isAvailable;

    public Counter(int id) {
        this.id = id;
        this.isAvailable = true;
    }

    public int getId() {
        return this.id;
    }

    public void makeAvailable() {
        isAvailable = true;
    }

    public void makeUnavailable() {
        isAvailable = false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}