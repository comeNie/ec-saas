package com.yjg.ec.platform.erp.integration.auth.session;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * shiro的session接口
 */
public interface ShiroSessionRepository {

    void saveSession(Session session);

    void deleteSession(Serializable sessionId);

    Session getSession(Serializable sessionId);

    Collection<Session> getAllSessions();
}  
