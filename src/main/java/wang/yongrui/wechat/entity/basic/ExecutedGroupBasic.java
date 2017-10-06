/**
 * 
 */
package wang.yongrui.wechat.entity.basic;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class ExecutedGroupBasic extends GroupBasic {

	private Integer executedQuantity;

	private Double executedWeight;

}
