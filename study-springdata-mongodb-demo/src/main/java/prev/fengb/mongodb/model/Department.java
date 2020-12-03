package prev.fengb.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门
 *
 * @author fengb
 * @date 2020年12月2日 下午11:50:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "departments")
public class Department {

	@Id
	private String id;
	/** 部门名 */
	private String name;
	/** 部门领导ID */
	private String bossId;
}
