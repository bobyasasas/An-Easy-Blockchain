package org.kai;

import java.sql.Timestamp;


public class Main {
    public static void main(String[] args){
        Block initialBlock = new Block(0,Md5.md5("kai"),"kai",new Timestamp(2023,07,1,0,0,0,0));
    }
}