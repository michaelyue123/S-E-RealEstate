package employees;

import java.util.HashMap;
public class employee {
	private final String employeeId;
	private final String employeeStatus;
	private final Double hourlyRate;
	private  Double hours;
	HashMap<Integer,String>assignedProperty_List= new HashMap<Integer,String>();

	public String getEmployeeId() {
        return employeeId;
    }
	public employee(String employeeId,String employeeStatus,Double hourlyRate,Double hours) {
		this.employeeId = employeeId;
		this.employeeStatus = employeeStatus;
		this.hourlyRate = hourlyRate;
		this.hours = hours;
	}

	
		
    public String getEmployeeStatus() {
        return employeeStatus;
        }

	public Double getHourlyRate() {
		return hourlyRate;
	}
	public Double getHours() {
		return hours;
	}

}

