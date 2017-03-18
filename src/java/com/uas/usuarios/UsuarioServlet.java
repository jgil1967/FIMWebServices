/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uas.usuarios;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jonathangil
 */
public class UsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       	String task = request.getParameter("task");
                //System.out.println("task: " + task);
                 if (task.equalsIgnoreCase("cerrarsesionhttp"))
	       {
                 //  System.out.println("cerrarsesionhttp");
	    	response.setContentType("text/html");
	       	Cookie[] cookies = request.getCookies();
	       	if(cookies != null){
	       	for(Cookie cookie : cookies){
	       		if(cookie.getName().equals("JSESSIONID")){
	       			//System.out.println("JSESSIONID="+cookie.getValue());
	       			break;
	       		}
	       	}
	       	}
	       	//invalidate the session if exists
	       	HttpSession session = request.getSession(false);
	      // 	System.out.println("User="+session.getAttribute("user"));
	       	if(session != null){
	       		session.invalidate();
	       	}
	       	response.sendRedirect("login.html");
	       }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try{
			
		 UsuarioFacade facade = new UsuarioFacade();
		String task = request.getParameter("task");
		
	if (task.equalsIgnoreCase("iniciarsesionhttp"))
	       {String user = request.getParameter("user");
	   		String pwd = request.getParameter("pwd");
	   		UsuarioDTO u = new UsuarioDTO();
	   		u.setName(user);
	   		u.setcontrasena(pwd);
	   		u = facade.iniciarSesion(u);
                        
	   		if(u.isVerified())
	   		{	
                          //System.out.println("u.isIsAdministrator() : " + u.getIsAdministrator());
//                        
	   			HttpSession session = request.getSession();
	   			session.setAttribute("user", u.getName());
                                
                                if(u.getIsAdministrator() == false){
                                    session.setAttribute("isAdministrator", "false");
                                }
                                else{
                                    session.setAttribute("isAdministrator", "true");
                                }
                                
	   			
	   			session.setAttribute("idUsuario", u.getId());
	   			session.setMaxInactiveInterval(30*60);
	   			Cookie userName = new Cookie("user", user);
	   			userName.setMaxAge(30*60);
	   			response.addCookie(userName);
	   			response.sendRedirect("index.jsp");
	   		}
	   		else{
	   		                      
	   			response.sendRedirect("login.html");
	   			
	   		}

	       }
	       else if (task.equalsIgnoreCase("cerrarsesionhttp"))
	       {
                   System.out.println("cerrarsesionhttp");
	    	response.setContentType("text/html");
	       	Cookie[] cookies = request.getCookies();
	       	if(cookies != null){
	       	for(Cookie cookie : cookies){
	       		if(cookie.getName().equals("JSESSIONID")){
	       			//System.out.println("JSESSIONID="+cookie.getValue());
	       			break;
	       		}
	       	}
	       	}
	       	//invalidate the session if exists
	       	HttpSession session = request.getSession(false);
	      // 	System.out.println("User="+session.getAttribute("user"));
	       	if(session != null){
	       		session.invalidate();
	       	}
	       	response.sendRedirect("login.html");
	       }
	       else if (task.equalsIgnoreCase("verificasesion"))
	       {
	    	HttpSession session = request.getSession();
	    	 if(session.getAttribute("user") == null)
	    		 
	    	 {System.out.println("User="+session.getAttribute("user"));
	    	   System.out.println("rol="+session.getAttribute("rol"));
	    		 response.sendRedirect("login_solicitudes.html");
	    		 return;
			
	    	 } 
	    	 else
	    	 {	UsuarioDTO u = new UsuarioDTO();
	    		 System.out.println("User="+session.getAttribute("user"));
		    	 System.out.println("rol="+session.getAttribute("rol"));
		    	 //u.isIsAdministrator(Boolean.parseBoolean(session.getAttribute("isAdministrator").toString()));
		    	 u.setId((Integer.parseInt(session.getAttribute("user").toString())));
		    	
		    	 response.setContentType("application/json;charset=UTF-8");
//		          try (PrintWriter out = response.getWriter())
//		          {
//		         out.println(new Gson().toJson(u));
//		          }
		    	 
	    	 }
	       }
		}
		catch (Exception e){
			e.printStackTrace();
		}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
