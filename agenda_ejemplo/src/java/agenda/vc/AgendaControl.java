/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.vc;

import local.*;
import agenda.modelo.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author curso
 */
@WebServlet(name = "AgendaControl", urlPatterns = {"*.do", "/logoff"})
public class AgendaControl extends HttpServlet {

    private final String error = "/error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion, next = error;

        try {
            String path = request.getServletPath();
            String s[] = Wutil.getURLData(path);
            accion = s[1] == ".do" ? s[2] : s[0];
            accion = accion.toLowerCase().intern();

            String user = null;
            Principal p = request.getUserPrincipal();
            if (p != null) {
                user = p.getName();
            }

            HttpSession session = request.getSession();

            do {
                if (accion == "logoff") {
                    session.invalidate();
                    next = "index.html";
                    break;
                }

                if (accion == "lista" && user != null) {
                    request.setAttribute("user", user);

                    AgendaDao dao = new AgendaDao(false);
                    List<AgendaBean> lista = dao.getAgendaByUserag(user);
                    request.setAttribute("lista", lista);

                    next = "agenda.jsp";
                    break;
                }

            } while (false);
            
        }
            catch (Exception e) {
            next = error;
        }

            RequestDispatcher rd = request.getRequestDispatcher(next);
            rd.forward(request, response);
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
