package org.courseregistration.client.auth;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class DigestAuthDetails {
    private static final Logger logger = LoggerFactory.getLogger(DigestAuthDetails.class);

    private String username = "";
    private String password = "";
    private String realm = "Resource Realm via Digest Authentication";
    private String nonce = "";
    private String uri = "";
    private String qop = "auth";
    private int nounceCount = 1;
    private String clientNonce = "SecretClientNonce123";
    private String response = "";
    private String httpMethod = "";

    private DigestAuthDetails() {
    }

    public static DigestAuthDetails from(String username, String password) {
        return new DigestAuthDetails(username, password);
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

    public String getHttpMethod() {
        return httpMethod;
    }

    public DigestAuthDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public DigestAuthDetails forHTTPMethod(String httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public DigestAuthDetails withNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    public DigestAuthDetails withUri(String uri) {
        this.uri = uri;
        return this;
    }

    public DigestAuthDetails withNounceCount(int nounceCount) {
        this.nounceCount = nounceCount;
        return this;
    }

    public String getDigestHeader() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        this.response = calculateResponse();
        return buildHeaderString();
    }

    private String buildHeaderString() {
        return "Digest username=\"" + username + "\", realm=\"" + realm + "\", nonce=\"" + nonce + "\", uri=\"" + uri + "\", qop=auth, nc=\"" + nounceCount + "\", cnonce=\"" + clientNonce + "\", response=\"" + response + "\", opaque=\"\"";
    }


    /**
     * Response is calculated using
     * H1 = md5("username:realm:password")
     * H2 = md5("httpmethod:uri")
     * response = md5("H1:nonce:nc:cnonce:qop:H2")
     */
    private String calculateResponse() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash_1 = DigestUtils.md5Hex((username + ":" + realm + ":" + password));
        String hash_2 = DigestUtils.md5Hex((httpMethod + ":" + uri));

        String hash_3_string = hash_1 + ":" + nonce + ":" + nounceCount +
                ":" + clientNonce + ":" + qop + ":" + hash_2;

        return DigestUtils.md5Hex(hash_3_string);

    }
}


