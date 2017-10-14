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

	Sunday("Sunday"), Monday("Monday"), Tuesday("Tuesday"), Wednesday("Wednesday"), Thursday("Thursday"), Friday(
			"Friday"), Saturday("Saturday");

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
