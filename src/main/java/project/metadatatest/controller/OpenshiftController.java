package project.metadatatest.controller;

import io.kubernetes.client.openapi.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.metadatatest.service.OpenshiftService;
import project.metadatatest.vo.HpaListVO;
import project.metadatatest.vo.PodListVO;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/openshift")
public class OpenshiftController {

    private final OpenshiftService openshiftService;

    @GetMapping("/pod-list")
    public List<PodListVO> getPodList() throws IOException, ApiException {

        List<PodListVO> result = openshiftService.getPodList();

        log.info("result {}", result);

        return result;
    }

    @GetMapping("/hpa-list")
    public List<HpaListVO> getHpaList() throws ApiException {

        List<HpaListVO> result = openshiftService.getHPAList();

        log.info("result {}", result);

        return result;
    }

}

