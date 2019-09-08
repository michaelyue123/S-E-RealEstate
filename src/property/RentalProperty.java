package property;

public class RentalProperty extends Property {
    private double weeklyRent;
    private double contractLength;
    private  double acceptableDuration;
    private double managementFee;

    public RentalProperty(String id, String address, String suburbCode, Type propertyType, int numOfBedroom,
                          int numOfBath, int numOfCarSpace, double weeklyRent, double acceptableDuration) {
        super(id, address, suburbCode, propertyType, numOfBedroom, numOfBath, numOfCarSpace);
        this.weeklyRent = weeklyRent;
        this.acceptableDuration = acceptableDuration;
    }

    public void setContractLength(double contractLength) {
        this.contractLength = contractLength;
    }

    public void setManagementFee(double managementFee) {
        this.managementFee = managementFee;
    }
}
