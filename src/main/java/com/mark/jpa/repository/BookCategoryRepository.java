package com.mark.jpa.repository;

import com.mark.jpa.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tiffany Mark on 26/06/2016.
 */

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {



}
