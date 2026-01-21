package com.store_sample.store.infrastructure.jpa.channels;

import com.store_sample.store.infrastructure.jpa.channel_members.TblChannelMembers;
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
@Table(name = "CHANNELS")
@Getter
@Setter
public class TblChannels {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name", nullable = false, length = 30)
  private String name;

  @OneToMany(
      mappedBy = "channel",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private Set<TblChannelMembers> channelMembers = new HashSet<>();
}