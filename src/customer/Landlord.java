package customer;

import property.Property;
import property.RentalProperty;
import property.Type;


public class Landlord extends VLCustomers {

    int numOfRent = 0;

    public Landlord(String customerId, String passWord,String name, String emailAddress) {
        super(customerId, passWord, name, emailAddress);
    }

    public Property addProperty(String id, String address, String suburbCode, Type propertyType, int numOfBedroom,
                                int numOfBath, int numOfCarSpace, double weeklyRent, double acceptableDuration){
        Property property = new RentalProperty(id, address, suburbCode, propertyType,numOfBedroom,
                numOfBath,numOfCarSpace, weeklyRent, acceptableDuration) ;
        getPropertyList().add(id);
        return property;
    }
}
