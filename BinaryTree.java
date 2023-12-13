import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int item) {
        data = item;
        left = right = null;
    }
}

public class BinaryTree {
    private TreeNode root;
    private Scanner scanner;

    public InteractiveBinaryTree() {
        root = null;
        scanner = new Scanner(System.in);
    }

    // Method to insert a value into the binary tree
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.data) {
            root.left = insertRec(root.left, key);
        } else if (key > root.data) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    // Method to delete a value from the binary tree
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private TreeNode deleteRec(TreeNode root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.data) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.data) {
            root.right = deleteRec(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children, get the inorder successor
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private int minValue(TreeNode root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    // Method to search for a value in the binary tree and return the index
    public int search(int key) {
        return searchRec(root, key, 1);
    }

    private int searchRec(TreeNode root, int key, int index) {
        if (root == null || root.data == key) {
            return (root != null) ? index : -1;
        }

        if (key < root.data) {
            return searchRec(root.left, key, 2 * index);
        } else {
            return searchRec(root.right, key, 2 * index + 1);
        }
    }

    // Method for in-order traversal
    public void inOrderTraversal() {
        inOrderTraversalRec(root);
        System.out.println();
    }

    private void inOrderTraversalRec(TreeNode root) {
        if (root != null) {
            inOrderTraversalRec(root.left);
            System.out.print(root.data + " ");
            inOrderTraversalRec(root.right);
        }
    }

    // Method for post-order traversal
    public void postOrderTraversal() {
        postOrderTraversalRec(root);
        System.out.println();
    }

    private void postOrderTraversalRec(TreeNode root) {
        if (root != null) {
            postOrderTraversalRec(root.left);
            postOrderTraversalRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Method for pre-order traversal
    public void preOrderTraversal() {
        preOrderTraversalRec(root);
        System.out.println();
    }

    private void preOrderTraversalRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderTraversalRec(root.left);
            preOrderTraversalRec(root.right);
        }
    }

    public static void main(String[] args) {
        InteractiveBinaryTree tree = new InteractiveBinaryTree();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("1. Random Numbers");
            System.out.println("2. Traversals");
            System.out.println("3. Insert");
            System.out.println("4. Delete");
            System.out.println("5. Search");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume invalid input
                choice = 0; // Set choice to an invalid value to loop again
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter random numbers (comma-separated): ");
                    String input = scanner.next();
                    String[] numbers = input.split(",");
                    for (String number : numbers) {
                        try {
                            tree.insert(Integer.parseInt(number.trim()));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter valid integers.");
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("InOrder Traversal: ");
                    tree.inOrderTraversal();

                    System.out.println("PostOrder Traversal: ");
                    tree.postOrderTraversal();

                    System.out.println("PreOrder Traversal: ");
                    tree.preOrderTraversal();
                    break;
                case 3:
                    System.out.print("Enter a number to insert: ");
                    try {
                        int insertKey = scanner.nextInt();
                        tree.insert(insertKey);
                        System.out.println("Number " + insertKey + " inserted.");
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.next(); // Consume invalid input
                    }
                    break;
                case 4:
                    System.out.print("Enter a number to delete: ");
                    try {
                        int deleteKey = scanner.nextInt();
                        if (tree.search(deleteKey) != -1) {
                            tree.delete(deleteKey);
                            System.out.println("Number " + deleteKey + " deleted.");
                        } else {
                            System.out.println("Number " + deleteKey + " not found.");
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.next(); // Consume invalid input
                    }
                    break;
                case 5:
                    System.out.print("Enter a number to search: ");
                    try {
                        int searchKey = scanner.nextInt();
                        int result = tree.search(searchKey);
                        if (result != -1) {
                            System.out.println("Number " + searchKey + " found at index " + result + ".");
                        } else {
                            System.out.println("Number " + searchKey + " not found.");
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.next(); // Consume invalid input
                    }
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }

        } while (choice != 6);

        // Close the scanner
        scanner.close();
    }
}
