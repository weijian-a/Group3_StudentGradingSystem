package com.sgs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sgs.dao.FacultyDao;

public class FacultyController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2831237519433956321L;
	private String FACULTY_SEARCH = "./faculty_home.jsp";
    private String FACULTY_LISTMOD = "./faculty_listmodule.jsp";
    private String FACULTY_LISTSTU = "./faculty_liststudent.jsp";
    private String CHANGE_PASS = "./change_password.jsp";
    private String ERROR = "/login.jsp?invaliduser";
    
    public FacultyController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	System.out.println("FacultyController doGet");
    	
//        String forward="";
//        String result ="";
//        
//	    result = request.getParameter("action");
////	    
////	    //depending on the button pressed on the faculty_home.jsp, it will trigger different events
//////	    if (result.equals("search")){
//////            forward = FACULTY_SEARCH;
//////        }else if (result.equals("mod")){
//////            forward = FACULTY_LISTMOD;
//////        } else if (result.equals("stu")){
//////        	forward = FACULTY_LISTSTU;
//////        	request.setAttribute("results", dao.listStudents((String)hs.getAttribute("uname")));
//////        } else if (result.equals("change")){
//////        	forward = CHANGE_PASS;
//////        } else {
//////            forward = ERROR;
//////        }
//    	
//        forward = FACULTY_LISTSTU;
//        request.setAttribute("results", dao.listStudents((String)hs.getAttribute("uname")));
////
//        RequestDispatcher view = request.getRequestDispatcher(forward);
//        view.forward(request, response);
    	//response.sendRedirect(forward);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        FacultyDao dao = new FacultyDao();
    	HttpSession hs = request.getSession();
    	System.out.println("FacultyController doPost");
    	
        String forward="";
        String result ="";
        
        //it will process input with name="action"
        result = request.getParameter("action");
        System.out.println(result);
        
        try{
	    if (result.equals("search")){
            forward = FACULTY_SEARCH;
        }else if (result.equals("mod")){
            forward = FACULTY_LISTMOD;
        } else if (result.equals("stu") || result.equals("Cancel")){
        	forward = FACULTY_LISTSTU;
        	request.setAttribute("results", dao.listStudents((String)hs.getAttribute("uname")));
        } else if (result.equals("change")){
        	forward = CHANGE_PASS;
        } else {
            forward = ERROR;
        }
        
        }catch(Exception e){}
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    	
    }
    
}