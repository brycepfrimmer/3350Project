package objects;

import java.util.ArrayList;

public final class PartsList {
    private ArrayList<String> partsList;

    public PartsList() {
        partsList = new ArrayList<String>();
    }

    public boolean addPart(String part) {
        boolean success = false;
        success = part != null && !part.trim().isEmpty();
        if (partsList.contains(part)) {
            success = false;
        }
        if (success) {
            partsList.add(part);
        }
        return success;
    }

    public boolean removePart(String part) {
        return partsList.remove(part);
    }

    public boolean print() {
        for (String part : partsList) {
            System.out.println(part);
        }
        return true;
    }

    public ArrayList<String> getPartsList() {
        return partsList;
    }

    public boolean isEmpty() {
        return partsList.isEmpty();
    }

    public String toString() {
        String listString = "";

        for (String s : partsList) {
            listString += "\n\t\t\t" + s;
        }

        return listString;
    }

}// End PartsList Class

