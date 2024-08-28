package tool.readsql;

import java.util.List;

/**
 * 建表信息
 * @author lipf3
 * 2017年12月2日
 */
public class TableInfo {
	
	//表明、字段名称、字段类型、字段长度、主键名
	private String tableName;
	private List<String> colName;
	private List<String> colType;
	private List<Integer> colLength;
	private String key;
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the colName
	 */
	public List<String> getColName() {
		return colName;
	}
	/**
	 * @param colName the colName to set
	 */
	public void setColName(List<String> colName) {
		this.colName = colName;
	}
	/**
	 * @return the colType
	 */
	public List<String> getColType() {
		return colType;
	}
	/**
	 * @param colType the colType to set
	 */
	public void setColType(List<String> colType) {
		this.colType = colType;
	}
	/**
	 * @return the colLength
	 */
	public List<Integer> getColLength() {
		return colLength;
	}
	/**
	 * @param colLength the colLength to set
	 */
	public void setColLength(List<Integer> colLength) {
		this.colLength = colLength;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
