package library.core;

import java.util.List;

public interface IReader extends ILibraryObject{

	void read(ILibraryItem item);
	void hold(ILibraryItem item);
	List<ILibraryItem> readItems();
	List<ILibraryItem> onHold();
	boolean isRead(ILibraryItem item);
	
}
