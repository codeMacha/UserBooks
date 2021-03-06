package com.cognixia.jump.react.servlet;

import com.cognixia.jump.react.customExceptions.BadUserIdException;
import com.cognixia.jump.react.customExceptions.IncorrectPasswordException;
import com.cognixia.jump.react.user.User;
import com.cognixia.jump.react.user.UserDAOClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.google.gson.Gson;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private Gson gson = new Gson();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in login servlet");
		
		String s = new BufferedReader( 
				new InputStreamReader(request.getInputStream()))
				.lines()
				.collect(Collectors.joining("\n"));
		
		System.out.println(s);

		JSONParser parser = new JSONParser();

		try {
			JSONObject injson = (JSONObject) parser.parse(s);

			String username = (String) injson.get("username");
			String password = (String) injson.get("password");

			boolean login = false;
			User user = null;
			UserDAOClass userDAOClass = new UserDAOClass();
			System.out.println(userDAOClass.authenticateUser(username, password));
			try{
				login = userDAOClass.authenticateUser(username, password);

				if(login == false) {
					user = userDAOClass.getUserbyUsername(username);
					if(user == null) {
						throw new BadUserIdException();
					}else {
						throw new IncorrectPasswordException();
					}
				}
			}catch(BadUserIdException e){
				e.getMessage();

			}catch(IncorrectPasswordException e) {
				e.getMessage();
			}
			user = userDAOClass.getUserbyUsername(username);

			String jsonobj = gson.toJson(user);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(jsonobj);
			out.flush();

		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
}
