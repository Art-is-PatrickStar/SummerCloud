package com.wsw.summercloud.user.handler;

import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.basic.ResultStatus;
import com.wsw.summercloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2022/8/8 17:07
 */
@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 自定义异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BusinessException.class)
    public Result<String> businessExceptionHandler(BusinessException e) {
        log.error("全局异常 - businessExceptionHandler: ", e);
        return Result.fail(e.getResultStatusEnums());
    }

    /**
     * SQL语法异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public Result<String> badSqlGrammarHandler(BadSqlGrammarException e) {
        log.error("全局异常 - badSqlGrammarHandler: ", e);
        return Result.fail(ResultStatus.SQL_ERROR_EXCEPTION, ResultStatus.SQL_ERROR_EXCEPTION.getMsg() + e.getMessage());
    }

    /**
     * IO异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = IOException.class)
    public Result<String> ioExceptionHandler(IOException e) {
        log.error("全局异常 - ioExceptionHandler: ", e);
        return Result.fail(ResultStatus.IO_EXCEPTION, ResultStatus.IO_EXCEPTION.getMsg() + ": " + e.getMessage());
    }

    /**
     * 默认异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        log.error("全局异常 - exceptionHandler: ", e);
        return Result.fail(ResultStatus.SYSTEM_EXCEPTION, ResultStatus.SYSTEM_EXCEPTION.getMsg() + ": " + e.getMessage());
    }
}
