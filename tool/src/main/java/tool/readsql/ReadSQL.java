package tool.readsql;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadSQL {
	public static void main(String[] args) throws IOException {
		// 1. 按行读入sql文件，作为一个类返回建表信息
		TableInfo newTable = new TableInfo();
		newTable = readFileByLines("batch.sql");

		// 2. 读取.h 的模版，将建表信息作为变量，插入到模板中
		write(newTable, "h");

		// 3. 读取.c 的模版，将建表信息作为变量，插入到模板中
		write(newTable, "c");

	}

	private static void write(TableInfo newTable, String flag) throws IOException {
		FileWriter writer = null;
		File file = null;
		// 文件名为表名+.h
		if (flag == "h") {
			file = new File("tmp.h");
			writer = new FileWriter(newTable.getTableName() + ".h");
		} else {
			file = new File("tmp.c");
			writer = new FileWriter(newTable.getTableName() + ".c");
		}
		BufferedWriter bw = new BufferedWriter(writer);

		// 读文件，读一行写一行，替换模版中定义的指定字符串
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 读一行写一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 替换模版中定义的字符串,分别替换表名、字段信息、主键信息
				if (tempString.contains("TABLENAME")) {
					tempString.replace("TABLENAME", newTable.getTableName().toUpperCase());
				}
				if (tempString.contains("tablename")) {
					tempString.replace("tablename", newTable.getTableName());
				}
				if (tempString.contains("key")) {
					tempString.replace("key", newTable.getKey());
				}
				// 列信息单独处理，循环写入
				if (tempString.contains("colInfo")) {
					for (int i = 0; i < newTable.getColName().size(); i++) {
						String colLength = "";
						// if (newTable.getColLength().get(i).toString()!=null)
						// {
						// colLength =
						// newTable.getColLength().get(i).toString();
						// }else{
						// colLength = "";
						// }
						tempString = newTable.getColType().get(i) + "" + newTable.getColName().get(i) + "" + "["
								+ colLength + "]";
						bw.write(tempString);
						bw.newLine();// 换行
					}
					// 写完列信息，结束本次循环,继续读下一行
					continue;
				}

				// c文件里的sql语句
				if (tempString.contains("colNameList")) {
					for (int i = 0; i < newTable.getColName().size(); i++) {

						tempString = newTable.getColName().get(i) + ",";
						bw.write(tempString);
						bw.newLine();// 换行
					}
					// 写完列信息，结束本次循环,继续读下一行
					continue;
				}

				// c文件里的最后一个函数内容
				if (tempString.contains("bindcols")) {
					for (int i = 0; i < newTable.getColName().size(); i++) {
						tempString = "SQLBindCol(statement, 2, SQL_C_CHAR, model->" + newTable.getColName().get(i)
								+ "bind, sizeof(model->password), &indicator)";
						bw.write(tempString);
						bw.newLine();// 换行
					}
					// 写完列信息，结束本次循环,继续读下一行
					continue;
				}
				bw.write(tempString);
				bw.newLine();// 换行
			}
			reader.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以行为单位读取文件,返回一个包含表信息的类
	 */
	public static TableInfo readFileByLines(String fileName) {
		File file = new File(fileName);
		// 新建一个表类
		TableInfo tableInfo = new TableInfo();
		String tableName = null;
		List<String> colName = new ArrayList<String>();
		List<String> colType = new ArrayList<String>();
		// List<Integer> colLength = new ArrayList<Integer>();
		String key = null;
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 按空格分割每行字符串
				String str = tempString;
				String[] splited = str.split("\\s+");
				// 按行号获取信息
				if (line == 6) {
					tableName = splited[2];
				}
				if (line >= 7 && line <= 12) {
					System.out.println(splited[1]);
					colName.add(splited[1]);
					System.out.println(splited[2]);
					colType.add(splited[2]);
				}
				if (line == 13) {
					key = splited[5];
					System.out.println(key);
				}
				System.out.println("line " + line + ": " + tempString);

				line++;
			}
			// 将获取到的表信息存入类中，返回
			tableInfo.setTableName(tableName);
			tableInfo.setColName(colName);
			tableInfo.setColType(colType);
			tableInfo.setKey(key);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return tableInfo;
	}
}
