package study.musical.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import study.musical.domain.member.entity.Member;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
public class PrincipalDetails implements UserDetails {

    private Member member;

    public PrincipalDetails(Member member) {// PrincipalDetails 안에 Member 정보를 넣기 위해 생성자에 셋팅!
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();   //계정이 가지고 있는 권한 목록
        collections.add(() -> String.valueOf(member.getRole()));
        return collections;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
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
