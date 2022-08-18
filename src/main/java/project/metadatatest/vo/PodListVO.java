package project.metadatatest.vo;

import lombok.Data;

@Data
public class PodListVO {

    private String podName;
    private String namespace;
    private String nodeName;
    private String ipAddress;
    private String podStatus;

}
