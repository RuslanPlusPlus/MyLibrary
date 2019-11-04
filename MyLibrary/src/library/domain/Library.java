package library.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import library.controller.Type;
import library.core.IArticle;
import library.core.ILibrary;
import library.core.ILibraryItem;
import library.core.ILibraryObject;
import library.core.IReader;

public class Library implements ILibrary{

	private Map<Serializable,IReader>readers = new HashMap<Serializable, IReader>();
	private Map<Serializable,ILibraryItem>items = new HashMap<Serializable, ILibraryItem>();
	private Map<Serializable, IArticle>articles = new HashMap<Serializable, IArticle>();

	public List<IReader> getReaders() {
		return new ArrayList<IReader>(this.readers.values());
	}
	
	public List<ILibraryItem> getLibraryItemsList(Type type) {
		switch(type) {
		case BOOK:
			return this.items.values().stream().filter(obj -> obj instanceof Book).collect(Collectors.toList());
		case NEWSPAPER:
			return this.items.values().stream().filter(obj -> obj instanceof Newspaper).collect(Collectors.toList());
		case MAGAZINE:
			return this.items.values().stream().filter(obj -> obj instanceof Magazine).collect(Collectors.toList());
		default:
			return new ArrayList<ILibraryItem>(this.items.values());
		}
	}
	
	@Override
	public List<IArticle> getArticlesList() {
		return new ArrayList<IArticle>(this.articles.values());    
	}

	@Override
	public void add(ILibraryObject ob) {
		if(ob instanceof IReader){
			this.readers.put(ob.getId(), (IReader) ob); 
			return;
		}
		if(ob instanceof ILibraryItem){
			this.items.put(ob.getId(), (ILibraryItem) ob);
			if (((ILibraryItem) ob).getArticleListSize() > 0) {
				 addArticle((ILibraryItem)ob);
			}
			return;
		}
	}

	@Override
	public List<ILibraryItem> getFreeItems() {
		return this.items.values().stream().filter(i -> i.isFree()).collect(Collectors.toList());
	}

	@Override
	public List<ILibraryItem> getBusyItems() {
		return this.items.values().stream().filter(i -> !i.isFree()).collect(Collectors.toList());
	}

	private void addArticle(ILibraryItem ob) {
		for (String articleName: ob.getArticles()) {
			IArticle article = new Article(articleName, ob);
			this.articles.put(article.getId(), article);
		}
	}

	@Override
	public List<IArticle> getFreeArticles() {
		return this.articles.values().stream().filter(article->article.isFree()).collect(Collectors.toList());
	}

	@Override
	public List<IArticle> getBusyArticles() {
		return this.articles.values().stream().filter(article->!article.isFree()).collect(Collectors.toList());
	}

	@Override
	public List<ILibraryItem> getFreeBooks() {
		List<ILibraryItem>list = this.getFreeItems();
		return list.stream().filter(item ->item instanceof Book).collect(Collectors.toList());
	}

	@Override
	public List<ILibraryItem> getFreeMagazines() {
		List<ILibraryItem>list = this.getFreeItems();
		return list.stream().filter(item ->item instanceof Magazine).collect(Collectors.toList());
	}

	@Override
	public List<ILibraryItem> getFreeNewspapers() {
		List<ILibraryItem>list = this.getFreeItems();
		return list.stream().filter(item ->item instanceof Newspaper).collect(Collectors.toList());
	}

	@Override
	public List<IReader> getRadersReadMoreThanTwoBook() {
		return this.readers.values().stream().filter(i->i.readItems().size() > 2).collect(Collectors.toList());
	}

	
	
}
