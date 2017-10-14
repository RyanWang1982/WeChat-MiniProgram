/**
 * 
 */
package wang.yongrui.wechat.initializer;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wang.yongrui.wechat.entity.enumeration.BodyPart;
import wang.yongrui.wechat.entity.enumeration.Gender;
import wang.yongrui.wechat.entity.enumeration.Grade;
import wang.yongrui.wechat.entity.enumeration.Purpose;
import wang.yongrui.wechat.entity.enumeration.WeekDay;
import wang.yongrui.wechat.entity.jpa.ActionEntity;
import wang.yongrui.wechat.entity.jpa.CircleDayEntity;
import wang.yongrui.wechat.entity.jpa.ExerciseEntity;
import wang.yongrui.wechat.entity.jpa.GroupEntity;
import wang.yongrui.wechat.entity.jpa.PartEntity;
import wang.yongrui.wechat.entity.jpa.PlanEntity;
import wang.yongrui.wechat.entity.jpa.UserEntity;
import wang.yongrui.wechat.repository.UserRepository;

/**
 * @author Wang Yongrui
 *
 */
@Component
public class SystemUserInitializer {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void initial() {
		GroupEntity planGroupEntity = new GroupEntity();
		planGroupEntity.setForPlan(true);
		planGroupEntity.setUom("KG");
		planGroupEntity.setQuantity(20);
		planGroupEntity.setWeight(50.0);
		Set<GroupEntity> planGroupEntitySet = new HashSet<>();
		planGroupEntitySet.add(planGroupEntity);

		PartEntity partEntity = new PartEntity();
		partEntity.setBodyPart(BodyPart.Chest);
		partEntity.setName("partName");
		partEntity.setDescription("partDescription");
		partEntity.setImageUrl("partEntityImageUrl");
		partEntity.setPredefined(true);
		Set<PartEntity> partEntitySet = new HashSet<>();
		partEntitySet.add(partEntity);

		ActionEntity actionEntity = new ActionEntity();
		actionEntity.setName("actionName");
		actionEntity.setDescription("actionDescription");
		actionEntity.setImageUrl("actionImageUrl");
		actionEntity.setEquipment("actionEquipment");
		actionEntity.setPartEntitySet(partEntitySet);
		Set<ActionEntity> actionEntitySet = new HashSet<>();
		actionEntitySet.add(actionEntity);

		ExerciseEntity exerciseEntity = new ExerciseEntity();
		exerciseEntity.setForPlan(true);
		exerciseEntity.setActionEntity(actionEntity);
		exerciseEntity.setGroupEntitySet(planGroupEntitySet);
		Set<ExerciseEntity> exerciseEntitySet = new HashSet<>();
		exerciseEntitySet.add(exerciseEntity);

		CircleDayEntity circleDayEntity = new CircleDayEntity();
		circleDayEntity.setWeekDay(WeekDay.Monday);
		circleDayEntity.setExerciseEntitySet(exerciseEntitySet);
		Set<CircleDayEntity> circleDayEntitySet = new HashSet<>();
		circleDayEntitySet.add(circleDayEntity);

		PlanEntity planEntity = new PlanEntity();
		planEntity.setName("planName");
		planEntity.setDescription("planDescription");
		planEntity.setPurpose(Purpose.Muscle);
		Calendar planFromDate = Calendar.getInstance();
		planFromDate.set(2017, 9, 1);
		planEntity.setFromDate(planFromDate);
		Calendar planToDate = Calendar.getInstance();
		planToDate.set(2017, 11, 31);
		planEntity.setToDate(planToDate);
		planEntity.setGrade(null);
		planEntity.setCircleDayEntitySet(circleDayEntitySet);
		Set<PlanEntity> planPlanEntitySet = new HashSet<>();
		planPlanEntitySet.add(planEntity);

		UserEntity userEntity = new UserEntity();
		userEntity.setLoginName("loginName");
		userEntity.setWechatUnionId("wechatUnionId");
		userEntity.setWechatMPOpenId("wechatMPOpenId");
		userEntity.setNickName("nickName");
		userEntity.setFirstName("FirstName");
		userEntity.setLastName("LastName");
		userEntity.setMobileNumber("13888888888");
		userEntity.setEmail("email");
		userEntity.setGender(Gender.Male);
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(2000, 0, 1);
		userEntity.setDateOfBirth(dateOfBirth);
		userEntity.setAvatarUrl("avatarUrl");
		userEntity.setGrade(Grade.Primary);
		userEntity.setPlanEntitySet(planPlanEntitySet);
		userRepository.saveAndFlush(userEntity);
	}

}
