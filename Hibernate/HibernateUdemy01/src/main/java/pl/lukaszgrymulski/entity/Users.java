package pl.lukaszgrymulski.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name="users")
public class Users {
    @Id @Column(name="user_id")     int userId;
    @Column(name="username")        String username;
    @Column(name="password")        String password;
    @Column(name="first_name")      String firstName;
    @Column(name="last_name")       String lastName;

    public Users(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
