package com.snow.cs.enums;

import com.snow.cs.exception.BizException;
import com.snow.cs.model.response.Response;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * @author JonSnow
 * @description 响应枚举
 * @date 2022/10/10
 */
public enum ResponseEnum {

    /**
     * 业务异常
     */
    BIZ_EXCEPTION {
        @Override
        public boolean validate(Exception e) {
            return e instanceof BizException;
        }

        @Override
        public Response<Object> process(Exception e) {
            BizException bizException = (BizException) e;
            return Response.error(bizException.getCode(), bizException.getMsg());
        }
    },
    /**
     * METHOD_ARGUMENT_NOT_VALID_EXCEPTION
     */
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION {
        @Override
        public boolean validate(Exception e) {
            return e instanceof MethodArgumentNotValidException;
        }

        @Override
        public Response<Object> process(Exception e) {
            List<ObjectError> errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            return getResponse(errors);
        }
    },
    /**
     * BIND_EXCEPTION
     */
    BIND_EXCEPTION {
        @Override
        public boolean validate(Exception e) {
            return e instanceof BindException;
        }

        @Override
        public Response<Object> process(Exception e) {
            BindException bindException = (BindException) e;
            List<ObjectError> errors = bindException.getBindingResult().getAllErrors();
            return getResponse(errors);
        }
    },
    /**
     * CONSTRAINT_VIOLATION_EXCEPTION
     */
    CONSTRAINT_VIOLATION_EXCEPTION {
        @Override
        public boolean validate(Exception e) {
            return e instanceof ConstraintViolationException;
        }

        @Override
        public Response<Object> process(Exception e) {
            ConstraintViolationException exception = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
            return getResponse(constraintViolations);
        }
    },
    /**
     * DEFAULT
     */
    DEFAULT {
        @Override
        public boolean validate(Exception e) {
            return false;
        }

        @Override
        public Response<Object> process(Exception e) {
            return Response.error(ResponseCode.SERVER_ERROR.getCode(), ResponseCode.SERVER_ERROR.getMsg());
        }
    };

    abstract boolean validate(Exception e);

    public abstract Response<Object> process(Exception e);

    private static final EnumSet<ResponseEnum> KNOWN_EXCEPTIONS = EnumSet.of(BIZ_EXCEPTION, BIND_EXCEPTION,
            METHOD_ARGUMENT_NOT_VALID_EXCEPTION, CONSTRAINT_VIOLATION_EXCEPTION);

    public static ResponseEnum cast(Exception e) {
        for (ResponseEnum res : KNOWN_EXCEPTIONS) {
            if (res.validate(e)) {
                return res;
            }
        }
        return DEFAULT;
    }


    private static final String PARAMS_ERROR_TEMPLATE = "参数名:%s,错误信息:%s";

    Response<Object> getResponse(List<ObjectError> errors) {
        String msg = ResponseCode.PARAMS_ERROR.getMsg();
        if (!CollectionUtils.isEmpty(errors)) {
            FieldError error = (FieldError) errors.get(0);
            msg = String.format(PARAMS_ERROR_TEMPLATE, error.getField(), error.getDefaultMessage());
        }
        return Response.error(ResponseCode.PARAMS_ERROR.getCode(), msg);
    }

    Response<Object> getResponse(Set<ConstraintViolation<?>> errors) {
        String msg = ResponseCode.PARAMS_ERROR.getMsg();
        if (!CollectionUtils.isEmpty(errors)) {
            ConstraintViolation<?> violation = errors.iterator().next();
            msg = violation.getMessageTemplate();
        }
        return Response.error(ResponseCode.PARAMS_ERROR.getCode(), msg);
    }
}
