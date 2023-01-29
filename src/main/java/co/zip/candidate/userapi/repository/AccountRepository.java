package co.zip.candidate.userapi.repository;

import co.zip.candidate.userapi.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

  boolean existsByContactNumber(String contactNumber);

  List<Account> findByUserId(Long userId);

  @Query(value = "select distinct user_id from accounts", nativeQuery = true)
  List<Long> findDistinctUserIds();
}
