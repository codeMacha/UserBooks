package com.cognixia.jump.react.user;
import java.util.List;

import com.cognixia.jump.react.userbook.UserBook;

public class User {

	public User(int userId, String username, String password, List<UserBook> readingList) {
		
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.readingList = readingList;
	}
public User(int userId, String username, String password) {
		
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.readingList = null;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", readingList="
				+ readingList + "]";
	}
	private int userId;
	private String username;
	private String password;
	private List<UserBook> readingList;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<UserBook> getReadingList() {
		return readingList;
	}
	public void addReadingList(UserBook book) {
		readingList.add(book);
		
	}
	public void removeReadingList(UserBook book) {
		readingList.add(book);
		
	}
	
}
