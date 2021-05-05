package com.json.merge;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class MergeTest {

	static String merged_file = "F:\\test_file_java\\merged_file.xml";
	// TODO Auto-generated method stub
	static String file_path = "F:\\test_file_java\\xml_1.xml";
	static String file_path_1 = "F:\\test_file_java\\xml_2.xml";
	static String path_to_merge = "message_container/message/message_body/pos_rec";
	
	public static void main(String[] args) {
		try {
			int total_run = 100;
			System.out.println(Files.exists(Paths.get(merged_file)));
			Instant start = Instant.now();System.out.println("Started");
			for(int i=0;i<total_run;i++) {
			String param_content = Files.readString(Paths.get(file_path),Charset.forName("UTF-8"));
			xmlToJsonProcessing(param_content);}
			System.out.println("Ended");
			Instant end = Instant.now();
			long total_time_took = Duration.between(start, end).toMillis();
			System.out.println("Total Time took -> "+total_time_took+ " ms for "+total_run);
////			String source_file = null;
////			source_file = Files.exists(Paths.get(merged_file))? getMergedFile():saveInitialRun();
//			
//			String file_content = Files.readString(Paths.get(file_path), Charset.forName("UTF-8"));
//			String file_content_1 = Files.readString(Paths.get(file_path_1), Charset.forName("UTF-8"));
//			JSONObject sample = XML.toJSONObject(file_content);		// source
//			JSONObject sample_1 = XML.toJSONObject(file_content_1);	// target
//			String key = "pos_rec";
//			JSONObject arr = new JSONObject();
//			System.out.println(getValue(sample_1, key,arr));
////			System.out.println(mergeJson(getValue(sample_1, key,arr), sample, path_to_merge).toString(4));
//			JSONObject final_object = new JSONObject();
//			for(int i=1;i<50000;i++) final_object = merger(sample, arr, path_to_merge);
////			System.err.println(final_object.toString(4));
//			String final_otp = XML.toString(final_object);
////			System.out.println(final_otp);
//			Files.write(Paths.get(merged_file), final_otp.getBytes());
////			System.out.println(merger(sample, arr, path_to_merge).toString(4));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private static void xmlToJsonProcessing(String xml) {
		// for initial run lets assume this xml string is source as well as target and check for merged file
		//check if merged file is there or not
		try {
			if(Files.exists(Paths.get(merged_file))) {	
				if(Files.isReadable(Paths.get(merged_file)) && Files.isWritable(Paths.get(merged_file))&& ! Files.isDirectory(Paths.get(merged_file))) {
					String source_file_data = Files.readString(Paths.get(merged_file),Charset.forName("UTF-8"));
					JSONObject source_object = XML.toJSONObject(source_file_data);
					JSONObject target_object = XML.toJSONObject(xml);
					String key = "pos_rec";
					JSONObject target_data = getValue(target_object, key, new JSONObject());
					JSONObject final_object = merger(source_object, target_data, path_to_merge);	// can be converted to xml here
					if(final_object.length()>0) {
						//convert to xml
						String data = XML.toString(final_object);
						Files.write(Paths.get(merged_file),data.getBytes());
//						System.out.println("Done writing to file");
					}
				}else {
					System.out.println("Not a proper file or no permission to read/write");
				}
			}else {	// xml param gonna be the source 
				Files.write(Paths.get(merged_file), xml.getBytes(Charset.forName("UTF-8")));
//				System.out.println("Initial Run Saved");
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private static JSONObject getValue(JSONObject target,String key,JSONObject arr) throws JSONException{
		Iterator<?> keys = target.keys();
//		JSONObject o = null;
		while(keys.hasNext()) {
			String tmp = keys.next().toString();
//			System.out.println(tmp);
			Object ob = target.get(tmp);
			if(tmp.equalsIgnoreCase(key)) {
				arr.put(key,target.get(tmp));
//				System.out.println(arr.toString(4));
				
			}else {
				if(ob instanceof JSONObject) {
					JSONObject tmpo = target.getJSONObject(tmp);
					getValue(tmpo, key,arr);
				}else {
					if(ob instanceof JSONArray) {
						JSONArray arj = target.getJSONArray(key);
						for(int i =0;i<arj.length();i++) {
							JSONObject o = arj.getJSONObject(i);
							getValue(o, key, arr);
						}
					}
				}
			}
		}
		return arr;
	}
	
	private static JSONObject merger(JSONObject source, JSONObject target , String key_to_merge) throws JSONException{
		String[] keyarr = key_to_merge.split("/");
		int len_of_key_arr = keyarr.length;
//		System.out.println(target);
		for(String key : JSONObject.getNames(source)) {
//			System.out.println("current_key : "+key);
			Object tmp_obj = source.get(key);
			if(key.equals(keyarr[len_of_key_arr-1])) {	//message_body
				if(tmp_obj instanceof JSONObject) {	//for first run
//					System.out.println((JSONObject)tmp_obj);
//					System.out.println(source.get(key));
					JSONArray ar = new JSONArray();
					ar.put(source.get(key));
					ar.put(target.get(key));
					source.put(key, ar);
				}else {
					if(tmp_obj instanceof JSONArray) {
						JSONArray arr = ((JSONArray)tmp_obj);
						arr.put(target.get(key));
					}
				}
			}else {
				if(tmp_obj instanceof JSONObject) {
					merger((JSONObject)tmp_obj,target,key_to_merge);
				}
				
			}
		}
		return source;
	}
	

}
