package cmmsObjects;

import java.util.Date;

public class ServiceItem {
    private final String description;
    private long serviceTime;
    private int serviceKm;
    private Date dateLastServiced;
    private int kmLastServiced;
    
    public ServiceItem(String d, long serviceTime, Date dateLastServiced) {
        this.description = d;
        this.serviceTime = serviceTime;
        this.setDateLastServiced(dateLastServiced);
        this.serviceKm = 0;
        this.setKmLastServiced(0);
    }
    
    public ServiceItem(String d, int serviceKm, int kmLastServiced) {
        this.description = d;
        this.serviceKm = serviceKm;
        this.setKmLastServiced(kmLastServiced);
        this.serviceTime = 0;
        this.setDateLastServiced(null);
    }

    public boolean checkNeedsService(int currentKm) {
        boolean retVal = false;
        if (serviceKm > 0) {
            if (kmLastServiced+serviceKm <= currentKm) {
                retVal = true;
            }
        } else if (dateLastServiced != null) {
            Date tempDate = new Date();
            if (dateLastServiced.getTime()+serviceTime <= tempDate.getTime()) {
                retVal = true;
            }
        } else {
            System.err.println("ServiceItem instantiated improperly.");
        }
        return retVal;
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

    public Date getDateLastServiced() {
        return dateLastServiced;
    }

    public void setDateLastServiced(Date dateLastServiced) {
        this.dateLastServiced = dateLastServiced;
    }

    public int getKmLastServiced() {
        return kmLastServiced;
    }

    public void setKmLastServiced(int kmLastServiced) {
        this.kmLastServiced = kmLastServiced;
    }
}
