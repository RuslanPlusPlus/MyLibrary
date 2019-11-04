package library.controller;

public enum Type {

	LIBRARY_ITEM("Библиотечная единица"),
	BOOK("Книга"),
	MAGAZINE("Журнал"),
	NEWSPAPER("Газета");
	
	private String type;
	
	private Type(String type){
		this.type = type;
	}
	
	public String toString(){
		return this.type;
	}
	
}
