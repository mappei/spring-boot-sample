package com.mpp.web.repository;

import com.mpp.web.domain.User;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {

    private final ConcurrentHashMap<Integer, User> repository = new ConcurrentHashMap<>();

    //id自增长生成器
    private final static AtomicInteger idGenerator = new AtomicInteger();

    public boolean save(User user) {
        int id = idGenerator.incrementAndGet();
        user.setId(id);
        return repository.put(id, user) == null;
    }

    public User get(Integer id) {
        return repository.get(id);
    }

    public boolean delete(Integer id) {
        return repository.remove(id) != null;
    }

    public Collection<User> findAll() {
        return repository.values();
    }

    public boolean update(User user) {
        User oldUser = repository.get(user.getId());

        if (!StringUtil.isNullOrEmpty(user.getName()))
            oldUser.setName(user.getName());
        return repository.put(user.getId(), oldUser) == null;
    }

    public User findOne(Integer id) {
        return repository.get(id);
    }

}
