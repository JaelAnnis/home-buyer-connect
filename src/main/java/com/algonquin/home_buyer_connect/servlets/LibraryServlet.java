package com.algonquin.home_buyer_connect.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.algonquin.home_buyer_connect.beans.*;
import com.algonquin.home_buyer_connect.dao.*;

public class LibraryServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    public LibraryServlet() {
    	
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