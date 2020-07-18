package io.dao;

public interface Repository<Entity, Identifier> {
    Entity create(Entity entity);

    Entity get(Identifier identifier);

    Entity update(Entity entity);

    boolean delete(Identifier identifier);
}
