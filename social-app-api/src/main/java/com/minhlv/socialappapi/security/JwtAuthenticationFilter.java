package com.minhlv.socialappapi.security;

import com.minhlv.socialappapi.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    @Autowired
//    private ThongKeTruyCapService thongKeTruyCapService;

//    @Autowired
//    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //String requestURL = request.getRequestURL().toString();
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                String username = jwtTokenProvider.getUsernameFromJWT(jwt);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    try {
//                        Calendar date = new GregorianCalendar();
//                        SystemUserEntity systemUserEntity = userService
//                                .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//                        ThongKeTruyCapEntity thongKeTruyCapEntity = new ThongKeTruyCapEntity(date.get(Calendar.YEAR),
//                                date.get(Calendar.MONTH) + 1, date.get(Calendar.DATE), requestURL);
//                        thongKeTruyCapService.save(thongKeTruyCapEntity, systemUserEntity);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken)) {
            if (bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            } else {
                return bearerToken;
            }
        }
        return bearerToken;
    }
}