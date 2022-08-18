package project.metadatatest.engine;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.AutoscalingV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.Config;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class K8sEngine {

    private final static String k8sUrl = "https://100.0.0.175:6443";
    private final static String accessToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6InZwSFkyQmFyaDlNNTBOcF90enZPMHo0eDVfTnVaNkR5bkdvQko2M0NIcVUifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJzZXJ2aWNlIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6InNhLXNlcnZpY2UtY2F0YWxvZy1lbmdpbmUtdG9rZW4tZHpqcGwiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoic2Etc2VydmljZS1jYXRhbG9nLWVuZ2luZSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjljMmVlMjZiLWVmZDctNDg1Ny1hN2E5LWQ3Y2U3ZTU1Njc5MSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpzZXJ2aWNlOnNhLXNlcnZpY2UtY2F0YWxvZy1lbmdpbmUifQ.fOfzpqseGxbagvPWGEJsBzuDl5iEusHOd8_uhsKmLup8EbBd17n8fVXW0D6kNdzp8z_3UUjZgXa--KUP_UlAsnFOnsl8bqDkoeN1Qxk0KWdtd-wnhakiPXiKS-EZPXYkR4LGeOJDHzryFDWnwebtj0pgaiY1n-PAegwIQ6q2iUB2VgKYp2yLPLCSRo6JaM6LKibgkPDJ5f0nrKnkuYZhIpsIA9q0MRfEOM9Xg18B9xSpHTiCv4bwBWJMi45EKIWzHR5wLbNOPpyG9gjg7V-KDn3W2QSZkm0cXpmSCdXlI6IEiBxUzHI0VTzVfI01901WCgMex66k-wtbZPqz5d8aaA";

    public CoreV1Api coreV1Api() throws IOException {

        ApiClient client = Config.fromToken(k8sUrl, accessToken, false);

        CoreV1Api coreV1Api = new CoreV1Api();
        coreV1Api.setApiClient(client);

        return coreV1Api;
    }

    public AutoscalingV1Api autoscalingV1Api() {

        ApiClient client = Config.fromToken(k8sUrl, accessToken, false);

        AutoscalingV1Api autoscalingV1Api = new AutoscalingV1Api();
        autoscalingV1Api.setApiClient(client);

        return autoscalingV1Api;
    }
}

