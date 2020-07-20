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
    Entity getByIdentifier(Identifier identifier);

    /**
     * Обновление сущности
     * @param entity сущность
     * @return успешность обновления (true/false)
     */
    boolean update(Entity entity);

    /**
     * Удаление сущности по индентификатору
     * @param identifier идентификатор
     */
    void delete(Identifier identifier);

    /**
     * Постраничный вывод сущностей
     * @param idFrom id с которого начинается вывод
     * @param limit количество сущностей для вывода (не более 20)
     * @return возращает пролистываемый список
     * @see PageableView
     */
    PageableView<Entity> pageView(Long idFrom, int limit);
}
