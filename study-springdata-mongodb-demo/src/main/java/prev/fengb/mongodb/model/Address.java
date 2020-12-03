package prev.fengb.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址
 *
 * @author fengb
 * @date 2020年12月2日 下午11:40:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 县 */
	private String county;
}
