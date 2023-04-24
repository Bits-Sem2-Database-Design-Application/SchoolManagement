package bits.pilani.sem2.dda.assignment1.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_credentials")
public class UserCredentials {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="User_name")
	private String userName;
	@Column(name="password")
	private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_id", referencedColumnName = "id")
	private User user;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Role_id", referencedColumnName = "id")
	private Role role;
}
