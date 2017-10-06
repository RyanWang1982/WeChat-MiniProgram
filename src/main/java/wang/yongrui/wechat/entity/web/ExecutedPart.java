/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.PartBasic;

/**
 * @author Wang Yongrui
 *
 */
public class ExecutedPart extends PartBasic {

	@Getter
	@Setter
	private Set<ExecutedAction> executedActionSet;

}
