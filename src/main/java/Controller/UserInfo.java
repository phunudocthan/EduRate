/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author Bang
 */
public class UserInfo extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserInfo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserInfo at " + request.getContextPath() + "</h1>");
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
        String path = request.getRequestURI();
        if (path.startsWith("/UserInfo/User/")) {
            String[] getUsId = path.split("/");
            String id = getUsId[getUsId.length - 1];
            request.setAttribute("UserId", id);
            request.getRequestDispatcher("/UserPage.jsp").forward(request, response);
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
    String action = request.getParameter("action");
    UserDAO userDAO = new UserDAO();
    HttpSession session = request.getSession();
    String userId = request.getParameter("userId");

    if ("updateInfo".equals(action)) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthday");
        int tagId = Integer.parseInt(request.getParameter("tag"));
        int genderId = Integer.parseInt(request.getParameter("gender"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int province = Integer.parseInt(request.getParameter("province"));
        int district = Integer.parseInt(request.getParameter("district"));
        int ward = Integer.parseInt(request.getParameter("ward"));
        
        User user = new User(userId, firstName, lastName, Date.valueOf(birthDate), tagId, genderId, province, district, ward, "");
        
        int result = userDAO.updateUserInfo(user, phone, email);
        if (result > 0) {
            session.setAttribute("successMessage", "Thông tin đã được cập nhật thành công!");
        } else {
            session.setAttribute("errorMessage", "Cập nhật thông tin thất bại. Vui lòng thử lại.");
        }
    } else if ("changePassword".equals(action)) {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        
        try {
            int result = userDAO.changePassword(userId, currentPassword, newPassword);
            if (result > 0) {
                session.setAttribute("successMessage", "Mật khẩu đã được thay đổi thành công!");
            } else {
                session.setAttribute("errorMessage", "Thay đổi mật khẩu thất bại. Vui lòng kiểm tra lại mật khẩu hiện tại.");
            }
        } catch (SQLException e) {
            session.setAttribute("errorMessage", "Đã xảy ra lỗi khi thay đổi mật khẩu. Vui lòng thử lại.");
            e.printStackTrace();
        }
    }
    
    // Chuyển hướng về trang chủ sau khi cập nhật
    response.sendRedirect(request.getContextPath() + "/index.jsp");
}}