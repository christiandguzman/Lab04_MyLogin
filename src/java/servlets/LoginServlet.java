/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import models.UserService;
import models.User;
import java.io.IOException;

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

    
    Cookie cookie;
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //get session
        HttpSession session = request.getSession();
        //CHECK FOR COOKIES
        if(cookie != null){
            request.setAttribute("username",cookie.getValue());
             getServletContext()
                    .getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        }
        if(request.getParameter("home")!=null){
            session.invalidate();
            request.setAttribute("invalid", "Succesfully Logged out");
             getServletContext()
                    .getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        }
         getServletContext()
                    .getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        

        
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
        
        User u = UserService.login(username,password);
  
        
        if( u!=null){              
            HttpSession session = request.getSession();

           if(request.getParameter("remember")!=null){       
                cookie = new Cookie("cookie",username);
                response.addCookie(cookie);
                
           } else {
               
                 Cookie[]list = request.getCookies();
                 if (list!= null)
                    for (Cookie check : list) {
                        check.setValue("");
                        check.setPath("/");
                        check.setMaxAge(0);
                        response.addCookie(check);
                }
           }
           session.setAttribute("username",username);
           response.sendRedirect("home");
                
        }else{
            request.setAttribute("username", username);           
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);  

        }           

    }   

   
}
