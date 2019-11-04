package library.controller;

public enum Command {

	EXIT("Выход"),
	SHOW_READERS("Показать читателей"),
	CONTINUE("Продолжить"), 
	ADD_READER("Добавить читателя"),
	CONFIRM("Подтвердить"),
	DECLINE("Отклонить"),
	ADD_LIBRARY_ITEM("Добавить в библиотеку"),
	ADD_BOOK("Добавить книгу"),
	SHOW_ALL_LIBRARY_ITEMS("Показать весь список библиотеки"),
	SHOW_LIBRARY_BOOKS("Покозать список книг библиотеки"),
	SHOW_LIBRARY_NEWSPAPERS("Показать список газет библиотеки"),
	SHOW_LIBRARY_MAGAZINES("Показать список журналов библиотеки"),
	SHOW_ARTICLES("Показать список статей библиотеки"),
	ADD_NEWSPAPER("Добавить газету"),
	ADD_MAGAZINE("Добавиь журнал"),
	ADD_ARTICLE("Добавить статью"),
	GIVE_TO_READER("Выдать читателю"),
	SEARCHING_OVER_ARTICLES("Поиск по статьям"),
	SEARCHING_OVER_ITEMS("Поиск по источникам"),
	CHOOSE_BOOK("Выбрать книгу"),
	CHOOSE_MAGAZINE("Выбрать журнал"),
	CHOOSE_NEWSPAPER("Выбрать газету"),
	RETURN_TO_LIBRARY("Вернуть в библиотеку"),
	READ_MORE_THAN_TWO("Прочитавшие больше двух книг");
	
	private Command(String command){
		this.command = command;
	}
	
	private String command;
	
	public String toString(){
		return this.command;
	}
	
}
