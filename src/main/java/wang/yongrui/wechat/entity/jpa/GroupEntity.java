/**
 * 
 */
package wang.yongrui.wechat.entity.jpa;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.GroupBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "GRP")
@Getter
@Setter
public class GroupEntity extends GroupBasic {

}
