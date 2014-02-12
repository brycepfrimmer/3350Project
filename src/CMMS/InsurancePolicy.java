package CMMS;

final class InsurancePolicy
{
	private String policyNum;
	private String type;
	
	protected InsurancePolicy( String pNum, String message )
	{
		this.policyNum = pNum;
		this.type = message;
	}
	//Print out the insurance object details
	protected void print()
	{
		System.out.println("\tInsurance policy number: " + this.policyNum);
		System.out.println("\tInsurance type: " + this.type);
	}
}//End InsurancePolicy Class
