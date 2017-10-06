/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.RealityBasic;

/**
 * @author Wang Yongrui
 *
 */
public class Reality extends RealityBasic {

	@Getter
	@Setter
	private Set<ExecutedPart> executedPartSet;

}
