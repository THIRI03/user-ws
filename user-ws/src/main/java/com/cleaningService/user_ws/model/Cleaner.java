//    JAD-CA2
//    Class-DIT/FT/2A/23
//    Student Name: Thiri Lae Win
//    Admin No.: P2340739

package com.cleaningService.user_ws.model;

public class Cleaner {
	
	private int id;
	private String email;
	
	public Cleaner() {
		
	}
	
	public Cleaner(int id, String email){
		this.id = id;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
