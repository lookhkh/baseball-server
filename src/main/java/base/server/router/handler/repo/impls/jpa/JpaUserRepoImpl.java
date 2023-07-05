package base.server.router.handler.repo.impls.jpa;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import base.log.DefaultLogFormatter;
import base.server.router.handler.repo.UserRepository;
import base.server.router.handler.repo.impls.jpa.domains.UserInfoDto;
import base.server.user.connection.dto.user.UserInfo;

public class JpaUserRepoImpl implements UserRepository {
	
	@Override
	public Optional<UserInfo> findById(String userId) {
			
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("user");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			UserInfoDto u = em.find(UserInfoDto.class, userId);
			tx.commit();
			return u==null ? Optional.empty() :Optional.of(u.toUserInfo());
		}catch(Exception e) {
			DefaultLogFormatter.printError(e, e.getMessage());
		}

		return Optional.empty();
	}
}
