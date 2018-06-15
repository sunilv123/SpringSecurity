package net.sunil.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.sunil.modal.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	AppUser findByEmail(String email);

	AppUser findByMobile(String mobile);

}
