package org.camunda.bpm.getstarted.loanapproval.rest.dto;

import java.io.Serializable;
import java.util.Date;

public class UpdateCustomerFullNameModel implements Serializable{
	
   private String  id;

   private  String firstName ;
  
   private  String lastName ;
   
   private Date birthday;
   
   private Integer  age ;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UpdateCustomerFullNameModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthday=" + birthday + ", age=" + age + "]";
	}
	
	

   

   
   
	

}
