package ndhc.cloud.logic.mpgenerator.model;

/**
 * Created with IDEA
 * Date on 2017/4/7
 * Time on 12:02
 */
public class ResultModel {

    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum Type {
        SUCCESS(0, "success"),
        PARAMETERS_ERROR(-1001, "参数不匹配。"),
        APP_ERROR(-5000, "服务器内部错误，攻城狮正在修复。"),
        COMMON_PARAMETER_ERROR(-996, "参数有误"),
        COMMON_NOT_FIND(-994, "资源不存在或已删除.");
        public int code;
        public String message;
        Type(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }


    /**
     * 构建错误信息
     *
     * @param type 错误枚举
     * @return
     */
    public static ResultModel buildError(Type type) {
        ResultModel res = new ResultModel();
        res.setCode(type.code);
        res.setMessage(type.message);
        return res;
    }

    /**
     * 构建错误信息
     *
     * @param type    错误枚举
     * @param message 自定义错误信息
     * @return
     */
    public static ResultModel buildError(Type type, String message) {
        ResultModel res = new ResultModel();
        res.setCode(type.code);
        res.setMessage(message);
        return res;
    }

 /*   *//**
     * 根据 msgcode 构建错误信息
     *
     * @param code
     * @return
     *//*
    public static ResultModel buildError(MsgCode code) {
        ResultModel res = new ResultModel();
        res.setCode(Integer.parseInt(code.getCode()));
        res.setMessage(code.getCode());
        return res;
    }*/

    /**
     * 构建 异常消息
     *
     * @param ex 异常信息
     * @return
     */
    public static ResultModel buildException(Exception ex) {
        ResultModel res = new ResultModel();
        res.setCode(Type.APP_ERROR.code);
        res.setMessage(Type.APP_ERROR.message);
        StringBuilder data = new StringBuilder();
        data.append(ex.toString());
        for (StackTraceElement item : ex.getStackTrace()) {
            data.append(" at 【 ").append(item.getClassName()).append(".").append(item.getMethodName())
                    .append("( ").append(item.getFileName()).append(" : ").append(item.getLineNumber()).append(" ) 】 ");
            break;
        }
        res.setData(data.toString());
        return res;
    }

    /**
     * 构建成功信息
     *
     * @param data 返回的数据
     * @return
     */
    public static ResultModel buildSuccess(Object data) {
        ResultModel res = new ResultModel();
        res.setCode(Type.SUCCESS.code);
        res.setMessage(Type.SUCCESS.message);
        res.setData(data);
        return res;
    }


}
