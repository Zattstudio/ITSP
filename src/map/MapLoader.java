package map;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MapLoader {
	Gson gson;
	
	

	
	public Map loadMap(String nr) {
		try {
			gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			
			Reader reader = Files.newBufferedReader(Paths.get("assets/levels/" + nr + ".json"));
			
			Map mapObj  =  gson.fromJson(reader, Map.class);
			
			
			
			reader.close();
			return mapObj;
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			 // temp trace
			 return null;
		}
	}
}
