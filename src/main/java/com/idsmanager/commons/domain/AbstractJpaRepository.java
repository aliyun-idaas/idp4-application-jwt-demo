package com.idsmanager.commons.domain;

import java.util.Collection;
import java.util.List;

/**
 * 2020/9/14
 * <pre>
 *
 * </pre>
 *
 * @author Guilty_Crown
 */
public interface AbstractJpaRepository {

    <T extends AbstractJpaDomain> T findById(long id, Class<T> clazz);

    <T extends AbstractJpaDomain> T findByUuid(Class<T> clazz, String uuid);

    <T extends AbstractJpaDomain> void saveOrUpdate(T JpaDomain);

    <T extends AbstractJpaDomain> void saveOrUpdateAll(Collection<T> JpaDomainList);

    <T extends AbstractJpaDomain> void delete(T JpaDomain);

    <T extends AbstractJpaDomain> void deleteByUuid(Class<T> clazz, String uuid);

    <T extends AbstractJpaDomain> void deleteAll(Collection<T> JpaDomainList);

    <T extends AbstractJpaDomain> List<T> findAll(Class<T> clazz, boolean archived);

    <T extends AbstractJpaDomain> List<T> findByUuids(Class<T> clazz, List<String> uuidList);

}
