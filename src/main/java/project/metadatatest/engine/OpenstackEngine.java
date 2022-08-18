package project.metadatatest.engine;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.client.IOSClientBuilder;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenstackEngine {

    private final static String endpointURL = "http://100.0.0.189:5000/v3";
    private final static String domain = "Default";
    private final static String project = "admin";
    private final static String username = "admin";
    private final static String password = "okestro2018";

    private static OSClientV3 connect() {

        IOSClientBuilder.V3 iosClientBuilder = OSFactory.builderV3();
        Identifier domainIdentifier = Identifier.byName(domain);
        Identifier projectIdentifier = Identifier.byName(project);
        org.openstack4j.core.transport.Config osClientConfig = Config.newConfig();
        String natResolution = endpointURL.substring(endpointURL.indexOf("//")+2, endpointURL.lastIndexOf(":"));
        osClientConfig.withEndpointNATResolution(natResolution);

        OSClientV3 osClient = iosClientBuilder
                .withConfig(endpointURL.startsWith("https://") ? osClientConfig.withSSLVerificationDisabled() : osClientConfig)
                .endpoint(endpointURL)
                .credentials(username, password, domainIdentifier)
                .scopeToProject(projectIdentifier, domainIdentifier)
                .authenticate();

        return osClient;
    }

    public OSClientV3 openstackInstance() {
        return connect();
    }
}
