/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itaca.callcenter.web.listener;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Web application lifecycle listener.
 *
 * @author Gerardo Blanco
 */
@WebListener
public class BootStrapApp implements ServletContextListener {

    private static final Logger log = LogManager.getLogger(BootStrapApp.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext conexto = sce.getServletContext();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
