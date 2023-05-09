package tn.esprit.spring.spring11.interfaces;

import tn.esprit.spring.spring11.entities.Notification;

import javax.mail.MessagingException;
import java.util.List;

public interface INotificationService {
    public List<Notification> addAllNotif(List<Notification> notifications);
    List<Notification> selectAllNotification();
    void deleteAllNotif (List<Notification> notifications);
    void sendEmail(String to, String subject, String text) throws MessagingException;
}