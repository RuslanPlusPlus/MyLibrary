package library.domain;

import library.controller.Type;

public class Newspaper extends LibraryItem{

	public Newspaper(String name) {
		super(name);
	}

	@Override
	public Type getType() {
		return Type.NEWSPAPER;
	}
	
}
