package org.kai;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.security.MessageDigest;
public class Block {
    private int index;
    private String previousHash;
    private String hash;
    private Timestamp previousTimestamp;
    private Timestamp timestamp;
    private String data;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Timestamp getPreviousTimestamp() {
        return previousTimestamp;
    }

    public void setPreviousTimestamp(Timestamp previousTimestamp) {
        this.previousTimestamp = previousTimestamp;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public Block(int previousIndex,String previousHash,String data,Timestamp previousTimestamp){
        this.index=previousIndex+1;
        this.previousHash=previousHash;
        this.data=data;
        this.previousTimestamp=previousTimestamp;
        this.timestamp=new Timestamp(System.currentTimeMillis());
        String expression = (index + 1) +previousHash+data+previousTimestamp+timestamp;
        this.hash=Md5.md5(expression);
    }
}
