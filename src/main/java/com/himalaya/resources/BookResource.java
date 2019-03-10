package com.himalaya.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.himalaya.domain.Book;
import com.himalaya.service.BookService;
import com.sun.jersey.spi.resource.Singleton;

/**
 * This class is Used to perform CURD operations on Book Data
 * 
 * @author Ashok
 *
 */
@Path("/book")
@Singleton
public class BookResource {

	public BookResource() {
		System.out.println("BookResource::Controller called");
	}

	private BookService service = new BookService();

	/**
	 * This method is used to Handle POST request
	 * 
	 * @param book
	 * @return String
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response add(Book book) {
		System.out.println("add() method Called :: " + book);
		boolean isAdded = service.addBook(book);

		if (isAdded) {
			String successMsg = "Book Added Successfully..!!";
			return Response.ok(successMsg).build();
		}
		String failureMsg = "Recieved Duplicate Book Details";
		return Response.status(Status.BAD_REQUEST).entity(failureMsg).build();
	}

	/**
	 * This method is used to handle GET req with Path param
	 * 
	 * @param isbn
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("{isbn}")
	public Response getByIsbn(@PathParam("isbn") String isbn) {
		System.out.println("Get By ISBn Called :: " + isbn);
		Book book = service.findByIsbn(isbn);

		if (null != book) {
			return Response.ok(book).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Invalid ISBN").build();
		}
	}

	/**
	 * This method is used handle PUT request
	 * 
	 * @param book
	 * @return Book
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response update(Book book) {
		System.out.println("update called");
		Book updatedBook = service.updateBook(book);
		return Response.ok(updatedBook).build();
	}

	/**
	 * This method is used to handle DELETE request
	 * 
	 * @param isbn
	 * @return
	 */
	@DELETE
	@Path("{isbn}")
	public Response delete(@PathParam("isbn") String isbn) {
		System.out.println("Delete method called");
		boolean isDeleted = service.deleteByIsbn(isbn);
		if (isDeleted) {
			String succMsg = "Deleted Successfully";
			return Response.ok(succMsg).build();
		}
		String errMsg = "Failed to delete";
		return Response.status(Status.BAD_REQUEST).entity(errMsg).build();
	}

	/**
	 * This method is used to handle get request
	 * 
	 * @return List<Book>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Book> getAllBooks() {
		System.out.println("getAllBooks() called");
		Collection<Book> books = service.findAll();
		return new ArrayList<Book>(books);
	}

}
