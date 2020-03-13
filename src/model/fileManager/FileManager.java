package model.fileManager;

public interface FileManager {

    Object read(String fileName);

    void write(String fileName);
}