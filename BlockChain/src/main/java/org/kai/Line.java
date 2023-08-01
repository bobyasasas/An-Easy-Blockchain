package org.kai;

import com.sun.jdi.Value;

public class Line {
    private String line;
    public String hash;
    public long timestamp;
    public Line(String line){
        this.line=line;
        String[] arr_line = line.split(" ");
        this.hash=arr_line[0];
        this.timestamp=Long.valueOf(arr_line[1]);
    }
}
