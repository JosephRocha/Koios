package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Overwatch {
	
	private String SystemName;
	private Map< Integer, Profile > users;
	
	
	public Overwatch() {
		this.SystemName = "System X";
		this.users = new HashMap<>();
		
	}
	
	public void addUser(Profile p) {
		this.users.put(p.getUserId(), p); // add user here;
	}
	
	public Profile getUser(Profile p) {
		return this.users.get(p.getUserId());
	}
	
	
		
}
