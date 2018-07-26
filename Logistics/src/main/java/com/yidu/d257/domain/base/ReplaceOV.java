package com.yidu.d257.domain.base;

import java.util.Date;

public class ReplaceOV {

	private String replaceId;
	private String rDispatcherNo;
	private String replaceName;
	private String rdDispatcherNo;
	private String replacedName;
	private Date startTime;
	private Date endTime;
	private String organizationId;
	private String organizationName;
	private String useable;
	private String description;
	
	public ReplaceOV() {
		super();
	}

	public ReplaceOV(String replaceId, String rDispatcherNo, String replaceName, String rdDispatcherNo,
			String replacedName, Date startTime, Date endTime, String organizationId, String organizationName,
			String useable, String description) {
		super();
		this.replaceId = replaceId;
		this.rDispatcherNo = rDispatcherNo;
		this.replaceName = replaceName;
		this.rdDispatcherNo = rdDispatcherNo;
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

	public String getrDispatcherNo() {
		return rDispatcherNo;
	}

	public void setrDispatcherNo(String rDispatcherNo) {
		this.rDispatcherNo = rDispatcherNo;
	}

	public String getReplaceName() {
		return replaceName;
	}

	public void setReplaceName(String replaceName) {
		this.replaceName = replaceName;
	}

	public String getRdDispatcherNo() {
		return rdDispatcherNo;
	}

	public void setRdDispatcherNo(String rdDispatcherNo) {
		this.rdDispatcherNo = rdDispatcherNo;
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
		result = prime * result + ((rDispatcherNo == null) ? 0 : rDispatcherNo.hashCode());
		result = prime * result + ((rdDispatcherNo == null) ? 0 : rdDispatcherNo.hashCode());
		result = prime * result + ((replaceId == null) ? 0 : replaceId.hashCode());
		result = prime * result + ((replaceName == null) ? 0 : replaceName.hashCode());
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
		ReplaceOV other = (ReplaceOV) obj;
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
		if (rDispatcherNo == null) {
			if (other.rDispatcherNo != null)
				return false;
		} else if (!rDispatcherNo.equals(other.rDispatcherNo))
			return false;
		if (rdDispatcherNo == null) {
			if (other.rdDispatcherNo != null)
				return false;
		} else if (!rdDispatcherNo.equals(other.rdDispatcherNo))
			return false;
		if (replaceId == null) {
			if (other.replaceId != null)
				return false;
		} else if (!replaceId.equals(other.replaceId))
			return false;
		if (replaceName == null) {
			if (other.replaceName != null)
				return false;
		} else if (!replaceName.equals(other.replaceName))
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
		return "ReplaceOV [replaceId=" + replaceId + ", rDispatcherNo=" + rDispatcherNo + ", replaceName=" + replaceName
				+ ", rdDispatcherNo=" + rdDispatcherNo + ", replacedName=" + replacedName + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", organizationId=" + organizationId + ", organizationName="
				+ organizationName + ", useable=" + useable + ", description=" + description + "]";
	}
}
