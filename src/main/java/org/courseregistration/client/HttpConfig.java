package org.courseregistration.client;


public class HttpConfig {

    private final String host;
    private final int port;
    private final String protocol;
    private final int maxDefaultConnectionsPerRoute;
    private final int maxTotalConnections;
    private final String realmName;
    private final String baseUrl;

    private HttpConfig(Builder builder){
        this.host = builder.host;
        this.port = builder.port;
        this.protocol = builder.protocol;
        this.maxDefaultConnectionsPerRoute = builder.maxDefaultConnectionsPerRoute;
        this.maxTotalConnections = builder.maxTotalConnections;
        this.realmName = builder.realmName;
        this.baseUrl = builder.baseUrl;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public Integer getMaxDefaultConnectionsPerRoute() {
        return maxDefaultConnectionsPerRoute;
    }

    public Integer getMaxTotalConnections() {
        return maxTotalConnections;
    }

    public String getRealmName() {
        return realmName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public static class Builder {
        private  String host = "localhost";
        private  int port=8888;
        private  String protocol="http";
        private  int maxDefaultConnectionsPerRoute =100;
        private  int maxTotalConnections=100;
        private  String realmName="Resource Realm via Digest Authentication";
        private String baseUrl="http://localhost:8888/api.courseregistration";

        public Builder setHost(String host) {
            this.host = host;
            return  this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return  this;
        }

        public Builder setProtocol(String protocol) {
            this.protocol = protocol;
            return  this;
        }

        public Builder setMaxDefaultConnectionsPerRoute(Integer maxDefaultConnectionsPerRoute) {
            this.maxDefaultConnectionsPerRoute = maxDefaultConnectionsPerRoute;
            return  this;
        }

        public Builder setMaxTotalConnections(Integer maxTotalConnections) {
            this.maxTotalConnections = maxTotalConnections;
            return  this;
        }

        public Builder setRealmName(String realmName) {
            this.realmName = realmName;
            return  this;
        }
        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return  this;
        }

        public HttpConfig build(){
            return  new HttpConfig(this);
        }
    }

}
