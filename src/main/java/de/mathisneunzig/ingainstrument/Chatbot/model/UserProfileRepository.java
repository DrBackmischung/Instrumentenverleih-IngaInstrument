package de.mathisneunzig.ingainstrument.Chatbot.model;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, UUID>{
	Optional<UserProfile> findByIp(String ip);
}
