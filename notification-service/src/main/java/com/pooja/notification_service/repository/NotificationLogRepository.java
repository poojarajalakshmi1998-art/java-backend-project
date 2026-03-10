package com.pooja.notification_service.repository;

import com.pooja.notification_service.entity.NotificationLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationLogRepository extends MongoRepository<NotificationLog, String> {
}
