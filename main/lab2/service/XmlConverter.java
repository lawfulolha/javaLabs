package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import exception.ConvertException;

import java.io.File;
import java.io.IOException;


public class XmlConverter<T> implements IO<T> {

    private Class<T> classObject;

  //  @Contract(pure = true)
    public XmlConverter(Class<T> type) {
        this.classObject=type;
    }

    @Override
    public void toFile(T obj, File file) throws ConvertException {
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(file, obj);
        } catch (IOException e) {
            throw new ConvertException(e.getMessage());
        }
    }

    @Override
    public T fromFile(File file) throws ConvertException {
        try {

            XmlMapper mapper = new XmlMapper();
            return mapper.readValue(file, classObject);

        } catch (IOException e) {
            throw new ConvertException(e.getMessage());
        }
    }

    @Override
    public String serializeToString(T obj) throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();
        return mapper.writeValueAsString(obj);
    }

    @Override
    public T deserializeFromString(String string) throws IOException {
        XmlMapper mapper= new XmlMapper();
        return mapper.readValue(string, classObject);
    }

}