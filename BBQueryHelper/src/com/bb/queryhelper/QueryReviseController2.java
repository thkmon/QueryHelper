package com.bb.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryReviseController2 {

	
	public String reviseQuery(String queryText, String paramText) {
		if (queryText != null) {
			queryText = queryText.trim();
		} else {
			queryText = "";
		}
		
		if (paramText != null) {
			paramText = paramText.trim();
		} else {
			paramText = "";
		}
		
		
		// 첫번째 칸은 쿼리 : select #{user_id}, #{user_name} from dual
		// 두번째 칸은 json : {user_id=test, user_name=테스트}
		
		String newQueryText = queryText;
		
		ArrayList<String> mapKeyList = new ArrayList<String>();
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String[] paramTextArr = null;
		
		if (paramText.length() > 0) {
			if (paramText.startsWith("{") && paramText.endsWith("}")) {
				paramText = paramText.substring(1, paramText.length() - 1);
			}
			
			if (paramText.indexOf(",") > -1) {
				paramTextArr = paramText.split(",");
			} else {
				paramTextArr = new String[1];
				paramTextArr[0] = paramText;
			}
		}
		
		if (paramTextArr != null && paramTextArr.length > 0) {
			int paramCount = paramTextArr.length;
			for (int i=0; i<paramCount; i++) {
				if (paramTextArr[i] == null) {
					continue;
				}
				
				paramTextArr[i] = paramTextArr[i].trim();
				
				String key = "";
				String value = "";
				int idxEqual = paramTextArr[i].indexOf("=");
				if (idxEqual > -1) {
					key = paramTextArr[i].substring(0, idxEqual).trim();
					value = paramTextArr[i].substring(idxEqual + 1).trim();
					
					if (key.length() > 0) {
						paramMap.put(key, value);
						mapKeyList.add(key);
					}
				}
			}
		}
		
		if (mapKeyList != null && mapKeyList.size() > 0) {
			String oneKey = "";
			String oneValue = "";
			
			String oneTarget = "";
			String oneResult = "";
			
			int mapKeyCount = mapKeyList.size();
			for (int i=0; i<mapKeyCount; i++) {
				oneKey = mapKeyList.get(i);
				if (oneKey == null || oneKey.length() == 0) {
					continue;
				}
				
				System.out.println(oneKey);
				oneValue = paramMap.get(oneKey);
				if (oneValue == null) {
					oneValue = "";
				}
				
				oneTarget = "#{" + oneKey + "}";
				oneResult = "'" + oneValue + "'";
				
				newQueryText = newQueryText.replace(oneTarget, oneResult);
				
				oneTarget = "${" + oneKey + "}";
				oneResult = "" + oneValue + "";
				
				newQueryText = newQueryText.replace(oneTarget, oneResult);
			}
		}
		
		return newQueryText;		
	}
}