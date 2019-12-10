package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import exception.ConvertException;

import java.io.File;
import java.io.IOException;

public interface IO<T> {

    void toFile(T obj, File file) throws ConvertException;

    T fromFile(File file) throws ConvertException;

    String serializeToString(T obj) throws JsonProcessingException;

    T deserializeFromString(String string) throws IOException;

}