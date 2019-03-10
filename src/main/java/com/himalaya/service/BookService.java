package com.himalaya.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.himalaya.domain.Book;

/**
 * 
 * @author Ashok
 *
 */
public class BookService {

	private static Map<String, Book> dataMap = new HashMap<String, Book>();

	/**
	 * This method is used to store Book obj into map
	 * 
	 * @param book
	 * @return boolean
	 */
	public boolean addBook(Book book) {

		String isbn = book.getIsbn();

		if (!dataMap.containsKey(isbn)) {
			dataMap.put(isbn, book);
			System.out.println("Map :: " + dataMap);
			return true;// success
		}

		return false;// failure

	}

	/**
	 * This method is used to retrive value from map using key
	 * 
	 * @param isbn
	 * @return
	 */
	public Book findByIsbn(String isbn) {
		System.out.println("Service::Map::" + dataMap);
		if (dataMap.containsKey(isbn)) {
			return dataMap.get(isbn);
		}
		return null;
	}

	/**
	 * This method is used to update Book in map
	 * 
	 * @param book
	 * @return
	 */
	public Book updateBook(Book book) {
		if (dataMap.containsKey(book.getIsbn())) {
			return dataMap.put(book.getIsbn(), book);
		}
		return null;
	}

	/**
	 * This method is used delete Book using isbn
	 * 
	 * @param isbn
	 * @return
	 */
	public boolean deleteByIsbn(String isbn) {
		if (dataMap.containsKey(isbn)) {
			dataMap.remove(isbn);
			return true;
		}
		return false;
	}

	/**
	 * This method is used to retrive all values from map
	 * 
	 * @return Collection<Book>
	 */
	public Collection<Book> findAll() {
		return dataMap.values();
	}

}
