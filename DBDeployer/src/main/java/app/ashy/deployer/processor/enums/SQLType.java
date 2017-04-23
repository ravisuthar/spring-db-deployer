/*
 * Copyright 2017 Ashish Gundewad

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
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
