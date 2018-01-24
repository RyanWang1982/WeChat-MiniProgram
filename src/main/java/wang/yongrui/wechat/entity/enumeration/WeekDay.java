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
public enum WeekDay implements BasicEnum {

	Sunday("日"), Monday("一"), Tuesday("二"), Wednesday("三"), Thursday("四"), Friday("五"), Saturday("六");

	private String description;

	/**
	 * @param description
	 */
	private WeekDay(String description) {
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
