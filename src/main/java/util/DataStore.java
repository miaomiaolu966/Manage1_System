package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.User;

public class DataStore {
    public static final String DB_DIR = "src/main/resources/db";

    private final static Map<String, List<Object>> dbData= new HashMap<>()     {{
            File folder = new File(DB_DIR);//获取文件所在的文件夹的路径
            File [] files =folder.listFiles();//获取文件夹下的所有文件
            for (File file :files){//遍历每个文件
                put(file.getName().replace(".json",""),loadListFromFile(file.getName(), Object.class,DB_DIR));
            }
        }};
    public static Map<String, List<Object>> GetMap(){
        return dbData;
    }
    public boolean add(Object obj, Class clazz) {
        String className = clazz.getSimpleName();

        List<Object> classList=  dbData.get(className);

        classList.add(obj);


        saveObjectToFile(classList,className,DB_DIR);

        return true;
    }

    public List<Object> delete(Class clazz,String del_name){
        String className = clazz.getSimpleName();

        List<Object> classlist = dbData.get(className);



        //saveObjectToFile(classList,className,DB_DIR);

        return classlist;
    }



    public static void saveObjectToFile(Object obj, String fileName, String DB_DIR) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(obj);
        try (FileWriter writer = new FileWriter(DB_DIR + File.separator + fileName + ".json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T loadObjectFromFile(String fileName, Class<T> clazz, String DB_DIR) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = Files.newBufferedReader(Paths.get(DB_DIR + "/" + fileName))) {
            return gson.fromJson(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> loadListFromFile(String fileName, Class<T> clazz, String DB_DIR) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = Files.newBufferedReader(Paths.get(DB_DIR + File.separator + fileName))) {
            return gson.fromJson(reader, new TypeToken<List<T>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void saveObjectToFile(SerializableObject obj) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String json = gson.toJson(obj);
//        String fileName = obj.getIdentifier() + ".json";
//        String dbDir = obj.getDBDir();
//        String filePath = dbDir + File.separator + fileName;
//
//        try (FileWriter writer = new FileWriter(filePath)) {
//            writer.write(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        new DataStore();

}
}
