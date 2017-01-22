package com.lightside.fg.web.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Anwar
 */
public abstract class MapperAdapter<R, S> implements DomainMapper<R, S> {

    @Override
    public Collection<S> map(Collection<R> sources) {
        if (sources == null) {
            return Collections.emptyList();
        }
        final Collection<S> destTypes = new ArrayList<>(sources.size());
        destTypes.addAll(sources.stream().map(this::map).collect(Collectors.toList()));
        return destTypes;
    }
}
