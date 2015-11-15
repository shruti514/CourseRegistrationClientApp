package org.courseregistration.client.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DigestAuthDetails {
    private static final Logger logger = LoggerFactory.getLogger(DigestAuthDetails.class);
    private String username;
    private String password;
    private String realm;
    private String nonce;
    private String uri;
    private String qop;
    private int nounceCount;
    private String clientNonce;
    private String response;

    private DigestAuthDetails(String username, String password, String realm, String nonce, String uri, String qop, int nounceCount, String clientNonce, String response) {
        this.username = username;
        this.password = password;
        this.realm = realm;
        this.nonce = nonce;
        this.uri = uri;
        this.qop = qop;
        this.nounceCount = nounceCount;
        this.clientNonce = clientNonce;
        this.response = response;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRealm() {
        return realm;
    }

    public String getNonce() {
        return nonce;
    }

    public String getUri() {
        return uri;
    }

    public String getQop() {
        return qop;
    }

    public int getNounceCount() {
        return nounceCount;
    }

    public String getClientNonce() {
        return clientNonce;
    }

    public String getResponse() {
        return response;
    }

    public static DigestAuthDetailsBuilder digestBuilder(String username, String password) {
        return new DigestAuthDetailsBuilder(username, password);
    }

    @Override
    public String toString() {
        return "Digest username=\""+username+"\", realm=\""+realm+"\", nonce=\""+nonce+"\", uri=\""+uri+"\", qop=auth, nc=\""+nounceCount+"\", cnonce=\""+clientNonce+"\", response=\""+response+"\", opaque=\"\"";
    }

    static class DigestAuthDetailsBuilder {
        private String username="";
        private String password="";
        private String realm="";
        private String nonce="";
        private String uri="";
        private String qop="";
        private int nounceCount = 1;
        private String clientNonce ="";
        private String response="";
        private String httpMethod="";

        public DigestAuthDetailsBuilder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public DigestAuthDetailsBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public DigestAuthDetailsBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public DigestAuthDetailsBuilder forHTTPMethod(String httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }


        public DigestAuthDetailsBuilder withRealm(String realm) {
            this.realm = realm;
            return this;
        }

        public DigestAuthDetailsBuilder withNonce(String nonce) {
            this.nonce = nonce;
            return this;
        }

        public DigestAuthDetailsBuilder withUri(String uri) {
            this.uri = uri;
            return this;
        }

        public DigestAuthDetailsBuilder withQop(String qop) {
            this.qop = qop;
            return this;
        }

        public DigestAuthDetailsBuilder withNounceCount(int nounceCount) {
            this.nounceCount = nounceCount;
            return this;
        }

        public DigestAuthDetailsBuilder withClientNonce(String clientNonce) {
            this.clientNonce = clientNonce;
            return this;
        }

        public DigestAuthDetails build() throws UnsupportedEncodingException, NoSuchAlgorithmException {
            this.response = calculateResponse();
            return new DigestAuthDetails(username, password, realm, nonce, uri, qop, nounceCount, clientNonce, response);
        }



        /**
         * Response is calculated using
         * H1 = md5("username:realm:password")
         * H2 = md5("httpmethod:uri")
         * response = md5("H1:nonce:nc:cnonce:qop:H2")
         */
        private String calculateResponse() throws UnsupportedEncodingException, NoSuchAlgorithmException {
            try {
                String algorithm = "MD5";

                MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
                byte[] hash_1 = messageDigest.digest((username + ":" + realm + ":" + password).getBytes("UTF-8"));
                byte[] hash_2 = messageDigest.digest(( httpMethod + ":" + uri).getBytes("UTF-8"));

                String hash_3_string = Arrays.toString(hash_1) + ":"+nonce+":"+nounceCount+
                        ":"+clientNonce+":"+qop+":"+Arrays.toString(hash_2);

                byte[] responseBytes = messageDigest.digest(hash_3_string.getBytes("UTF-8"));

                return Arrays.toString(responseBytes);
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                logger.error("Error calculating MD5 response");
                throw e;
            }
        }
    }
}


