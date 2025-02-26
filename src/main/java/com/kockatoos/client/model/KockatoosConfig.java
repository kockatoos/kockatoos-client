package com.kockatoos.client.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class KockatoosConfig {

    private Local local;
    private Group group;
    private Application application;
    private Host host;
    private Cloud cloud;
    private Resources resources;

    @Getter
    @Setter
    @ToString
    public static class Local {
        private String expose;
    }

    @Getter
    @Setter
    @ToString
    public static class Group {
        private String name;
    }

    @Getter
    @Setter
    @ToString
    public static class Application {
        private String name;
        private String port;
        private String context;

    }
    @Getter
    @Setter
    @ToString
    public static class Host {
        private String name;
        private String environment;
    }

    @Getter
    @Setter
    @ToString
    public static class Cloud {
        private Server server;

        @Getter
        @Setter
        @ToString
        public static class Server {
            private String url;
            private String enable;
            private String secret;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Resources {
        private List<JMSBroker> jms;
        private Http http;

        @Getter
        @Setter
        @ToString
        public static class JMSBroker {

            private String brokerName;
            private String brokerType;
            private List<JMSQueue> inbound;
            private List<JMSQueue> outbound;

            @Getter
            @Setter
            @ToString
            public static class JMSQueue {
                private String name;
                private String jndi;
            }
        }

        @Getter
        @Setter
        @ToString
        public static class Http {
            private Rest rest;
            private Soap soap;

            @Getter
            @Setter
            @ToString
            public static class Rest {
                private List<HttpEndpoint> inbound;
                private List<HttpEndpoint> outbound;
            }

            @Getter
            @Setter
            @ToString
            public static class Soap {
                private List<HttpEndpoint> inbound;
                private List<HttpEndpoint> outbound;
            }

            @Getter
            @Setter
            @ToString
            public static class HttpEndpoint {
                private String url;
                private String method;
            }
        }
    }
}
