/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.ActionBasic;

/**
 * @author Wang Yongrui
 *
 */
public class ExecutedAction extends ActionBasic {

	@Getter
	@Setter
	private Set<ExecutedGroup> executedGroupSet;

}
