package pojo.authentication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateToken {
    public String username;
    public String password;
}
