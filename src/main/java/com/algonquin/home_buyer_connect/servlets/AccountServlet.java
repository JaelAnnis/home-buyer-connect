package com.algonquin.home_buyer_connect.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.algonquin.home_buyer_connect.beans.*;
import com.algonquin.home_buyer_connect.dao.*;

public class AccountServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    public AccountServlet() {
    	
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Cookie[] cookies = request.getCookies();
    	
    	if (cookies != null) {
    	
	        for (Cookie cookie : cookies) {
	        	
	            if (cookie.getName().equals("account")) {
	            	
	            	String page = getHTMLString(request.getServletContext().getRealPath("/html/library.html"), "");
	            	response.getWriter().write(page);
	            }
	        }
	        
    	} else {
    		
        	String page = getHTMLString(request.getServletContext().getRealPath("/html/signin.html"), "");
        	response.getWriter().write(page);
    	}
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	ApplicationDao dao = new ApplicationDao();
    	
    	String form = request.getParameter("form");
    	
    	if (form.equals("signin")) {

        	String email = request.getParameter("email");
        	
        	String password = request.getParameter("password");
        	
        	try {
        		
            	ResultSet account = dao.signInAccount(email, password);
            	
            	if (!account.next() ) {
            		
					String page = getHTMLString(request.getServletContext().getRealPath("/html/signin.html"), "Your email or password is incorrect.");
					response.getWriter().write(page);
            	    
            	} else {

            	    do {
            	    
            	    	Cookie cookie = new Cookie("account", account.getString("id"));

            	    	cookie.setMaxAge(60 * 60 * 24);

            	    	response.addCookie(cookie);
            	    	
            	    	response.sendRedirect("/com.algonquin.home_buyer_connect/library");
            	    	
            	    } while (account.next());
            	}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
        	
    	} else if (form.equals("create")) {
    		
        	String firstName = request.getParameter("firstName");
        	
        	String lastName = request.getParameter("lastName");
    		
        	String email = request.getParameter("email");
        	
        	String password = request.getParameter("password");
    		
        	Account account = new Account(firstName, lastName, email, password);
        	
        	if (dao.createAccount(account)) {
				
				String page = getHTMLString(request.getServletContext().getRealPath("/html/signin.html"), "Your account was created. Please sign in.");
				response.getWriter().write(page);
				
			} else {
				
				String page = getHTMLString(request.getServletContext().getRealPath("/html/signin.html"), "Your account was not created.");
				response.getWriter().write(page);
			}
        	
    	}
    }
    
    public String getHTMLString(String filePath, String message) throws IOException {
    	
    	BufferedReader reader = new BufferedReader(new FileReader(filePath));
    	String line="";
    	StringBuffer buffer = new StringBuffer();
    	while((line=reader.readLine()) != null) {
    		buffer.append(line);
    	}
    	reader.close();
    	String page = buffer.toString();
    	page = MessageFormat.format(page, message);
    	return page;
    }
}