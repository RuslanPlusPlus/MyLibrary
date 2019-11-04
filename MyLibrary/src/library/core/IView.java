package library.core;

import java.util.List;

public interface IView {

	void showReaders(List<IReader>list);
	void showLibraryItems(List<ILibraryItem>list);
	void showLibraryBooks(List<ILibraryItem>list);
	void showLibraryNewspapers(List<ILibraryItem>list);
	void showLibraryMagazines(List<ILibraryItem>list);
	void showMessage(String message);
	void showArticles(List<IArticle>list1);
}
