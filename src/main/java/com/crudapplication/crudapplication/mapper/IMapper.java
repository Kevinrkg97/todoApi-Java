package com.crudapplication.crudapplication.mapper;

public interface IMapper <I, O> {
    public O map(I in);
}
