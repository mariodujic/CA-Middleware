package com.groundzero.camw.security

import com.groundzero.camw.cachier.CacheController
import com.groundzero.camw.utils.PropertiesUtils
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SecretAuthenticationFilter : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        val key = PropertiesUtils.getAuthenticationCredentials<SecretAuthenticationFilter>("key")
        val secret = PropertiesUtils.getAuthenticationCredentials<SecretAuthenticationFilter>("secret")

        val auth = request.getHeader(key)
        if (secret != auth) {
            throw SecurityException()
        }
        chain.doFilter(request, response)
    }
}