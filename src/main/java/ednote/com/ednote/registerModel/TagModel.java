package ednote.com.ednote.registerModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tag_table")
public class TagModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tagName;
	
	private String name;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="email")
	private RegisterModel registerModel;
	
	public TagModel() {
		
	}

	public TagModel(int id, String tagName, String name, RegisterModel registerModel) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.name = name;
		this.registerModel = registerModel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	public RegisterModel getRegisterModel() {
		return registerModel;
	}
	@JsonIgnore
	public void setRegisterModel(RegisterModel registerModel) {
		this.registerModel = registerModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((registerModel == null) ? 0 : registerModel.hashCode());
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
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
		TagModel other = (TagModel) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (registerModel == null) {
			if (other.registerModel != null)
				return false;
		} else if (!registerModel.equals(other.registerModel))
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}

	

}
