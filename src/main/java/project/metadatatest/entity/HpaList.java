package project.metadatatest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class HpaList {

    @Id
    @Column(name = "SEQUENCE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sequenceId;

    @Column(name = "HPA_NAME")
    private String hpaName;

    @Column(name = "NAMESPACE")
    private String namespace;

    @Column(name = "TARGET_KIND")
    private String targetKind;

    @Column(name = "TARGET_NAME")
    private String targetName;

    @Column(name = "MIN_REPLICAS")
    private Integer minReplicas;

    @Column(name = "MAX_REPLICAS")
    private Integer maxReplicas;

    @Column(name = "TARGET_CPU_UTILIZATION_PRECENTAGE")
    private Integer targetCPUUtilizationPercentage;

    @Column(name = "CURRENT_CPU_UTILIZATION_PRECENTAGE")
    private Integer currentCPUUtilizationPercentage;

}
