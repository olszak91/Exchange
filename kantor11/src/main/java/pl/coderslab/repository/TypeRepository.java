package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.TransactionType;

public interface TypeRepository extends JpaRepository<TransactionType, Long>{

	
	TransactionType findByName(String lastname);

	
}




