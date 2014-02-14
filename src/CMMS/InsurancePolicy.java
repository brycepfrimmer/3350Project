package CMMS;

public final class InsurancePolicy implements CMMSInterface {
    private String policyNum;
    private String type;

    public InsurancePolicy(String pNum, String message) {
        this.policyNum = pNum;
        this.type = message;
    }

    public boolean setPolicyNum(String num) {
        boolean isValid = false;
        isValid = num != null && num.matches("[0-9a-zA-Z]+");
        if (isValid) {
            this.policyNum = num;
        }
        return isValid;
    }

    public boolean setType(String type) {
        boolean isValid = false;
        isValid = type != null && type.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !type.trim().isEmpty();
        if (isValid) {
            this.policyNum = type;
        }
        return isValid;
    }

    public String getPolicyNum() {
        return this.policyNum;
    }

    public String getType() {
        return this.type;
    }

    // Print out the insurance object details
    public String toString() {
        // System.out.println("\tInsurance policy number: " + this.policyNum);
        // System.out.println("\tInsurance type: " + this.type);
        return this.policyNum + "\n" + this.type;
    }
}// End InsurancePolicy Class
