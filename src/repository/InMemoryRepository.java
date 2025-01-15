package repository;

import model.HasID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMemoryRepository<T extends HasID> implements IRepository<T>{
    private final Map<Integer, T> repo = new HashMap<>();

    @Override
    public void create(T entity) {
        repo.putIfAbsent(entity.getId(), entity);
    }

    @Override
    public T read(Integer id) {
        return repo.get(id);
    }

    @Override
    public void update(Integer id, T entity) {
        repo.replace(entity.getId(), entity);
    }

    @Override
    public void delete(Integer id) {
        repo.remove(id);
    }

    @Override
    public List<T> getAll() {
        return repo.values().stream().toList();
    }

    @Override
    public Set<Integer> getKeys() {
        return repo.keySet();
    }
}
