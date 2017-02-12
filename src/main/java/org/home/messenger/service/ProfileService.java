package org.home.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.home.messenger.database.DatabaseClass;
import org.home.messenger.model.Message;
import org.home.messenger.model.Profile;

public class ProfileService {
	
	
private static Map<String,Profile>profiles=DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("oleg",new Profile(1l,"oleg", "oleg", "mv"));
		profiles.put("ivan",new Profile(2l,"ivan","ivan","vh"));
	}
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
		
	}
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(),profile);
		return profile;
	}
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}

}
