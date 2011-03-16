/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.tasks.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.tasks.NoSuchTasksEntryException;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.model.impl.TasksEntryImpl;
import com.liferay.tasks.model.impl.TasksEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the tasks entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see TasksEntryPersistence
 * @see TasksEntryUtil
 * @generated
 */
public class TasksEntryPersistenceImpl extends BasePersistenceImpl<TasksEntry>
	implements TasksEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TasksEntryUtil} to access the tasks entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TasksEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByGroupId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_ASSIGNEEUSERID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByAssigneeUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSIGNEEUSERID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByAssigneeUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_RESOLVERUSERID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByResolverUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOLVERUSERID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByResolverUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_G_U = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_G_A = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByG_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_A = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByG_A",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_G_R = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByG_R",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByG_R",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the tasks entry in the entity cache if it is enabled.
	 *
	 * @param tasksEntry the tasks entry to cache
	 */
	public void cacheResult(TasksEntry tasksEntry) {
		EntityCacheUtil.putResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryImpl.class, tasksEntry.getPrimaryKey(), tasksEntry);
	}

	/**
	 * Caches the tasks entries in the entity cache if it is enabled.
	 *
	 * @param tasksEntries the tasks entries to cache
	 */
	public void cacheResult(List<TasksEntry> tasksEntries) {
		for (TasksEntry tasksEntry : tasksEntries) {
			if (EntityCacheUtil.getResult(
						TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
						TasksEntryImpl.class, tasksEntry.getPrimaryKey(), this) == null) {
				cacheResult(tasksEntry);
			}
		}
	}

	/**
	 * Clears the cache for all tasks entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TasksEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TasksEntryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the tasks entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(TasksEntry tasksEntry) {
		EntityCacheUtil.removeResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryImpl.class, tasksEntry.getPrimaryKey());
	}

	/**
	 * Creates a new tasks entry with the primary key. Does not add the tasks entry to the database.
	 *
	 * @param tasksEntryId the primary key for the new tasks entry
	 * @return the new tasks entry
	 */
	public TasksEntry create(long tasksEntryId) {
		TasksEntry tasksEntry = new TasksEntryImpl();

		tasksEntry.setNew(true);
		tasksEntry.setPrimaryKey(tasksEntryId);

		return tasksEntry;
	}

	/**
	 * Removes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the tasks entry to remove
	 * @return the tasks entry that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tasksEntryId the primary key of the tasks entry to remove
	 * @return the tasks entry that was removed
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry remove(long tasksEntryId)
		throws NoSuchTasksEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TasksEntry tasksEntry = (TasksEntry)session.get(TasksEntryImpl.class,
					Long.valueOf(tasksEntryId));

			if (tasksEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + tasksEntryId);
				}

				throw new NoSuchTasksEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					tasksEntryId);
			}

			return tasksEntryPersistence.remove(tasksEntry);
		}
		catch (NoSuchTasksEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the tasks entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tasksEntry the tasks entry to remove
	 * @return the tasks entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry remove(TasksEntry tasksEntry) throws SystemException {
		return super.remove(tasksEntry);
	}

	protected TasksEntry removeImpl(TasksEntry tasksEntry)
		throws SystemException {
		tasksEntry = toUnwrappedModel(tasksEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, tasksEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryImpl.class, tasksEntry.getPrimaryKey());

		return tasksEntry;
	}

	public TasksEntry updateImpl(
		com.liferay.tasks.model.TasksEntry tasksEntry, boolean merge)
		throws SystemException {
		tasksEntry = toUnwrappedModel(tasksEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, tasksEntry, merge);

			tasksEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryImpl.class, tasksEntry.getPrimaryKey(), tasksEntry);

		return tasksEntry;
	}

	protected TasksEntry toUnwrappedModel(TasksEntry tasksEntry) {
		if (tasksEntry instanceof TasksEntryImpl) {
			return tasksEntry;
		}

		TasksEntryImpl tasksEntryImpl = new TasksEntryImpl();

		tasksEntryImpl.setNew(tasksEntry.isNew());
		tasksEntryImpl.setPrimaryKey(tasksEntry.getPrimaryKey());

		tasksEntryImpl.setTasksEntryId(tasksEntry.getTasksEntryId());
		tasksEntryImpl.setGroupId(tasksEntry.getGroupId());
		tasksEntryImpl.setCompanyId(tasksEntry.getCompanyId());
		tasksEntryImpl.setUserId(tasksEntry.getUserId());
		tasksEntryImpl.setUserName(tasksEntry.getUserName());
		tasksEntryImpl.setCreateDate(tasksEntry.getCreateDate());
		tasksEntryImpl.setModifiedDate(tasksEntry.getModifiedDate());
		tasksEntryImpl.setTitle(tasksEntry.getTitle());
		tasksEntryImpl.setPriority(tasksEntry.getPriority());
		tasksEntryImpl.setAssigneeUserId(tasksEntry.getAssigneeUserId());
		tasksEntryImpl.setResolverUserId(tasksEntry.getResolverUserId());
		tasksEntryImpl.setDueDate(tasksEntry.getDueDate());
		tasksEntryImpl.setFinishDate(tasksEntry.getFinishDate());
		tasksEntryImpl.setStatus(tasksEntry.getStatus());

		return tasksEntryImpl;
	}

	/**
	 * Finds the tasks entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the tasks entry to find
	 * @return the tasks entry
	 * @throws com.liferay.portal.NoSuchModelException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the tasks entry with the primary key or throws a {@link com.liferay.tasks.NoSuchTasksEntryException} if it could not be found.
	 *
	 * @param tasksEntryId the primary key of the tasks entry to find
	 * @return the tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByPrimaryKey(long tasksEntryId)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = fetchByPrimaryKey(tasksEntryId);

		if (tasksEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + tasksEntryId);
			}

			throw new NoSuchTasksEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				tasksEntryId);
		}

		return tasksEntry;
	}

	/**
	 * Finds the tasks entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the tasks entry to find
	 * @return the tasks entry, or <code>null</code> if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the tasks entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tasksEntryId the primary key of the tasks entry to find
	 * @return the tasks entry, or <code>null</code> if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry fetchByPrimaryKey(long tasksEntryId)
		throws SystemException {
		TasksEntry tasksEntry = (TasksEntry)EntityCacheUtil.getResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
				TasksEntryImpl.class, tasksEntryId, this);

		if (tasksEntry == null) {
			Session session = null;

			try {
				session = openSession();

				tasksEntry = (TasksEntry)session.get(TasksEntryImpl.class,
						Long.valueOf(tasksEntryId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (tasksEntry != null) {
					cacheResult(tasksEntry);
				}

				closeSession(session);
			}
		}

		return tasksEntry;
	}

	/**
	 * Finds all the tasks entries where groupId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @return the matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the tasks entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the tasks entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<TasksEntry>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_GROUPID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first tasks entry in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last tasks entry in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByGroupId(groupId);

		List<TasksEntry> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] findByGroupId_PrevAndNext(long tasksEntryId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, tasksEntry, groupId,
					orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByGroupId_PrevAndNext(session, tasksEntry, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByGroupId_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the tasks entries where groupId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @return the matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the tasks entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByGroupId(long groupId, int start, int end)
		throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the tasks entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] filterFindByGroupId_PrevAndNext(long tasksEntryId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(tasksEntryId, groupId,
				orderByComparator);
		}

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, tasksEntry,
					groupId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = filterGetByGroupId_PrevAndNext(session, tasksEntry,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry filterGetByGroupId_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the tasks entries where userId = &#63;.
	 *
	 * @param userId the user ID to search with
	 * @return the matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the tasks entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the tasks entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<TasksEntry>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_USERID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first tasks entry in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByUserId(userId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last tasks entry in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByUserId(userId);

		List<TasksEntry> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the tasks entries before and after the current tasks entry in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] findByUserId_PrevAndNext(long tasksEntryId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(session, tasksEntry, userId,
					orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByUserId_PrevAndNext(session, tasksEntry, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByUserId_PrevAndNext(Session session,
		TasksEntry tasksEntry, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the tasks entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID to search with
	 * @return the matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByAssigneeUserId(long assigneeUserId)
		throws SystemException {
		return findByAssigneeUserId(assigneeUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the tasks entries where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByAssigneeUserId(long assigneeUserId,
		int start, int end) throws SystemException {
		return findByAssigneeUserId(assigneeUserId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the tasks entries where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByAssigneeUserId(long assigneeUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				assigneeUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ASSIGNEEUSERID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				list = (List<TasksEntry>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_ASSIGNEEUSERID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ASSIGNEEUSERID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByAssigneeUserId_First(long assigneeUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByAssigneeUserId(assigneeUserId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assigneeUserId=");
			msg.append(assigneeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByAssigneeUserId_Last(long assigneeUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByAssigneeUserId(assigneeUserId);

		List<TasksEntry> list = findByAssigneeUserId(assigneeUserId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("assigneeUserId=");
			msg.append(assigneeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the tasks entries before and after the current tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param assigneeUserId the assignee user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] findByAssigneeUserId_PrevAndNext(long tasksEntryId,
		long assigneeUserId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByAssigneeUserId_PrevAndNext(session, tasksEntry,
					assigneeUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByAssigneeUserId_PrevAndNext(session, tasksEntry,
					assigneeUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByAssigneeUserId_PrevAndNext(Session session,
		TasksEntry tasksEntry, long assigneeUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assigneeUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the tasks entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID to search with
	 * @return the matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByResolverUserId(long resolverUserId)
		throws SystemException {
		return findByResolverUserId(resolverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the tasks entries where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByResolverUserId(long resolverUserId,
		int start, int end) throws SystemException {
		return findByResolverUserId(resolverUserId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the tasks entries where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByResolverUserId(long resolverUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				resolverUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_RESOLVERUSERID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resolverUserId);

				list = (List<TasksEntry>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_RESOLVERUSERID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_RESOLVERUSERID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByResolverUserId_First(long resolverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByResolverUserId(resolverUserId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resolverUserId=");
			msg.append(resolverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByResolverUserId_Last(long resolverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByResolverUserId(resolverUserId);

		List<TasksEntry> list = findByResolverUserId(resolverUserId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resolverUserId=");
			msg.append(resolverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the tasks entries before and after the current tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param resolverUserId the resolver user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] findByResolverUserId_PrevAndNext(long tasksEntryId,
		long resolverUserId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByResolverUserId_PrevAndNext(session, tasksEntry,
					resolverUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByResolverUserId_PrevAndNext(session, tasksEntry,
					resolverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByResolverUserId_PrevAndNext(Session session,
		TasksEntry tasksEntry, long resolverUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resolverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @return the matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByG_U(long groupId, long userId)
		throws SystemException {
		return findByG_U(groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByG_U(long groupId, long userId, int start,
		int end) throws SystemException {
		return findByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, userId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_U,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				list = (List<TasksEntry>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_U,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_U,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByG_U_First(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByG_U(groupId, userId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByG_U_Last(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByG_U(groupId, userId);

		List<TasksEntry> list = findByG_U(groupId, userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] findByG_U_PrevAndNext(long tasksEntryId, long groupId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_U_PrevAndNext(session, tasksEntry, groupId,
					userId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByG_U_PrevAndNext(session, tasksEntry, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_U_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @return the matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByG_U(long groupId, long userId)
		throws SystemException {
		return filterFindByG_U(groupId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByG_U(long groupId, long userId,
		int start, int end) throws SystemException {
		return filterFindByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByG_U(long groupId, long userId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U(groupId, userId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] filterFindByG_U_PrevAndNext(long tasksEntryId,
		long groupId, long userId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U_PrevAndNext(tasksEntryId, groupId, userId,
				orderByComparator);
		}

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = filterGetByG_U_PrevAndNext(session, tasksEntry, groupId,
					userId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = filterGetByG_U_PrevAndNext(session, tasksEntry, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry filterGetByG_U_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @return the matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByG_A(long groupId, long assigneeUserId)
		throws SystemException {
		return findByG_A(groupId, assigneeUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByG_A(long groupId, long assigneeUserId,
		int start, int end) throws SystemException {
		return findByG_A(groupId, assigneeUserId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByG_A(long groupId, long assigneeUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, assigneeUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_A,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				list = (List<TasksEntry>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_A,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_A,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByG_A_First(long groupId, long assigneeUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByG_A(groupId, assigneeUserId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", assigneeUserId=");
			msg.append(assigneeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByG_A_Last(long groupId, long assigneeUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByG_A(groupId, assigneeUserId);

		List<TasksEntry> list = findByG_A(groupId, assigneeUserId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", assigneeUserId=");
			msg.append(assigneeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] findByG_A_PrevAndNext(long tasksEntryId, long groupId,
		long assigneeUserId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_A_PrevAndNext(session, tasksEntry, groupId,
					assigneeUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByG_A_PrevAndNext(session, tasksEntry, groupId,
					assigneeUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_A_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long assigneeUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(assigneeUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @return the matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByG_A(long groupId, long assigneeUserId)
		throws SystemException {
		return filterFindByG_A(groupId, assigneeUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByG_A(long groupId, long assigneeUserId,
		int start, int end) throws SystemException {
		return filterFindByG_A(groupId, assigneeUserId, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByG_A(long groupId, long assigneeUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A(groupId, assigneeUserId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] filterFindByG_A_PrevAndNext(long tasksEntryId,
		long groupId, long assigneeUserId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_PrevAndNext(tasksEntryId, groupId, assigneeUserId,
				orderByComparator);
		}

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = filterGetByG_A_PrevAndNext(session, tasksEntry, groupId,
					assigneeUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = filterGetByG_A_PrevAndNext(session, tasksEntry, groupId,
					assigneeUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry filterGetByG_A_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long assigneeUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(assigneeUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @return the matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByG_R(long groupId, long resolverUserId)
		throws SystemException {
		return findByG_R(groupId, resolverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByG_R(long groupId, long resolverUserId,
		int start, int end) throws SystemException {
		return findByG_R(groupId, resolverUserId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findByG_R(long groupId, long resolverUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, resolverUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_R,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(resolverUserId);

				list = (List<TasksEntry>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_R,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_R,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByG_R_First(long groupId, long resolverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByG_R(groupId, resolverUserId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", resolverUserId=");
			msg.append(resolverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a matching tasks entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry findByG_R_Last(long groupId, long resolverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByG_R(groupId, resolverUserId);

		List<TasksEntry> list = findByG_R(groupId, resolverUserId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", resolverUserId=");
			msg.append(resolverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] findByG_R_PrevAndNext(long tasksEntryId, long groupId,
		long resolverUserId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_R_PrevAndNext(session, tasksEntry, groupId,
					resolverUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByG_R_PrevAndNext(session, tasksEntry, groupId,
					resolverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_R_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long resolverUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(resolverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @return the matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByG_R(long groupId, long resolverUserId)
		throws SystemException {
		return filterFindByG_R(groupId, resolverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByG_R(long groupId, long resolverUserId,
		int start, int end) throws SystemException {
		return filterFindByG_R(groupId, resolverUserId, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> filterFindByG_R(long groupId, long resolverUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R(groupId, resolverUserId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(resolverUserId);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws com.liferay.tasks.NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TasksEntry[] filterFindByG_R_PrevAndNext(long tasksEntryId,
		long groupId, long resolverUserId, OrderByComparator orderByComparator)
		throws NoSuchTasksEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R_PrevAndNext(tasksEntryId, groupId, resolverUserId,
				orderByComparator);
		}

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = filterGetByG_R_PrevAndNext(session, tasksEntry, groupId,
					resolverUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = filterGetByG_R_PrevAndNext(session, tasksEntry, groupId,
					resolverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry filterGetByG_R_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long resolverUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(resolverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the tasks entries.
	 *
	 * @return the tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the tasks entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @return the range of tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the tasks entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks entries to return
	 * @param end the upper bound of the range of tasks entries to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<TasksEntry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TASKSENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TASKSENTRY.concat(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (TasksEntry tasksEntry : findByGroupId(groupId)) {
			tasksEntryPersistence.remove(tasksEntry);
		}
	}

	/**
	 * Removes all the tasks entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (TasksEntry tasksEntry : findByUserId(userId)) {
			tasksEntryPersistence.remove(tasksEntry);
		}
	}

	/**
	 * Removes all the tasks entries where assigneeUserId = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAssigneeUserId(long assigneeUserId)
		throws SystemException {
		for (TasksEntry tasksEntry : findByAssigneeUserId(assigneeUserId)) {
			tasksEntryPersistence.remove(tasksEntry);
		}
	}

	/**
	 * Removes all the tasks entries where resolverUserId = &#63; from the database.
	 *
	 * @param resolverUserId the resolver user ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByResolverUserId(long resolverUserId)
		throws SystemException {
		for (TasksEntry tasksEntry : findByResolverUserId(resolverUserId)) {
			tasksEntryPersistence.remove(tasksEntry);
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_U(long groupId, long userId)
		throws SystemException {
		for (TasksEntry tasksEntry : findByG_U(groupId, userId)) {
			tasksEntryPersistence.remove(tasksEntry);
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and assigneeUserId = &#63; from the database.
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_A(long groupId, long assigneeUserId)
		throws SystemException {
		for (TasksEntry tasksEntry : findByG_A(groupId, assigneeUserId)) {
			tasksEntryPersistence.remove(tasksEntry);
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and resolverUserId = &#63; from the database.
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_R(long groupId, long resolverUserId)
		throws SystemException {
		for (TasksEntry tasksEntry : findByG_R(groupId, resolverUserId)) {
			tasksEntryPersistence.remove(tasksEntry);
		}
	}

	/**
	 * Removes all the tasks entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TasksEntry tasksEntry : findAll()) {
			tasksEntryPersistence.remove(tasksEntry);
		}
	}

	/**
	 * Counts all the tasks entries where groupId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @return the number of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the tasks entries where groupId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @return the number of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the tasks entries where userId = &#63;.
	 *
	 * @param userId the user ID to search with
	 * @return the number of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the tasks entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID to search with
	 * @return the number of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAssigneeUserId(long assigneeUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { assigneeUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSIGNEEUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSIGNEEUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the tasks entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID to search with
	 * @return the number of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByResolverUserId(long resolverUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { resolverUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RESOLVERUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resolverUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RESOLVERUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @return the number of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_U(long groupId, long userId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_U,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_U, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param userId the user ID to search with
	 * @return the number of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_U(long groupId, long userId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_U(groupId, userId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @return the number of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_A(long groupId, long assigneeUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, assigneeUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_A, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param assigneeUserId the assignee user ID to search with
	 * @return the number of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_A(long groupId, long assigneeUserId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_A(groupId, assigneeUserId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @return the number of matching tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_R(long groupId, long resolverUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, resolverUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(resolverUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_R, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID to search with
	 * @param resolverUserId the resolver user ID to search with
	 * @return the number of matching tasks entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_R(long groupId, long resolverUserId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_R(groupId, resolverUserId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(), _FILTER_COLUMN_PK,
				_FILTER_COLUMN_USERID, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(resolverUserId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the tasks entries.
	 *
	 * @return the number of tasks entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TASKSENTRY);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the tasks entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.tasks.model.TasksEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TasksEntry>> listenersList = new ArrayList<ModelListener<TasksEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<TasksEntry>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(TasksEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = TasksEntryPersistence.class)
	protected TasksEntryPersistence tasksEntryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_TASKSENTRY = "SELECT tasksEntry FROM TasksEntry tasksEntry";
	private static final String _SQL_SELECT_TASKSENTRY_WHERE = "SELECT tasksEntry FROM TasksEntry tasksEntry WHERE ";
	private static final String _SQL_COUNT_TASKSENTRY = "SELECT COUNT(tasksEntry) FROM TasksEntry tasksEntry";
	private static final String _SQL_COUNT_TASKSENTRY_WHERE = "SELECT COUNT(tasksEntry) FROM TasksEntry tasksEntry WHERE ";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "tasksEntry.groupId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "tasksEntry.userId = ?";
	private static final String _FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2 = "tasksEntry.assigneeUserId = ?";
	private static final String _FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2 = "tasksEntry.resolverUserId = ?";
	private static final String _FINDER_COLUMN_G_U_GROUPID_2 = "tasksEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_USERID_2 = "tasksEntry.userId = ?";
	private static final String _FINDER_COLUMN_G_A_GROUPID_2 = "tasksEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_ASSIGNEEUSERID_2 = "tasksEntry.assigneeUserId = ?";
	private static final String _FINDER_COLUMN_G_R_GROUPID_2 = "tasksEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_RESOLVERUSERID_2 = "tasksEntry.resolverUserId = ?";
	private static final String _FILTER_SQL_SELECT_TASKSENTRY_WHERE = "SELECT DISTINCT {tasksEntry.*} FROM TMS_TasksEntry tasksEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {TMS_TasksEntry.*} FROM (SELECT DISTINCT tasksEntry.tasksEntryId FROM TMS_TasksEntry tasksEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN TMS_TasksEntry ON TEMP_TABLE.tasksEntryId = TMS_TasksEntry.tasksEntryId";
	private static final String _FILTER_SQL_COUNT_TASKSENTRY_WHERE = "SELECT COUNT(DISTINCT tasksEntry.tasksEntryId) AS COUNT_VALUE FROM TMS_TasksEntry tasksEntry WHERE ";
	private static final String _FILTER_COLUMN_PK = "tasksEntry.tasksEntryId";
	private static final String _FILTER_COLUMN_USERID = "tasksEntry.userId";
	private static final String _FILTER_ENTITY_ALIAS = "tasksEntry";
	private static final String _FILTER_ENTITY_TABLE = "TMS_TasksEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tasksEntry.";
	private static final String _ORDER_BY_ENTITY_TABLE = "TMS_TasksEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TasksEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TasksEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TasksEntryPersistenceImpl.class);
}