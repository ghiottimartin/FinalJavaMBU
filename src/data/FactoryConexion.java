package data;

import java.sql.*;

import utils.*;

public class FactoryConexion {
	
	private String dbDriver="com.mysql.jdbc.Driver";
	private String host="node216028-fightclub.j.layershift.co.uk";
	//private String host="localhost";
	private String port="3306";
	private String user="root";
	private String pass="RXOkrz43548";
	//private String pass="root";
	private String db="db_tp_java";
	private String dbType="mysql";
	
	private Connection conn;
	private int cantConn=0;
	
	private FactoryConexion() throws ApplicationException{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e, "Error del Driver JDBC.\n"+e.getMessage());
		}
	}
	
	private static FactoryConexion instancia;
	
	public static FactoryConexion getInstancia() throws ApplicationException{
		if (instancia==null){
			instancia = new FactoryConexion();
		}
		return instancia;
	}
	
	public Connection getConn() throws ApplicationException{
		try {
			if(conn==null || conn.isClosed()){
				conn = DriverManager.getConnection(
						"jdbc:"+dbType+"://"+host+":"+port+"/"+
						db+"?user="+user+"&password="+pass);
				cantConn++;
			}
		} catch (SQLException e) {
			throw new ApplicationException(e, "Error al conectar a la DB.\n"+e.getMessage());
		}
		return conn;
	}
	
	public void releaseConn() throws ApplicationException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw new ApplicationException(e, "Error al cerrar conexi�n.\n"+e.getMessage());
		}
		
	}

}
