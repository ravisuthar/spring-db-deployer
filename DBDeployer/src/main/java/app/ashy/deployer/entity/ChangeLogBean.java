package app.ashy.deployer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHANGELOG")
public class ChangeLogBean {

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	private long identifier;
	@Column(name = "TYPE", nullable = false)
	private String type;
	@Column(name = "OPERATION", nullable = false)
	private String operation;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "APPLIEDDATE", nullable = false)
	private Date appliedDate;
	@Column(name = "APPLIEDBY", nullable = false)
	private String appliedBy;

	public long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(long identifier) {
		this.identifier = identifier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (identifier ^ (identifier >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangeLogBean other = (ChangeLogBean) obj;
		if (identifier != other.identifier)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
