package pl.coderslab.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.coderslab.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	 @Query("select b from Transaction b " +
	         "where b.date > ?1 and b.date < ?2")
	  List<Transaction> findByDateBetween(Date dat1, Date dat2);
	 
	 @Query("select sum(b.income) from Transaction b where b.date > ?1 and b.date < ?2")
	 Double totalIncome(Date dat1, Date dat2);
	 
	 
	 @Query("select b from Transaction b " +
	         "where b.date > ?1 and b.date < ?2 and b.curr.name like ?3")
	  List<Transaction> findByDateBetweenIn(Date dat1, Date dat2, String curr);
	 
	 @Query("select sum(b.income) from Transaction b where b.date > ?1 and b.date < ?2 and b.curr.name like ?3")
	 Double totalIncomeIn(Date dat1, Date dat2, String curr);
	 
	 
}
