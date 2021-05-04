import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
public class XMLParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file_path = "F:\\test_file_java\\simple.xml";
		try {
			String file_content = Files.readString(Paths.get(file_path), Charset.forName("UTF-8"));
			JSONObject sample = XML.toJSONObject(file_content);
			System.out.println(sample.toString(4));
			Iterator<String> keys = sample.keys();
			
			while(keys.hasNext()) {
				String k = keys.next();
				if(sample.get(k) instanceof JSONArray) {
					JSONArray arr = sample.getJSONArray(k);
					System.out.println("JSONARRAY");
				}
				else {
					if(sample.get(k) instanceof JSONObject) {
						JSONObject jobj = sample.getJSONObject(k);
						System.out.println("JSONOBJECT"+jobj.keySet().toString());
						Iterator<String> tmpkey = jobj.keys();
						while(tmpkey.hasNext()) {
							String ks = tmpkey.next();
//							System.out.println(jobj.get(tmpkey.next().toString()));
							if(jobj.get(ks) instanceof JSONArray) {
							System.out.println(getNestedKeys(jobj.getJSONArray(ks)));
							}
							else {
								System.out.println(jobj.get(ks));
							}
						}
					}
					else {
						System.out.println(sample.get(k));
					}
				}
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			e.printStackTrace();;
		}
	}
	
	public static Set<String> getNestedKeys(JSONArray arr){
		Set<String> keys = new HashSet<String>();
		try {
			for(int i=0;i<arr.length();i++) {
//				System.out.println(arr.get(i));
				if(arr.get(i) instanceof JSONObject) {
					JSONObject tmpobj = arr.getJSONObject(i);
					tmpobj.keySet().forEach((e)->{keys.add(String.valueOf(e));System.out.println(tmpobj.get(String.valueOf(e)));});
					
				}else {
					System.out.println("Nothing to Print");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return keys;
	}

}
