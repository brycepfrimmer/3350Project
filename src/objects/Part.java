package objects;

import java.util.ArrayList;

public final class Part {
    private String partDesc;
    private ArrayList<ServiceItem> serviceIList;

    public Part(String p) {
        this.partDesc = p;
        serviceIList = new ArrayList<ServiceItem>();
    }

    public void addServiceItem(ServiceItem s) {
        this.serviceIList.add(s);
    }
    
    public String getPartDesc() {
        return partDesc;
    }
    
    public String toString() {
        String returnString = "\n\t\t\t" + partDesc;
        if (serviceIList != null && !serviceIList.isEmpty()) {
            for (int i = 0; i < serviceIList.size(); i++) {
                returnString = returnString + "\n\t\t\t - " + serviceIList.get(i).getDescription();
                if (serviceIList.get(i).getServiceTime() != 0) {
                    returnString = returnString + " every " + serviceIList.get(i).getServiceTime() + " days";
                } else if (serviceIList.get(i).getServiceKm() != 0) {
                    returnString = returnString + " every " + serviceIList.get(i).getServiceTime() + " kilometers";
                } else {
                    System.err.println("IMPROPERLY ASSIGNED ServiceItem.");
                }
            }
        }

        return returnString;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        
        Part p = (Part) obj;
        if (this.partDesc.equals(p.partDesc)) {
            return true;
        } else {
            return false;
        }
    }

}// End PartsList Class

