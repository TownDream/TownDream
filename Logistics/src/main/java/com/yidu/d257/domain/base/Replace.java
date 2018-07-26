package com.yidu.d257.domain.base;

import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;

public class Replace {

	private String replaceId;
	private String replaceManId;
	private String replaceName;
	private String replacedId;
	private String replacedName;
	private Date startTime;
	private Date endTime;
	private String organizationId;
	private String organizationName;
	private String useable;
	private String description;
	
	public Replace() {
		super();
	}

	public Replace(String replaceId, String replaceManId, String replaceName, String replacedId, String replacedName,
			Date startTime, Date endTime, String organizationId, String organizationName, String useable,
			String description) {
		super();
		this.replaceId = replaceId;
		this.replaceManId = replaceManId;
		this.replaceName = replaceName;
		this.replacedId = replacedId;
		this.replacedName = replacedName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.useable = useable;
		this.description = description;
	}

	public String getReplaceId() {
		return replaceId;
	}

	public void setReplaceId(String replaceId) {
		this.replaceId = replaceId;
	}

	public String getReplaceManId() {
		return replaceManId;
	}

	public void setReplaceManId(String replaceManId) {
		this.replaceManId = replaceManId;
	}

	public String getReplaceName() {
		return replaceName;
	}

	public void setReplaceName(String replaceName) {
		this.replaceName = replaceName;
	}

	public String getReplacedId() {
		return replacedId;
	}

	public void setReplacedId(String replacedId) {
		this.replacedId = replacedId;
	}

	public String getReplacedName() {
		return replacedName;
	}

	public void setReplacedName(String replacedName) {
		this.replacedName = replacedName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((organizationId == null) ? 0 : organizationId.hashCode());
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		result = prime * result + ((replaceId == null) ? 0 : replaceId.hashCode());
		result = prime * result + ((replaceManId == null) ? 0 : replaceManId.hashCode());
		result = prime * result + ((replaceName == null) ? 0 : replaceName.hashCode());
		result = prime * result + ((replacedId == null) ? 0 : replacedId.hashCode());
		result = prime * result + ((replacedName == null) ? 0 : replacedName.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((useable == null) ? 0 : useable.hashCode());
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
		Replace other = (Replace) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (organizationId == null) {
			if (other.organizationId != null)
				return false;
		} else if (!organizationId.equals(other.organizationId))
			return false;
		if (organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!organizationName.equals(other.organizationName))
			return false;
		if (replaceId == null) {
			if (other.replaceId != null)
				return false;
		} else if (!replaceId.equals(other.replaceId))
			return false;
		if (replaceManId == null) {
			if (other.replaceManId != null)
				return false;
		} else if (!replaceManId.equals(other.replaceManId))
			return false;
		if (replaceName == null) {
			if (other.replaceName != null)
				return false;
		} else if (!replaceName.equals(other.replaceName))
			return false;
		if (replacedId == null) {
			if (other.replacedId != null)
				return false;
		} else if (!replacedId.equals(other.replacedId))
			return false;
		if (replacedName == null) {
			if (other.replacedName != null)
				return false;
		} else if (!replacedName.equals(other.replacedName))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (useable == null) {
			if (other.useable != null)
				return false;
		} else if (!useable.equals(other.useable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Replace [replaceId=" + replaceId + ", replaceManId=" + replaceManId + ", replaceName=" + replaceName
				+ ", replacedId=" + replacedId + ", replacedName=" + replacedName + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", organizationId=" + organizationId + ", organizationName="
				+ organizationName + ", useable=" + useable + ", description=" + description + "]";
	}
}
