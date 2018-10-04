/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import models.CookieUtil;
import models.UserService;
import models.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 683676
 */
public class LoginServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //get session
        HttpSession session = request.getSession();
        Cookie o = (Cookie) session.getAttribute("cookie");
        //CHECK FOR COOKIES
        if(o != null){
            request.setAttribute("username",o.getValue()); 
        }
        
        if(request.getParameter("logout")!=null){
            session.invalidate();
            request.setAttribute("invalid", "Succesfully Logged out");
             getServletContext()
                    .getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        }
        
        
        
        
        
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
         
        
        //check if the textfields are empty
        if(username ==null ||username.equalsIgnoreCase("") 
                ||password==null ||password.equalsIgnoreCase("")){
            request.setAttribute("invalid","Invalid");
            
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);  
            return;
        }
        
        //create the user object to check in the login ()
        User u = new User(username, password);
        
        HttpSession session = request.getSession();
        //
        if( UserService.login(u)!=null){
           if(request.getParameter("remember")!=null){
               Cookie o =  new Cookie("username",u.getUsername());
               session.setAttribute("cookie",o);
           } else {
            Cookie cookie = (Cookie)session.getAttribute("cookie");
            cookie.setMaxAge(0); //delete the cookie
            cookie.setPath("/"); //allow the download application to access it
            
           }

        }else{
            session.setAttribute("username",u.getUsername());
            request.setAttribute("invalid","Username or password are invalid");
            response.sendRedirect("home");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);  
    }   

   
}
