package com.student.server.repositories;

import com.student.server.data.dto.IdentifiableDTO;
import io.reactivex.rxjava3.core.Single;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.UpdatableRecord;

import java.util.UUID;

public abstract class AbstractCRUDRepository<R extends UpdatableRecord<?>, P extends IdentifiableDTO> {

    protected final DSLContext dsl;
    private final Table<R> table;
    private final Field<String> idField;
    private final Class<P> pojoClass;

    public AbstractCRUDRepository(DSLContext dslContext, Table<R> table, Field<String> idField, Class<P> pojoClass) {
        this.dsl = dslContext;
        this.table = table;
        this.pojoClass = pojoClass;
        this.idField = idField;
    }

    public P getById(String id) {
        R record = getRecordById(id);
        if (record == null) {
            return null;
        }
        return record.into(pojoClass);
    }

    protected R getRecordById(String id) {
        return dsl.selectFrom(table)
                .where(idField.eq(id))
                .fetchOne();
    }

    public P create(P pojo) {
        R record = dsl.newRecord(table, pojo);
        record.set(idField, UUID.randomUUID().toString());
        record.store();
        return record.into(pojoClass);
    }

    public Single<P> createWithSingle(P pojo) {
        return Single.fromCallable(()->{
            R record = dsl.newRecord(table, pojo);
            record.set(idField, UUID.randomUUID().toString());
            record.store();
            return record.into(pojoClass);
        });
    }

}
