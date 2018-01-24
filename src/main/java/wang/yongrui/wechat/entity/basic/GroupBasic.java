/**
 * 
 */
package wang.yongrui.wechat.entity.basic;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class GroupBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Integer orderNumber;

	@Column(nullable = false)
	private boolean forPlan;

	@Column(nullable = false)
	private String groupUom;

	@Column(nullable = false)
	private String actionUom;

	@Column(nullable = false)
	private Integer quantityPerGroup;

	@Column(nullable = false)
	private Integer quantityPerAction;

	@Column
	private Integer executedQuantityPerGroup;

	@Column
	private Integer executedQuantityPerAction;

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
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
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
		GroupBasic other = (GroupBasic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		return true;
	}

}
