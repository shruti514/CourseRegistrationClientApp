package org.courseregistration.client;


import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class CustomTrustManager implements X509TrustManager {

    /**
     * check whether client is trusted
     * @param x509Certificates
     * @param s
     * @throws CertificateException
     */
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    /**
     * check server is trusted
     * @param x509Certificates
     * @param s
     * @throws CertificateException
     */
    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    /**
     * X509 certificate
     * @return
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
