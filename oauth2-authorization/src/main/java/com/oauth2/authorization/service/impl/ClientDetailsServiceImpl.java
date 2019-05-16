package com.oauth2.authorization.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义ClientDetailsService
 * @author fangxing.peng
 *
 */
@Component("clientDetailsService")
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
@Primary
public class ClientDetailsServiceImpl implements ClientDetailsService, ClientRegistrationService{

//    @Resource
//    private ClientDetailsMapper clientDetailsMapper;

    public ClientDetails loadClientByClientId(String clientId)
            throws ClientRegistrationException
    {
//        logger.debug("client id is {}", clientId);
//        if (Objects.isNull(clientId)) {
//            throw new NoSuchClientException("No client with requested id is null");
//        }
//        Z3Client p = this.clientDetailsMapper.load(clientId);
//        if (Objects.isNull(p)) {
//            throw new NoSuchClientException("No client with requested id: " + clientId);
//        }
//        return new Z3ClientDetails(p);
        return new BaseClientDetails();
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {

    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {

    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {

    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {

    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return null;
    }
}
