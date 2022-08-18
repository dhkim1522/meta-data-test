package project.metadatatest.vo;

import lombok.Data;

@Data
public class HpaListVO {

    private String hpaName;
    private String namespace;
    private String targetKind;
    private String targetName;
    private Integer minReplicas;
    private Integer maxReplicas;
    private Integer targetCPUUtilizationPercentage;
    private Integer currentCPUUtilizationPercentage;
}
