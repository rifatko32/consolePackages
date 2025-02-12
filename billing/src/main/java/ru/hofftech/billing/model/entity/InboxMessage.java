package ru.hofftech.billing.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import ru.hofftech.billing.model.dto.CreatePackageBillRequest;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class InboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "aggregate_id", nullable = false)
    private String aggregateId;

    @Column(name = "payload", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private CreatePackageBillRequest payload;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private InboxMessageStatus status;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "published_at")
    private Timestamp publishedAt;
}