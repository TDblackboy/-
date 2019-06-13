package modal;

/**
 * 实时地点信息
 */
public class RealTimeData {
	
	public String location;
	public String persons;
	public String dataTime;
	
	public String getLocation() {
		return location;
	}
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPersons() {
		return persons;
	}
	public void setPersons(String persons) {
		this.persons = persons;
	}
	
	
	
	
}
