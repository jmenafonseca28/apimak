package mye.fisio.mak.apimak.data;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mye.fisio.mak.apimak.domain.Client;

@Service
public class ClientDetailImpl implements UserDetailsService {

    @Autowired
    private ClientServiceJpa clientServiceJpa;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Client userDetail = clientServiceJpa.getClientByEmail(username);

        Collection<? extends GrantedAuthority> authorities = List.of(userDetail.getRole()).stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role))).collect(Collectors.toSet());

        return new User(userDetail.getEmail(), userDetail.getPassword(), true, true,
                true, true, authorities);
    }

}
