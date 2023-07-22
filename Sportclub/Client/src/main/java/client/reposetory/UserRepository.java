package client.reposetory;


import client.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByName (String name);
    List<User> findAll();
}
