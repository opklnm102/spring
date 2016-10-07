package me.dong.model.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAbstractEntity is a Querydsl query type for AbstractEntity
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QAbstractEntity extends EntityPathBase<AbstractEntity<? extends java.io.Serializable>> {

    private static final long serialVersionUID = 1860858454L;

    public static final QAbstractEntity abstractEntity = new QAbstractEntity("abstractEntity");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final NumberPath<Long> updatedAt = createNumber("updatedAt", Long.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    @SuppressWarnings("all")
    public QAbstractEntity(String variable) {
        super((Class)AbstractEntity.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QAbstractEntity(Path<? extends AbstractEntity> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    @SuppressWarnings("all")
    public QAbstractEntity(PathMetadata<?> metadata) {
        super((Class)AbstractEntity.class, metadata);
    }

}

