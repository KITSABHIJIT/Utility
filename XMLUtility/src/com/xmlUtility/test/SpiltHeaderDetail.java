package com.xmlUtility.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.transformer.Main;

public class SpiltHeaderDetail {


	public static List<Map<String,List<String>>> splitHeaderDetailData(List<String> data){
		List<Map<String,List<String>>> EDI_DATA=new ArrayList<Map<String,List<String>>>();
		List<NodeEntity> headerNodeEntities=ReadXML.readXMLFileWithNode(FileConstants.INPUT_HEADER_XML,"node");
		List<NodeEntity> detailNodeEntities=ReadXML.readXMLFileWithNode(FileConstants.INPUT_DETAIL_XML,"node");
		int count=0,totalSize=data.size();
		Map<String,List<String>> edi_Map=new HashMap<String, List<String>>();
		for(String entry:data){
			if((entry.substring(headerNodeEntities.get(1).getStartPosition(), headerNodeEntities.get(1).getEndPosition()).startsWith("EDI")) && count>0){
				EDI_DATA.add(edi_Map);
				edi_Map=new HashMap<String, List<String>>();
			}
			if(entry.substring(headerNodeEntities.get(1).getStartPosition(), headerNodeEntities.get(1).getEndPosition()).startsWith("EDI")){
				for(NodeEntity entity:headerNodeEntities){
					List<String> headerData=new ArrayList<String>();
					headerData.add(StringUtil.trim(entry.substring(entity.getStartPosition(), entity.getEndPosition())));
					edi_Map.put(entity.getNodeName(),headerData );
				}
			}else{
				for(NodeEntity entity:detailNodeEntities){

					if(edi_Map.containsKey(entity.getNodeName())){
						List<String> existDetailData=edi_Map.get(entity.getNodeName());
						existDetailData.add(StringUtil.trim(entry.substring(entity.getStartPosition(), entity.getEndPosition())));
						edi_Map.put(entity.getNodeName(), existDetailData);
					}else{
						List<String> detailData=new ArrayList<String>();
						detailData.add(StringUtil.trim(entry.substring(entity.getStartPosition(), entity.getEndPosition())));
						edi_Map.put(entity.getNodeName(), detailData);
					}
				}
			}

			count++;
			if(count==totalSize){
				EDI_DATA.add(edi_Map);
			}
		}
		return EDI_DATA;
	}

	public static void main(String args[]){
		List<Map<String,List<String>>> ediData=splitHeaderDetailData(ReadFile.getData(FileConstants.INPUT_FILE));
		for(Map<String,List<String>> ediInvoice:ediData){
			try {
				Main.getTargetXML(ediInvoice);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
