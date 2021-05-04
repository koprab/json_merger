package com.json.merge;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class JsonMerge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject target = new JSONObject();
		String file_path = "F:\\test_file_java\\xml_3.xml";
		String file_path_1 = "F:\\test_file_java\\xml_2.xml";
		String merge_key = "pos_rec";
		JSONArray arr = new JSONArray();
		try {
			String file_content = Files.readString(Paths.get(file_path), Charset.forName("UTF-8"));
			String file_content_1 = Files.readString(Paths.get(file_path_1), Charset.forName("UTF-8"));
			JSONObject sample = XML.toJSONObject(file_content);
			JSONObject sample_1 = XML.toJSONObject(file_content_1);
			System.out.println(sample.toString(4));
			// get value of merger key
//			getMergerValue(sample, merge_key, target);
//			for(String s:JSONObject.getNames(sample)) 
			
		} catch (IOException e) {
//			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(target.toString(4));
		
	}
//	
//	private static JSONObject getMergerValue(JSONObject source, String key,JSONObject target) throws JSONException{
////		boolean counter = false;
//		for(String k: JSONObject.getNames(source)) {
//			Object tmp = source.get(k);
//			if(k.equals(key)) {
//				target = source.getJSONObject(key);
////				counter=true;
//				break;
//			}else {
//				if(tmp instanceof JSONObject) {
//					JSONObject tmp1 = (JSONObject)tmp;
//					getMergerValue(tmp1, key, target);
//				}
//			}
////			if(counter)break;
//		}
//		System.out.println(target.toString(4));
//		
//		return target;
//	}
//	
	
//	private static JSONObject getMergerKeyValue(JSONObject sample,String key) {
//		// TODO Auto-generated method stub
//		JSONObject jobj = null;
//		for(String s:JSONObject.getNames(sample)) {
//			System.out.println("--"+s);
//			Object o = sample.get(s);
//			if(s.equals(key)) {
//				jobj = sample.getJSONObject(key);
//				System.out.println(jobj.toString(4));
//				break;
//			}else {
//				if(o instanceof JSONObject) {
//					getMergerKeyValue(((JSONObject)o), key);
//				}
//			}
//		}
//		return jobj;
//	}

//	public static JSONObject mergerJson(JSONObject target, JSONObject source) throws JSONException{
//		try {
//			for(String key:JSONObject.getNames(target)) {
//				
//			}
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		
//		return null;
//	}
	
//	public static JSONArray getJsonArray(JSONArray source, JSONObject target) throws JSONException{
//		try {
//			for(String key:JSONObject.getNames(target)) {
//				System.out.println(key);
//				Object tmp = target.get(key);
//				if(tmp instanceof JSONObject) {
//					JSONObject obj = target.getJSONObject(key);
////					System.out.println(((JSONObject) tmp).toString(4));
//					getJsonArray(source , obj);
//				}else {
//					if(tmp instanceof JSONArray) {
//						System.out.println(key);
//						JSONArray arr =  target.getJSONArray(key);
//						
//						for(int i=0;i<arr.length();i++) {
////							System.out.println(arr.get(i));
//							source.put(arr.getJSONObject(i));
//						}
//					}
//				}
//			}
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return  null;
//	}
}
