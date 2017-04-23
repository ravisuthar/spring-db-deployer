package app.ashy.deployer.exception;

public class DBSchemaException extends RuntimeException {

	private static final long serialVersionUID = -7209855229058705690L;
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

	public DBSchemaException() {
		super();
	}

	public DBSchemaException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.cause = cause;
	}

	public DBSchemaException(String message) {
		super(message);
		this.message = message;
	}

	public DBSchemaException(Throwable cause) {
		super(cause);
		this.cause = cause;
	}
	
}
