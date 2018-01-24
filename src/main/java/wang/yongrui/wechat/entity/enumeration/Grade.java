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
public enum Grade implements BasicEnum {

	Primary("初级"), Medium("中级"), Senior("高级");

	private String description;

	/**
	 * @param description
	 */
	private Grade(String description) {
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
