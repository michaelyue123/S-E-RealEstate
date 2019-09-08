package property;

import sun.jvm.hotspot.debugger.Address;

public class SaleProperty extends Property {

    private double minPrice;
    private double finalPrice;
    private boolean isUnderContract;
    private double commissionRate;
    private double commission;

    public SaleProperty(String id, String address, String suburbCode, Type propertyType, int numOfBedroom,
                        int numOfBath, int numOfCarSpace, double minPrice){
        super(id, address, suburbCode, propertyType, numOfBedroom, numOfBath, numOfCarSpace);
        this.minPrice = minPrice;
    }


    public void setCommissionRate(double newCommissionRate) {
        this.commissionRate = newCommissionRate;
    }

    public void setFinalPrice(double newFinalPrice) {
        this.finalPrice = newFinalPrice;
    }

    public void setUnderContract(boolean underContract) {
        this.isUnderContract = underContract;
    }

    public double calculateCommission(){
        this.commission = finalPrice * commissionRate;
        return commission;
    }
}
