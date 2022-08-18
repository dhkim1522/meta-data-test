package project.metadatatest.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HypervisorVO {

    private String id;
    private String name;
    private String timestamp;
    private String providerName;
    private String hypervisorHostname;
    private String ipAddress;
    private String type;
    private String state;
    private String status;
    private Integer currentWorkload;
    private Integer leastDiskAvailable;
    private Integer hypervisorVersion;
    private Integer runningVms;

    private String availabilityZone;
    private String project;
    private String machineModel;

    private Integer vmCount;
    private Integer totalVCpu;
    private Integer usedVCpu;
    private BigDecimal vCpuPercent;
    private long totalMemory;
    private Integer usedMemory;
    private BigDecimal memoryPercent;
    private Integer totalDisk;
    private Integer usedDisk;
    private BigDecimal diskPercent;
    private Integer freeDiskGb;
    private Integer freeRam;

    private Boolean isCompute;
}
