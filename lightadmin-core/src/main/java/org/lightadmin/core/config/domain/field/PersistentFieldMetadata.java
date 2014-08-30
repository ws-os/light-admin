package org.lightadmin.core.config.domain.field;

import org.hibernate.validator.constraints.NotBlank;
import org.lightadmin.core.persistence.metamodel.PersistentPropertyType;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.util.ReflectionUtils;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.lightadmin.core.persistence.metamodel.PersistentPropertyType.*;
import static org.springframework.util.ReflectionUtils.invokeMethod;

public class PersistentFieldMetadata extends AbstractFieldMetadata {

    private final String field;

    private boolean primaryKey;

    private PersistentProperty persistentProperty;

    public PersistentFieldMetadata(final String name, final String field, boolean primaryKey) {
        this(name, field);
        this.primaryKey = primaryKey;
    }

    public PersistentFieldMetadata(final String name, final String field) {
        super(name);
        this.field = field;
    }

    public static FieldMetadata keyField(final String name, final String field) {
        return new PersistentFieldMetadata(name, field, true);
    }

    public String getField() {
        return field;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public boolean isRequired() {
        return persistentProperty.isAnnotationPresent(NotNull.class)
                || persistentProperty.isAnnotationPresent(NotBlank.class)
                || persistentProperty.isAnnotationPresent(org.hibernate.validator.constraints.NotEmpty.class);
    }

    public boolean isGeneratedValue() {
        return persistentProperty.isAnnotationPresent(GeneratedValue.class);
    }

    public void setPrimaryKey(final boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setPersistentProperty(final PersistentProperty persistentProperty) {
        this.persistentProperty = persistentProperty;
    }

    public PersistentProperty getPersistentProperty() {
        return persistentProperty;
    }

    @Override
    public boolean isSortable() {
        PersistentPropertyType persistentPropertyType = PersistentPropertyType.forPersistentProperty(persistentProperty);
        return persistentPropertyType != ASSOC && persistentPropertyType != ASSOC_MULTI && persistentPropertyType != FILE;
    }

    @Override
    public Object getValue(Object entity) {
        Method getter = persistentProperty.getGetter();
        if (null != getter) {
            return invokeMethod(getter, entity);
        }
        Field fld = persistentProperty.getField();
        if (null != fld) {
            return ReflectionUtils.getField(fld, entity);
        }
        return null;
    }

    @Override
    public String getUuid() {
        return field;
    }

}