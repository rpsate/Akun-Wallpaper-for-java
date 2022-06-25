package com.rpsate.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import com.rpsate.expection.PermissionDenied;
import com.rpsate.expection.TokenNullException;
import com.rpsate.pojo.User;
import com.rpsate.response.RespCodeMsg;
import com.rpsate.response.RespData;
import com.rpsate.utils.TokenProcessor;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Value("${jwt.secret}")
    private String secret;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String bearer = request.getHeader(TokenProcessor.Authorization);
        String token = TokenProcessor.getTokenFromBearer(bearer);
        if (token == null || token == "") {
            throw new TokenNullException(RespCodeMsg.MSG.get(RespCodeMsg.TOKEN_EMPTY));
        }
        User user = TokenProcessor.getUserFromToken(token, secret);
        if (user == null || user.getGrade() == null || user.getGrade() < 1)
            throw new PermissionDenied();
        return true;
    }
}
