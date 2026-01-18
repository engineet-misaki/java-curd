package com.store_sample.store.infrastructure.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store_sample.store.infrastructure.channel_members.TblChannelMembers;
import com.store_sample.store.infrastructure.channels.TblChannels;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class TblUsers {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "username", nullable = false, length = 50)
  private String username;

  @Column(name = "password", nullable = false, length = 500)
  private String password;

  @Column(name = "enabled", nullable = false)
  private Boolean enabled;

  @Column(name = "role", nullable = true)
  private String role;

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  @JsonIgnore
  private Set<TblChannelMembers> channelMembers = new HashSet<>();

  public void joinChannel(TblChannels channel) {
    TblChannelMembers member = new TblChannelMembers(this, channel);
    channelMembers.add(member);
    channel.getChannelMembers().add(member);
  }

  public void leaveChannel(TblChannels channel) {
    channelMembers.removeIf(m -> m.getChannel().equals(channel));
  }
}