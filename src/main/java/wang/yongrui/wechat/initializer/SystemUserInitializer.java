/**
 * 
 */
package wang.yongrui.wechat.initializer;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

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
import wang.yongrui.wechat.entity.jpa.RealityEntity;
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

	// @PostConstruct
	public void initial() {
		GroupEntity planGroupEntity = new GroupEntity();
		planGroupEntity.setForPlan(true);
		planGroupEntity.setUom("KG");
		planGroupEntity.setQuantity(20);
		planGroupEntity.setWeight(50.0);
		Set<GroupEntity> planGroupEntitySet = new LinkedHashSet<>();
		planGroupEntitySet.add(planGroupEntity);

		PartEntity partEntity = new PartEntity();
		partEntity.setBodyPart(BodyPart.Chest);
		partEntity.setName("partName");
		partEntity.setDescription("partDescription");
		partEntity.setImageUrl("partEntityImageUrl");
		partEntity.setPredefined(true);
		Set<PartEntity> partEntitySet = new LinkedHashSet<>();
		partEntitySet.add(partEntity);

		ActionEntity actionEntity = new ActionEntity();
		actionEntity.setName("actionName");
		actionEntity.setDescription("actionDescription");
		actionEntity.setImageUrl("actionImageUrl");
		actionEntity.setEquipment("actionEquipment");
		actionEntity.setPartEntitySet(partEntitySet);
		Set<ActionEntity> actionEntitySet = new LinkedHashSet<>();
		actionEntitySet.add(actionEntity);

		ExerciseEntity planExerciseEntity = new ExerciseEntity();
		planExerciseEntity.setForPlan(true);
		planExerciseEntity.setActionEntity(actionEntity);
		planExerciseEntity.setGroupEntitySet(planGroupEntitySet);
		Set<ExerciseEntity> planExerciseEntitySet = new LinkedHashSet<>();
		planExerciseEntitySet.add(planExerciseEntity);

		CircleDayEntity circleDayEntity = new CircleDayEntity();
		circleDayEntity.setWeekDay(WeekDay.Monday);
		circleDayEntity.setExerciseEntitySet(planExerciseEntitySet);
		Set<CircleDayEntity> circleDayEntitySet = new LinkedHashSet<>();
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
		Set<PlanEntity> planPlanEntitySet = new LinkedHashSet<>();
		planPlanEntitySet.add(planEntity);

		GroupEntity executeGroupEntity = new GroupEntity();
		executeGroupEntity.setForPlan(false);
		executeGroupEntity.setUom("KG");
		executeGroupEntity.setQuantity(20);
		executeGroupEntity.setWeight(50.0);
		executeGroupEntity.setExecutedQuantity(30);
		executeGroupEntity.setExecutedWeight(55.0);
		Set<GroupEntity> executeGroupEntitySet = new LinkedHashSet<>();
		executeGroupEntitySet.add(executeGroupEntity);

		ExerciseEntity executeExerciseEntity = new ExerciseEntity();
		executeExerciseEntity.setForPlan(false);
		executeExerciseEntity.setActionEntity(actionEntity);
		executeExerciseEntity.setGroupEntitySet(executeGroupEntitySet);
		Set<ExerciseEntity> executeExerciseEntitySet = new LinkedHashSet<>();
		executeExerciseEntitySet.add(executeExerciseEntity);

		RealityEntity realityEntity = new RealityEntity();
		realityEntity.setDate(Calendar.getInstance());
		realityEntity.setExerciseEntitySet(executeExerciseEntitySet);
		Set<RealityEntity> realityEntitySet = new LinkedHashSet<>();
		realityEntitySet.add(realityEntity);

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
		userEntity.setRealityEntitySet(realityEntitySet);
		userRepository.saveAndFlush(userEntity);
	}

}
