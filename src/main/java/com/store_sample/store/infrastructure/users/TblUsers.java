package com.store_sample.store.infrastructure.users;

import com.store_sample.store.infrastructure.channels.TblChannels;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "USERS")
@Data
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

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "channel_members",
      joinColumns = {
          @JoinColumn(name = "user_id", referencedColumnName = "id")
      },
      inverseJoinColumns = {
          @JoinColumn(name = "channel_id", referencedColumnName = "id")
      }
  )
  private List<TblChannels> users;

}