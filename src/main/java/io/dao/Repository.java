package io.dao;

import io.entities.PageableView;

import java.io.Serializable;

public interface Repository<Entity extends Serializable, Identifier> {

    /**
     * Общий метод создания сущностей
     * @param entity сущность
     * @return созданную сущность
     */
    Entity create(Entity entity);

    /**
     * Получение сущности по идентификатору
     * @param identifier идентификатор
     * @return сущность
     */
    Entity getById(Identifier identifier);

    /**
     * Обновление сущности
     * @param entity сущность
     * @return
     */
    boolean update(Entity entity);

    /**
     * Удаление сущности по индентификатору
     * @param identifier идентификатор
     * @return результат выполениния (true/false)
     */
    void delete(Identifier identifier);

    /**
     *
     * @param entity
     * @param page
     * @param limit
     * @return
     */
    PageableView<Entity> pageView(Entity entity, int page, int limit);
}
