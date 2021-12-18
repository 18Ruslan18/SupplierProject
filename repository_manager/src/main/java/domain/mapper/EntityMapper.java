package domain.mapper;

public interface EntityMapper<T1, T2> {

    T2 convert(T1 item);

    T1 deconvert(T2 item);
}
