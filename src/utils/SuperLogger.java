package utils;

import org.apache.logging.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class SuperLogger {
	public static final Logger logger = 
			LogManager.getLogger(SuperLogger.class.getName());

}