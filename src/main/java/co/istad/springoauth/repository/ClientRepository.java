package co.istad.springoauth.repository;

import co.istad.springoauth.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,String>    {
    Optional<Client> findByClientId(String clientId);

}
