package kt_1_7.def_nn;

import org.jetbrains.annotations.NotNull;

public interface JavaInterface<T> {
    public T work(T value);

    @NotNull
    public T print(@NotNull T value);
}
