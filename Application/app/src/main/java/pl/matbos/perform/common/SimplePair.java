package pl.matbos.perform.common;

/**
 * This class duplicates the one found in the Android framework but it
 * allows to fully decouple model classes from the framework.
 */
public class SimplePair<F, S> {
    public final F first;
    public final S second;

    public SimplePair(F first, S second) {
        this.first = first;
        this.second = second;
    }
}