package com.util.SQLGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.util.pojo.ExcelSheet;
import com.util.pojo.SQLObject;
import com.util.read.excel.FileUtil;
import com.util.read.excel.ReadExcel;
import com.util.read.excel.StringUtil;

public class SQLGenerator {

	//public static String FILE_NAME="C:\\Users\\royab001\\Desktop\\work done\\Galaxy Remediation\\SQL Details\\GLRMFLDLS1 - MOONBEAM Field List_Modified_Duplicates Removed.xls";
	//public static String FILE_NAME="C:\\Users\\royab001\\Desktop\\work done\\Galaxy Remediation\\SQL Details\\GLRMFLDLS1 - GALAXY Field List_Modified_Duplicates Removed.xls";
	public static String FILE_NAME="C:\\Users\\royab001\\Desktop\\work done\\Galaxy Remediation\\SQL Details\\GLRMKEYLS1 - GALAXY Key List_Modified_No dupes.xls";
	//public static String FILE_NAME="C:\\Users\\royab001\\Desktop\\work done\\Galaxy Remediation\\SQL Details\\GLRMKEYLSM - MOONBEAM Key List_Modified_No dupes.xls";

	//public static String OUTPUT_DIR="C:\\Users\\royab001\\Desktop\\work done\\Galaxy Remediation\\SQL Details\\Moonbeam Tables";
	public static String OUTPUT_DIR="C:\\Users\\royab001\\Desktop\\work done\\Galaxy Remediation\\SQL Details\\Galaxy Tables";
	
	

	//public static String TABLE_PREFIX="MNB_";

	static Logger log =Logger.getLogger(SQLGenerator.class);
	
	public static void generateCreateQuery(String fileName){
		String processingTable =null;
		try {
			List<SQLObject> sqlObjectList =ReadExcel.readXLSFileSpecificColumns(fileName);

			Map<String,String> tableDefinition=new HashMap<String,String>();
			Map<String,List<String>> tableComments=new HashMap<String,List<String>>();

			for(SQLObject object:sqlObjectList){
				processingTable=object.getTableName();
				StringBuffer column=new StringBuffer("\""+escapeMetaCharacters(object.getColumnName())+"\"");

				if("A".equals(object.getDataType()) && object.getColumnLength()>3000){
					column.append(" LONG");
				}else if("A".equals(object.getDataType()) && object.getColumnLength()==1){
					column.append(" CHAR(").append((object.getColumnLength()).intValue()).append(")");
				}else if("A".equals(object.getDataType()) && object.getColumnLength()>1 ){
					column.append(" VARCHAR2(").append((object.getColumnLength()).intValue()).append(" CHAR)");
				}else{
					column.append(" NUMBER(").append((object.getNoOfDigit()).intValue()).append(","+(object.getDecimalPosition()).intValue()).append(")");
				}
				if(tableDefinition.containsKey(processingTable)){
					String columndata=tableDefinition.get(processingTable);
					columndata=columndata+", "+column.toString();
					tableDefinition.put(processingTable, columndata);
				}else{
					tableDefinition.put(processingTable, column.toString());
				}

				String columnText=SQLConstant.COMMENT_QUERY;
				String escapedColumn=escapeMetaCharacters(object.getColumnName());
				String escapedColumnText=escapeMetaCharacters(object.getColumnText());

				columnText=columnText.replaceAll("<TABLENAME>", processingTable);
				columnText=columnText.replaceAll("<COLUMN_NAME>", escapedColumn);
				columnText=columnText.replaceAll("<COLUMN_TEXT>", escapedColumnText);

				if(tableComments.containsKey(processingTable)){
					List<String> columnTextList=tableComments.get(processingTable);
					columnTextList.add(columnText);
					tableComments.put(processingTable, columnTextList);
				}else{
					List<String> columnTextList=new ArrayList<String>();
					columnTextList.add(columnText);
					tableComments.put(processingTable, columnTextList);
				}
			}

			for (Map.Entry<String, String> entry : tableDefinition.entrySet()) {
				String outputFile=OUTPUT_DIR+"/"+entry.getKey()+".sql";
				String createQuery=SQLConstant.CREATE_QUERY;
				createQuery=createQuery.replaceAll("<TABLENAME>", entry.getKey());
				createQuery=createQuery.replaceAll("<COLUMNSDATA>", entry.getValue());
				log.debug("/*Table: "+entry.getKey()+" Query: */"+createQuery);
				FileUtil.writeToFile(createQuery, outputFile);
				FileUtil.writeToFile("\n", outputFile);
				for(String comments:tableComments.get(entry.getKey())){
					log.debug("/*Comments: */"+comments);
					FileUtil.writeToFile(comments, outputFile);
					FileUtil.writeToFile("\n", outputFile);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Error in :"+processingTable);
			e.printStackTrace();
		}
	}
	
	
	public static void generateIndexQuery(String fileName){
		String processingTable =null;
		try {
			List<ExcelSheet> indexList =ReadExcel.readXLSFileColumns(fileName);

			Map<String,Map<String,String>> tableIndex=new HashMap<String,Map<String,String>>();
			Map<String,Map<String,String>> uniqueIndex=new HashMap<String,Map<String,String>>();


			for(ExcelSheet object:indexList){
				if(object.getColD()>0){
					String indexName=object.getColA()+"_IDX";
					processingTable=(StringUtil.isBlankOrEmpty(object.getColN()))?object.getColA():object.getColN();
					boolean isUniqueIndex=("Y".equals(object.getColH()))?true:false;

					if(((isUniqueIndex)?uniqueIndex:tableIndex).containsKey(processingTable)){
						Map<String,String> idxList=((isUniqueIndex)?uniqueIndex:tableIndex).get(processingTable);
						if(idxList.containsKey(indexName)){
							String indexedColumn=idxList.get(indexName);
							indexedColumn=indexedColumn+","+object.getColF();
							idxList.put(indexName, indexedColumn);
						}else{
							idxList.put(indexName, object.getColF());
						}
						((isUniqueIndex)?uniqueIndex:tableIndex).put(processingTable, idxList);
					}else{
						Map<String,String> idxList= new HashMap<String,String>();
						idxList.put(indexName, object.getColF());
						((isUniqueIndex)?uniqueIndex:tableIndex).put(processingTable, idxList);
					}
				}else{
					log.error("File ignored: "+object.getColA());
				}
			}

			for (Entry<String, Map<String, String>> entry : tableIndex.entrySet()) {
				String outputFile=OUTPUT_DIR+"/Index.sql";
				for (Map.Entry<String, String> inerEntry : entry.getValue().entrySet()) {
					String indexQuery=SQLConstant.INDEX_QUERY;
					indexQuery=indexQuery.replaceAll("<TABLENAME>", entry.getKey());
					indexQuery=indexQuery.replaceAll("<INDEX_NAME>", inerEntry.getKey());
					indexQuery=indexQuery.replaceAll("<COLUMN_NAME>", inerEntry.getValue());
					log.debug("/*Table: "+entry.getKey()+" Index: */"+indexQuery);
					FileUtil.writeToFile(indexQuery, outputFile);
					FileUtil.writeToFile("\n", outputFile);
				}
			}
			
			for (Entry<String, Map<String, String>> entry : uniqueIndex.entrySet()) {
				String outputFile=OUTPUT_DIR+"/UniqueIndex.sql";
				for (Map.Entry<String, String> inerEntry : entry.getValue().entrySet()) {
					String indexQuery=SQLConstant.UNIQUE_INDEX_QUERY;
					indexQuery=indexQuery.replaceAll("<TABLENAME>", entry.getKey());
					indexQuery=indexQuery.replaceAll("<INDEX_NAME>", inerEntry.getKey());
					indexQuery=indexQuery.replaceAll("<COLUMN_NAME>", inerEntry.getValue());
					log.debug("/*Table: "+entry.getKey()+" UniqueIndex: */"+indexQuery);
					FileUtil.writeToFile(indexQuery, outputFile);
					FileUtil.writeToFile("\n", outputFile);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Error in :"+processingTable);
			e.printStackTrace();
		}
	}

	public static String escapeMetaCharacters(String inputString){
		final String[] metaCharacters = {"\\","^","$","{","}","[","]","(",")",".","*","+","?","|","<",">","-","&","'"};
		String outputString=inputString;
		for (int i = 0 ; i < metaCharacters.length ; i++){
			if(inputString.contains(metaCharacters[i])){
				outputString = inputString.replace(metaCharacters[i],"\\"+metaCharacters[i]);
				inputString = outputString;
			}
		}
		return outputString;
	}

	public static void main(String[] args) throws IOException {

		//generateCreateQuery(FILE_NAME);
		generateIndexQuery(FILE_NAME);
		//System.out.println(escapeMetaCharacters("Brokerage/Insurance $"));

		//readXLSXFile();

	}
}
