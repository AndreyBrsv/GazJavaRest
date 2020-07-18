package io.dao;

public interface Repository<Entity, Identifier> {
    Entity create(Entity t);

    Entity get(Identifier i);

    Entity update(Entity t);

    boolean delete(Identifier i);
}
