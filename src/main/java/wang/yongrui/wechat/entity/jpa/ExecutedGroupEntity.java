/**
 * 
 */
package wang.yongrui.wechat.entity.jpa;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.ExecutedGroupBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "EXECUTED_GROUP")
@Getter
@Setter
public class ExecutedGroupEntity extends ExecutedGroupBasic {

}
