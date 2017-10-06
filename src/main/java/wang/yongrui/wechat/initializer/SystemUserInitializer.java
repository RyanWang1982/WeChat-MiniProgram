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

import wang.yongrui.wechat.entity.enumeration.Gender;
import wang.yongrui.wechat.entity.enumeration.Grade;
import wang.yongrui.wechat.entity.enumeration.Purpose;
import wang.yongrui.wechat.entity.jpa.ActionEntity;
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
		planGroupEntity.setUom("KG");
		planGroupEntity.setQuantity(20);
		planGroupEntity.setWeight(50.0);
		Set<GroupEntity> planGroupEntitySet = new HashSet<>();
		planGroupEntitySet.add(planGroupEntity);

		ActionEntity planActionEntity = new ActionEntity();
		planActionEntity.setName("planActionName");
		planActionEntity.setDescription("planActionDescription");
		planActionEntity.setImageUrl("planActionImageUrl");
		planActionEntity.setEquipment("planActionEquipment");
		planActionEntity.setGroupEntitySet(planGroupEntitySet);
		Set<ActionEntity> planActionEntitySet = new HashSet<>();
		planActionEntitySet.add(planActionEntity);

		PartEntity planPartEntity = new PartEntity();
		planPartEntity.setName("planPartName");
		planPartEntity.setDescription("planPartDescription");
		planPartEntity.setImageUrl("planPartEntityImageUrl");
		planPartEntity.setPredefined(true);
		planPartEntity.setActionEntitySet(planActionEntitySet);
		Set<PartEntity> planPartEntitySet = new HashSet<>();
		planPartEntitySet.add(planPartEntity);

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
		planEntity.setRecurring("Mon,Tue,Wed,Thu,Fri");
		planEntity.setPartEntitySet(planPartEntitySet);
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
