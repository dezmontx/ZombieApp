package com.zombie.app.interceptor;

import com.zombie.app.service.AuthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = "*")
public class RestInterceptor implements HandlerInterceptor {
    private static final int UNAUTHORIZED_CODE = 401;
    private static final String LOGIN_PATH = "/rest/login";
    private static final String LOGOUT_PATH = "/rest/logout";
    private static final String REGISTER_PATH = "/rest/register";
    private static final String TOKEN_HEADER = "token";

    private final AuthService authService;

    public RestInterceptor(AuthService authService) {this.authService = authService;}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String path = request.getServletPath();

        if (LOGIN_PATH.equals(path) ||
            LOGOUT_PATH.equals(path) ||
            REGISTER_PATH.equals(path)) {
            return true;
        }

        if (!authService.checkZombie(request.getHeader(TOKEN_HEADER))) {
            response.getWriter().write("Token is not valid");
            response.setStatus(UNAUTHORIZED_CODE);

            return false;
        }

        return false;
    }
}
