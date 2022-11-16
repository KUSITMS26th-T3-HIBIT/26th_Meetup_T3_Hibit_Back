package com.hibit.kusitms26tht3hibitback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@Entity
@Table(name = "users")
@Schema(description = "회원")
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false, length = 15, unique = true)
    @Schema(description = "아이디", example = "arin123")
    private String id;

    @Column(nullable = false, length = 20)
    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @Column(nullable = false, length = 20)
    @Schema(description = "닉네임", example = "아린")
    private String nickname;

    @Column(nullable = false, length = 11)
    @Schema(description = "전화번호", example = "01011112222")
    private String phone_number;

    @Column(nullable = false, length = 8)
    @Schema(description = "생년월일")
    private String birth;

    @Column(nullable = false)
    @Schema(description = "성별", example = "True")
    private boolean gender;

    @Column(nullable = false)
    @Schema(description = "이메일")
    private String email;

    @Column(nullable = true)
    private double temperature;

    @Column(nullable = false, length = 50)
    @Schema(description = "거주지", example = "경기도")
    private String home;

    @Column(nullable = false, length = 100)
    @Schema(description = "자기소개", example = "안녕")
    private String introduce;

    @Column(nullable = false)
    @Schema(description = "관람 스타일")
    private int style;

    @ElementCollection
    @Schema(description = "성격")
    private List<Integer> personality;

    @ElementCollection
    @Schema(description = "취미")
    private List<Integer> hobby;

    @Column
    private String admin="USER";

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
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