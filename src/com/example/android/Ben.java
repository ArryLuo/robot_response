package com.example.android;

public class Ben {
public static  int TYPE_RESULT=100000;
public static final int TYPE_SEND=1;
private String content;
private int reslut;
public int getReslut() {
	return reslut;
}
public void setReslut(int reslut) {
	this.reslut = reslut;
}
private int type;

public Ben() {
	super();
	// TODO Auto-generated constructor stub
}
public Ben(String content, int type) {
	super();
	this.content = content;
	this.type = type;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
@Override
public String toString() {
	return "Ben [content=" + content + ", reslut=" + reslut + ", type=" + type
			+ "]";
}
}
