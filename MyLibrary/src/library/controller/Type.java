package library.controller;

public enum Type {

	LIBRARY_ITEM("������������ �������"),
	BOOK("�����"),
	MAGAZINE("������"),
	NEWSPAPER("������");
	
	private String type;
	
	private Type(String type){
		this.type = type;
	}
	
	public String toString(){
		return this.type;
	}
	
}
