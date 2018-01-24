/**
 * 
 */
package wang.yongrui.wechat.entity.enumeration;

import lombok.Getter;

/**
 * @author Wang Yongrui
 *
 */
@Getter
public enum BodyPart implements BasicEnum {

	Whole("全身"), Chest("胸部"), Shoulder("肩部"), Back("背部"), Waist("腰部"), Abdomen("腹部"), Arm("手臂"), Leg("腿部");

	private String description;

	/**
	 * @param description
	 */
	private BodyPart(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.wechat.entity.enumeration.BasicEnum#getName()
	 */
	@Override
	public String getName() {
		return this.name();
	}

}
