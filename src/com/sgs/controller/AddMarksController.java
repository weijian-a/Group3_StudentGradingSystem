package com.sgs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sgs.dao.FacultyDao;
import com.sgs.dao.LogDao;
import com.sgs.dao.ModuleDao;
import com.sgs.model.StudentModule;

public class AddMarksController extends HttpServlet implements java.io.Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6465803230560981680L;
	private String ADD_MARKS = "/faculty_addmarks.jsp";
    private String FACULTY_LISTSTU = "./faculty_liststudent.jsp";
	private String CANCEL = "/FacultyController";
	
	public AddMarksController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		hs = request.getSession();
//		System.out.println("AddMarksController doGet");
//		
//		String forward = "";
//		
//		List<StudentModule> results = new ArrayList<StudentModule>();
//		
//		//Retrieve values from from data
//		StudentModule studentModule = new StudentModule();
//		String[] stID = request.getParameterValues("stID");
//		String[] stName = request.getParameterValues("stName");
//		String[] stModID = request.getParameterValues("stModID");
//		String[] stModName = request.getParameterValues("stModName");
//		
//		//set list
//		studentModule.setUsername(stID[0]);
//		studentModule.setName(stName[0]);
//		studentModule.setIdMod(stModID[0]);
//		studentModule.setModName(stModName[0]);
//		results.add(studentModule);
//		
//		forward = ADD_MARKS;
//		request.setAttribute("results", results);
//		
//		RequestDispatcher view = request.getRequestDispatcher(forward);
//		view.forward(request, response);
		
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	

		ModuleDao dao = new ModuleDao();
    	HttpSession hs = request.getSession();
    	System.out.println("AddMarksController doPost");
    	
    	String forward = "";
    	String result = "";
    	
    	//process fields with input name="action"
    	result = request.getParameter("action");
    	
    	if (result.equals("Submit")){
    		System.out.println("Submit button triggered");
    		
    		//retrieve data
    		String modId = request.getParameter("modID");
    		String modName = request.getParameter("moduleName");
    		String stId = request.getParameter("stID");
    		float marks = Float.parseFloat(request.getParameter("grades"));
    		
    		System.out.println(marks);
    		System.out.println(modId + " " + stId);
    		
    		LogDao logDao = new LogDao();
    		
    		try {
				dao.addMarks(marks, modId, stId);
				FacultyDao facultyDao = new FacultyDao();
				forward = FACULTY_LISTSTU;
	        	request.setAttribute("results", facultyDao.listStudents((String)hs.getAttribute("uname")));
	        	request.setAttribute("getAlert", "Yes");
	        	
	        	logDao.logAddMarks((String)hs.getAttribute("uname"), stId, modName);
	        	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	} else if (result.equals("Add")) {
    		System.out.println("Add Marks Button Triggered");
    		
    		List<StudentModule> results = new ArrayList<StudentModule>();
    		
    		//Retrieve values from from data
    		StudentModule studentModule = new StudentModule();
    		String[] stID = request.getParameterValues("stID");
    		String[] stName = request.getParameterValues("stName");
    		String[] stModID = request.getParameterValues("stModID");
    		String[] stModName = request.getParameterValues("stModName");
    		
    		//set list
    		studentModule.setUsername(stID[0]);
    		studentModule.setName(stName[0]);
    		studentModule.setIdMod(stModID[0]);
    		studentModule.setModName(stModName[0]);
    		results.add(studentModule);
    		
    		forward = ADD_MARKS;
    		request.setAttribute("results", results);
    		
//    		RequestDispatcher view = request.getRequestDispatcher(forward);
//    		view.forward(request, response);
    	
    	} else {
    		System.out.println("Cancel button triggered");
    		request.setAttribute("action", "stu");
    		forward = CANCEL;
    	}

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
