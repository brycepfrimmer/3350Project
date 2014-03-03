package objects;

public class ServiceItem {
    private final String description;
    private long serviceTime;
    private int serviceKm;
    
    public ServiceItem(String d, long serviceTime) {
        this.description = d;
        this.serviceTime = serviceTime;
        this.serviceKm = 0;
    }
    
    public ServiceItem(String d, int serviceKm) {
        this.description = d;
        this.serviceKm = serviceKm;
        this.serviceTime = 0;
    }

    public String getDescription() {
        return description;
    }
    
    public int getServiceKm() {
        return serviceKm;
    }
    
    public long getServiceTime() {
        return serviceTime;
    }
}
