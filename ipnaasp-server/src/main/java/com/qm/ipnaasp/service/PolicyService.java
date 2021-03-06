package com.qm.ipnaasp.service;

import com.qm.ipnaasp.domain.Policy;
import com.qm.ipnaasp.domain.Recording;
import com.qm.ipnaasp.domain.User;
import com.qm.ipnaasp.domain.enumeration.RecordingType;
import com.qm.ipnaasp.repository.PolicyRepository;
import com.qm.ipnaasp.repository.RecordingRepository;
import com.qm.ipnaasp.repository.UserRepository;
import com.qm.ipnaasp.security.SecurityUtils;
import com.qm.ipnaasp.web.rest.vm.PolicyVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Service Implementation for managing Policy.
 */
@Service
@Transactional
public class PolicyService {

    private final Logger log = LoggerFactory.getLogger(PolicyService.class);

    @Inject
    private PolicyRepository policyRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private RecordingRepository recordingRepository;
    /**
     * Save a policy.
     *
     * @param policy the entity to save
     * @return the persisted entity
     */
    public Policy save(Policy policy) {
        log.debug("Request to save Policy : {}", policy);
        Policy result = policyRepository.save(policy);
        return result;
    }

    @Transactional(readOnly = true)
    public Policy createPolicy(PolicyVM PolicyVM) {
        Policy policy = new Policy();
        policy.setType(PolicyVM.getPolicyType());
        policy.setCycle(PolicyVM.getPolicyCycle());
        policy.setDirection(PolicyVM.getPolicyDirection());
        policy.setStatus(PolicyVM.getPolicyStatus());
        policy.setEntryPoint(PolicyVM.getEntryPoint());
        policy.setExitPoint(PolicyVM.getExitPoint());
        policy.setRealEntryPoint(PolicyVM.getRealEntryPoint());
        policy.setRealEntryPoint(PolicyVM.getRealExitPoint());
        policy.setReason(PolicyVM.getReason());
        policy.setPush(PolicyVM.getPushPolicyFlag());
        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> {
            policy.setCreator(u);
            log.debug("get current user: {}", u);
        });
        policy.setCreateTime(ZonedDateTime.now());
        Policy result = policyRepository.save(policy);
        log.debug("Created Information for User: {}", policy);
        return result;
    }

    @Transactional(readOnly = true)
    public Policy updateMyPolicy(PolicyVM PolicyVM) {
        Policy policy = findOne(PolicyVM.getId());
        policy.setStatus(PolicyVM.getPolicyStatus());
        policy.setRealEntryPoint(PolicyVM.getRealEntryPoint());
        policy.setRealExitPoint(PolicyVM.getRealExitPoint());
        Policy result = policyRepository.save(policy);

//        Recording record = new Recording();
//        record.setPolicy(result);
//        record.setRecorder(result.getCreator());
//        record.setRecordingTime(ZonedDateTime.now());
//        record.setType(PolicyVM);
//        Recording recording = recordingRepository.save(record);
//        result.addRecordings(recording);

        result = policyRepository.save(result);

        policyRepository.flush();
        log.debug("---------------------------------------------");
        log.debug("updata Policy Information for User: {}", result);
        log.debug("---------------------------------------------");
        return result;
    }
    /**
     *  Get all the policies.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Policy> findAll() {
        log.debug("Request to get all Policies");
        List<Policy> result = policyRepository.findAll();

        return result;
    }

    @Transactional(readOnly = true)
    public List<Policy> findMyPolicies() {
        log.debug("Request to get my Policies");
        List<Policy> result = policyRepository.findByCreatorIsCurrentUser();

        return result;
    }

    /**
     *  Get one policy by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Policy findOne(Long id) {
        log.debug("Request to get Policy : {}", id);
        Policy policy = policyRepository.findOne(id);
        return policy;
    }

    /**
     *  Delete the  policy by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Policy : {}", id);
        policyRepository.delete(id);
    }
}
