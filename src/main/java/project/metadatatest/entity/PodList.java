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
public class PodList {

    @Id
    @Column(name = "SEQUENCE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sequenceId;

    @Column(name = "POD_NAME")
    private String podName;

    @Column(name = "NAMESPACE")
    private String namespace;

    @Column(name = "NODE_NAME")
    private String nodeName;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "POD_STATUS")
    private String podStatus;

}
