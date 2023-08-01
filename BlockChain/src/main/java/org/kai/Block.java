package org.kai;

public class Block {
    private int index;
    private String previousHash;
    private String hash;
    private long previousTimestamp;
    private long timestamp;
    private String data;

    public int getIndex() {
        return index;
    }


    public String getHash() {
        return hash;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public Block(int previousIndex,String previousHash,String data,long previousTimestamp){
        this.index=previousIndex+1;
        this.previousHash=previousHash;
        this.data=data;
        this.previousTimestamp=previousTimestamp;
        this.timestamp=System.currentTimeMillis();
        String expression = (index + 1) +previousHash+data+previousTimestamp+timestamp;
        this.hash=Md5.md5(expression);
    }
    public Block(int previousIndex,String previousHash,String data,long previousTimestamp,long timestamp){
        this.index=previousIndex+1;
        this.previousHash=previousHash;
        this.data=data;
        this.previousTimestamp=previousTimestamp;
        this.timestamp=timestamp;
        String expression = (index + 1) +previousHash+data+previousTimestamp+timestamp;
        this.hash=Md5.md5(expression);
    }
}
