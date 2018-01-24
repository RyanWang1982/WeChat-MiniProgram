/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import static wang.yongrui.wechat.utils.EntityUtils.*;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.RealityBasic;
import wang.yongrui.wechat.entity.jpa.ExerciseEntity;
import wang.yongrui.wechat.entity.jpa.RealityEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy" })
@JsonInclude(value = Include.NON_EMPTY)
public class Reality extends RealityBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private User user;

	@Getter
	@Setter
	private Set<Exercise> exerciseSet;

	/**
	 * 
	 */
	public Reality() {
		super();
	}

	/**
	 * @param realityEntity
	 */
	public Reality(RealityEntity realityEntity) {
		super();
		if (null != realityEntity) {
			BeanUtils.copyProperties(realityEntity, this);
			User user = new User();
			user.setId(realityEntity.getUserEntity().getId());
			setUser(user);
			setExerciseSet(getObjectSetFromEntitySetWithHeritage(realityEntity.getExerciseEntitySet(),
					ExerciseEntity.class, Exercise.class));
		}
	}
}
