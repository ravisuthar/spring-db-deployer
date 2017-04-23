/**
 * 
 */
package app.ashy.deployer.processor.enums;

/**
 * This Enum defines the types od SQL statements supported by this library.
 * Basically, there are three types of SQL statements ex. DDL, DML, PFT. These
 * are also the folder names which will have the SQL files stored by it's types
 * and will be executed by below sequence.
 * <p>
 * <code>
 *  DLL: Data Defination Language <br/>
 * 	DML: Data Manipulation Language <br/>
 *  PFT: Procedure, Function and Trigger <br/>
 * </code>
 * </p>
 * 
 * @author Ashish
 *
 */
public enum SQLType {

	DLL("DLL"), PFT("PFT"), DML("DML");

	private String sqlType;

	private SQLType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String toString() {
		return this.sqlType;
	}
}
