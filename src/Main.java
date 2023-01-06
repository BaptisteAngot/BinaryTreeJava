public class Main {
    public static void main(String[] args) {
        // Create a binary tree with the following structure:
        //
        //     1
        //    / \
        //   2   3
        //  / \ / \
        // 6  9 10  23
        //         / \
        //        45   52
        BinaryTree<Integer> tree = new BinaryTree<>(1);
        tree.setLeft(new BinaryTree<>(2));
        tree.setRight(new BinaryTree<>(3));
        tree.getLeft().setLeft(new BinaryTree<>(6));
        tree.getLeft().setRight(new BinaryTree<>(9));
        tree.getRight().setLeft(new BinaryTree<>(10));
        tree.getRight().setRight(new BinaryTree<>(23));
        tree.getRight().getRight().setLeft(new BinaryTree<>(45));
        tree.getRight().getRight().setRight(new BinaryTree<>(52));

        Integer found = tree.search(9);
        Integer max = tree.maximum();
        Integer min = tree.minimum();
        System.out.println("Found: " + found);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);

        int sum = tree.foldLeft(0, Integer::sum); // 151
        System.out.println("Sum: " + sum);

        int product = tree.foldRight(1, (a, b) -> a * b);
        System.out.println("Product: " + product);
    }
}