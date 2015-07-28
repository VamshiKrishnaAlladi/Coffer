package com.avk.coffer;

import java.io.File;
import java.util.StringTokenizer;

public class CofferPasswordEntry {
	private String id, title, username, password, url;
	
	public void setId(String Id){ this.id = Id; }
	public void setTitle(String title){ this.title = title; }
	public void setUsername(String username){ this.username = username; }
	public void setPassword(String password){ this.password = password; }
	public void setUrl(String url){ this.url = url; }
	
	public String getId(){ return id; }
	public String getTitle(){ return title; }
	public String getUsername(){ return username; }
	public String getPassword(){ return password; }
	public String getUrl(){ return url; }
	
	public void setValuesFromFile(File file, int index){
		try {
			StringTokenizer st = new StringTokenizer(CofferCrypt.decryptFromFile_Index(index, file), "|"); 
			this.id = st.nextToken();
			this.title = st.nextToken();
			this.username = st.nextToken();
			this.password = st.nextToken();
			this.url = st.nextToken();
		} 
		catch (Exception e) { e.printStackTrace(); }
	}
	
}
