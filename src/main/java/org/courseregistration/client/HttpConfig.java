package org.courseregistration.client;


public class HttpConfig {

    private final String host;
    private final int port;
    private final String protocol;
    private final int maxDefaultConnectionsPerRoute;
    private final int maxTotalConnections;
    private final String realmName;
    private final String baseUrl;

    /**
     * Constructor
     * @param builder
     */
    private HttpConfig(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.protocol = builder.protocol;
        this.maxDefaultConnectionsPerRoute = builder.maxDefaultConnectionsPerRoute;
        this.maxTotalConnections = builder.maxTotalConnections;
        this.realmName = builder.realmName;
        this.baseUrl = builder.baseUrl;
    }

    /**
     * get host
     * @return String
     */
    public String getHost() {
        return host;
    }

    /**
     * get port
     * @return int
     */
    public int getPort() {
        return port;
    }

    /**
     * get protocold
     * @return String
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * get max default connections per route
     * @return Integer
     */
    public Integer getMaxDefaultConnectionsPerRoute() {
        return maxDefaultConnectionsPerRoute;
    }

    /**
     * get max total connections
     * @return Integer
     */
    public Integer getMaxTotalConnections() {
        return maxTotalConnections;
    }

    /**
     * get real mname
     * @return String
     */
    public String getRealmName() {
        return realmName;
    }

    /**
     * get base url
     * @return String
     */
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

        /**
         * set host
         * @param host
         * @return Builder
         */
        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        /**
         * set port
         * @param port
         * @return Builder
         */
        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        /**
         * set protocol
         * @param protocol
         * @return Builder
         */
        public Builder setProtocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        /**
         * set max default connections per route
         * @param maxDefaultConnectionsPerRoute
         * @return Builder
         */
        public Builder setMaxDefaultConnectionsPerRoute(Integer maxDefaultConnectionsPerRoute) {
            this.maxDefaultConnectionsPerRoute = maxDefaultConnectionsPerRoute;
            return this;
        }

        /**
         * setMaxTotalConnections
         * @param maxTotalConnections
         * @return Builder
         */
        public Builder setMaxTotalConnections(Integer maxTotalConnections) {
            this.maxTotalConnections = maxTotalConnections;
            return this;
        }

        /**
         * set real mname
         * @param realmName
         * @return Builder
         */
        public Builder setRealmName(String realmName) {
            this.realmName = realmName;
            return this;
        }

        /**
         * set base url
         * @param baseUrl
         * @return Builder
         */
        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * build
         * @return HttpConfig
         */
        public HttpConfig build() {
            return new HttpConfig(this);
        }

        /**
         * build HttpsConfig
         * @return HttpConfig
         */
        public HttpConfig buildHttpsConfig() {
            this.host = "localhost";
            this.port = 8443;
            this.protocol = "https";
            this.baseUrl = "https://localhost:8443/api.courseregistration";
            return new HttpConfig(this);
        }
    }

}
