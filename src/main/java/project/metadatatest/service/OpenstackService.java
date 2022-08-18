package project.metadatatest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.compute.ext.Hypervisor;
import org.openstack4j.model.compute.ext.Service;
import org.openstack4j.model.identity.v3.Token;
import project.metadatatest.engine.OpenstackEngine;
import project.metadatatest.vo.HypervisorVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class OpenstackService {

    private final OpenstackEngine openstackEngine;

    public String getAccessToken() {

        OSClientV3 connect = openstackEngine.openstackInstance();

        Token token = connect.getToken();

        return token.getId();
    }

    public List<HypervisorVO> getHypervisor() {
        OSClientV3 osClient = openstackEngine.openstackInstance();

        List<HypervisorVO> resultList = new ArrayList<>();

        List<? extends Hypervisor> hypervisors = osClient.compute().hypervisors().list();
        List<? extends Service> services = osClient.compute().services().list();

        hypervisors.forEach(item -> {
            HypervisorVO hypervisorVO = new HypervisorVO();

            hypervisorVO.setId(item.getId());
            hypervisorVO.setName(hypervisorVO.getId());
            hypervisorVO.setHypervisorHostname(item.getHypervisorHostname());
            hypervisorVO.setIpAddress(item.getHostIP());
            hypervisorVO.setState(item.getState());
            hypervisorVO.setStatus(item.getStatus());
            hypervisorVO.setVmCount(item.getRunningVM());

            // system
            hypervisorVO.setTotalVCpu(item.getVirtualCPU());
            hypervisorVO.setUsedVCpu(item.getVirtualUsedCPU());
            hypervisorVO.setTotalMemory(item.getLocalMemory());
            hypervisorVO.setUsedMemory(item.getLocalMemoryUsed());
            hypervisorVO.setTotalDisk(item.getLocalDisk());
            hypervisorVO.setUsedDisk(item.getLocalDiskUsed());

            // CPU
            BigDecimal totalCPU = new BigDecimal(hypervisorVO.getTotalVCpu());
            BigDecimal usedCPU = new BigDecimal(hypervisorVO.getUsedVCpu());
            BigDecimal percentCPU = usedCPU.multiply(new BigDecimal(100));

            percentCPU = percentCPU.divide(totalCPU, BigDecimal.ROUND_UP);
            hypervisorVO.setVCpuPercent(percentCPU);

            // Memory
            BigDecimal totalMemory = new BigDecimal(hypervisorVO.getTotalMemory());
            BigDecimal usedMemory = new BigDecimal(hypervisorVO.getUsedMemory());
            BigDecimal percentMemory = usedMemory.multiply(new BigDecimal(100));

            percentMemory = percentMemory.divide(totalMemory, BigDecimal.ROUND_UP);
            hypervisorVO.setMemoryPercent(percentMemory);

            // Local Disk
            BigDecimal totalDisk = new BigDecimal(hypervisorVO.getTotalDisk());
            BigDecimal usedDisk = new BigDecimal(hypervisorVO.getUsedDisk());
            BigDecimal percentDisk = usedDisk.multiply(new BigDecimal(100));

            percentDisk = percentMemory.divide(totalDisk, BigDecimal.ROUND_UP);
            hypervisorVO.setDiskPercent(percentDisk);

            for(Service service : services) {
                if (item.getService().getId().equals(service.getId()) && "nova-compute".equals(service.getBinary())) {
                    hypervisorVO.setIsCompute(true);
                    break;
                }
            }

            resultList.add(hypervisorVO);
        });

        return resultList;
    }
}

