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

	Chest("Chest"), Shoulder("Shoulder"), Back("Back"), Waist("Waist"), Abdomen("Abdomen"), Arm("Arm"), Leg("Leg");

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
