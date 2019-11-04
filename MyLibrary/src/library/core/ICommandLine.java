package library.core;

import java.util.List;

import library.controller.Command;

public interface ICommandLine {

	Command getNextCommand(Command...commands);
	Command getNextCommand(String message, Command...commands);
	String getString(String question);
	int getInteger(String question);
	IReader getReader(List<IReader>list);
	IArticle getArticle(List<IArticle>list);
	ILibraryItem getItem(List<ILibraryItem>list);
	
}
