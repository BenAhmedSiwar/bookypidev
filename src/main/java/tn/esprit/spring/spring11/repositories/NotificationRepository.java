package tn.esprit.spring.spring11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.spring11.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
