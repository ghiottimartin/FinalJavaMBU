package utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Throwable innerException;
	private String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ApplicationException(Throwable e, String message){
		this.innerException=e;
		this.setMessage(message);
	}
	
	public ApplicationException(Throwable e, String message, Level errorLevel){
		this(e,message);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel,message);
	}

}