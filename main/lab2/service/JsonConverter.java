package service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonConverter<T> implements IO<T> {
    private Class<T> classObject;
    private ObjectMapper mapper;

    public JsonConverter(Class<T> type) {
        this.classObject =type;
    }
    @Override
    public void toFile(T obj, File file) {

        try {
            mapper= new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, obj);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            System.out.println("Exception Occurred while converting java obj into json" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param file
     * @return java object
     * read JSON string from file and convert string into java object
     */
    @Override
    public T fromFile(File file)  {
        try {
            mapper= new ObjectMapper();
            return mapper.readValue(file, classObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String serializeToString(T obj) throws JsonProcessingException {
        mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    @Override
    public T deserializeFromString(String string) throws IOException {
        mapper = new ObjectMapper();
        return mapper.readValue(string, classObject);
    }

}