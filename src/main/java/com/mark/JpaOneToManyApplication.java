package com.mark;

import com.mark.jpa.model.Book;
import com.mark.jpa.model.BookCategory;
import com.mark.jpa.repository.BookCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JpaOneToManyApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(JpaOneToManyApplication.class);

	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaOneToManyApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception {

		BookCategory categoryA = new BookCategory("Category A");
		Set bookAs = new HashSet<Book>(){{
			add(new Book("Book A1", categoryA));
			add(new Book("Book A2", categoryA));
			add(new Book("Book A3", categoryA));
		}};
		categoryA.setBooks(bookAs);

		BookCategory categoryB = new BookCategory("Category B");
		Set bookBs = new HashSet<Book>(){{
			add(new Book("Book B1", categoryB));
			add(new Book("Book B2", categoryB));
			add(new Book("Book B3", categoryB));
		}};
		categoryB.setBooks(bookBs);

		bookCategoryRepository.save(new HashSet<BookCategory>() {{
			add(categoryA);
			add(categoryB);
		}});

		for (BookCategory bookCategory : bookCategoryRepository.findAll()) {
			logger.info(bookCategory.toString());
		}
	}
}
