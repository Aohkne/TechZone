/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Le Huu Khoa - CE181099
 */
@WebFilter(urlPatterns = {"/Cart", "/Voucher"}) // Sử dụng @WebFilter để áp dụng cho URL /Cart
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get User data
        Cookie[] cookies = httpRequest.getCookies();
        String idUser = null; 
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    idUser = cookie.getValue();
                    break;
                }
            }
        }

        // Kiểm tra xem idUser có giá trị hay không
        if (idUser != null) {
            chain.doFilter(request, response); 
        } else {
            // Chuyển hướng về trang đăng nhập
            httpResponse.sendRedirect("/Login"); 
        }
    }

    @Override
    public void destroy() {
        // Cleanup nếu cần khi filter bị hủy
    }
}
