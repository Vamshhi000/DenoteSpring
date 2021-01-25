package ednote.com.ednote.registerModel;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "image")
public class ImgModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "type")
	private String type;
	
	@Column(name = "pic_byte", length = 1000)
	private byte[] picByte;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="email")
	private RegisterModel registerModel;
	
	public ImgModel() {
		
	}

	public ImgModel(String name, String type, byte[] picByte) {
		super();
		this.name = name;
		this.type = type;
		this.picByte = picByte;

	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
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
		result = prime * result + Arrays.hashCode(picByte);
		result = prime * result + ((registerModel == null) ? 0 : registerModel.hashCode());
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
		ImgModel other = (ImgModel) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(picByte, other.picByte))
			return false;
		if (registerModel == null) {
			if (other.registerModel != null)
				return false;
		} else if (!registerModel.equals(other.registerModel))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	






	



	
}
