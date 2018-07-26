package com.yidu.d257.domain.base;

public class AssistantDocument {

	private String assistantDocumentId;
	private String assistantDocumentNo;
	private String assistantDocumentName;
	private String baseDocumentId;
	private String baseDocumentNo;
	private String shortName;
	private String useable;
	private String description;
	public AssistantDocument() {
		super();
	}
	public AssistantDocument(String assistantDocumentId, String assistantDocumentNo, String assistantDocumentName,
			String baseDocumentId, String baseDocumentNo, String shortName, String useable, String description) {
		super();
		this.assistantDocumentId = assistantDocumentId;
		this.assistantDocumentNo = assistantDocumentNo;
		this.assistantDocumentName = assistantDocumentName;
		this.baseDocumentId = baseDocumentId;
		this.baseDocumentNo = baseDocumentNo;
		this.shortName = shortName;
		this.useable = useable;
		this.description = description;
	}
	public String getAssistantDocumentId() {
		return assistantDocumentId;
	}
	public void setAssistantDocumentId(String assistantDocumentId) {
		this.assistantDocumentId = assistantDocumentId;
	}
	public String getAssistantDocumentNo() {
		return assistantDocumentNo;
	}
	public void setAssistantDocumentNo(String assistantDocumentNo) {
		this.assistantDocumentNo = assistantDocumentNo;
	}
	public String getAssistantDocumentName() {
		return assistantDocumentName;
	}
	public void setAssistantDocumentName(String assistantDocumentName) {
		this.assistantDocumentName = assistantDocumentName;
	}
	public String getBaseDocumentId() {
		return baseDocumentId;
	}
	public void setBaseDocumentId(String baseDocumentId) {
		this.baseDocumentId = baseDocumentId;
	}
	public String getBaseDocumentNo() {
		return baseDocumentNo;
	}
	public void setBaseDocumentNo(String baseDocumentNo) {
		this.baseDocumentNo = baseDocumentNo;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
		result = prime * result + ((assistantDocumentId == null) ? 0 : assistantDocumentId.hashCode());
		result = prime * result + ((assistantDocumentName == null) ? 0 : assistantDocumentName.hashCode());
		result = prime * result + ((assistantDocumentNo == null) ? 0 : assistantDocumentNo.hashCode());
		result = prime * result + ((baseDocumentId == null) ? 0 : baseDocumentId.hashCode());
		result = prime * result + ((baseDocumentNo == null) ? 0 : baseDocumentNo.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
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
		AssistantDocument other = (AssistantDocument) obj;
		if (assistantDocumentId == null) {
			if (other.assistantDocumentId != null)
				return false;
		} else if (!assistantDocumentId.equals(other.assistantDocumentId))
			return false;
		if (assistantDocumentName == null) {
			if (other.assistantDocumentName != null)
				return false;
		} else if (!assistantDocumentName.equals(other.assistantDocumentName))
			return false;
		if (assistantDocumentNo == null) {
			if (other.assistantDocumentNo != null)
				return false;
		} else if (!assistantDocumentNo.equals(other.assistantDocumentNo))
			return false;
		if (baseDocumentId == null) {
			if (other.baseDocumentId != null)
				return false;
		} else if (!baseDocumentId.equals(other.baseDocumentId))
			return false;
		if (baseDocumentNo == null) {
			if (other.baseDocumentNo != null)
				return false;
		} else if (!baseDocumentNo.equals(other.baseDocumentNo))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
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
		return "assistantDocument [assistantDocumentId=" + assistantDocumentId + ", assistantDocumentNo="
				+ assistantDocumentNo + ", assistantDocumentName=" + assistantDocumentName + ", baseDocumentId="
				+ baseDocumentId + ", baseDocumentNo=" + baseDocumentNo + ", shortName=" + shortName + ", useable="
				+ useable + ", description=" + description + "]";
	}
}
