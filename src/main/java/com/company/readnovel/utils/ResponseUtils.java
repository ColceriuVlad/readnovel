package com.company.readnovel.utils;

import com.company.readnovel.model.GenericResponse;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {
    private DateUtils dateUtils;
    private Logger logger;

    public ResponseUtils(DateUtils dateUtils, Logger logger) {
        this.dateUtils = dateUtils;
        this.logger = logger;
    }

    public GenericResponse getGenericErrorResponse(Exception exception, Integer statusCode) {
        var date = dateUtils.getCurrentDate();
        var errorMessage = exception.getMessage();
        logger.error(errorMessage);
        var errorResponse = new GenericResponse(statusCode, date, errorMessage);
        return errorResponse;
    }

    public GenericResponse getGenericSuccessResponse(String message) {
        var statusCode = HttpStatus.OK.value();
        var date = dateUtils.getCurrentDate();
        logger.info(message);
        var successResponse = new GenericResponse(statusCode, date, message);
        return successResponse;
    }
}
