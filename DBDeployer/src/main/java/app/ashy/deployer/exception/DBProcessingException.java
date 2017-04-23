package app.ashy.deployer.exception;

public class DBProcessingException extends RuntimeException {

	private static final long serialVersionUID = -7068512771074416209L;
	private String message;
	private Throwable cause;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public DBProcessingException() {
		super();
	}

	public DBProcessingException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.cause = cause;
	}

	public DBProcessingException(String message) {
		super(message);
		this.message = message;
	}

	public DBProcessingException(Throwable cause) {
		super(cause);
		this.cause = cause;
	}

}
