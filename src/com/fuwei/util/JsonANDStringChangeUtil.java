package com.fuwei.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonANDStringChangeUtil {
	public static String getJsonStringBYHashMapList(List<HashMap<String, String>>	hashMapList){
		JSONArray jArray=new JSONArray();
		
		
		for (HashMap<String,String> hashMap : hashMapList) {
			JSONObject jObject=new JSONObject();
			Set<String> keySet=hashMap.keySet();
			for (String key : keySet) {
				jObject.put(key, hashMap.get(key));
			}
			jArray.add(jObject);
		}
		return jArray.toString();
		
	}
	
	public static List<HashMap<String, String>> getHashMapListByJsonString(String jsonString){
		JSONArray jArray=JSONArray.fromObject(jsonString);
		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		for (int i = 0; i < jArray.size(); i++) {
			HashMap<String, String> hashMap=new HashMap<String, String>();
			JSONObject jObject=(JSONObject)jArray.get(i);
			Set<String> keySet=jObject.keySet();
			for (String key : keySet) {
				hashMap.put(key, jObject.getString(key));
			}
			list.add(hashMap);
		}
		return list;
	}
	
	
}
