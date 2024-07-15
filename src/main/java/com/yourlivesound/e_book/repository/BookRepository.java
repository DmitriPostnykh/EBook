package com.yourlivesound.e_book.repository;

import com.yourlivesound.e_book.model.EBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<EBook, Long> {
}
