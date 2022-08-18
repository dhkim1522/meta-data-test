package project.metadatatest.service;

import io.kubernetes.client.openapi.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.metadatatest.entity.HpaList;
import project.metadatatest.entity.Hypervisor;
import project.metadatatest.entity.PodList;
import project.metadatatest.repository.HpaListRepository;
import project.metadatatest.repository.HypervisorRepository;
import project.metadatatest.repository.PodListRepository;
import project.metadatatest.vo.HpaListVO;
import project.metadatatest.vo.HypervisorVO;
import project.metadatatest.vo.PodListVO;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MetaService {

    private final HypervisorRepository hypervisorRepository;
    private final PodListRepository podListRepository;
    private final HpaListRepository hpaListRepository;

    private final OpenstackService openstackService;
    private final OpenshiftService openshiftService;

    @Transactional
    public void insertHypervisorMetadata() {

        List<HypervisorVO> hypervisorVOList;

        hypervisorVOList = openstackService.getHypervisor();

        log.info("hypervisorVOList size", hypervisorVOList.size());
        log.info("hypervisorVOList {}", hypervisorVOList);

        hypervisorVOList.forEach(item -> {
            Hypervisor entity
                    = Hypervisor.builder()
                        .id(item.getId())
                        .hypervisorHostname(item.getHypervisorHostname())
                        .ipAddress(item.getIpAddress())
                        .status(item.getStatus())
                        .diskPercent(item.getDiskPercent())
                        .isCompute(item.getIsCompute())
                        .memoryPercent(item.getMemoryPercent())
                        .totalDisk(item.getTotalDisk())
                        .totalMemory(item.getTotalMemory())
                        .totalVCpu(item.getTotalVCpu())
                        .usedDisk(item.getUsedDisk())
                        .usedMemory(item.getUsedMemory())
                        .usedVCpu(item.getUsedVCpu())
                        .vCpuPercent(item.getVCpuPercent())
                        .vmCount(item.getVmCount())
                    .build();

            hypervisorRepository.save(entity);
        });
    }

    @Transactional
    public void insertPodListMetadata() throws IOException, ApiException {

        List<PodListVO> podListVOList;

        podListVOList = openshiftService.getPodList();

        log.info("podListVOList size", podListVOList.size());
        log.info("podListVOList {}", podListVOList);

        podListVOList.forEach(item -> {
            PodList entity = PodList.builder()
                    .podName(item.getPodName())
                    .namespace(item.getNamespace())
                    .podStatus(item.getPodStatus())
                    .ipAddress(item.getIpAddress())
                    .nodeName(item.getNodeName())
                    .build();

            podListRepository.save(entity);
        });
    }

    @Transactional
    public void insertHPAListMetadata() throws IOException, ApiException {

        List<HpaListVO> hpaListVOList;

        hpaListVOList = openshiftService.getHPAList();

        log.info("hpaListVOList size", hpaListVOList.size());
        log.info("hpaListVOList {}", hpaListVOList);

        hpaListVOList.forEach(item -> {
            HpaList entity = HpaList.builder()
                    .hpaName(item.getHpaName())
                    .namespace(item.getNamespace())
                    .targetKind(item.getTargetKind())
                    .targetName(item.getTargetName())
                    .minReplicas(item.getMinReplicas())
                    .maxReplicas(item.getMaxReplicas())
                    .currentCPUUtilizationPercentage(item.getCurrentCPUUtilizationPercentage())
                    .targetCPUUtilizationPercentage(item.getTargetCPUUtilizationPercentage())
                    .build();

            hpaListRepository.save(entity);
        });
    }
}
