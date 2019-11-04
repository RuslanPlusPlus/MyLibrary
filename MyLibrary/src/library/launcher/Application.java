package library.launcher;

import library.controller.Command;
import library.core.IArticle;
import library.core.ICommandLine;
import library.core.ILibrary;
import library.core.ILibraryItem;
import library.core.IReader;
import library.core.IView;
import library.domain.Book;
import library.domain.Library;
import library.domain.Magazine;
import library.domain.Newspaper;
import library.domain.Reader;
import library.ui.View;
import static library.controller.Command.*;
import static library.controller.Type.*;

public class Application {

	private ILibrary library;
	private ICommandLine commandLine;
	private IView view;
	
	{
		this.library = new Library();
		this.commandLine = new View();
		this.view = (IView)commandLine;
		
	}
	
	@SuppressWarnings("incomplete-switch")
	public void run(){
		boolean run = true;
		while(run){
			Command command = this.commandLine.getNextCommand(SHOW_READERS,ADD_READER,EXIT,ADD_LIBRARY_ITEM,
					SHOW_ALL_LIBRARY_ITEMS, SHOW_LIBRARY_BOOKS, SHOW_LIBRARY_NEWSPAPERS, SHOW_LIBRARY_MAGAZINES, SHOW_ARTICLES, 
					GIVE_TO_READER, RETURN_TO_LIBRARY, READ_MORE_THAN_TWO);
			switch(command){
			case ADD_READER:addReader();
			break;
			case SHOW_READERS:showReaders();
			break;
			case EXIT:close();
			run=false;
			break;
			case ADD_LIBRARY_ITEM:this.addLibraryItem();
			break;
			case SHOW_ALL_LIBRARY_ITEMS:this.showLibraryItems();
			break;
			case SHOW_LIBRARY_BOOKS: this.showLibraryBooks();
			break;
			case SHOW_LIBRARY_NEWSPAPERS: this.showLibraryNewspapers();
			break;
			case SHOW_LIBRARY_MAGAZINES: this.showLibraryMagazines();
			break;
			case SHOW_ARTICLES: this.showArticles();
			break;
			case GIVE_TO_READER: this.giveToReader();
			break;
			case RETURN_TO_LIBRARY: this.returnToLibrary();
			break;
			case READ_MORE_THAN_TWO: this.readMoreThanTwoBooks();
			break;
			}
		}
	}
	
	private void showReaders(){
		this.view.showReaders(this.library.getReaders());
	}
	
	private void showLibraryItems(){
		this.view.showLibraryItems(this.library.getLibraryItemsList(LIBRARY_ITEM));
	}
	
	private void showLibraryBooks() {
		this.view.showLibraryBooks(this.library.getLibraryItemsList(BOOK));
	}
	
	private void showLibraryNewspapers() {
		this.view.showLibraryNewspapers(this.library.getLibraryItemsList(NEWSPAPER));
	}
	
	private void showLibraryMagazines() {
		this.view.showLibraryMagazines(this.library.getLibraryItemsList(MAGAZINE));
	}
	
	private void addReader(){
		String name = this.commandLine.getString("������� ��� ��������:");
		Command command = this.commandLine.getNextCommand("�������� �������� � ������ '"+name+"' ?",CONFIRM,DECLINE);
		switch(command){
		case CONFIRM:
			this.library.add(new Reader(name));
			this.view.showMessage("����� �������� ������� ��������");
			break;
		default:this.view.showMessage("...������");
		}
	}
	
	private void close(){
		this.view.showMessage("�� ��������");
	}
	
	private void addBook() {
		String bookName = this.commandLine.getString("������� �������� �����:");
		Book book = new Book(bookName);
		Command command = this.commandLine.getNextCommand("�������� ������ ?", CONTINUE,CONFIRM,DECLINE);
		switch (command) {
		case CONFIRM: 
			addArticles(book);
			break;
		default:
			this.view.showMessage("...������");
			break;
		}
		
	    command = this.commandLine.getNextCommand("�������� ����� � ��������� '"+bookName+"' ?",CONTINUE,CONFIRM,DECLINE);
		switch (command) {
		case CONFIRM:
			this.library.add(book);
			this.view.showMessage("����� ����� ������� ���������");
			break;
		default:this.view.showMessage("...������");
		break;
		}
		
	}
	
	private void addNewspaper() {
		String newspaperName = this.commandLine.getString("������� �������� ������:");
		Newspaper newspaper = new Newspaper(newspaperName);
		Command command = this.commandLine.getNextCommand("�������� ������ ?", CONTINUE,CONFIRM,DECLINE);
		switch (command) {
		case CONFIRM: 
			addArticles(newspaper);
			break;
		default:
			this.view.showMessage("...������");
			break;
		}
		
		command = this.commandLine.getNextCommand("�������� ������ � ��������� '"+newspaperName+"' ?",CONTINUE,CONFIRM,DECLINE);
		switch(command){
		case CONFIRM:
			this.library.add(newspaper);
			this.view.showMessage("����� ������ ������� ���������");
			break;
		default:this.view.showMessage("...������");
		break;
		}
	}	
	
	private void addMagazine() {
		String magazineName = this.commandLine.getString("������� �������� �������:");
		Magazine magazine = new Magazine(magazineName);
		Command command = this.commandLine.getNextCommand("�������� ������ ?", CONTINUE,CONFIRM,DECLINE);
		switch (command) {
		case CONFIRM: 
			addArticles(magazine);
			break;
		default:
			this.view.showMessage("...������");
			break;
		}
		
		command = this.commandLine.getNextCommand("�������� ������ � ��������� '"+magazineName+"' ?",CONTINUE,CONFIRM,DECLINE);
		switch (command) {
		case CONFIRM:
			this.library.add(magazine); 
			this.view.showMessage("����� ������ ������� ��������");
			break;
		default:
			this.view.showMessage("...������");
			break;
		}
	}
	
	private void addLibraryItem(){
		Command command = this.commandLine.getNextCommand(CONTINUE, ADD_BOOK, ADD_NEWSPAPER, ADD_MAGAZINE);
		switch (command) {
		case ADD_BOOK:
			addBook();
			break;
		case ADD_NEWSPAPER:
			addNewspaper();
			break;
		case ADD_MAGAZINE:
			addMagazine();
			break;
		default:
			break;
		}
	}
	
	private void addArticles(ILibraryItem item) {
		boolean add = true;
		while (add) {
			Command command = this.commandLine.getNextCommand("�������� ������?", CONFIRM, DECLINE);
			switch (command) {
			case CONFIRM:
				String name = this.commandLine.getString("������� �������� ������");
				Command confirm = this.commandLine.getNextCommand("�������� ������ � ��������� " + name, CONFIRM, DECLINE);
				switch(confirm) {
				case CONFIRM: 
					item.addArticle(name);
					break;
				default:
					this.view.showMessage("...������");
					break;
				}
				break;
			default:
				add = false;
				this.view.showMessage("...������");
				break;
			}
		}
	}
	
	private void showArticles() {
		this.view.showArticles(library.getArticlesList());
	}
	
	private void giveToReader() {
		IReader reader = this.commandLine.getReader(this.library.getReaders());
		boolean searching = true;
		if (reader != null) {
			while (searching) {
			Command command = this.commandLine.getNextCommand("������ ������: ", SEARCHING_OVER_ARTICLES, SEARCHING_OVER_ITEMS, CONTINUE);
			switch(command) {
			case SEARCHING_OVER_ARTICLES:
				IArticle article = this.commandLine.getArticle(this.library.getFreeArticles());
				if (article != null) {
					article.busy(reader);
				}
				else {
					this.view.showMessage("������ ���");
				}
				break;
			case SEARCHING_OVER_ITEMS:
				Command source = this.commandLine.getNextCommand(CHOOSE_BOOK, CHOOSE_MAGAZINE, CHOOSE_NEWSPAPER, CONTINUE);
				switch(source) {
				case CHOOSE_BOOK:
					ILibraryItem bookItem = this.commandLine.getItem(this.library.getFreeBooks());
					if (bookItem != null) {
						bookItem.busy(reader);
					}
					else {
						this.view.showMessage("���� ���");
					}
					break;
				case CHOOSE_MAGAZINE:
					ILibraryItem magazineItem = this.commandLine.getItem(this.library.getFreeMagazines());
					if (magazineItem != null) {
						magazineItem.busy(reader);
					}
					else {
						this.view.showMessage("�������� ���");
					}
					break;
				case CHOOSE_NEWSPAPER:
					ILibraryItem newspaperItem = this.commandLine.getItem(this.library.getFreeMagazines());
					if (newspaperItem != null) {
						newspaperItem.busy(reader);
					}
					else {
						this.view.showMessage("����� ���");
					}
					break;
				default:
					break;
				}
				break;
			default:
				searching = false;
				this.view.showMessage("...������");
				break;
				}
			}
			}
		else {
			this.view.showMessage("��������� ���");
		}
	}
	
	private void returnToLibrary() {
		IReader reader = this.commandLine.getReader(this.library.getReaders());
		boolean run = true;
		if (reader != null) {
			while (run) {
				Command command = this.commandLine.getNextCommand(RETURN_TO_LIBRARY, CONTINUE);
				switch(command) {
				case RETURN_TO_LIBRARY:
					ILibraryItem item = this.commandLine.getItem(reader.onHold());
					if (item != null) {
					Command confirm = this.commandLine.getNextCommand("������� �������� � ��������� " + item.getName() + " id: " 
					+ item.getId() + " �������� " + reader.getName() + " id: " + reader.getId(), CONFIRM,DECLINE);
					switch (confirm) {
					case CONFIRM:
						item.free();
						break;
					default:
						this.view.showMessage("...������");
						break;
						}
					}
					else {
						this.view.showMessage("���������� �� ����� ���");
						run = false;
						break;
					}
					break;
				default:
					run = false;
					this.view.showMessage("...������");
					break;
				}
				
			}
		}
		else {
			this.view.showMessage("��������� ���");
		}
	}
	
	private void readMoreThanTwoBooks() {
		this.view.showMessage("����������� ������ ���� ����:");
		this.view.showReaders(this.library.getRadersReadMoreThanTwoBook());
	}
	
}
