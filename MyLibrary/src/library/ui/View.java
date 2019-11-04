package library.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import library.controller.Command;
import library.core.IArticle;
import library.core.ICommandLine;
import library.core.ILibraryItem;
import library.core.IReader;
import library.core.IView;

public class View implements IView, ICommandLine{

	private Scanner scanner = new Scanner(System.in);

	@Override
	public void showReaders(List<IReader> list) {
		System.out.println("������ ���������:");
		for(IReader reader:list){
			System.out.println("reader: "+reader.getName()+" (id:"+reader.getId()+")");
		}
		System.out.println("   �����: "+list.size());
	}

	@Override
	public Command getNextCommand(Command...commands) {
		System.out.println("�������� ���������� ��������:");
		boolean exit = false;
		boolean cont = false;
		int i = 1;
		Map<Integer,Command>commandMap = new HashMap<>();
		for(Command command:commands){
			if(command==Command.CONTINUE){
				cont = true;
				continue;
			}
			if(command==Command.EXIT){
				exit=true;
				continue;
			}
			commandMap.put(i, command);
			System.out.println((i++)+" - "+command.toString());
		}
		if(exit){
			commandMap.put(0, Command.EXIT);
			System.out.println("... ��� ������ �� ��������� ������� '0'");
		}
		if(cont){
			System.out.println("... ����� ������ ���� ��� ������ � ������� ����");
		}
		Integer integer = this.getInteger("��� ������ ������?");
		Command command = commandMap.get(integer);
		return command!=null?command:Command.CONTINUE;
	}

	@Override
	public String getString(String question) {
		System.out.println(question);
		return scanner.nextLine();
	}

	@Override
	public int getInteger(String question){
			System.out.println(question);
			String line = scanner.nextLine();
			try{
				int result = Integer.parseInt(line);
				return result;
			}catch(NumberFormatException e){
				System.out.println("������������ ����!");
				return getInteger(question);
			}	
	}

	@Override
	public Command getNextCommand(String message, Command... commands) {
		System.out.println(message);
		return this.getNextCommand(commands);
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void showLibraryItems(List<ILibraryItem> list) {
		System.out.println("������:");
		for(ILibraryItem item:list){
			System.out.println("id: "+item.getId()+"���: "+item.getType()+"  ������������: "+item.getName()
					+" ������: "+(item.isFree()?"��������":"�����"));
		}
		System.out.println("�����: "+list.size()+" �������");
		
	}

	@Override
	public void showLibraryBooks(List<ILibraryItem> list) {
		System.out.println("������ ����:");
		for(ILibraryItem item:list){
			System.out.println("id: "+item.getId()+" ������������: "+item.getName()
					+" ������: "+(item.isFree()?"��������":"�����"));
		}
		System.out.println("�����: "+list.size()+" �������");
	}

	@Override
	public void showLibraryNewspapers(List<ILibraryItem> list) {
		System.out.println("������ �����:");
		for(ILibraryItem item:list){
			System.out.println("id: "+item.getId()+" ������������: "+item.getName()
					+" ������: "+(item.isFree()?"��������":"�����"));
		}
		System.out.println("�����: "+list.size()+" �������");
	}

	@Override
	public void showLibraryMagazines(List<ILibraryItem> list) {
		System.out.println("������ ��������:");
		for(ILibraryItem item:list){
			System.out.println("id: "+item.getId()+" ������������: "+item.getName()
					+" ������: "+(item.isFree()?"��������":"�����"));
		}
		System.out.println("�����: "+list.size()+" �������");
	}

	@Override
	public void showArticles(List<IArticle> list1) {
		System.out.println("������ ������ � ����������:");
		for (IArticle article: list1) {
			System.out.println("id: " + article.getId() + " ��������:" + article.getName() + 
					" | " + article.getNameOfLibraryItem() + " (" + article.getTypeOfLibraryItem() +
					")" + " ������: " + (article.isFree()?"��������":"�����"));
		}
		System.out.println("�����: " + list1.size() + " �������");
	}

	@Override
	public IReader getReader(List<IReader> list) {
		IReader chosenReader = null;
		if (list.size() == 0) {
			return null;
		}
		Map<Integer, IReader> map = new HashMap<>();
		int i = 1;
		for (IReader reader:list) {
			map.put(i, reader);
			System.out.println((i++) + " - " + reader.getName() + " id: " + reader.getId());
		}
		while (chosenReader == null) {
			Integer choice = this.getInteger("�������� ��������");
			chosenReader = map.get(choice);
		}
		return chosenReader;
	}

	@Override
	public IArticle getArticle(List<IArticle> list) {
		IArticle chosenArticle = null;
		if (list.size() == 0) {
			return null;
		}
		Map<Integer, IArticle> map = new HashMap<>();
		int i = 1;
		for (IArticle article:list) {
			map.put(i, article);
			System.out.println((i++) + " - " + article.getName() + " id: " + article.getId() + "| " + 
			article.getNameOfLibraryItem() + " (" + article.getTypeOfLibraryItem() + ")");
		}
		while (chosenArticle == null) {
			Integer choice = this.getInteger("�������� �������� � ������� �������");
			chosenArticle = map.get(choice);
		}
		return chosenArticle;
	}

	@Override
	public ILibraryItem getItem(List<ILibraryItem> list) {
		ILibraryItem chosenItem = null;
		if (list.size() == 0) {
			return null;
		}
		Map<Integer, ILibraryItem> map = new HashMap<>();
		int i = 1;
		for (ILibraryItem item:list) {
			map.put(i, item);
			System.out.println((i++) + " - " + item.getName() + "("  + item.getType() + ")" + " id: " + item.getId());
		}
		while (chosenItem == null) {
			Integer choice = this.getInteger("�������� ��������");
			chosenItem = map.get(choice);
		}
		return chosenItem;
	}
	
}
