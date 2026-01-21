package com.store_sample.store.infrastructure.jpa.channel_members;

import com.store_sample.store.infrastructure.jpa.channels.TblChannels;
import com.store_sample.store.infrastructure.jpa.users.TblUsers;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;

@Entity
@Table(
    name = "CHANNEL_MEMBERS",
    uniqueConstraints = @UniqueConstraint(columnNames = {"channel_id", "user_id"})
)
@Getter
public class TblChannelMembers {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private TblUsers user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "channel_id", nullable = false)
  private TblChannels channel;

  @Column(name = "joined_at", nullable = true)
  private LocalDateTime joinedAt;

  @PrePersist
  protected void onCreate() {
    this.joinedAt = LocalDateTime.now();
  }

  protected TblChannelMembers() {
  }

  public TblChannelMembers(TblUsers user, TblChannels channel) {
    this.user = user;
    this.channel = channel;
    this.joinedAt = LocalDateTime.now();
  }

  // equals / hashCode（業務キー）
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TblChannelMembers)) {
      return false;
    }
    TblChannelMembers that = (TblChannelMembers) o;
    return Objects.equals(user, that.user)
        && Objects.equals(channel, that.channel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, channel);
  }
}