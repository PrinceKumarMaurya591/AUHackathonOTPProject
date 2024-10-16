package com.au.otp.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ErrorResponse {
    private String statusCode;
    private String subcode;
    private String respMessage;
    private LocalDateTime timestamp;
    private String type;
    private List<ValidationError> errors;
    private String path;

   
    public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getSubcode() {
		return subcode;
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	public String getRespMessage() {
		return respMessage;
	}

	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

	public ErrorResponse(String statusCode, String subcode, String respMessage, LocalDateTime timestamp, String type,
			List<ValidationError> errors, String path) {
		super();
		this.statusCode = statusCode;
		this.subcode = subcode;
		this.respMessage = respMessage;
		this.timestamp = timestamp;
		this.type = type;
		this.errors = errors;
		this.path = path;
	}


	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	private static class ValidationError {
        private final String field;
        private final String message;
		public String getField() {
			return field;
		}
		public String getMessage() {
			return message;
		}
		public ValidationError(String field, String message) {
			super();
			this.field = field;
			this.message = message;
		}
		
        
        
    }

    public void addValidationError(String field, String message) {
        if (Objects.isNull(errors)) {
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }
}
