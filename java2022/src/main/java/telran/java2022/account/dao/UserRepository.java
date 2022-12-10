package telran.java2022.account.dao;

import org.springframework.data.repository.CrudRepository;

import telran.java2022.account.model.User;


public interface UserRepository extends CrudRepository<User, String> {
}
