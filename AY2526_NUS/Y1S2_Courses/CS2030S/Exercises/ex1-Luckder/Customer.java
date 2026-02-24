public class Customer {

    private double serviceTime;
    private int id;

    public Customer(int id, double serviceTime) {
        this.id = id;
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return this.id;
    }

    public double getServiceTime(){
        return this.serviceTime;
    }
}