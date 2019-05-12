package utils;

import org.apache.log4j.Level;
import org.apache.logging.*;

public class ApplicationException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ApplicationException(){
		super();
		SuperLogger.logger.error("Empty exception");
	}
	
	public ApplicationException(String message){
		super(message);
		SuperLogger.logger.log(Level.ERROR, message);
	}
	
	public ApplicationException(String message, Throwable cause){
		super(message,cause);
		SuperLogger.logger.log(Level.ERROR, message, cause);
		
	}
}
