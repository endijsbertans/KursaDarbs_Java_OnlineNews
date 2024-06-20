package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="UserTable")
@Entity
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  // Rename to id

    @NotNull
    @Pattern(regexp = "[A-Za-z.]+")
    @Size(min = 3, max = 20)
    @Column(name = "Username")
    private String username;

    @NotNull
    @Column(name = "Password")
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Collection<MyAuthority> authorities = new ArrayList<>();

    public MyUser(String username, String password, MyAuthority... auths) {
        setUsername(username);
        setPassword(password);
        for (MyAuthority tempA : auths)
            addAuthority(tempA);
    }

    public void addAuthority(MyAuthority authority) {
        if (!authorities.contains(authority))
            authorities.add(authority);
    }

    public void removeAuthority(MyAuthority authority) {
        if (authorities.contains(authority))
            authorities.remove(authority);
    }
}