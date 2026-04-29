//package birenproject.data;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class DataReader {
//
//    public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException {
//
//        // Read JSON file to String
//        String jsonContent = FileUtils.readFileToString(
//                new File(Filepath),
//                "UTF-8");
//
//        // Convert String to HashMap using Jackson Databind
//        ObjectMapper mapper = new ObjectMapper();
//
//        List<HashMap<String, String>> data = mapper.readValue(
//                jsonContent,
//                new TypeReference<List<HashMap<String, String>>>() {}
//        );
//
//        return data;
//    }
//}