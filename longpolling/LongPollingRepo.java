package com.chandan.webservices.restfulwebservices.longpolling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LongPollingRepo extends JpaRepository<Messages, String> {

}
