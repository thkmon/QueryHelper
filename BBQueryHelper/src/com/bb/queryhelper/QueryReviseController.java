package com.bb.queryhelper;

import java.util.ArrayList;

public class QueryReviseController {

	
	public String reviseQuery(String originText) {
		
//		2019-12-04 20:42:50,686 DEBUG [com.bb.test.query1] ==>  Preparing: select ? a, ? b, ? c from dual where aaa = ? and bbb = ? and ccc = ? and ddd = ? 
//		2019-12-04 20:42:50,686 DEBUG [com.bb.test.query1] ==> Parameters: 1(Long), 818(String), 801(String), 802(String), 802(String), 99(Long), 1(Long)
		
		String[] originArr = originText.split("\n");
		if (originArr == null || originArr.length == 0) {
			return "";
		}
		
		ArrayList<String> queryList = new ArrayList<String>();
		
		String prepareString = "";
		String parameterString = "";
		
		boolean firstLine = false;
		
		int originLineCount = originArr.length;
		for (int i=0; i<originLineCount; i++) {
			String str = originArr[i];
			if (str == null || str.length() == 0) {
				continue;
			}
			
			boolean bPreparing = false;
			boolean bParameters = false;
			
			if (!firstLine) {
				firstLine = true;
				bPreparing = true;
			}
			
			int idxPreparing = str.indexOf("Preparing:");
			if (idxPreparing > -1) {
				str = str.substring(idxPreparing + "Preparing:".length());
				bPreparing = true;
				
			} else {
				int idxParameters = str.indexOf("Parameters:");
				if (idxParameters > -1) {
					str = str.substring(idxParameters + "Parameters:".length());
					bParameters = true;
				}
				
				if (str.startsWith("[") && str.endsWith("]")) {
					str = str.substring(1, str.length() - 1);
					bParameters = true;
				}
			}
			
			if (!bPreparing && !bParameters) {
				continue;
			}
			
			while (str.indexOf("\t") > -1) {
				str = str.replace("\t", " ");
			}
			
			while (str.indexOf("\r") > -1) {
				str = str.replace("\r", " ");
			}
			
			while (str.indexOf("\n") > -1) {
				str = str.replace("\n", " ");
			}
			
			while (str.indexOf("  ") > -1) {
				str = str.replace("  ", " ");
			}
			
			str = str.trim();
			
			if (bPreparing) {
				prepareString = str;
				
			} else {
				String oneQuery = prepareString;
				parameterString = str;
				
				String[] paramArray = parameterString.split(",");
				if (paramArray != null && paramArray.length > 0) {
					int paramCount = paramArray.length;
					for (int k=0; k<paramCount; k++) {
						int idxBeginBracket = paramArray[k].indexOf("(");
						
						if (idxBeginBracket > -1 && paramArray[k].endsWith(")")) {
							paramArray[k] = paramArray[k].substring(0, idxBeginBracket);
						}
						
						paramArray[k] = paramArray[k].trim();
						
						if (oneQuery.indexOf("?") > -1) {
							oneQuery = replaceOne(oneQuery, "?", "'" + paramArray[k] + "'");
						}
					}
					
					queryList.add(oneQuery);
				}
			}
		}
		
		
		StringBuffer resultBuff = new StringBuffer();
		
		if (queryList != null && queryList.size() > 0) {
			String oneQuery = "";
			
			int queryCount = queryList.size();
			for (int i=0; i<queryCount; i++) {
				oneQuery = queryList.get(i);
				
				if (resultBuff.length() > 0) {
					resultBuff.append("\n\n");
				}
				
				resultBuff.append(oneQuery);
			}
		}
		
		return resultBuff.toString();
		
	}

	private static String replaceOne(String str, String strToFind, String strToReplace) {
		if (str == null || str.length() == 0) {
			return "";
		}
		
		if (strToFind == null || strToFind.length() == 0) {
			return str;
		}
		
		if (strToReplace == null) {
			strToReplace = "";
		}
		
		int idx = str.indexOf(strToFind);
		if (idx < 0) {
			return str;
		}
		
		return str.substring(0, idx) + strToReplace + str.substring(idx + strToFind.length());
	}
}
