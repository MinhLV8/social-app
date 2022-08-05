package com.minhlv.socialappapi.secury;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.minhlv.socialappapi.entity.SystemUser;
import com.minhlv.socialappapi.entity.ThongKeTruyCapEntity;
import com.minhlv.socialappapi.service.ThongKeTruyCapService;
import com.minhlv.socialappapi.service.UserService;
import com.minhlv.socialappapi.service.impl.UserDetailsServiceImpl;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private ThongKeTruyCapService thongKeTruyCapService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURL = request.getRequestURL().toString();
        // log.info("request : {}", requestURL);
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                String username = tokenProvider.getUsernameFromJWT(jwt);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    try {
                        Calendar date = new GregorianCalendar();
                        SystemUser systemUser = userService
                                .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
                        ThongKeTruyCapEntity thongKeTruyCapEntity = new ThongKeTruyCapEntity(date.get(Calendar.YEAR),
                                date.get(Calendar.MONTH) + 1, date.get(Calendar.DATE), requestURL);
                        thongKeTruyCapService.save(thongKeTruyCapEntity, systemUser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
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