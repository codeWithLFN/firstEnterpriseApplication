/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.tut;

import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import za.tut.Entity.Item;
import za.tut.Service.ItemService;
import za.tut.Service.SumService;

/**
 *
 * @author CodeWithLufuno
 */
@WebServlet(name = "ItemServelet", urlPatterns = {"/ItemServelet"})
public class ItemServlet extends HttpServlet {
    
    //Inject the local session bean
    @EJB
    ItemService itemService;

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
            throws ServletException, IOException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ItemServlet</title>");
            out.println("</head>");
            out.println("<body>");
            
            //JNDI to lookup for all our remote seeions
            InitialContext context = new InitialContext();
            
            // Intantiate your session bean
            SumService sumService = (SumService) context.lookup("za.ac.tut.service.SumService");
                    
            String decide = (String) request.getParameter("select");
                    
            //INterpret the request
            if (decide.equals("add item"))
                {
                    Item item = new Item();
                    item.setId(Integer.parseInt(request.getParameter("id")));
                    item.setName(request.getParameter("name"));
                    item.setPrice(Double.parseDouble(request.getParameter("price")));
                        
                    itemService.add(item);
                    out.println("<h1>" + item.toString() + "is added to list</h1>");
                 
                }
                else if (decide.equals("remove"))
                {
                   int id = Integer.parseInt(request.getParameter("id"));
                   itemService.deleteItem(id);
                   out.println("<h1> Item id " + id + " is deleted </h1>");
                }
                else if (decide.equals("display"))
                {
                    List<Item> items = itemService.getAll();
                        
                    for (Item item : items)
                    {
                        out.println("<h1>" + item.toString() + "is added to list</h1>");
                    }
                }
                else if (decide.equals("add"))
                {
                    int sum = sumService.add(23, 3, 4);
                    out.println("<h1>Sum is" + sum + "</h1>");
               }
                    
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
