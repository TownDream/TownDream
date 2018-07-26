package com.yidu.d257.domain.base;

public class Dispatcher {

	private String dispatcherId;
	private String dispatcherNo;
	private String dispatcherName;
	private String phoneNumber;
	private String dispatcherTypeId;
	private String dispatcherType;
	private String usePDA;
	private String checkPwd;
	private String pickStandardId;
	private String pickStandardName;
	private String busId;
	private String busType;
	private String busNumber;
	private String organizationId;
	private String organizationName;
	private String useable;
	private String description;
	
	public Dispatcher() {
		super();
	}

	public Dispatcher(String dispatcherId, String dispatcherNo, String dispatcherName, String phoneNumber,
			String dispatcherTypeId, String dispatcherType, String usePDA, String checkPwd, String pickStandardId,
			String pickStandardName, String busId, String busType, String busNumber, String organizationId,
			String organizationName, String useable, String description) {
		super();
		this.dispatcherId = dispatcherId;
		this.dispatcherNo = dispatcherNo;
		this.dispatcherName = dispatcherName;
		this.phoneNumber = phoneNumber;
		this.dispatcherTypeId = dispatcherTypeId;
		this.dispatcherType = dispatcherType;
		this.usePDA = usePDA;
		this.checkPwd = checkPwd;
		this.pickStandardId = pickStandardId;
		this.pickStandardName = pickStandardName;
		this.busId = busId;
		this.busType = busType;
		this.busNumber = busNumber;
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.useable = useable;
		this.description = description;
	}

	public String getDispatcherId() {
		return dispatcherId;
	}

	public void setDispatcherId(String dispatcherId) {
		this.dispatcherId = dispatcherId;
	}

	public String getDispatcherNo() {
		return dispatcherNo;
	}

	public void setDispatcherNo(String dispatcherNo) {
		this.dispatcherNo = dispatcherNo;
	}

	public String getDispatcherName() {
		return dispatcherName;
	}

	public void setDispatcherName(String dispatcherName) {
		this.dispatcherName = dispatcherName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDispatcherTypeId() {
		return dispatcherTypeId;
	}

	public void setDispatcherTypeId(String dispatcherTypeId) {
		this.dispatcherTypeId = dispatcherTypeId;
	}

	public String getDispatcherType() {
		return dispatcherType;
	}

	public void setDispatcherType(String dispatcherType) {
		this.dispatcherType = dispatcherType;
	}

	public String getUsePDA() {
		return usePDA;
	}

	public void setUsePDA(String usePDA) {
		this.usePDA = usePDA;
	}

	public String getCheckPwd() {
		return checkPwd;
	}

	public void setCheckPwd(String checkPwd) {
		this.checkPwd = checkPwd;
	}

	public String getPickStandardId() {
		return pickStandardId;
	}

	public void setPickStandardId(String pickStandardId) {
		this.pickStandardId = pickStandardId;
	}

	public String getPickStandardName() {
		return pickStandardName;
	}

	public void setPickStandardName(String pickStandardName) {
		this.pickStandardName = pickStandardName;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
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
		result = prime * result + ((busId == null) ? 0 : busId.hashCode());
		result = prime * result + ((busNumber == null) ? 0 : busNumber.hashCode());
		result = prime * result + ((busType == null) ? 0 : busType.hashCode());
		result = prime * result + ((checkPwd == null) ? 0 : checkPwd.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dispatcherId == null) ? 0 : dispatcherId.hashCode());
		result = prime * result + ((dispatcherName == null) ? 0 : dispatcherName.hashCode());
		result = prime * result + ((dispatcherNo == null) ? 0 : dispatcherNo.hashCode());
		result = prime * result + ((dispatcherType == null) ? 0 : dispatcherType.hashCode());
		result = prime * result + ((dispatcherTypeId == null) ? 0 : dispatcherTypeId.hashCode());
		result = prime * result + ((organizationId == null) ? 0 : organizationId.hashCode());
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((pickStandardId == null) ? 0 : pickStandardId.hashCode());
		result = prime * result + ((pickStandardName == null) ? 0 : pickStandardName.hashCode());
		result = prime * result + ((usePDA == null) ? 0 : usePDA.hashCode());
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
		Dispatcher other = (Dispatcher) obj;
		if (busId == null) {
			if (other.busId != null)
				return false;
		} else if (!busId.equals(other.busId))
			return false;
		if (busNumber == null) {
			if (other.busNumber != null)
				return false;
		} else if (!busNumber.equals(other.busNumber))
			return false;
		if (busType == null) {
			if (other.busType != null)
				return false;
		} else if (!busType.equals(other.busType))
			return false;
		if (checkPwd == null) {
			if (other.checkPwd != null)
				return false;
		} else if (!checkPwd.equals(other.checkPwd))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dispatcherId == null) {
			if (other.dispatcherId != null)
				return false;
		} else if (!dispatcherId.equals(other.dispatcherId))
			return false;
		if (dispatcherName == null) {
			if (other.dispatcherName != null)
				return false;
		} else if (!dispatcherName.equals(other.dispatcherName))
			return false;
		if (dispatcherNo == null) {
			if (other.dispatcherNo != null)
				return false;
		} else if (!dispatcherNo.equals(other.dispatcherNo))
			return false;
		if (dispatcherType == null) {
			if (other.dispatcherType != null)
				return false;
		} else if (!dispatcherType.equals(other.dispatcherType))
			return false;
		if (dispatcherTypeId == null) {
			if (other.dispatcherTypeId != null)
				return false;
		} else if (!dispatcherTypeId.equals(other.dispatcherTypeId))
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
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (pickStandardId == null) {
			if (other.pickStandardId != null)
				return false;
		} else if (!pickStandardId.equals(other.pickStandardId))
			return false;
		if (pickStandardName == null) {
			if (other.pickStandardName != null)
				return false;
		} else if (!pickStandardName.equals(other.pickStandardName))
			return false;
		if (usePDA == null) {
			if (other.usePDA != null)
				return false;
		} else if (!usePDA.equals(other.usePDA))
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
		return "Dispatcher [dispatcherId=" + dispatcherId + ", dispatcherNo=" + dispatcherNo + ", dispatcherName="
				+ dispatcherName + ", phoneNumber=" + phoneNumber + ", dispatcherTypeId=" + dispatcherTypeId
				+ ", dispatcherType=" + dispatcherType + ", usePDA=" + usePDA + ", checkPwd=" + checkPwd
				+ ", pickStandardId=" + pickStandardId + ", pickStandardName=" + pickStandardName + ", busId=" + busId
				+ ", busType=" + busType + ", busNumber=" + busNumber + ", organizationId=" + organizationId
				+ ", organizationName=" + organizationName + ", useable=" + useable + ", description=" + description
				+ "]";
	}
}

