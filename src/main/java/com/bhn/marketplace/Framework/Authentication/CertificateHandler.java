package com.bhn.marketplace.Framework.Authentication;

import com.bhn.marketplace.Framework.Utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

public class CertificateHandler {

    private static final String SECURITY_ALGORITHM = "PKCS12";
    private static CertificateHandler certificateHandler = null;
    private static RestAssuredConfig certificateConfig = null;

    private CertificateHandler() {
        try {
            String certificatePath = PropertyReader.getGlobalValue("API_CERTIFICATE_PATH");
            String password = PropertyReader.getGlobalValue("API_CERTIFICATE_PASS");
            String currentDirectory = System.getProperty("user.dir");
            FileInputStream certFile = new FileInputStream(new File(currentDirectory + certificatePath));
            KeyStore keyStore = KeyStore.getInstance(SECURITY_ALGORITHM);
            keyStore.load(certFile, password.toCharArray());
            X509HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, password);
            socketFactory.setHostnameVerifier(hostnameVerifier);
            certificateConfig = RestAssured.config()
                    .sslConfig(new SSLConfig().with().sslSocketFactory(socketFactory).and().allowAllHostnames());
        } catch (Exception certError) {
            Assert.fail(certError.getMessage());
        }

    }

    public static RestAssuredConfig getCertificateConfig() {
        try {
            if (certificateHandler == null) {
                certificateHandler = new CertificateHandler();
            }
        } catch (Exception certError) {
            Assert.fail(certError.getMessage());
        }
        return certificateConfig;
    }

}
