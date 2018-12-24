package com.connext.service;

import java.util.List;

public interface BatchDao<T> {
    public void batchInsert(List<T> list);

    public void batchUpdate(List<T> list);
}
