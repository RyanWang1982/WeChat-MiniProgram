/**
 *
 */
package wang.yongrui.wechat.entity.basic;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.enumeration.Gender;
import wang.yongrui.wechat.entity.enumeration.Grade;
import wang.yongrui.wechat.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class UserBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String loginName;

	@Column(unique = true)
	private String wechatUnionId;

	@Column(unique = true)
	private String wechatMPOpenId;

	private String nickName;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String mobileNumber;

	@Column(unique = true)
	private String email;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	@ApiModelProperty(required = true)
	private Gender gender;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(required = true, example = "yyyy-MM-dd")
	private Calendar dateOfBirth;

	@Column(unique = true)
	private String avatarUrl;

	@Column(nullable = true)
	@Enumerated(value = EnumType.STRING)
	private Grade grade;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBasic other = (UserBasic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
