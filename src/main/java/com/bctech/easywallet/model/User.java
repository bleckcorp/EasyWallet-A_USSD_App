package com.bctech.easywallet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Size(min = 3, message = "Name can not be less than 3")
    String name;

    @NotEmpty
    @Email
    String email;

    @NotEmpty
    @Size(min = 11, message = "Phone number should have at least 11 characters")
    String phoneNumber;

    @Size(min = 11, max = 11, message = "BVN number must not be less than 11")
    String bvn;

    @Size(min = 4, max = 4, message = "Pin can not be more than 4")
    String pin;

    @CreationTimestamp
    @Builder.Default
    LocalDateTime createdAt = LocalDateTime.now();
    @UpdateTimestamp
    @Builder.Default
    LocalDateTime modifiedAt = LocalDateTime.now();
}
