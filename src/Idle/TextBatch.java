package Idle;

import java.io.*;
import java.util.*;

public class TextBatch {

    private final List<String> strings;

    private int index;

    public TextBatch(){
        this.strings = new ArrayList<>();
        this.index = 0;
    }

    public String getNext(){
        System.out.println(strings);
        String s = strings.get(index);
        index = Math.min(index + 1, strings.size() - 1);
        return s;
    }

    public void reset(){
        index = 0;
    }

    public void load(String filePath) throws IOException {
        InputStream stream = ClassLoader.getSystemResourceAsStream(filePath);
        if (stream == null) throw new IOException("No File found");
        strings.clear();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))){
            StringBuilder stringBuilder = new StringBuilder();
            String read;
            while ((read = reader.readLine()) != null){
                if (read.equals("<>")){
                    strings.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
                else {
                    stringBuilder.append(read).append("\n");
                }
            }
        }
    }
}
