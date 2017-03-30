package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Splitter;

public class Utils {
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
        BufferedReader br = null;
        Map<String, String> dataMap = null;

        try {

            InputStreamReader reader = new InputStreamReader(
                    request.getInputStream());
            br = new BufferedReader(reader);

            String data = br.readLine();

            dataMap = Splitter.on('&')
                    .trimResults()
                    .withKeyValueSeparator(
                            Splitter.on('=')
                            .limit(2)
                            .trimResults()
                            )
                    .split(data);
            Map<String, String> result = new HashMap<String, String>();
            for (String key: dataMap.keySet()) {
            	String value = dataMap.get(key);
            	value = value.replace('+', ' ');
            	result.put(key, value);
            }
            return result;
            
        } catch (IOException ex) {
            ex.getStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.getStackTrace();
                }
            }
        }

        return dataMap;
    }
	
}
