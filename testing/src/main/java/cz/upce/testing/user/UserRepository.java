package cz.upce.testing.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTestApp, Long> {
}
