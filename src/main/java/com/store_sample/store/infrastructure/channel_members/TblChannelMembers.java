package com.store_sample.store.infrastructure.channel_members;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "CHANNEL_MEMBERS")
@Data
public class TblChannelMembers {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "user_id", nullable = false)
  private int userId;

  @Column(name = "channel_id", nullable = false)
  private int channelId;

  @Column(name = "joined_at", nullable = true)
  private LocalDateTime joinedAt;


  @PrePersist
  protected void onCreate() {
    this.joinedAt = LocalDateTime.now();
  }
}