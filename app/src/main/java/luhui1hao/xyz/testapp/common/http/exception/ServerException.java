package luhui1hao.xyz.testapp.common.http.exception;

/**
 * Created by shaoyang on 2018/1/15.
 * Http server exception
 */

public class ServerException extends RuntimeException {
    private int code;
    private ErrorBean errorBean;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ErrorBean getErrorBean() {
        return errorBean;
    }

    public void setErrorBean(ErrorBean errorBean) {
        this.errorBean = errorBean;
    }

    public static class ErrorBean {
        /**
         * timestamp : 2020-02-19T01:19:34.003+0000
         * status : 500
         * error : Internal Server Error
         * message :
         * path : /productApp/info/04f2a9483a7c11eaa9538cec4bac430a
         */

        private String timestamp;
        private int status;
        private String error;
        private String message;
        private String trace;
        private String path;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTrace() {
            return trace;
        }

        public void setTrace(String trace) {
            this.trace = trace;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

//        private String error;
//        private String error_description;
//
//        public String getError() {
//            return error;
//        }
//
//        public void setError(String error) {
//            this.error = error;
//        }
//
//        public String getError_description() {
//            return error_description;
//        }
//
//        public void setError_description(String error_description) {
//            this.error_description = error_description;
//        }
    }
}
