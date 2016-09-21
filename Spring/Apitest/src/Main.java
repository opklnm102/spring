import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Main {
	
	private static final String ENDPOINT = "http://drug.mfds.go.kr/admin/openapi/simpleSearch.do";
	
	private OkHttpClient client;
	
	public Main() throws IOException {
		client = new OkHttpClient();
		
		
		String strRequest = ENDPOINT + "?key=알비스정";
	
		
		Request request = new Request.Builder()
		        .url(strRequest)
		        .build();
		
		Response response = client.newCall(request).execute();
		
		ResponseBody body = response.body();
//		Reader charStream = body.charStream();
//		
//		String result = "";
//	
//		while(charStream.read() > 0){
//			result += 
//		}
//		
//		String result = charStream.read();
		
//		System.out.println(result);
		
		System.out.println(body.string());
		
	}
	
	
	public static void main(String[] args) {
		try {
			new Main();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	

	
	
	
}
