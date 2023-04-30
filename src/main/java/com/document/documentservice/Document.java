package com.document.documentservice;

public class Document {
    
    private String name;
    private String version;
    private long length;
    private String path;

    public Document() {
        this.name = "";
        this.version = "";
        this.length = 0;
        this.path = "";
    }

    public Document(String name, String version, long length, String path) {
        this.name = name;
        this.version = version;
        this.length = length;
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String toString() {
        return "[" + this.name + ", " + this.version + ", " + 
        this.length + ", " + this.path + "]";
    }

}
