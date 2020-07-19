package io.dao;

public interface Repository<Entity, Identifier> {

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
    boolean delete(Identifier identifier);
}
