package example.mykarsolcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import example.mykarsolcrud.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
