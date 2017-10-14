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

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.enumeration.Grade;
import wang.yongrui.wechat.entity.enumeration.Purpose;
import wang.yongrui.wechat.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class PlanBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Purpose purpose;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar fromDate;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar toDate;

	@Column(nullable = false)
	private boolean predefined;

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
		PlanBasic other = (PlanBasic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
