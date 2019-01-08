package pl.lukaszgrymulski;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Student {
	private String firstName;
	private String lastName;
	private String country;
	private String age;
	
	//listOfAgeRanges for the second drop-down list
	List<String> ageRangesList;
	
	
	public Student() {
		ageRangesList = new ArrayList();
		ageRangesList.add("16 or less");
		ageRangesList.add("17-20");
		ageRangesList.add("21-24");
		ageRangesList.add("25-29");
		ageRangesList.add("30-39");
		ageRangesList.add("40 or more");
		
	}	

	public List<String> getAgeRangesList() {
		return ageRangesList;
	}	
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
}
