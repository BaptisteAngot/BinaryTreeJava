import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BinaryTree<T extends Number> {
    private T value;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree(T value) {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTree<T> left, BinaryTree<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public BinaryTree<T> getRight() {
        return right;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(BinaryTree<T> left) {
        this.left = left;
    }

    public void setRight(BinaryTree<T> right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public T search(T searchItem) {
        if (value.equals(searchItem)) {
            return value;
        }

        if (left != null) {
            T found = left.search(searchItem);
            if (found != null) {
                return found;
            }
        }

        if (right != null) {
            T found = right.search(searchItem);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public T maximum() {
        if (isLeaf()) return value;
        T max = value;
        T leftMax = left.maximum();
        T rightMax = right.maximum();
        if (((Comparable<T>) leftMax).compareTo(max) < 0) max = leftMax;
        if (((Comparable<T>) rightMax).compareTo(max) < 0) max = rightMax;
        return max;
    }

    public T minimum() {
        if (isLeaf()) return value;
        T min = value;
        T leftMin = left.minimum();
        T rightMin = right.minimum();
        if (((Comparable<T>) leftMin).compareTo(min) > 0) min = leftMin;
        if (((Comparable<T>) rightMin).compareTo(min) > 0) min = rightMin;
        return min;
    }

    public int depth() {
        if (isLeaf()) return 1;
        return 1 + Math.max(left.depth(), right.depth());
    }

    public <R extends Number> BinaryTree<R> map(Function<T, R> f) {
        return new BinaryTree<>(f.apply(value), left.map(f), right.map(f));
    }

    public <R> Iterable<R> flatMap(Function<T, Iterable<R>> f) {
        List<R> results = new ArrayList<>();
        results.addAll((Collection<? extends R>) f.apply(value));
        results.addAll((Collection<? extends R>) left.flatMap(f));
        results.addAll((Collection<? extends R>) right.flatMap(f));
        return results;
    }

    public int foldLeft(int initial, BiFunction<Integer, T, Integer> f) {
        int result = f.apply(initial, value);
        if (left != null) {
            result = left.foldLeft(result, f);
        }
        if (right != null) {
            result = right.foldLeft(result, f);
        }
        return result;
    }

    public int foldRight(int initial, BiFunction<Integer, T, Integer> f) {
        int result = f.apply(initial, value);
        if (right != null) {
            result = right.foldRight(result, f);
        }
        if (left != null) {
            result = left.foldRight(result, f);
        }
        return result;
    }
}
