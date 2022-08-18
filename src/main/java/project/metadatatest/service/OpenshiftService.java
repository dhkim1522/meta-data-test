package project.metadatatest.service;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AutoscalingV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1HorizontalPodAutoscaler;
import io.kubernetes.client.openapi.models.V1HorizontalPodAutoscalerList;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.metadatatest.engine.K8sEngine;
import project.metadatatest.vo.HpaListVO;
import project.metadatatest.vo.PodListVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenshiftService {

    private final K8sEngine kubeEngine;

    public List<PodListVO> getPodList() throws ApiException, IOException {

        CoreV1Api coreV1Api = kubeEngine.coreV1Api();

        V1PodList v1PodList = coreV1Api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);

        List<V1Pod> podList = v1PodList.getItems();

        List<PodListVO> resultList = new ArrayList<>();

        podList.forEach(item -> {

            PodListVO podListVO = new PodListVO();

            podListVO.setPodName(item.getMetadata().getName());
            podListVO.setNodeName(item.getSpec().getNodeName());
            podListVO.setNamespace(item.getMetadata().getNamespace());
            podListVO.setIpAddress(item.getStatus().getPodIP());
            podListVO.setPodStatus(item.getStatus().getPhase());

            resultList.add(podListVO);
        });

        return resultList;
    }

    public List<HpaListVO> getHPAList() throws ApiException {

        AutoscalingV1Api autoscalingV1Api = kubeEngine.autoscalingV1Api();

        V1HorizontalPodAutoscalerList v1HorizontalPodAutoscalerList = autoscalingV1Api.listHorizontalPodAutoscalerForAllNamespaces(null,null,null,null,null,null,null,null,null);

        List<V1HorizontalPodAutoscaler> hpaList = v1HorizontalPodAutoscalerList.getItems();

        List<HpaListVO> resultList = new ArrayList<>();

        hpaList.forEach(item -> {

            HpaListVO hpaListVO = new HpaListVO();

            hpaListVO.setHpaName(item.getMetadata().getName());
            hpaListVO.setNamespace(item.getMetadata().getNamespace());
            hpaListVO.setTargetKind(item.getSpec().getScaleTargetRef().getKind());
            hpaListVO.setTargetName(item.getSpec().getScaleTargetRef().getName());
            hpaListVO.setMinReplicas(item.getSpec().getMinReplicas());
            hpaListVO.setMaxReplicas(item.getSpec().getMaxReplicas());
            hpaListVO.setTargetCPUUtilizationPercentage(item.getSpec().getTargetCPUUtilizationPercentage());
            hpaListVO.setCurrentCPUUtilizationPercentage(item.getStatus().getCurrentCPUUtilizationPercentage());

            resultList.add(hpaListVO);
        });

        return resultList;
    }

}

