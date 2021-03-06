/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gemstone.gemfire.cache.query.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.gemstone.gemfire.cache.CacheClosedException;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.internal.ProxyCache;
import com.gemstone.gemfire.cache.client.internal.UserAttributes;
import com.gemstone.gemfire.cache.query.CqAttributes;
import com.gemstone.gemfire.cache.query.CqException;
import com.gemstone.gemfire.cache.query.CqExistsException;
import com.gemstone.gemfire.cache.query.CqQuery;
import com.gemstone.gemfire.cache.query.CqServiceStatistics;
import com.gemstone.gemfire.cache.query.Index;
import com.gemstone.gemfire.cache.query.IndexExistsException;
import com.gemstone.gemfire.cache.query.IndexInvalidException;
import com.gemstone.gemfire.cache.query.IndexNameConflictException;
import com.gemstone.gemfire.cache.query.IndexType;
import com.gemstone.gemfire.cache.query.MultiIndexCreationException;
import com.gemstone.gemfire.cache.query.Query;
import com.gemstone.gemfire.cache.query.QueryInvalidException;
import com.gemstone.gemfire.cache.query.QueryService;
import com.gemstone.gemfire.cache.query.RegionNotFoundException;
import com.gemstone.gemfire.cache.query.internal.cq.ClientCQ;
import com.gemstone.gemfire.cache.query.internal.cq.InternalCqQuery;
import com.gemstone.gemfire.internal.i18n.LocalizedStrings;
import com.gemstone.gemfire.internal.logging.LogService;

/**
 * A wrapper class over an actual QueryService instance. This is used when the
 * multiuser-authentication attribute is set to true.
 * 
 * @see ProxyCache
 * @since 6.5
 */
public class ProxyQueryService implements QueryService {
  private static final Logger logger = LogService.getLogger();

  ProxyCache   proxyCache;
  QueryService realQueryService;
  List<String> cqNames = new ArrayList<String>();

  public ProxyQueryService(ProxyCache proxyCache, QueryService realQueryService) {
    this.proxyCache = proxyCache;
    this.realQueryService = realQueryService;
  }

  public void closeCqs() {
    closeCqs(false);
  }

  public void closeCqs(boolean keepAlive) {
    preOp();
    try {
      ((DefaultQueryService)realQueryService).getCqService().closeAllCqs(
          !keepAlive, Arrays.asList(this.getCqs()), keepAlive);
      this.cqNames.clear();
    } catch (CqException cqe) {
      if (logger.isDebugEnabled()) {
        logger.debug("Unable to closeAll Cqs. Error: {}", cqe.getMessage(), cqe);
      }
    }
  }
  
  public Index createHashIndex(String indexName, String indexedExpression,
      String fromClause) throws IndexInvalidException,
      IndexNameConflictException, IndexExistsException,
      RegionNotFoundException, UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }
  
  public Index createHashIndex(String indexName, String indexedExpression,
      String fromClause, String imports) throws IndexInvalidException,
      IndexNameConflictException, IndexExistsException,
      RegionNotFoundException, UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  public Index createIndex(String indexName, IndexType indexType,
      String indexedExpression, String fromClause)
      throws IndexInvalidException, IndexNameConflictException,
      IndexExistsException, RegionNotFoundException,
      UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  public Index createIndex(String indexName, IndexType indexType,
      String indexedExpression, String fromClause, String imports)
      throws IndexInvalidException, IndexNameConflictException,
      IndexExistsException, RegionNotFoundException,
      UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  public Index createIndex(String indexName, String indexedExpression,
      String fromClause) throws IndexInvalidException,
      IndexNameConflictException, IndexExistsException,
      RegionNotFoundException, UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  public Index createIndex(String indexName, String indexedExpression,
      String fromClause, String imports) throws IndexInvalidException,
      IndexNameConflictException, IndexExistsException,
      RegionNotFoundException, UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  public Index createKeyIndex(String indexName, String indexedExpression,
      String fromClause) throws IndexInvalidException,
      IndexNameConflictException, IndexExistsException,
      RegionNotFoundException, UnsupportedOperationException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }


  @Override
  public void defineKeyIndex(String indexName, String indexedExpression,
      String fromClause) throws RegionNotFoundException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  @Override
  public void defineHashIndex(String indexName, String indexedExpression,
      String regionPath) throws RegionNotFoundException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  @Override
  public void defineHashIndex(String indexName, String indexedExpression,
      String regionPath, String imports) throws RegionNotFoundException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  @Override
  public void defineIndex(String indexName, String indexedExpression,
      String regionPath) throws RegionNotFoundException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  @Override
  public void defineIndex(String indexName, String indexedExpression,
      String regionPath, String imports) throws RegionNotFoundException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  @Override
  public List<Index> createDefinedIndexes() throws MultiIndexCreationException {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }
  
  @Override
  public boolean clearDefinedIndexes() {
    throw new UnsupportedOperationException(
        "Index creation on the server is not supported from the client.");
  }

  public void executeCqs() throws CqException {
    preOp();
    try {
      ((DefaultQueryService)realQueryService).getCqService().executeCqs(
          Arrays.asList(this.getCqs()));
    } catch (CqException cqe) {
      if (logger.isDebugEnabled()) {
        logger.debug("Unable to execute cqs. Error: {}", cqe.getMessage(), cqe);
      }
    }
  }

  public void executeCqs(String regionName) throws CqException {
    preOp();
    try {
      ((DefaultQueryService)realQueryService).getCqService().executeCqs(
          Arrays.asList(this.getCqs(regionName)));
    } catch (CqException cqe) {
      if (logger.isDebugEnabled()) {
        logger.debug("Unable to execute cqs on the specified region. Error: {}", cqe.getMessage(), cqe);
      }
    }
  }

  public CqQuery getCq(String cqName) {
    preOp();
    if (this.cqNames.contains(cqName)) {
      return this.realQueryService.getCq(cqName);
    } else {
      return null;
    }
  }

  public CqServiceStatistics getCqStatistics() {
    throw new UnsupportedOperationException();
  }

  public ClientCQ[] getCqs() {
    preOp();
    ClientCQ[] cqs = null;
    try {
      ArrayList<InternalCqQuery> cqList = new ArrayList<InternalCqQuery>();
      for (String name : this.cqNames) {
        cqList.add(((DefaultQueryService)realQueryService).getCqService()
            .getCq(name));
      }
      cqs = new ClientCQ[cqList.size()];
      cqList.toArray(cqs);
    } catch (CqException cqe) {
      if (logger.isDebugEnabled()) {
        logger.debug("Unable to get Cqs. Error: {}", cqe.getMessage(), cqe);
      }
    }
    return cqs;
  }

  public ClientCQ[] getCqs(String regionName) throws CqException {
    preOp();
    Collection<? extends InternalCqQuery> cqs = null;
    try {
      ArrayList<CqQuery> cqList = new ArrayList<CqQuery>();
      cqs = ((DefaultQueryService)realQueryService).getCqService()
          .getAllCqs(regionName);
      for (InternalCqQuery cq : cqs) {
        if (this.cqNames.contains(cq.getName())) {
          cqList.add((CqQuery) cq);
        }
      }
      ClientCQ[] results = new ClientCQ[cqList.size()];
      cqList.toArray(results);
      return results;
    } catch (CqException cqe) {
      if (logger.isDebugEnabled()) {
        logger.debug("Unable to get Cqs. Error: {}", cqe.getMessage(), cqe);
      }
      throw cqe;
    }
  }

  public Index getIndex(Region<?, ?> region, String indexName) {
    throw new UnsupportedOperationException(
        "Index operation on the server is not supported from the client.");
  }

  public Collection<Index> getIndexes() {
    throw new UnsupportedOperationException(
        "Index operation on the server is not supported from the client.");
  }

  public Collection<Index> getIndexes(Region<?, ?> region) {
    throw new UnsupportedOperationException(
        "Index operation on the server is not supported from the client.");
  }

  public Collection<Index> getIndexes(Region<?, ?> region, IndexType indexType) {
    throw new UnsupportedOperationException(
        "Index operation on the server is not supported from the client.");
  }

  public CqQuery newCq(String queryString, CqAttributes cqAttributes)
      throws QueryInvalidException, CqException {
    preOp(true);
    ClientCQ cq = null;
    try {
      cq = ((DefaultQueryService)realQueryService).getCqService()
          .newCq(null, queryString, cqAttributes,
              ((DefaultQueryService)realQueryService).getPool(), false);
      cq.setProxyCache(this.proxyCache);
      this.cqNames.add(cq.getName());
    } catch (CqExistsException cqe) {
      // Should not throw in here.
      if (logger.isDebugEnabled()) {
        logger.debug("Unable to createCq. Error: {}", cqe.getMessage(), cqe);
      }
    } finally {
      postOp();
    }
    return cq;
  }

  public CqQuery newCq(String queryString, CqAttributes cqAttributes,
      boolean isDurable) throws QueryInvalidException, CqException {
    preOp(true);
    ClientCQ cq = null;
    try {
      cq = ((DefaultQueryService)realQueryService).getCqService()
          .newCq(null, queryString, cqAttributes,
              ((DefaultQueryService)realQueryService).getPool(), isDurable);
      cq.setProxyCache(this.proxyCache);
      this.cqNames.add(cq.getName());
    } catch (CqExistsException cqe) {
      // Should not throw in here.
      if (logger.isDebugEnabled()) {
        logger.debug("Unable to createCq. Error: {}", cqe.getMessage(), cqe);
      }
    } finally {
      postOp();
    }
    return cq;
  }

  public CqQuery newCq(String cqName, String queryString,
      CqAttributes cqAttributes) throws QueryInvalidException,
      CqExistsException, CqException {
    preOp(true);
    try {
      if (cqName == null) {
        throw new IllegalArgumentException(
            LocalizedStrings.DefaultQueryService_CQNAME_MUST_NOT_BE_NULL
                .toLocalizedString());
      }
      ClientCQ cq = ((DefaultQueryService)realQueryService)
          .getCqService().newCq(cqName, queryString, cqAttributes,
              ((DefaultQueryService)realQueryService).getPool(), false);
      cq.setProxyCache(proxyCache);
      this.cqNames.add(cq.getName());
      return cq;
    } finally {
      postOp();
    }
  }

  public CqQuery newCq(String cqName, String queryString,
      CqAttributes cqAttributes, boolean isDurable)
      throws QueryInvalidException, CqExistsException, CqException {
    preOp(true);
    try {
      if (cqName == null) {
        throw new IllegalArgumentException(
            LocalizedStrings.DefaultQueryService_CQNAME_MUST_NOT_BE_NULL
                .toLocalizedString());
      }
      ClientCQ cq = ((DefaultQueryService)realQueryService)
          .getCqService().newCq(cqName, queryString, cqAttributes,
              ((DefaultQueryService)realQueryService).getPool(),
              isDurable);
      cq.setProxyCache(proxyCache);
      this.cqNames.add(cq.getName());
      return cq;
    } finally {
      postOp();
    }
  }

  public Query newQuery(String queryString) {
    preOp();
    return ((DefaultQueryService)realQueryService).newQuery(queryString,
        this.proxyCache);
  }

  public void removeIndex(Index index) {
    throw new UnsupportedOperationException(
        "Index operation on the server is not supported from the client.");
  }

  public void removeIndexes() {
    throw new UnsupportedOperationException(
        "Index operation on the server is not supported from the client.");
  }

  public void removeIndexes(Region<?, ?> region) {
    throw new UnsupportedOperationException(
        "Index operation on the server is not supported from the client.");
  }

  public void stopCqs() throws CqException {
    preOp();
    try {
      ((DefaultQueryService)realQueryService).getCqService().stopCqs(
          Arrays.asList(this.getCqs()));
    } catch (CqException cqe) {
      if (logger.isDebugEnabled()) {
        logger.debug("Unable to stop all CQs. Error: {}", cqe.getMessage(), cqe);
      }
    }
  }

  public void stopCqs(String regionName) throws CqException {
    preOp();
    try {
      ((DefaultQueryService)realQueryService).getCqService().stopCqs(
          Arrays.asList(this.getCqs(regionName)));
    } catch (CqException cqe) {
      if (logger.isDebugEnabled()) {
        logger.debug("Unable to stop cqs on the specified region. Error: {}", cqe.getMessage(), cqe);
      }
    }
  }
  
  public List<String> getAllDurableCqsFromServer() throws CqException {
    preOp();
    try {
      ((DefaultQueryService)realQueryService).getAllDurableCqsFromServer();
    } catch (CqException cqe) {
      if (logger.isDebugEnabled()) {
        logger.debug("Unable to get all durablec client cqs on the specified region. Error: {}", cqe.getMessage(), cqe);
      }
    }
    return Collections.EMPTY_LIST;
  }

  private void preOp() {
    preOp(false);
  }

  private void preOp(boolean setTL) {
    if (this.proxyCache.isClosed()) {
      throw new CacheClosedException("Cache is closed for this user.");
    }
    if (setTL) {
      UserAttributes.userAttributes.set(this.proxyCache.getUserAttributes());
    }
  }

  private void postOp() {
    UserAttributes.userAttributes.set(null);
  }

}
