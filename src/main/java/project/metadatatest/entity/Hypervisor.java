package project.metadatatest.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Hypervisor {

    @Id
    @Column(name = "SEQUENCE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sequenceId;

    @Column(name = "HYPERVISOR_ID")
    private String id;

    @Column(name = "HYPERVISOR_NAME")
    private String hypervisorHostname;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "VM_COUNT")
    private Integer vmCount;

    @Column(name = "TOTAL_V_CPU")
    private Integer totalVCpu;

    @Column(name = "USED_V_CPUT")
    private Integer usedVCpu;

    @Column(name = "V_CPU_PERCENT")
    private BigDecimal vCpuPercent;

    @Column(name = "TOTAL_MEMORY")
    private long totalMemory;

    @Column(name = "USED_MEMORY")
    private Integer usedMemory;

    @Column(name = "MEMORY_PRECENT")
    private BigDecimal memoryPercent;

    @Column(name = "TOTAL_DISK")
    private Integer totalDisk;

    @Column(name = "USED_DISK")
    private Integer usedDisk;

    @Column(name = "DISK_PERCENT")
    private BigDecimal diskPercent;

    @Column(name = "IS_COMPUTE")
    private Boolean isCompute;
}
