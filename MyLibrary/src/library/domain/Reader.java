package library.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import library.core.ILibraryItem;
import library.core.IReader;

public class Reader implements IReader{

	private static long ID_COUNTER; 

	private String name;
	private long id;
	
	private Set<ILibraryItem>readBooks = new HashSet<>();
	private Set<ILibraryItem>onHold = new HashSet<>();
	
	public Reader(String name){
		this.name = name;
		this.id = ID_COUNTER++;
	}
	
	@Override
	public Serializable getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reader other = (Reader) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public void read(ILibraryItem item) {
		this.onHold.remove(item);
		this.readBooks.add(item);
		
	}

	@Override
	public List<ILibraryItem> readItems() {
		return new ArrayList<>(this.readBooks);
	}

	@Override
	public List<ILibraryItem> onHold() {
		return new ArrayList<>(this.onHold);
	}

	@Override
	public void hold(ILibraryItem item) {
		this.onHold.add(item);
	}

	@Override
	public boolean isRead(ILibraryItem item) {
		return this.readBooks.contains(item);
	}
	
}
