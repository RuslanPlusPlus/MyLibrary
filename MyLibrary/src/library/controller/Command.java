package library.controller;

public enum Command {

	EXIT("�����"),
	SHOW_READERS("�������� ���������"),
	CONTINUE("����������"), 
	ADD_READER("�������� ��������"),
	CONFIRM("�����������"),
	DECLINE("���������"),
	ADD_LIBRARY_ITEM("�������� � ����������"),
	ADD_BOOK("�������� �����"),
	SHOW_ALL_LIBRARY_ITEMS("�������� ���� ������ ����������"),
	SHOW_LIBRARY_BOOKS("�������� ������ ���� ����������"),
	SHOW_LIBRARY_NEWSPAPERS("�������� ������ ����� ����������"),
	SHOW_LIBRARY_MAGAZINES("�������� ������ �������� ����������"),
	SHOW_ARTICLES("�������� ������ ������ ����������"),
	ADD_NEWSPAPER("�������� ������"),
	ADD_MAGAZINE("������� ������"),
	ADD_ARTICLE("�������� ������"),
	GIVE_TO_READER("������ ��������"),
	SEARCHING_OVER_ARTICLES("����� �� �������"),
	SEARCHING_OVER_ITEMS("����� �� ����������"),
	CHOOSE_BOOK("������� �����"),
	CHOOSE_MAGAZINE("������� ������"),
	CHOOSE_NEWSPAPER("������� ������"),
	RETURN_TO_LIBRARY("������� � ����������"),
	READ_MORE_THAN_TWO("����������� ������ ���� ����");
	
	private Command(String command){
		this.command = command;
	}
	
	private String command;
	
	public String toString(){
		return this.command;
	}
	
}
