//package com.myshop.apiGateway.exceptions;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
//import org.springframework.core.annotation.MergedAnnotation;
//import org.springframework.core.annotation.MergedAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.reactive.function.server.ServerRequest;
//
//import java.util.Map;
//import java.util.logging.Logger;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler extends DefaultErrorAttributes {
//
//    public GlobalExceptionHandler() {
//    }
//
//    public GlobalExceptionHandler(boolean includeException) {
//        super();
//    }
//
//    @Override
//    public Map<String, Object> getErrorAttributes(ServerRequest request,
//                                                  boolean includeStackTrace) {
//        Throwable error = this.getError(request);
//        //logger.error("Error occured", error);
//        MergedAnnotation<ResponseStatus> responseStatusAnnotation = MergedAnnotations
//                .from(error.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(ResponseStatus.class);
//        HttpStatus errorStatus = findHttpStatus(error, responseStatusAnnotation);
//       // logger.info("errorStatus: {}", errorStatus);
//        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
//        String errorCode = getErrorCode(map, errorStatus);
//        map.remove("timestamp");
//        map.remove("path");
//        map.remove("error");
//        map.remove("requestId");
//        map.put("errorCode", errorCode);
//        return map;
//    }
//
//    private HttpStatus findHttpStatus(Throwable error, MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
//        if (error instanceof ResponseStatusException) {
//            return ((ResponseStatusException) error).getStatus();
//        }
//        return responseStatusAnnotation.getValue("code", HttpStatus.class).orElse(INTERNAL_SERVER_ERROR);
//    }
//
//    private String getErrorCode(Map<String, Object> map, HttpStatus errorStatus) {
//        String errorCode;
//        switch (errorStatus) {
//            case UNAUTHORIZED:
//                errorCode = "401 UnAuthorized";
//                break;
//            case NOT_FOUND:
//                logger.error("The url:{} is not found", map.get("path"));
//                errorCode = "404 Not Found";
//                map.put(MESSAGE, "NOT FOUND");
//                break;
//            case METHOD_NOT_ALLOWED:
//                logger.error("Invalid HTTP Method type for the url: {}", map.get("path"));
//                errorCode = "405 Method Not Allowed";
//                break;
//            default:
//                logger.error("Unexpected error happened");
//                logger.error("errorstatus is : {}", errorStatus);
//                errorCode = "500 Internal Server Error";
//                map.put(MESSAGE, "Unexpected Error");
//        }
//        return errorCode;
//    }
//}
