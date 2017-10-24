/**
 * 
 */
package wang.yongrui.wechat.entity.web.criteria;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wang Yongrui
 *
 */
@Getter
@Setter
public class RealityCriteria {

	private Long userId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar fromDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar toDate;

}
