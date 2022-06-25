package com.rpsate.Advice;

import com.rpsate.expection.PermissionDenied;
import com.rpsate.expection.TokenNullException;
import com.rpsate.response.RespCodeMsg;
import com.rpsate.response.RespData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.apache.tomcat.util.http.fileupload.impl.SizeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * File over size
     * @return
     */
    @ExceptionHandler(SizeException.class)
    public RespData doSizeException() {
        return new RespData(RespCodeMsg.IMAGE_OVER_SIZE, null);
    }

    /**
     * Parameter error
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class, HttpRequestMethodNotSupportedException.class})
    public RespData doIllegalArgumentExceptionAndHttpRequestMethodNotSupportedException() {
        return new RespData(RespCodeMsg.PARAMETER_ERR, null);
    }

    /**
     * Access denied
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public RespData doAccessDeniedException() {
        return new RespData(RespCodeMsg.ACCESS_DENIED, null);
    }

    /**
     * Token expired
     * @return
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public RespData doExpiredJwtException() {
        return new RespData(RespCodeMsg.TOKEN_EXPIRED, null);
    }

    /**
     * Token empty
     * @return
     */
    @ExceptionHandler(TokenNullException.class)
    public RespData doTokenNullException() {
        return new RespData(RespCodeMsg.TOKEN_EMPTY, null);
    }

    /**
     * Token invalid
     * @return
     */
    @ExceptionHandler(JwtException.class)
    public RespData doJwtException() {
        return new RespData(RespCodeMsg.TOKEN_INVALID, null);
    }

    @ExceptionHandler(PermissionDenied.class)
    public RespData doPermissionDenied() {
        return new RespData(RespCodeMsg.PERMISSION_DENIED, null);
    }

    /**
     * Exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public RespData doException() {
        return new RespData(RespCodeMsg.EXCEPTION, null);
    }
}
