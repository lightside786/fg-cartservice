package com.lightside.fg.web.mapper;

import java.util.Collection;

/**
 * @author Anwar
 */

public interface DomainMapper<T, U> {

    U map(T source);

    Collection<U> map(Collection<T> sources);
}
