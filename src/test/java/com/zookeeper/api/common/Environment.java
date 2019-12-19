package com.zookeeper.api.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provide insight into the runtime environment.
 *
 */
public class Environment {
    public static final String JAAS_CONF_KEY = "java.security.auth.login.config";

    public static class Entry {
        private String k;
        private String v;
        public Entry(String k, String v) {
            this.k = k;
            this.v = v;
        }
        public String getKey() { return k; }
        public String getValue() { return v; }
        
        @Override
        public String toString() {
            return k + "=" + v;
        }
    }

    private static void put(ArrayList<Entry> l, String k, String v) {
        l.add(new Entry(k,v));
    }

    public static List<Entry> list() {
        ArrayList<Entry> l = new ArrayList<Entry>();
        put(l, "zookeeper.version", "3.4.14");

        try {
            put(l, "host.name",
                InetAddress.getLocalHost().getCanonicalHostName());
        } catch (UnknownHostException e) {
            put(l, "host.name", "<NA>");
        }

        put(l, "java.version",
                System.getProperty("java.version", "<NA>"));
        put(l, "java.vendor",
                System.getProperty("java.vendor", "<NA>"));
        put(l, "java.home",
                System.getProperty("java.home", "<NA>"));
        put(l, "java.class.path",
                System.getProperty("java.class.path", "<NA>"));
        put(l, "java.library.path",
                System.getProperty("java.library.path", "<NA>"));
        put(l, "java.io.tmpdir",
                System.getProperty("java.io.tmpdir", "<NA>"));
        put(l, "java.compiler",
                System.getProperty("java.compiler", "<NA>"));
        put(l, "os.name",
                System.getProperty("os.name", "<NA>"));
        put(l, "os.arch",
                System.getProperty("os.arch", "<NA>"));
        put(l, "os.version",
                System.getProperty("os.version", "<NA>"));
        put(l, "user.name",
                System.getProperty("user.name", "<NA>"));
        put(l, "user.home",
                System.getProperty("user.home", "<NA>"));
        put(l, "user.dir",
                System.getProperty("user.dir", "<NA>"));
        
        return l;
    }
    
    public static void logEnv(String msg, Logger log) {
        List<Entry> env = Environment.list();
        for (Entry e : env) {
            log.info(msg + e.toString());
        }
    }
}