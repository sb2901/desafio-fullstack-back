package com.test.fullstack.test.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "user")
@Entity
public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false,name = "nr_id")
        private Integer id;

        @Column(nullable = false,name = "ds_name")
        private String name;

        @Column(unique = true, length = 100, nullable = false,name = "ds_email")
        private String email;

        @Column(nullable = false,name = "ds_password")
        private String password;

        @CreationTimestamp
        @Column(updatable = false, name = "dt_created")
        private Date createdAt;

        @UpdateTimestamp
        @Column(name = "dt_updated")
        private Date updatedAt;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
        }

        @Override
        public String getUsername() {
                return email;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }
}
