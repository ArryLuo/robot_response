package com.example.android;

public class JsonInfo {
private int code;
private String content;

public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
@Override
public String toString() {
	return "JsonInfo [code=" + code + ", content=" + content + "]";
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
}
