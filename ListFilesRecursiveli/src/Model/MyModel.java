package Model;

import java.util.List;

public class MyModel {
    private String fileName;
    private List<String> content;

    public MyModel(String fileName, List<String> content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }
    public void setContent(List<String> content) {
        this.content = content;
    }
    public List<String> getContent() {
        return content;
    }


}
