import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BinaryTree<AnyType> {

    private TreeNode<AnyType> root;

    public BinaryTree( ) {
        root = null;
    }
    public BinaryTree( AnyType rootItem ) {
        root = new TreeNode<>( rootItem, null, null, 0.0 );
    }
    public TreeNode<AnyType> getRoot( ) {
        return root;
    }
    public int size( ) {
        return TreeNode.size( root );
    }
    public int height( ) {
        return TreeNode.height( root );
    }
    public void printPreOrder( ) {
        if( root != null ) root.printPreOrder( );
    }
    public void printInOrder( ) {
        if( root != null ) root.printInOrder( );
    }
    public void printPostOrder( ) {
        if( root != null ) root.printPostOrder( );
    }
    public void makeEmpty( ) {
        root = null;
    }
    public boolean isEmpty( ) {
        return root == null;
    }
    public void merge( AnyType rootItem, BinaryTree<AnyType> t1, BinaryTree<AnyType> t2 ) {
        if( t1.root == t2.root && t1.root != null ) {
            throw new IllegalArgumentException( );
        }
        root = new TreeNode<AnyType>( rootItem, t1.root, t2.root, 0.0);
        if( this != t1 ) {
            t1.root = null;
        }
        if( this != t2 ) {
            t2.root = null;
        }
    }
    public static TreeNode<String> processInput(String input) {
        linkedStack<TreeNode<String>> stack = new linkedStack<>();
        String[] tokens = input.split(" ");
        for (int a = 0; a < tokens.length; a++) {
            if (!(tokens[a].equals("("))) {
                if (tokens[a].equals(")")) {
                    TreeNode<String> left_child = stack.pop();
                    TreeNode<String> right_child = stack.pop();
                    TreeNode<String> parent = stack.pop();
                    parent.setLeft(left_child);
                    parent.setRight(right_child);
                    stack.push(parent);
                } else {
                    stack.push(new TreeNode<>(tokens[a], null, null, Double.parseDouble(String.valueOf(tokens[++a]))));
                }
            }
        }
        return stack.pop();
    }
    public static TreeNode<String> readInput(String filename) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner input = new Scanner(Objects.requireNonNull(getFile(filename, new File(System.getProperty("user.dir")))));
            while (input.hasNextLine()) {
                sb.append(input.nextLine());
            }
        } catch (NullPointerException ex) {
            System.out.println("file not found please try again!");
        }
        return processInput(sb.toString());
    }
    public static ArrayList<File> files = new ArrayList<>();
    public static File getFile(String aFilename, File path) {
        if (aFilename.equals(path.getName()) && !(path.isDirectory())) {
            files.add(path);
        } else {
            File[] fileArr = path.listFiles();
            if (fileArr != null) {
                for (File f : fileArr) {
                    getFile(aFilename, new File(String.valueOf(f)));
                }
            }
        }
        for (File file : files) {
            if (file != null) {
                return file;
            }
        }
        files.clear();
        return null;
    }
    public static void main(String[] args) throws FileNotFoundException, IndexOutOfBoundsException {
        try {
//            TreeNode<String> tree = readInput("test.txt");
            TreeNode<String> tree = readInput(args[0]);
            tree.findClosest(tree, "*");

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("no filename was specified.");
        }
    }
}
